package com.erikgunderson.yo_yogyroscope;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by Steven on 4/10/2015.
 */
public class Sprite {

    private int x;
    private int y;
    private int width;
    private int height;
    private Bitmap image;

    Sprite(int x, int y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void move(String axis, int distance)
    {
        if(axis == "x" || axis == "X")
        {
            this.x += distance;
        }
        if(axis == "y" || axis == "Y")
        {
            this.y += distance;
        }
    }

    public void setLocation(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public boolean intersects(Sprite otherSprite)
    {
        //The left wall of this sprite is within the othersprite's x axis
        if(otherSprite.getX()+otherSprite.getWidth() >= this.getX() && this.getX() >= otherSprite.getX())
        {
            //The bottom of this sprite is within the y axis of the othersprite
            if(otherSprite.getY()+otherSprite.getHeight() >= this.getY() && this.getY() >= otherSprite.getY())
            {
                return true;
            }
            //The bottom of the other sprite is within the y axis of this sprite
            if(this.getY()+this.getHeight() >= otherSprite.getY() && otherSprite.getY() >= this.getY())
            {
                return true;
            }
        }
        //The right wall of this sprite is within the othersprite's x axis
        if(this.getX()+this.getWidth() >= otherSprite.getX() && otherSprite.getX() >= this.getX())
        {
            //The bottom of this sprite is within the y axis of the othersprite
            if(otherSprite.getY()+otherSprite.getHeight() >= this.getY() && this.getY() >= otherSprite.getY())
            {
                return true;
            }
            //The bottom of the other sprite is within the y axis of this sprite
            if(this.getY()+this.getHeight() >= otherSprite.getY() && otherSprite.getY() >= this.getY())
            {
                return true;
            }
        }
        return false;
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

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }
}
