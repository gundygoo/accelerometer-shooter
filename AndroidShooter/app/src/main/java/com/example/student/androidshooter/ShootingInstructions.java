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
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by student on 4/7/2015.
 */
//A view with a scrolling randomly created star background
public class ShootingInstructions extends View implements SensorEventListener
{
    private Player player;
    private int width = 0;
    private int height=0;
    //Starting position and keeps track of where ball is currently
    ArrayList<Projectile> projectiles;
    private double neutralYPos=0;
    private float x=0;
    private float y=0;
    private Paint p;
    private double xPos, yPos;
    private SensorManager mSensorManager;
    private int shootingFrames=0;
    //A sensor which we will later register as an accelerometer sensor
    private Sensor mSensor;
    //Constructor without attributes
    private Context mContext;
    private Bitmap shieldImg;
    public ShootingInstructions(Context mContext)
    {
        super(mContext);
        this.mContext = mContext;
        mSensorManager = (SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mSensor, mSensorManager.SENSOR_DELAY_NORMAL);
        projectiles = new ArrayList<Projectile>();
        player = new Player(mContext.getApplicationContext());
        neutralYPos=getCurrentNeutralYPos();
        shieldImg = BitmapFactory.decodeResource(getResources(), R.drawable.shield);
    }
    //Constructor with attributes
    public ShootingInstructions(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        mContext = context;
        player = new Player(mContext.getApplicationContext());
        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mSensor, mSensorManager.SENSOR_DELAY_NORMAL);
        projectiles = new ArrayList<Projectile>();
        neutralYPos=getCurrentNeutralYPos();
        shieldImg = BitmapFactory.decodeResource(getResources(), R.drawable.shield);
    }
    //Drawing a view with a ship that shoots but does not move todo: get rid of movability and add ability to shoot
    public void onDraw(Canvas canvas)
    {
        shootingFrames++;
        if(shootingFrames > 30 && !player.getShield())
        {
            player.shoot(projectiles);
            shootingFrames = 0;
        }
        if(width == 0) {
            width=canvas.getWidth();
            height=canvas.getHeight();
            x = (width+player.getWidth())/2;
        }
        for(int i = 0; i < projectiles.size(); i++)
        {
            projectiles.get(i).move("y", -20);
            if(projectiles.get(i) == null)
            {
                break;
            }
            canvas.drawBitmap(projectiles.get(i).getImage(), projectiles.get(i).getX(), projectiles.get(i).getY(), p);
            if(projectiles.get(i).getY() > height+projectiles.get(i).getHeight() || projectiles.get(i).getY() > height+projectiles.get(i).getHeight())
            {
                projectiles.remove(i);
                break;
            }

        }
        canvas.drawBitmap(player.getImage(), (-x+width), height-player.getHeight(), p);
        if(player.getShield())
        {
            canvas.drawBitmap(shieldImg, player.getX()-25, player.getY()-shieldImg.getHeight()/2, p);
        }
        player.setLocation(-x+width,height-player.getHeight());

        invalidate();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        xPos = event.values[0];
        yPos = event.values[1];
        if (yPos <= neutralYPos)
        {
            player.setShield(false);
        }
        if (yPos > neutralYPos)
        {
            player.setShield(true);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

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