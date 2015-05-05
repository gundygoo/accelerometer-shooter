package com.example.student.androidshooter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;

/**
 * Created by Steven on 4/10/2015.
 */
public class Player extends Sprite{

    private boolean shield = false;
    private boolean missileUpgrade = false;
    private int score = 0;
    private int health = 3;
    private int lives = 5;
    private Context context;

    Player(Context context)
    {
        super(context, 0, 0);
        this.context = context;
        //THE LINE OF CODE BELOW IS FIXED!!!!!!!!!!!
        Bitmap b = BitmapFactory.decodeResource(context.getResources(), R.drawable.player);
        super.setImage(Bitmap.createScaledBitmap(b, 106, 130, false));
        lives = 5;
        score = 0;
        health = 3;
    }

    public void shoot(ArrayList<Projectile> projectiles)
    {
        if(missileUpgrade)
        {
            projectiles.add(new Projectile(context, getX(), getY(), "player missile", true));
            projectiles.add(new Projectile(context, getX()+getWidth()-projectiles.get(0).getWidth(), getY(), "player missile", true));
        }
        else
        {
            projectiles.add(new Projectile(context, getX(), getY(), "bullet", true));
            projectiles.add(new Projectile(context, getX()+getWidth()-projectiles.get(0).getWidth(), getY(), "bullet", true));
        }
    }

    public void setMissileUpgrade(boolean setting)
    {
        missileUpgrade = setting;
    }

    public void setLives(int lives)
    {
        this.lives = lives;
    }

    public int getLives()
    {
        return lives;
    }

    public void setShield(boolean shield)
    {
        this.shield = shield;
    }

    public boolean getShield()
    {
        return shield;
    }

    public void setScore(int score)
    {
        this.score = score;
    }

    public int getScore()
    {
        return score;
    }
}
