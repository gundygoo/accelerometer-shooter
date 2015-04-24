package com.example.student.androidshooter;

import android.content.res.Resources;
import android.graphics.BitmapFactory;

/**
 * Created by Steven on 4/10/2015.
 */
public class Projectile extends Sprite {

    boolean hitEnemy;

    Projectile(int x, int y, String name, boolean hitEnemy)
    {
        super(x,y,10,42);
        if(name == "bullet")
        {
            super.setImage(BitmapFactory.decodeResource(Resources.getSystem(), R.mipmap.bullet));
        }
        this.hitEnemy = hitEnemy;
    }

}
