package com.example.lucy.medibook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.lucy.medibook.R.id.list_item;
import static com.example.lucy.medibook.R.id.spinner;

public class LogActivity extends AppCompatActivity {
    private EditText mETitle;
    private EditText mEContent;
    private String mLogFileName;
    private Log mLoadedLog;
    Spinner dropdownmenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        dropdownmenu = (Spinner) findViewById(spinner);

        mETitle = (EditText) findViewById(R.id.levelbox);
        mEContent = (EditText) findViewById(R.id.commentbox);
        mLogFileName = getIntent().getStringExtra("LOG_FILE");
        if(mLogFileName!= null && !mLogFileName.isEmpty()){
            mLoadedLog = Utilities.getLogByName(this, mLogFileName);
            if (mLoadedLog != null) {
                mETitle.setText(mLoadedLog.getLevel());
                mEContent.setText(mLoadedLog.getContent());

            }

        }


        List<String> list = new ArrayList<>();
        list.add("Morning");
        list.add("Lunch");
        list.add("Dinner");
        list.add("Snack");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        dropdownmenu.setAdapter(adapter);
        dropdownmenu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                String itemvalue = parent.getItemAtPosition(position).toString();
                Toast.makeText(LogActivity.this,"Selected: "+ itemvalue, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

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
