package com.example.lucy.medibook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView mListViewLogs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListViewLogs = (ListView) findViewById(R.id.listviewlog);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_new:
                //Start New Blood Log
                startActivity(new Intent(this, LogActivity.class));
                return true;
            case R.id.action_exit:
                finish();
                return true;
            default:
                return false;


        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mListViewLogs.setAdapter(null);

        ArrayList<Log> logs = Utilities.getallSavedLogs(this);

        if(logs == null || logs.size() ==0){
            Toast.makeText(this, " You have no save logs", Toast.LENGTH_SHORT).show();
            return;

        }else{
            LogAdapter la = new LogAdapter(this, R.layout.item_log, logs);
            mListViewLogs.setAdapter(la);

            mListViewLogs.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                    String fileName =((Log)mListViewLogs.getItemAtPosition(position)).getDateTime() + Utilities.FILE_EXTENSION;

                    Intent viewLogIntent = new Intent(getApplicationContext(),LogActivity.class);
                    viewLogIntent.putExtra("LOG_FILE", fileName);
                    startActivity(viewLogIntent);
                }


            });
        }
    }
}

