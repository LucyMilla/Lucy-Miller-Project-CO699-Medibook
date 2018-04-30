package com.example.lucy.medibook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.BundleCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by Lucy on 29/04/2018.
 */

public class CalActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cal);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_cal, menu);
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

            case R.id.action_home:
                startActivity(new Intent(this, MainActivity.class));
                return true;

            case R.id.action_calculate:
                startActivity(new Intent(this, CalActivity.class));
                return true;

            default:
                return false;


        }
    }




}



