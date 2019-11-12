package com.example.milestone2;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import java.util.Calendar;
import java.util.Date;

public class WalkingSpeedActivity extends AppCompatActivity implements SensorEventListener {

    private TextView speedTextView;

    //Sensor Manager
    private SensorManager mySensorManager;

    private long steps = 0;

    private long previousTime = 0;
    private long newTime = 0;

    //The step counter
    private Sensor stepCounter;

    //SensorValues float Array to store the sensor values read
    private float[] rawSensorValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walking_speed);

        speedTextView = findViewById(R.id.speedTextView);

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

        //Every 10 steps
        if (steps % 10 == 0) {

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
                }
            }
        } catch (Exception e) {
            Log.i("wow", e.toString());
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
