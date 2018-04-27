package com.example.lucy.medibook;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.lucy.medibook.R.id.list_item;

public class LogActivity extends AppCompatActivity {
    private EditText mETitle;
    private EditText mEContent;
    private String mLogFileName;
    private Log mLoadedLog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);



        mETitle = (EditText) findViewById(R.id.levelbox);
        mEContent = (EditText) findViewById(R.id.commenttbox);
        mLogFileName = getIntent().getStringExtra("LOG_FILE");
        if(mLogFileName!= null && !mLogFileName.isEmpty()){
            mLoadedLog = Utilities.getLogByName(getApplicationContext(),mLogFileName);
            if (mLoadedLog != null) {
                mETitle.setText(mLoadedLog.getLevel());
                mEContent.setText(mLoadedLog.getContent());

            }


        }


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_log, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_save:
                saveLog();
            case R.id.action_delete:
                deleteDiary();
                break;
        }
        return true;
    }
    private void saveLog(){
        Log log;
        if(mETitle.getText().toString().trim().isEmpty() ||mEContent.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(), "Please Enter a Level and Content", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mLoadedLog == null) {
            log = new Log(System.currentTimeMillis(), mETitle.getText().toString()
                    , mEContent.getText().toString());
        } else {
            log = new Log(mLoadedLog.getDateTime(), mETitle.getText().toString()
                    , mEContent.getText().toString());

        }
        /*calling this, for the object wouldnt show the toast message but getApplicationContext worked. */

        if (Utilities.saveLog(this, log)) {
            Toast.makeText(getApplicationContext(), "Success!! Blood Log Entry Saved", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(getApplicationContext(), "Not Saved, do you have enough space?", Toast.LENGTH_SHORT).show();
        }
        finish();

    }
    private void deleteDiary() {
        if (mLoadedLog == null) {
            finish();
        } else {

            AlertDialog.Builder dialog = new AlertDialog.Builder(this)
                    .setTitle("Delete")
                    .setMessage("You are about to Delete the Blood Log" + mETitle.getText().toString() + ", are you sure?")
                    .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Utilities.deleteLog(getApplicationContext()
                                    , mLoadedLog.getDateTime() + Utilities.FILE_EXTENSION);
                            Toast.makeText(getApplicationContext(), mETitle.getText().toString() + " Blood Log is Deleted", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    })
                    .setNegativeButton("no", null)
                    .setCancelable(false);
            dialog.show();
        }
    }
}
