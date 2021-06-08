package com.quokkadventure;

/**
 * Classe implémentant une vecteur 2d qui représente la position
 * des acteurs sur la carte
 *
 * @author Emmanuel Janssens
 * @date 03/06/2021
 */
public class Vector2D
{
    private int X = 0;
    private int Y = 0;

    public Vector2D(int x, int y)
    {
        setCoord(x, y);
    }

    void setCoord(int x, int y)
    {
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
        if (v.X == 0 || v.Y == 0)
            return null;
        return new Vector2D(this.X / v.X, this.Y / v.Y);

    }

    @Override
    public String toString()
    {
        return "(" + X + ";" + Y + ")";
    }

    public int getX()
    {
        return X;
    }

    public int getY()
    {
        return Y;
    }
}
