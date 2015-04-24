package com.example.student.androidshooter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.student.androidshooter.R;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;


public class Calibrate extends Activity implements View.OnClickListener, SensorEventListener  {
    private ImageButton calibrate;
    private ImageButton mainMenu;
    private SensorManager mSensorManager;
    private Sensor mSensor;
    private float currentTilt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calibrate);
        calibrate = (ImageButton) findViewById(R.id.calibrate2);
        calibrate.setOnClickListener(this);
        mainMenu = (ImageButton) findViewById(R.id.mainmenu);
        mainMenu.setOnClickListener(this);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mSensor, mSensorManager.SENSOR_DELAY_NORMAL);
    }
    //Every time the sensor data changes updates the speed at which x and y are moved
    public void onSensorChanged(SensorEvent event){
        currentTilt=event.values[1];
       // Log.d("Calibrate", Float.toString(currentTilt));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    //Stops listening for accelerometer data when app is paused
    public void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
    //Resumes listening to accelerometer data when app is brought out of paused state
    public void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mSensor, mSensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    public void onClick(View v)
    {
        if(v.getId()==R.id.calibrate2)
        {
            Context context = getApplicationContext();
            CharSequence text = "";
            int duration = Toast.LENGTH_SHORT;
            String FILENAME = "angle";
            String string = currentTilt + "";
            try {
                FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
                fos.write(string.getBytes());
                fos.close();
            }
            catch (Exception e)
            {

                text = "An Error Occurred!";

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
            text = "Angle Calibrated!";
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        if(v.getId()==R.id.mainmenu)
        {
            Intent i = getIntent();
            setResult(Activity.RESULT_OK, i);
            finish();
        }
    }

}
