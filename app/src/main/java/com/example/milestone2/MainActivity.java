package com.example.milestone2;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView user, pass;// create text view
    public static final String DEFAULT = "not available";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        user = findViewById( R.id.name );// initialize the variable
        pass = findViewById( R.id.password );// initialize the variable
    }

    public void Login(View view) {// create log in button, when user sign in username and password is correct, the program will execute order to next interface
        SharedPreferences prefs = getSharedPreferences( "Save Info", Context.MODE_PRIVATE );// initialize the shared preference
        String userName = prefs.getString( "username",DEFAULT );
        String password = prefs.getString( "password",DEFAULT );

        //if user sign in user name and password equal register information, the program will execute order to next interface. otherwise, the program will back to register interface.

    }
}