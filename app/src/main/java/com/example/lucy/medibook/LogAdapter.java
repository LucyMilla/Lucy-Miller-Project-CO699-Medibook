package com.example.lucy.medibook;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Lucy on 25/04/2018.
 */

public class LogAdapter extends ArrayAdapter<Log>{
    public LogAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<Log> logs) {
        super(context, resource, logs);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        if(convertView == null){
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_log,null);
        }
        Log log = getItem(position);
        if(log !=null){
            TextView level = (TextView) convertView.findViewById(R.id.list_log_level);
            TextView date = (TextView) convertView.findViewById(R.id.list_log_date);
            TextView content = (TextView) convertView.findViewById(R.id.list_log_content);

            level.setText(log.getLevel());
            date.setText(log.getDateTimeFormated(getContext()));

            if(log.getContent().length()>50){
                content.setText(log.getContent().substring(0,50));
            }else{
            content.setText(log.getContent());

        }
        }

        return convertView;
    }

}
