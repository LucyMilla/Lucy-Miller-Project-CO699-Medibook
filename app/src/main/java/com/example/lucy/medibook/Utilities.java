package com.example.lucy.medibook;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Lucy on 25/04/2018.
 */

public class Utilities {

    public static final String FILE_EXTENSION = ".bin";

    public static boolean saveLog(Context context, Log log) {

        String fileName = String.valueOf(log.getDateTime()) + FILE_EXTENSION;

        FileOutputStream fos;
        ObjectOutputStream oos;
        try {
            fos = context.openFileOutput(fileName, context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(log);
            oos.close();
            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
            return false; //Tells users that something went wrong

        }

        return true;
    }

    public static ArrayList<Log> getallSavedLogs(Context context) {
        ArrayList<Log> logs = new ArrayList<>();
        File filesDir = context.getFilesDir();

        ArrayList<String> logFiles = new ArrayList<>();

        for (String file : filesDir.list()) {
            if (file.endsWith(FILE_EXTENSION)) {
                logFiles.add(file);
            }
        }
        FileInputStream fis;
        ObjectInputStream ois;

        for(int i = 0; i < logFiles.size(); i++){
            try{
                fis = context.openFileInput(logFiles.get(i));
                ois = new ObjectInputStream(fis);

                logs.add((Log)ois.readObject());

                fis.close();
                ois.close();

            }catch (IOException | ClassNotFoundException e){
                e.printStackTrace();
                return null;
            }
        }
        return logs;
    }
    public static Log getLogByName(Context context, String fileName){
        File file = new File(context.getFilesDir(), fileName);
        Log log;
        if(file.exists()){
            FileInputStream fis;
            ObjectInputStream ois;
            try{
                fis = context.openFileInput(fileName);
                ois = new ObjectInputStream(fis);

                log = (Log) ois.readObject();

                fis.close();
                ois.close();
            }catch (IOException | ClassNotFoundException e){
                e.printStackTrace();
                return null;
            }
            return log;
        }
        return null;
    }

    public static void deleteLog(Context context, String fileName) {
        File dir= context.getFilesDir();
        File file = new File(dir, fileName);

        if(file.exists()){
            file.delete();
        }
    }
}

