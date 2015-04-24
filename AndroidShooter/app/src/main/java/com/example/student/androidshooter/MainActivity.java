package com.example.student.androidshooter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;


public class MainActivity extends Activity implements SensorEventListener {
    //Keeps track of all the different sensors we have
    private SensorManager mSensorManager;
    //A sensor which we will later register as an accelerometer sensor
    private Sensor mSensor;
    //Speed at which ball moves given by accelerometer data
    double xPos, yPos;
    //Base Y Position
    double neutralYPos = 0;
    //Player Object
    Player playerP;
    //Custom view
    MyDrawView myDrawing = null;
    //Size of screen
    int width = 0;
    int height;
    //Starting position and keeps track of where ball is currently
    float x=0;
    float y=0;
    //Size of ball
    int size;
    Paint p;
    Bitmap player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Setting up sensor to listen to accelerometer
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mSensor, mSensorManager.SENSOR_DELAY_NORMAL);
        //Gets the current display
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        //Gets the size of the screen in x and y values
        display.getSize(size);
        //Stores size of screen in width and height variables
        //width = size.x;
        //height = size.y;
        //Make the content view the custom view created above.
        myDrawing = new MyDrawView(this);
        setContentView(myDrawing);
        playerP = new Player();
        player = BitmapFactory.decodeResource(getResources(), R.mipmap.player);
        p = new Paint();


    }

    //Every time the sensor data changes updates the speed at which x and y are moved
    public void onSensorChanged(SensorEvent event) {

        xPos = event.values[0];
        yPos = event.values[1];

        /*if (x < xPos * 100) {
            x += 50;
        } else if (x > xPos * 100) {
            x -= 50;
        }
        /*if (yPos > neutralYPos)  //TODO: Set up neutralYPos calibration
        {
            player.shoot();
        }*/
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy){

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

    public class MyDrawView extends View {
        public MyDrawView(Context context) {
            super(context);
        }

        public void onDraw(Canvas canvas) {
            //Create a new paint color for the circle drawn later
            if(width == 0) {
                width=canvas.getWidth();
                height=canvas.getHeight();
                x = width/2;
                Log.i("Arrived", String.valueOf(x));
            }
            //Bitmap bmp = player.getImage();

            canvas.drawBitmap(player, (-x+width), height-250, p);
            //change log:
            //removed"-25+ from the width draw

            //draw a circle at the point designated based on accelerometer data and previous points with a specified size and a color P
            //canvas.drawCircle(x, y, size, p);

            //canvas.drawBitmap(player.getImage(), (float)yPos*100+width/2-25, height-100, p);
            //Move ball based on where ball is and accelerometer data
            x= (float) (xPos*2 + x);
            //y= (float) (yPos*2 + y);

            //Log.i("xPos", String.valueOf(xPos));
            //Log.i("yPos", String.valueOf(yPos));
            //Check for collision and move ball back into bounds if collision is is found
            if(topCollision(y))
            {
                y=size;
            }

            if(leftCollision(x))
            {
                x=size;
            }

            if(rightCollision(x))
            {
                x=width-size;
            }

            if(bottomCollision(y))
            {
                y = height-size;
            }
            invalidate();
        }
    }
    //Checks top of screen collision or if ball goes past
    private boolean topCollision(float y)
    {
        if(y-size <=0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    //Checks left of screen collision or if ball goes past
    private boolean leftCollision(float x)
    {
        if(x-size<=0)
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
        if(x>=width)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    //Checks bottom of screen collision or if ball goes past
    private boolean bottomCollision(float y)
    {
        if(y+size>=height)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
