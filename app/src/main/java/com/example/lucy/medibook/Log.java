package com.example.lucy.medibook;

import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.icu.util.TimeZone;

import java.util.Date;

/**
 * Created by Lucy on 26/03/2018.
 */

public class Log {
    private long mDateTime;
    private String mTOD;
    private String mLevel;
    private String mContent;

    public Log(String content, String tod, long dateTime, String level ){
        mContent = content;
        mTOD = tod;
        mDateTime = dateTime;
        mLevel = level;
    }

    public void setmDateTime(long mDateTime) {
        this.mDateTime = mDateTime;
    }

    public void setmTOD(String mTOD) {
        this.mTOD = mTOD;
    }

    public void setmLevel(String mLevel) {
        this.mLevel = mLevel;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent;
    }

    public long getmDateTime() {
        return mDateTime;
    }

    public String getmLevel() {
        return mLevel;
    }

    public String getmTOD() {
        return mTOD;
    }

    public String getmContent() {
        return mContent;
    }


    public String getDateTimeFormated(Context context){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", context.getResources().getConfiguration().locale);
        sdf.setTimeZone(TimeZone.getDefault());
        return sdf.format(new Date(mDateTime));
    }

    public static void d(String localClassName, String s) {

    }
}
