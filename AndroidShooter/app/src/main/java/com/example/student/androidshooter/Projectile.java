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
            Bitmap b = BitmapFactory.decodeResource(context.getResources(), R.drawable.bullet);
            super.setImage(Bitmap.createScaledBitmap(b, 10, 42, false));
            damage = 1;
        }
        if(name == "photon")
        {
            Bitmap b = BitmapFactory.decodeResource(context.getResources(), R.drawable.photon);
            super.setImage(Bitmap.createScaledBitmap(b, 26, 26, false));
            damage = 1;
        }
        if(name == "missile")
        {
            Bitmap b = BitmapFactory.decodeResource(context.getResources(), R.drawable.missile);
            super.setImage(Bitmap.createScaledBitmap(b, 42, 50, false));
            damage = 2;
        }
        if(name == "player missile")
        {
            Bitmap b = BitmapFactory.decodeResource(context.getResources(), R.drawable.playermissile);
            super.setImage(Bitmap.createScaledBitmap(b, 42, 50, false));
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
