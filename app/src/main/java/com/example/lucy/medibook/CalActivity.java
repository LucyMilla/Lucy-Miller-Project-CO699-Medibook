package com.example.lucy.medibook;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.BundleCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Lucy on 29/04/2018.
 */

public class CalActivity extends AppCompatActivity {
    private EditText num1;
    private EditText num2;
    private TextView color1;
    private Button divide;
    private TextView result;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cal);



    }
    public void onButtonClick(View view){
        EditText e1 = (EditText) findViewById(R.id.carb_n1);
        String carb = e1.getText().toString();
        EditText e2 = (EditText) findViewById(R.id.ratio_n2);
        String ratio = e2.getText().toString();
        TextView t1 = (TextView) findViewById(R.id.answer);

        int num1;
        try {
            num1 = Integer.parseInt(carb);
        }catch(NumberFormatException ex){

            t1.setText("0");
            return;
        }
        int num2;
        try{
            num2 = Integer.parseInt(ratio);
        }catch(NumberFormatException ex){

            t1.setText("0");
            return;
        }
        int sum = num1 / num2;
        t1.setText(Integer.toString(sum));
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



