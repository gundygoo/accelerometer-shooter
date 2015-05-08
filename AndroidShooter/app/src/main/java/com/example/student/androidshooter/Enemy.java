package com.example.student.androidshooter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by student on 4/24/2015.
 */
public class Enemy extends Sprite {

    private int health = 3;
    public String name = "null";
    private String ammoType = "null";
    private Context context;
    public int framesSinceShooting = 0;
    int shootingRate = 50;

    Enemy(Context context, int x, int y, String name)
    {
        super(context,x,y);
        this.context = context;
        if(name == "orb")
        {
            super.setImage(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy1), 80, 80, false));
            this.name = name;
            health = 3;
            ammoType = "photon";
        }
        if(name == "torpedo")
        {
            super.setImage(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy2), 78, 95, false));
            this.name = name;
            health = 1;
            ammoType = "missile";
        }
    }

    public void shoot(ArrayList<Projectile> projectiles)
    {
        framesSinceShooting++;
        if(framesSinceShooting > shootingRate)
        {
            if(ammoType == "photon")
            {
                projectiles.add(new Projectile(context, getX(), getY()+getHeight()/2, ammoType, false));
                projectiles.add(new Projectile(context, getX()+getWidth()-projectiles.get(0).getWidth(), getY()+getHeight()/2, ammoType, false));
                framesSinceShooting = 0;
                shootingRate = new Random().nextInt(50)+50;
            }
            if(ammoType == "missile")
            {
                projectiles.add(new Projectile(context, getX(), getY()+getHeight()/2, ammoType, false));
                framesSinceShooting = 0;
                shootingRate = new Random().nextInt(100)+100;
            }
        }
    }

    public void setHealth(int health)
    {
        this.health = health;
    }

    public int getHealth()
    {
        return health;
    }
}
