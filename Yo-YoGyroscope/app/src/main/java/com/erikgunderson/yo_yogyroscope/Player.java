package com.erikgunderson.yo_yogyroscope;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by student on 4/10/2015.
 */
public class Player extends Sprite{

    private int score;
    private int health;
    private int lives;
    private int x;
    private int y;
    private int width = 106;
    private int height = 130;
    private Bitmap image;

    void Player()
    {
        super.Sprite(0, 0, width, height);
        super.setImage(BitmapFactory.decodeResource(Resources.getSystem(), R.mipmap.player));
        lives = 4;
        score = 0;
        health = 3;
    }

    public void shoot()
    {
        //TODO: create projectile
    }
}
