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

    private TextView usernameTextView;
    private Button findStationButton;
    private ImageView iconButton, reportButton, accomplishmentButton, monitorButton;

    public static final String DEFAULT = "not available";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        usernameTextView = findViewById(R.id.usernameTextView);

        SharedPreferences prefs = getSharedPreferences( "Save Info", Context.MODE_PRIVATE );// initialize the shared preference
        String currentSessionUsername = prefs.getString( "CurrentSessionUsername", DEFAULT );

        usernameTextView.setText(currentSessionUsername);

        iconButton = findViewById(R.id.iconButton);
        iconButton.setOnClickListener(this);
        findStationButton = findViewById(R.id.findStationButton);
        findStationButton.setOnClickListener(this);
        reportButton = findViewById(R.id.reportButton);
        reportButton.setOnClickListener(this);
        accomplishmentButton = findViewById(R.id.accomplishmentButton);
        accomplishmentButton.setOnClickListener(this);
        monitorButton = findViewById(R.id.monitorButton);
        monitorButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iconButton) {
            Intent i = new Intent( this, ProfileActivity.class );
            startActivity( i );
        }

        if (v.getId() == R.id.findStationButton) {

        }

        if (v.getId() == R.id.reportButton) {
            Intent i = new Intent( this, ReportActivity.class );
            startActivity( i );
        }

        if (v.getId() == R.id.accomplishmentButton) {
            Intent i = new Intent( this, AchieveActivity.class );
            startActivity( i );
        }

        if (v.getId() == R.id.monitorButton) {

        }
    }
}
