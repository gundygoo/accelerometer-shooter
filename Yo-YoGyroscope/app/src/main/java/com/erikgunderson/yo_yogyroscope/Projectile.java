package com.erikgunderson.yo_yogyroscope;

import android.content.res.Resources;
import android.graphics.BitmapFactory;

/**
 * Created by Steven on 4/10/2015.
 */
public class Projectile extends Sprite {

    boolean hitEnemy;

    void Projectile(int x, int y, String name, boolean hitEnemy)
    {
        if(name == "bullet")
        {
            super.Sprite(x,y,10,42);
            super.setImage(BitmapFactory.decodeResource(Resources.getSystem(), R.mipmap.bullet));
        }
        this.hitEnemy = hitEnemy;
    }

}
