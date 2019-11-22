package com.example.milestone2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MyUserDatabase {

    private SQLiteDatabase db;
    private Context context;
    public final MyHelper helper;

    public MyUserDatabase(Context c){
        context = c;
        helper = new MyHelper(context);
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    public long insertData (String patientName, String password, String cardNum, String sex, String age, String phone)
    {
        db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.PATIENTNAME, patientName);
        contentValues.put(Constants.PASSWORD, password);
        contentValues.put(Constants.CARDNUM, cardNum);
        contentValues.put(Constants.SEX, sex);
        contentValues.put(Constants.AGE, age);
        contentValues.put(Constants.PHONE, phone);

        long id = db.insert(Constants.USERTABLE_NAME, null, contentValues);
        if (id < 0)
        {
            Log.i("wow", "User Db Insert Failed");
        }
        else
        {
            Log.i("wow", "User Db Insert Successful, inserted record #" + id);
        }
        return id;
    }

    public void updateData (String patientName, String cardNumber, String sex, String age, String phone) {
        db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Constants.CARDNUM, cardNumber);
        cv.put(Constants.SEX, sex);
        cv.put(Constants.AGE, age);
        cv.put(Constants.PHONE, phone);
        String selection = Constants.PATIENTNAME + "='" + patientName + "'";
        db.update(Constants.USERTABLE_NAME, cv, selection, null);
    }

    public Cursor getDataByName(String patientName)
    {
        //select plants from database of type 'herb'
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns = {Constants.PATIENTNAME, Constants.PASSWORD, Constants.CARDNUM, Constants.SEX, Constants.AGE, Constants.PHONE};

        String selection = Constants.PATIENTNAME + "='" + patientName + "'";
        Cursor cursor = db.query(Constants.USERTABLE_NAME, columns, selection, null, null, null, null);
        return cursor;
    }
}
