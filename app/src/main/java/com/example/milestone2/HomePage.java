package com.example.milestone2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class HomePage extends AppCompatActivity implements View.OnClickListener{

    //Variables
    private TextView usernameTextView;
    private Button findStationButton;
    private ImageView iconButton, reportButton, accomplishmentButton, monitorButton;

    //String to present when SharedPreference Retrieval Result is not present
    public static final String DEFAULT = "not available";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Get Reference to UI TextView
        usernameTextView = findViewById(R.id.usernameTextView);

        //Retrieving Current Session Logged-In User
        SharedPreferences prefs = getSharedPreferences( "Save Info", Context.MODE_PRIVATE );// initialize the shared preference
        String currentSessionUsername = prefs.getString( "CurrentSessionUsername", DEFAULT );

        //Set currentSessionUsername onto WelcomeTextuView
        usernameTextView.setText(currentSessionUsername);

        //Buttons:

        //icon USER
        iconButton = findViewById(R.id.iconButton);
        iconButton.setOnClickListener(this);

        //search map
        findStationButton = findViewById(R.id.findStationButton);
        findStationButton.setOnClickListener(this);

        //report
        reportButton = findViewById(R.id.reportButton);
        reportButton.setOnClickListener(this);

        //accomplishment
        accomplishmentButton = findViewById(R.id.accomplishmentButton);
        accomplishmentButton.setOnClickListener(this);

        //step count
        monitorButton = findViewById(R.id.monitorButton);
        monitorButton.setOnClickListener(this);


    }

    @Override

    //when clicked
    public void onClick(View v) {

        //Explicit Intent to transfer user to the next page
        if (v.getId() == R.id.iconButton) {
            Intent i = new Intent( this, ProfileActivity.class );
            startActivity( i );
        }

        if (v.getId() == R.id.findStationButton) {
            Intent i = new Intent( this, MapActivity.class );
            startActivity( i );
        }

        if (v.getId() == R.id.reportButton) {
            Intent i = new Intent( this, ReportChoosing.class );
            startActivity( i );
        }

        if (v.getId() == R.id.accomplishmentButton) {
            Intent i = new Intent( this, AchieveActivity.class );
            startActivity( i );
        }

        if (v.getId() == R.id.monitorButton) {
            Intent i = new Intent( this, WalkingSpeedActivity.class );
            startActivity( i );
        }
    }
}
