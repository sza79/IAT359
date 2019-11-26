package com.example.milestone2;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

//Activity to show Blood Report Data
public class ReportActivity extends AppCompatActivity  {

    //Variables
    private MyBloodReportDatabase bloodDb;
    private String myUid;
    private String[] myReportData;

    private TextView timeTV, bloodTypeTV, rbcTV, wbcTV, hepaATV, hepaBTV;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        //UI References
        timeTV = findViewById(R.id.timeTV);
        bloodTypeTV = findViewById(R.id.bloodTypeTV);
        rbcTV = findViewById(R.id.rbcTV);
        wbcTV = findViewById(R.id.wbcTV);
        hepaATV = findViewById(R.id.hepaATV);
        hepaBTV = findViewById(R.id.hepaBTV);

        //Retrieve the uid from Intent Extra
        //If there is, uid is sent from the last activity, with the id of the clicked report
        //The id is then used to retieve a specific blood report record in the database
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            myUid = extras.getString("uid");
        }

        //Initializing the Blood Report Database
        bloodDb = new MyBloodReportDatabase(this);

        //Get Data
        Cursor cursor = bloodDb.getReportsByName("April Zhang");

        //All the columns that we are getting, we get the index number of it
        int index0 = cursor.getColumnIndex(Constants.UID);
        int index1 = cursor.getColumnIndex(Constants.PATIENTNAME);
        int index2 = cursor.getColumnIndex(Constants.DATE);
        int index3 = cursor.getColumnIndex(Constants.BLOODTYPE);
        int index4 = cursor.getColumnIndex(Constants.WBC);
        int index5 = cursor.getColumnIndex(Constants.RBC);
        int index6 = cursor.getColumnIndex(Constants.HEPATITISA);
        int index7 = cursor.getColumnIndex(Constants.HEPATITISB);

        //Retrieving the data by Cursor looping through
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

            //Construct the data into a string separated by ","
            String tempString = uid + "," + patientName + "," + date + "," + bloodType + "," + wbc + "," + rbc + "," + hepatitisA + "," + hepatitisB;

            //Splitting out the uid from the string to check if the record is the one we are searching for
            String[] splittedTempString = tempString.split(",");
            //Compare with the uid we retrieved from the Intent Extra
            if (splittedTempString[0].equals(myUid)) {
                myReportData = splittedTempString;
            }
            cursor.moveToNext();
        }

        //If the report is filled with at least one result, we set the conent of the views in report browsing page
        if (myReportData[0] != null) {
            timeTV.setText(myReportData[2]);
            bloodTypeTV.setText(myReportData[3]);
            rbcTV.setText(myReportData[4] + " m/mcL");
            wbcTV.setText(myReportData[5] + " m/mcL");
            if (Integer.parseInt(myReportData[6]) == 0) {
                hepaATV.setText("Negative");
            } else {
                hepaATV.setText(("Positive"));
            }
            if (Integer.parseInt(myReportData[7]) == 0) {
                hepaBTV.setText("Negative");
            } else {
                hepaBTV.setText(("Positive"));
            }
        }

    }
}
