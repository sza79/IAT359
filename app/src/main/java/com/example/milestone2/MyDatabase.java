package com.example.milestone2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MyDatabase {
    private SQLiteDatabase db;
    private Context context;
    public final MyHelper helper;

    public MyDatabase (Context c){
        context = c;
        helper = new MyHelper(context);
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    public long insertData (String patientName, String bloodType, String wbc, String rbc, Boolean hepatitisA, Boolean hepatitisB)
    {
        db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.PATIENTNAME, patientName);
        contentValues.put(Constants.DATE, getDateTime());
        contentValues.put(Constants.BLOODTYPE, bloodType);
        contentValues.put(Constants.WBC, wbc);
        contentValues.put(Constants.RBC, rbc);
        contentValues.put(Constants.HEPATITISA, hepatitisA);
        contentValues.put(Constants.HEPATITISB, hepatitisB);
        //Put More
        long id = db.insert(Constants.TABLE_NAME, null, contentValues);
        if (id < 0)
        {
            Log.i("wow", "Insert Failed");
        }
        else
        {
            Log.i("wow", "Insert Successful, inserted record #" + id);
        }
        return id;
    }

    public Cursor getReportsByName(String patientName)
    {
        //select plants from database of type 'herb'
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns = {Constants.PATIENTNAME, Constants.DATE, Constants.BLOODTYPE, Constants.RBC, Constants.WBC, Constants.HEPATITISA, Constants.HEPATITISB};

        String selection = Constants.PATIENTNAME + "='" + patientName + "'";
        Cursor cursor = db.query(Constants.TABLE_NAME, columns, selection, null, null, null, null);
        return cursor;
    }

//    public Cursor getData()
//    {
//        SQLiteDatabase db = helper.getWritableDatabase();
//
//        String[] columns = {Constants.UID, Constants.PATIENTNAME};
//        Cursor cursor = db.query(Constants.TABLE_NAME, columns, null, null, null, null, null);
//        return cursor;
//    }


//    public String getSelectedDataByName(String patientName)
//    {
//        //select plants from database of type 'herb'
//        SQLiteDatabase db = helper.getWritableDatabase();
//        String[] columns = {Constants.PATIENTNAME};
//
//        String selection = Constants.PATIENTNAME + "='" + patientName + "'";
//        Cursor cursor = db.query(Constants.TABLE_NAME, columns, selection, null, null, null, null);
//
//        StringBuffer buffer = new StringBuffer();
//        while (cursor.moveToNext()) {
//
//            int index1 = cursor.getColumnIndex(Constants.DATE);
//            int index2 = cursor.getColumnIndex(Constants.);
//            String plantName = cursor.getString(index1);
//            String plantType = cursor.getString(index2);
//            buffer.append(plantName + " " + plantType + "\n");
//        }
//        return buffer.toString();
//    }




}
