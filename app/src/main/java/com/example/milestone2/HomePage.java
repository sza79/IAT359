package com.example.milestone2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

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
