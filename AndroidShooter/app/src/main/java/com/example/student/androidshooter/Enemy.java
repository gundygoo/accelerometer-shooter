package com.example.student.androidshooter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by student on 4/24/2015.
 */
public class Enemy extends Sprite {

    private int health = 3;
    private Context context;
    public int framesSinceShooting = 0;

    Enemy(Context context, int x, int y)
    {
        super(context,x,y,82,82);
        super.setImage(BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy1));
        this.context = context;
    }

    public void shoot(ArrayList<Projectile> projectiles)
    {
        framesSinceShooting++;
        if(framesSinceShooting > new Random().nextInt(50)+50)
        {
            projectiles.add(new Projectile(context, getX(), getY(), "photon", false));
            projectiles.add(new Projectile(context, getX()+getWidth()-projectiles.get(0).getWidth(), getY(), "photon", false));
            framesSinceShooting = 0;
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
