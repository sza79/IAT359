package com.example.milestone2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

//Profile activity showing the information of User
public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{
    //Variables
    private TextView usernameTextView, cardNumTextView, sexTextView, ageTextView, phoneTextView;
    private Button editButton;

    private MyUserDatabase userDb;

    public static final String DEFAULT = "not available";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //UI Reference
        usernameTextView = findViewById(R.id.usernameTextView);
        cardNumTextView = findViewById(R.id.cardNumTextView);
        sexTextView = findViewById(R.id.sexTextView);
        ageTextView = findViewById(R.id.ageTextView);
        phoneTextView = findViewById(R.id.phoneTextView);

        //Button Click Listener
        editButton = findViewById(R.id.editButton);
        editButton.setOnClickListener(this);

        //Declaration of UserDatabase, used to retrieve user data
        userDb = new MyUserDatabase(this);

        //Get the currently logged in user by using Shared Preference
        SharedPreferences prefs = getSharedPreferences( "Save Info", Context.MODE_PRIVATE );// initialize the shared preference
        String currentSessionUsername = prefs.getString( "CurrentSessionUsername", DEFAULT );

        //Retrieve reportList entries from database by patient name retrieved from SharedPreference
        Cursor cursor = userDb.getDataByName(currentSessionUsername);
        int index1 = cursor.getColumnIndex(Constants.PATIENTNAME);
        int index2 = cursor.getColumnIndex(Constants.CARDNUM);
        int index3 = cursor.getColumnIndex(Constants.SEX);
        int index4 = cursor.getColumnIndex(Constants.AGE);
        int index5 = cursor.getColumnIndex(Constants.PHONE);

        //Setting the TextView's value by the data retrieved
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Log.i("wow", "stuff changing, index 1 = " + cursor.getString(index1));
            usernameTextView.setText(cursor.getString(index1));
            cardNumTextView.setText(cursor.getString(index2));
            sexTextView.setText(cursor.getString(index3));
            ageTextView.setText(cursor.getString(index4));
            phoneTextView.setText(cursor.getString(index5));
            cursor.moveToNext();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    //When Edit Button is clicked
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.editButton){
            //EditButton Pressed
            Intent i = new Intent( this, profileEditActivity.class );
            startActivity(i);

        }
    }
}
