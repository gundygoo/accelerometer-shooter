package com.erikgunderson.yo_yogyroscope;

import android.content.res.Resources;
import android.graphics.BitmapFactory;

/**
 * Created by student on 4/24/2015.
 */
public class Enemy extends Sprite {

    Enemy(int x, int y)
    {
        super(x, y, 82, 82);
        super.setImage(BitmapFactory.decodeResource(Resources.getSystem(), R.mipmap.player));
    }
}
