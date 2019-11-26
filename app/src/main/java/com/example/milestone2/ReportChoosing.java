package com.example.milestone2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;

import java.util.ArrayList;

//RecyclerView Page to select Report
public class ReportChoosing extends AppCompatActivity {

    private RecyclerView myRecyclerview;

    private MyBloodReportDatabase bloodDb;

    //ArrayList for storing sensor name strings
    private ArrayList<String> reportList;

    //RecyclerViewAdapter
    private RecyclerViewAdapter myAdapter;

    private String currentSessionUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_choosing);

        SharedPreferences prefs = getSharedPreferences( "Save Info", Context.MODE_PRIVATE );// initialize the shared preference
        currentSessionUsername = prefs.getString( "CurrentSessionUsername", "Not Available" );

        //RecyclerView UI Item
        myRecyclerview = findViewById(R.id.reportRecyclerView);

        //Instantiate ArrayList
        reportList = new ArrayList<>();

        //Retrieve reportList entries from database
        bloodDb = new MyBloodReportDatabase(this);
        Cursor cursor = bloodDb.getReportsByName("April Zhang");

        int index0 = cursor.getColumnIndex(Constants.UID);
        int index1 = cursor.getColumnIndex(Constants.PATIENTNAME);
        int index2 = cursor.getColumnIndex(Constants.DATE);
        int index3 = cursor.getColumnIndex(Constants.BLOODTYPE);
        int index4 = cursor.getColumnIndex(Constants.WBC);
        int index5 = cursor.getColumnIndex(Constants.RBC);
        int index6 = cursor.getColumnIndex(Constants.HEPATITISA);
        int index7 = cursor.getColumnIndex(Constants.HEPATITISB);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String uid = cursor.getString(index0);
            String patientName = cursor.getString(index1);
            String date = cursor.getString(index2);
            String bloodType = cursor.getString(index3);
            String wbc = cursor.getString(index4);
            String rbc = cursor.getString(index5);
            String hepatitisA = cursor.getString(index6);
            String hepatitisB = cursor.getString(index7);

            String s = uid + "," + patientName + "," + date + "," + bloodType + "," + wbc + "," + rbc + "," + hepatitisA + "," + hepatitisB;
            reportList.add(s);
            cursor.moveToNext();
        }

        //RecyclerView Adapter Instantiation with the ArrayList
        myAdapter = new RecyclerViewAdapter(reportList, currentSessionUsername);

        //Setting Adapter for RecyclerView
        myRecyclerview.setAdapter(myAdapter);

        //This line is required for it to work, not sure why
        myRecyclerview.setLayoutManager(new LinearLayoutManager(this));

    }
}
