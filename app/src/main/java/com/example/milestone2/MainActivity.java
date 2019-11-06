package com.example.milestone2;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


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