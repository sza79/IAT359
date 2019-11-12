package com.example.milestone2;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class WalkingSpeedActivity extends AppCompatActivity implements SensorEventListener {

    private TextView speedTextView;

    //Sensor Manager
    private SensorManager mySensorManager;

    private long steps = 0;

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

        Log.i("wow", "Step : " + steps);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
