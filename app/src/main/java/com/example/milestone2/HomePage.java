package com.example.milestone2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class HomePage extends AppCompatActivity {

    private TextView usernameTextView;

    public static final String DEFAULT = "not available";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        usernameTextView = findViewById(R.id.usernameTextView);

        SharedPreferences prefs = getSharedPreferences( "Save Info", Context.MODE_PRIVATE );// initialize the shared preference
        String currentSessionUsername = prefs.getString( "CurrentSessionUsername", DEFAULT );

        usernameTextView.setText(currentSessionUsername);
    }


    public void Search(View view){
        Intent i = new Intent( this, ReportChoosing.class );
        startActivity( i );
    }

    public void enterReport(View view){
        Intent i = new Intent( this, ReportActivity.class );
        startActivity( i );
        Toast.makeText( this, "Entering Report page", Toast.LENGTH_LONG ).show();

    }

    public void enterAchieve(View view){
        Intent i = new Intent( this, AchieveActivity.class );
        startActivity( i );
        Toast.makeText( this, "Entering Achievement page", Toast.LENGTH_LONG ).show();

    }

    public void enterProfile(View view){
        Intent i = new Intent( this, ProfileActivity.class );
        startActivity( i );
        Toast.makeText( this, "Entering User Profile Page", Toast.LENGTH_LONG ).show();

    }



}
