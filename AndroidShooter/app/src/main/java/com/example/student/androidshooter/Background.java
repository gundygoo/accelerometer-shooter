package com.example.student.androidshooter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

/**
 * Created by student on 4/7/2015.
 */
//A view with a scrolling randomly created star background
public class Background extends View
{
    //Constructor without attributes
    public Background(Context mContext)
    {
        super(mContext);
    }
    //Constructor with attributes
    public Background(Context context, AttributeSet attrs) {super(context, attrs);}
    //Checks if stars have already been created
    private boolean appStarted=false;
    //Use this variable to manipulate number of stars created
    int numOfStars = 100;
    //Array of stars for background
    private Circle[] stars = new Circle[numOfStars];

    public void onDraw(Canvas canvas)
    {
        //Makes background black
        canvas.drawColor(Color.BLACK);
        if(!appStarted)
        {
            for (int i = 0; i < numOfStars; i++)
            {
                //Create star and random l location within canvas of size 10 put in stars array
                stars[i]= new Circle(randInt(5, canvas.getWidth()-5),randInt(5,canvas.getHeight()-5),(float)randInt(1,100)/25);
            }
            appStarted=true;
        }
        //Creates a white paint to color stars with
        Paint p = new Paint();
        p.setColor(Color.WHITE);
        for(int i =0; i<numOfStars; i++)
        {
            //Draw the stars at their current location
            canvas.drawCircle(stars[i].getX(),stars[i].getY(),stars[i].getRadius(),p);
            //Moves the stars bigger stars move faster
            stars[i].moveDownBasedOnSize();
            //If as star goes past the bottom of the screen move to top and to a random x value
            if(stars[i].getY()>canvas.getHeight()+5)
            {
                stars[i].setY(-5);
                stars[i].setX(randInt(5,canvas.getWidth()-5));
            }
        }
        invalidate();
    }
    private class Circle
    {
        private float x;
        private float y;
        private float radius;
        //Create a circle with a x,y location and a size
        public Circle(float x, float y, float radius)
        {
            this.x=x;
            this.y=y;
            this.radius=radius;
        }
        //Change the x location
        public void setX(int x)
        {
            this.x=x;
        }
        //Change the y location
        public void setY(int y)
        {
            this.y=y;
        }
        public float getX()
        {
            return x;
        }
        public float getY()
        {
            return y;
        }
        public float getRadius()
        {
            return radius;
        }
        //Move the y location down screen based on size
        public void moveDownBasedOnSize()
        {
            y=y+radius;
        }
    }
    //Retreive a random number between two numbers
    private static int randInt(int min, int max) {

        Random rand = new Random();

        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

}