package com.erikgunderson.yo_yogyroscope;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by Steven on 4/10/2015.
 */
public class Player extends Sprite{

    private boolean shield = false;
    private int score;
    private int health;
    private int lives;

    Player()
    {
        super(0, 0, 106, 130);
        super.setImage(BitmapFactory.decodeResource(Resources.getSystem(), R.mipmap.player));
        lives = 4;
        score = 0;
        health = 3;
    }

    public void shoot()
    {
        //TODO: create projectile
    }
    public void shield()
    {
        //TODO: turn on shield
    }
}
