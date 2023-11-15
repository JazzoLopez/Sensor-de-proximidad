package com.example.sensorproximity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SensorManager sensorManager = (SensorManager) getSystemService (SENSOR_SERVICE);

        Sensor proximity = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        if(proximity != null) { //SI ES NULL ES PORQUE NO CONTAMOS CON UN SENSOR DE PROXIMIDAD
            Toast.makeText(this, "Si tienes brother", Toast.LENGTH_SHORT).show();
        }
        
        SensorEventListener proximityEvent = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {

                if(event.values[0] <  proximity.getMaximumRange()) {
                    getWindow().getDecorView().setBackgroundColor(Color.GREEN);
                }
                else{

                    getWindow().getDecorView().setBackgroundColor(Color.RED);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
                Toast.makeText(MainActivity.this, "Nose que pase aqui", Toast.LENGTH_SHORT).show();
            }
        };
        
        
        sensorManager.registerListener(proximityEvent, proximity, 0); //Periodo de tiempo en cero para que no tarde


    }
}