package com.example.student.androidshooter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by Steven on 4/10/2015.
 */
public class Projectile extends Sprite {

    private boolean hitEnemy;
    private Context context;
    private int damage;

    Projectile(Context context, float x, float y, String name, boolean hitEnemy)
    {
        super(context,x,y);
        this.context = context;
        if(name == "bullet")
        {
            super.setImage(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.bullet), 10, 42, false));
            damage = 1;
        }
        if(name == "photon")
        {
            super.setImage(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.photon), 26, 26, false));
            damage = 1;
        }
        if(name == "missile")
        {
            super.setImage(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.missile), 42, 50, false));
            damage = 2;
        }
        if(name == "player missile")
        {
            super.setImage(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.playermissile), 42, 50, false));
            damage = 2;
        }
        this.hitEnemy = hitEnemy;
    }

    public boolean hitEnemy()
    {
        return hitEnemy;
    }

    public int getDamage()
    {
        return damage;
    }
}
