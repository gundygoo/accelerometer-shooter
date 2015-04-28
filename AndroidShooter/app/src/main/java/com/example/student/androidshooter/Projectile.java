package com.example.student.androidshooter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;

/**
 * Created by Steven on 4/10/2015.
 */
public class Projectile extends Sprite {

    private boolean hitEnemy;
    private Context context;

    Projectile(Context context, float x, float y, String name, boolean hitEnemy)
    {
        super(context,x,y,10,42);
        this.context = context;
        if(name == "bullet")
        {
            super.setImage(BitmapFactory.decodeResource(context.getResources(), R.drawable.bullet));
        }
        if(name == "photon")
        {
            super.setImage(BitmapFactory.decodeResource(context.getResources(), R.drawable.photon));
        }
        this.hitEnemy = hitEnemy;
    }

    public boolean hitEnemy()
    {
        return hitEnemy;
    }

}
