package com.example.lucy.medibook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class LogActivity extends AppCompatActivity {
    Spinner dropdownmenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        dropdownmenu = (Spinner) findViewById(R.id.spinner);

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
}
