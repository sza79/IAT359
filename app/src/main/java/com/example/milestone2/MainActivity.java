package com.example.milestone2;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Timestamp;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MyDatabase db;
    //create/ allow Edit text
    private EditText user, pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        //initialize variable for user name
        user = findViewById( R.id.name );

        //initialize variable for password
        pass = findViewById( R.id.password );

        db = new MyDatabase(this);

        //Insert Data
        // patientName, bloodType, wbc, rbc, hepatitisA, hepatitisB
        //For now, timestamp is generated automatically with current system time
        db.insertData("MyNameIsWOW", "B+", "555", "22210", false, false);

        //Get Data
        Cursor cursor = db.getReportsByName("MyNameIsWOW");

        int index1 = cursor.getColumnIndex(Constants.PATIENTNAME);
        int index2 = cursor.getColumnIndex(Constants.DATE);
        int index3 = cursor.getColumnIndex(Constants.BLOODTYPE);
        int index4 = cursor.getColumnIndex(Constants.WBC);
        int index5 = cursor.getColumnIndex(Constants.RBC);
        int index6 = cursor.getColumnIndex(Constants.HEPATITISA);
        int index7 = cursor.getColumnIndex(Constants.HEPATITISB);

        ArrayList<String> mArrayList = new ArrayList<String>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String patientName = cursor.getString(index1);
            String date = cursor.getString(index2);
            String bloodType = cursor.getString(index3);
            String wbc = cursor.getString(index4);
            String rbc = cursor.getString(index5);
            String hepatitisA = cursor.getString(index6);
            String hepatitisB = cursor.getString(index7);

            String s = patientName + "," + date + "," + bloodType + "," + wbc + "," + rbc + "," + hepatitisA + "," + hepatitisB;
            mArrayList.add(s);
            cursor.moveToNext();
        }
        Log.i("wow", "First Element is : " + mArrayList.get(0));

        //Print the Data found with the name
        for (String e : mArrayList) {
            Log.i("wow", e);
        }





    }

    public void Register(View view) {



        //initialize the shared preference
        SharedPreferences register = getSharedPreferences( "Save Info", Context.MODE_PRIVATE );

        //create editor object. using share preference to initialize that object call edit method
        SharedPreferences.Editor editor = register.edit();

        //editor name
        editor.putString( "username", user.getText().toString() );
        editor.putString( "password", pass.getText().toString() );
        Toast.makeText( this,"Save register information", Toast.LENGTH_LONG ).show();
        editor.commit();

    }


    public void gotoLogin(View view) {

        //create intent for an external application by using start activity
        Toast.makeText( this, "go to Log in interface", Toast.LENGTH_LONG ).show();
        Intent i = new Intent( this, LoginActivity.class );
        startActivity(i);

    }
}