package com.example.student.androidshooter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

/**
 * Created by student on 4/7/2015.
 */
//A view with a scrolling randomly created star background
public class ShootingInstructions extends View implements SensorEventListener
{
    private Bitmap player;
    private Player playerP;
    private int width = 0;
    private int height=0;
    //Starting position and keeps track of where ball is currently
    private double neutralyPos;
    private float x=0;
    private float y=0;
    private Paint p;
    private double xPos, yPos;
    private SensorManager mSensorManager;
    //A sensor which we will later register as an accelerometer sensor
    private Sensor mSensor;
    //Constructor without attributes
    Context mContext;
    public ShootingInstructions(Context mContext)
    {
        super(mContext);
        this.mContext = mContext;
        player = BitmapFactory.decodeResource(getResources(), R.drawable.player);
        mSensorManager = (SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mSensor, mSensorManager.SENSOR_DELAY_NORMAL);
        playerP = new Player(mContext);
        neutralyPos=getCurrentNeutralYPos();
    }
    //Constructor with attributes
    public ShootingInstructions(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        mContext = context;
        player = BitmapFactory.decodeResource(getResources(), R.drawable.player);
        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mSensor, mSensorManager.SENSOR_DELAY_NORMAL);
        playerP = new Player(mContext);
        neutralyPos=getCurrentNeutralYPos();
    }
    //Drawing a view with a ship that shoots but does not move todo: get rid of movability and add ability to shoot
    public void onDraw(Canvas canvas)
    {
        if(width == 0) {
            width=canvas.getWidth();
            height=canvas.getHeight();
            x = width/2;
            Log.i("Arrived", String.valueOf(x));
        }
        canvas.drawBitmap(player, (-x+width), height-playerP.getHeight(), p);
        x= (float) (xPos*2 + x);
        if(leftCollision(x))
        {
            //Log.d("MovementInstructions","Left Collision");
            x=width;
        }

        if(rightCollision(x))
        {
            //Log.d("MovementInstructions","Right Collision");
            x=playerP.getWidth();
        }

        invalidate();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        xPos = event.values[0];
        yPos = event.values[1];

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    //Checks left of screen collision or if ball goes past
    private boolean leftCollision(float x)
    {

        if(x>=width)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    //Checks right of screen collision or if ball goes past
    private boolean rightCollision(float x)
    {
        if(x-playerP.getWidth()<=0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    private double getCurrentNeutralYPos()
    {
        try {
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(
                    mContext.openFileInput("angle")));
            String inputString;
            StringBuffer stringBuffer = new StringBuffer();
            while ((inputString = inputReader.readLine()) != null) {
                stringBuffer.append(inputString);
            }
            Log.d("Angle", stringBuffer.toString());
            double tempPos = Double.parseDouble(stringBuffer.toString());
            return tempPos;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }
}