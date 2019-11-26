package com.example.milestone2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


//This class is a helper class for using the blood report database,
// contains a few of the helper function that makes life easier accessing the db
public class MyBloodReportDatabase {
    private SQLiteDatabase db;
    private Context context;
    public final MyHelper helper;

    public MyBloodReportDatabase(Context c){
        context = c;
        helper = new MyHelper(context);
    }

    //getDateTime is used for retrieving the current system time when inserting record
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    //InserData Method with input
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

        //id would become the number of record if successful, and negative if failed
        long id = db.insert(Constants.BLOODTABLE_NAME, null, contentValues);
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

    //With this method, we can retrieve one line of record by patient name
    public Cursor getReportsByName(String patientName)
    {
        //select plants from database of type 'herb'
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns = {Constants.UID, Constants.PATIENTNAME, Constants.DATE, Constants.BLOODTYPE, Constants.RBC, Constants.WBC, Constants.HEPATITISA, Constants.HEPATITISB};

        String selection = Constants.PATIENTNAME + "='" + patientName + "'";
        Cursor cursor = db.query(Constants.BLOODTABLE_NAME, columns, selection, null, null, null, null);
        return cursor;
    }
}
