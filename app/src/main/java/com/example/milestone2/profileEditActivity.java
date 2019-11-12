package com.example.milestone2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class profileEditActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView usernameTextView;
    private EditText cardNumEditText, sexEditText, ageEditText, phoneEditText;
    private Button doneButton;

    private MyUserDatabase userDb;

    public static final String DEFAULT = "not available";

    private String currentSessionUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

        usernameTextView = findViewById(R.id.usernameTextView);
        cardNumEditText = findViewById(R.id.cardNumEditText);
        sexEditText = findViewById(R.id.sexEditText);
        ageEditText = findViewById(R.id.ageEditText);
        phoneEditText = findViewById(R.id.phoneEditText);

        doneButton = findViewById(R.id.doneButton);
        doneButton.setOnClickListener(this);

        userDb = new MyUserDatabase(this);

        SharedPreferences prefs = getSharedPreferences( "Save Info", Context.MODE_PRIVATE );// initialize the shared preference
        currentSessionUsername = prefs.getString( "CurrentSessionUsername", DEFAULT );

        //Retrieve reportList entries from database
        Cursor cursor = userDb.getDataByName(currentSessionUsername);
        int index1 = cursor.getColumnIndex(Constants.PATIENTNAME);
        int index2 = cursor.getColumnIndex(Constants.CARDNUM);
        int index3 = cursor.getColumnIndex(Constants.SEX);
        int index4 = cursor.getColumnIndex(Constants.AGE);
        int index5 = cursor.getColumnIndex(Constants.PHONE);

        Log.i("wow", "Current Session Name = " + currentSessionUsername);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Log.i("wow", "stuff changing, index 1 = " + cursor.getString(index1));
            usernameTextView.setText(cursor.getString(index1));
            cardNumEditText.setText(cursor.getString(index2));
            sexEditText.setText(cursor.getString(index3));
            ageEditText.setText(cursor.getString(index4));
            phoneEditText.setText(cursor.getString(index5));
            cursor.moveToNext();
        }


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.doneButton) {
            userDb.updateData(currentSessionUsername, cardNumEditText.getText().toString(), sexEditText.getText().toString(), ageEditText.getText().toString(), phoneEditText.getText().toString());
            Intent refresh = new Intent(this, ProfileActivity.class);
            startActivity(refresh);
            this.finish();
        }

    }
}
