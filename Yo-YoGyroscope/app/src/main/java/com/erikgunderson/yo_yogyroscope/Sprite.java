package com.erikgunderson.yo_yogyroscope;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by student on 4/10/2015.
 */
public class Sprite {

    private int x;
    private int y;
    private int width;
    private int height;
    private Bitmap image;

    void Sprite(int x, int y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    protected void setImage(Bitmap newImage)
    {
        this.image = newImage;
    }

    public Bitmap getImage()
    {
        return image;
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }
}
