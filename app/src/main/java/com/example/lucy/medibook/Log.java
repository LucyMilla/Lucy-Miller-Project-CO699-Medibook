package com.example.lucy.medibook;

import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.icu.util.TimeZone;
import android.text.Editable;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Lucy on 26/03/2018.
 */
//Serializable- the application knows to pick up where left off incase user is in the middle of writing an unsaved log

public class Log implements Serializable{
    private long mDateTime;
    private String mLevel;
    private String mContent;

    public Log(long dateTime, String level,String content){
        mContent = content;
        mDateTime = dateTime;
        mLevel = level;
    }
    public void setLevel(String level) {
        mLevel = level;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public void setDateTime(long dateTime) {
        mDateTime = dateTime;
    }

    public long getDateTime() {
        return mDateTime;
    }

    public String getLevel() {
        return mLevel;
    }

    public String getContent() {
        return mContent;
    }


    public String getDateTimeFormated(Context context){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", context.getResources().getConfiguration().locale);
        sdf.setTimeZone(TimeZone.getDefault());
        return sdf.format(new Date(mDateTime));
    }

}
