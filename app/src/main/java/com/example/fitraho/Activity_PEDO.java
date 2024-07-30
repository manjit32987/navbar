package com.example.fitraho;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

public class Activity_PEDO extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor stepCounterSensor;
    private CircularProgressBar circularProgressBar;
    private TextView stepsTextView;
    private final int STEP_GOAL = 2500; // Define your step goal here

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pedo);

        stepsTextView = findViewById(R.id.tv_steptaken);
        circularProgressBar = findViewById(R.id.circularProgressBar);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager != null) {
            stepCounterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (stepCounterSensor == null) {
            Toast.makeText(this, "Sensor Not Found", Toast.LENGTH_SHORT).show();
        } else {
            sensorManager.registerListener(this, stepCounterSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (sensorManager != null) {
            sensorManager.unregisterListener(this);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            float stepsTaken = event.values[0];
            stepsTextView.setText(String.valueOf(stepsTaken));

            // Calculate the progress percentage
            float progressPercentage = (stepsTaken / STEP_GOAL) * 100;
            circularProgressBar.setProgressWithAnimation(progressPercentage, (long) 1000); // 1 second animation duration
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Handle changes in sensor accuracy if needed
    }
}
