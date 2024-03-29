package com.example.milestone2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;

public class WalkingSpeedActivity extends AppCompatActivity implements SensorEventListener, View.OnClickListener {

    private TextView speedTextView, linkToExternal;
    private ImageView walkingStatus;

    //Sensor Manager
    private SensorManager mySensorManager;

    private long steps = 0;

    private long previousTime = 0;
    private long newTime = 0;

    private long timeDifference = 0;

    //The step counter
    private Sensor stepCounter;

    //Vibrator Declaration
    public Vibrator v;

    //SensorValues float Array to store the sensor values read
    private float[] rawSensorValues;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walking_speed);

        //Initializing vibrater object
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        speedTextView = findViewById(R.id.speedTextView);

        walkingStatus = findViewById(R.id.walkingStatus);

        linkToExternal = findViewById(R.id.linkToExternal);
        linkToExternal.setOnClickListener(this);


        //Instantiate SensorManager
        mySensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        try {
            stepCounter = mySensorManager.getSensorList(Sensor.TYPE_STEP_DETECTOR).get(0);

        } catch (Exception e) {
            Log.i("wow", e.toString());
        }

    }

    protected void onResume() {
        super.onResume();
        this.mySensorManager.registerListener(this, this.stepCounter, 3);
    }

    protected void onPause() {
        this.mySensorManager.unregisterListener(this);
        super.onPause();
    }

    protected double toMeterPerSecond(long secondsPer10Step) {
        double temp = 1.312/(secondsPer10Step * 0.1);
        return temp;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;
        float[] values = event.values;
        int value = -1;

        if (values.length > 0) {
            value = (int) values[0];
        }

        if (sensor.getType() == Sensor.TYPE_STEP_DETECTOR) {
            steps++;
        }

        Log.i("wow", "Step : " + steps);
        Date currentTime = Calendar.getInstance().getTime();
        Long tsLong = System.currentTimeMillis()/1000;
        String ts = tsLong.toString();
        Log.i("wow", ts);


        try {
            if (steps == 1) {
                newTime = System.currentTimeMillis() / 1000;
            }

            if (steps >= 10) {
                if (steps % 10 == 0) {
                    previousTime = newTime;
                    newTime = System.currentTimeMillis() / 1000;
                    Log.i("wow", "Previous Time = " + previousTime + ", New Time = " + newTime);
                    timeDifference = newTime - previousTime;
                    if (timeDifference < 5) {
                        walkingStatus.setImageResource(R.drawable.toofast);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                            //Vibrate for 5 seconds
                            v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                        } else {
                            //deprecated in API 26 //Just in case you load this project in a lower version
                            v.vibrate(500);
                        }
                    } else {
                        walkingStatus.setImageResource(R.drawable.normal);
                    }
                }
                double tempMPS = toMeterPerSecond(timeDifference);
                double textViewOutput = new BigDecimal(tempMPS).setScale(2, RoundingMode.HALF_UP).doubleValue();
                speedTextView.setText(String.valueOf(textViewOutput));
            }
        } catch (Exception e) {
            Log.i("wow", e.toString());
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    //Implicit Intent Used
    //If "Learn More" button is clicked, we use implicit intent to open a link
    //This is implicit because we have only declared the url for the device to open,
    //But did not declare which app or process the device should start
    //The device/user can decide which browser they want to open the link with
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.linkToExternal) {
            String url = "https://blood.ca/en/blood/donating-blood/donation-process";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        }
    }
}
