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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MyBloodReportDatabase bloodDb;
    //create/ allow Edit text
    private EditText user, pass;

    private MyUserDatabase userDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        //initialize variable for user name
        user = findViewById( R.id.name );

        //initialize variable for password
        pass = findViewById( R.id.password );

        bloodDb = new MyBloodReportDatabase(this);

        userDb = new MyUserDatabase(this);

        //Round up the two randomized number to the closest 2 decimal place using BigDecimal Class
        //Learned from https://www.mkyong.com/java/how-to-round-double-float-value-to-2-decimal-points-in-java/
        double randomRBC = new BigDecimal(4.50f + Math.random() * (1.00f)).setScale(2, RoundingMode.HALF_UP).doubleValue();
        double randomWBC = new BigDecimal(4.50f + Math.random() * (1.00f)).setScale(2, RoundingMode.HALF_UP).doubleValue();

        //For now, we are faking the data
        //We insert a randomized blood report everytime the program start
        //Here is the insert query
        //Insert Data
        // patientName, bloodType, wbc, rbc, hepatitisA, hepatitisB
        //For now, timestamp is generated automatically with current system time
        bloodDb.insertData("April Zhang", "B+", String.valueOf(randomRBC), String.valueOf(randomWBC), false, false);
    }

    public void Register(View view) {
        //initialize the shared preference
        SharedPreferences register = getSharedPreferences( "Save Info", Context.MODE_PRIVATE );

        //create editor object. using share preference to initialize that object call edit method
        SharedPreferences.Editor editor = register.edit();

        //Retrieve the text entered from the UI
        String enteredUsername = user.getText().toString();
        String enteredPassword = pass.getText().toString();

        //Legacy code to register for the username and password, now we use sql database
//        editor.putString( enteredUsername + "_username", user.getText().toString() );
//        editor.putString( enteredPassword + "_password", pass.getText().toString() );
//        Toast.makeText( this,"Save register information", Toast.LENGTH_LONG ).show();
//        editor.commit();

        //Insert Data
        userDb.insertData(user.getText().toString(), pass.getText().toString(), "-", "-", "-", "-");

        //create intent for an external application by using start activity
        Toast.makeText( this, "go to Log in interface", Toast.LENGTH_LONG ).show();
        Intent i = new Intent( this, LoginActivity.class );
        startActivity(i);
    }

    public void gotoLogin(View view) {
        //create intent for an external application by using start activity
        Toast.makeText( this, "go to Log in interface", Toast.LENGTH_LONG ).show();
        Intent i = new Intent( this, LoginActivity.class );
        startActivity(i);
    }
}