package com.quokkadventure;

import java.util.Vector;

public class Vector2D {

    public int X=0;
    public int Y=0;

    public Vector2D(int x, int y)
    {
        setCoord(x,y);
    }

    void setCoord(int x, int y) {
        this.X = x;
        this.Y = y;
    }


    public Vector2D add(Vector2D v)
    {
        return new Vector2D(this.X + v.X, this.Y + v.Y);
    }
    public Vector2D substract(Vector2D v)
    {
        return new Vector2D(this.X - v.X, this.Y + v.Y);

    }
    public Vector2D multiply(Vector2D v)
    {
        return new Vector2D(this.X * v.X, this.Y * v.Y);
    }
    public Vector2D divide(Vector2D v)
    {
        if(v.X == 0 || v.Y == 0)
            return null;
        return new Vector2D(this.X / v.X, this.Y / v.Y);

    }

    @Override
    public String toString(){
        return "("+X+";"+Y +")";
    }
}
