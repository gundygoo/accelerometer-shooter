package com.example.student.mainmenu;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.Random;


public class MainMenu extends Activity {
    //boolean appStarted = false;
    //Circle[] stars = new Circle[20];
    Background b = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = new Background(this);
        setContentView(R.layout.activity_main_menu);
        //setContentView(b);
    }
    /*public class Background extends View
    {
        public Background(Context mContext)
        {
            super(mContext);
        }
        public Background(Context context, AttributeSet attrs) {super(context, attrs);}
        public void onDraw(Canvas canvas)
        {
            canvas.drawColor(Color.BLACK);
            if(!appStarted)
            {
                for (int i = 0; i < 20; i++)
                {
                    //Create star and random location within canvas of size 10 put in stars array
                    stars[i]= new Circle(randInt(5, canvas.getWidth()-5),randInt(5,canvas.getHeight()-5),5);
                }
                appStarted=true;
            }
            Paint p = new Paint();
            p.setColor(Color.YELLOW);
            for(int i =0; i<20; i++)
            {
                canvas.drawCircle(stars[i].getX(),stars[i].getY(),stars[i].getRadius(),p);
                stars[i].moveDown();
                if(stars[i].getY()>canvas.getHeight()+5)
                {
                    stars[i].setY(-5);
                }
            }
            invalidate();
        }

    }
    public class Circle
    {
        private int x;
        private int y;
        private int radius;

        public Circle(int x, int y, int radius)
        {
            this.x=x;
            this.y=y;
            this.radius=radius;
        }
        public void setX(int x)
        {
            this.x=x;
        }
        public void setY(int y)
        {
            this.y=y;
        }
        public int getX()
        {
            return x;
        }
        public int getY()
        {
            return y;
        }
        public int getRadius()
        {
            return radius;
        }
        public void moveDown()
        {
            y=y+5;
        }


    }
    public static int randInt(int min, int max) {

        Random rand = new Random();

        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }*/
}

