package com.example.lucy.medibook;

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
            mLoadedLog = Utilities.getLogByName(this, mLogFileName);
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
                break;
        }
        return true;
    }
    private void saveLog(){
        Log log = new Log(System.currentTimeMillis(), mETitle.getText().toString()
                ,mEContent.getText().toString());
        if (Utilities.saveLog(this, log)) {
            Toast.makeText(getApplicationContext(), "Success!! Diary Entry Saved", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(getApplicationContext(), "Not Saved, do you have enough space?", Toast.LENGTH_SHORT).show();
        }
        finish();

    }
}
