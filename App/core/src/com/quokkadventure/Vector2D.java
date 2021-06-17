package com.quokkadventure;

/**
 * Classe implémentant une vecteur 2D qui représente la position
 * des acteurs sur la carte.
 *
 * @author Berney Alec
 * @author Ferrari Teo
 * @author Forestier Quentin
 * @author Herzig Melvyn
 * @author Janssens Emmanuel
 * @date 03/06/2021
 */
public class Vector2D
{
    private int X = 0;
    private int Y = 0;

    /**
     * Constructeur.
     *
     * @param x Position x.
     * @param y Position y.
     */
    public Vector2D(int x, int y)
    {
        setCoord(x, y);
    }

    void setCoord(int x, int y)
    {
        this.X = x;
        this.Y = y;
    }

    /**
     * Somme de deux vecteurs.
     *
     * @param v Vecteur à sommer au premier fourni implicitement.
     * @return Le nouveau vecteur représentant la somme des 2.
     */
    public Vector2D add(Vector2D v)
    {
        return new Vector2D(this.X + v.X, this.Y + v.Y);
    }

    /**
     * Soustraction de deux vecteurs.
     *
     * @param v Vecteur à soustraire au premier fourni implicitement.
     * @return Le nouveau vecteur représentant la soustraction des 2.
     */
    public Vector2D substract(Vector2D v)
    {
        return new Vector2D(this.X - v.X, this.Y + v.Y);

    }

    /**
     * Multiplication de deux vecteurs.
     *
     * @param v Vecteur à multiplier au premier fourni implicitement.
     * @return Le nouveau vecteur représentant la multiplication des 2.
     */
    public Vector2D multiply(Vector2D v)
    {
        return new Vector2D(this.X * v.X, this.Y * v.Y);
    }

    /**
     * Division de deux vecteurs.
     *
     * @param v Vecteur à diviser au premier fourni implicitement.
     * @return Le nouveau vecteur représentant la division des 2.
     */
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

    /**
     * Accesseur à l'attribut X.
     *
     * @return La position X.
     */
    public int getX()
    {
        return X;
    }

    /**
     * Accesseur à l'attribut Y.
     *
     * @return La position Y.
     */
    public int getY()
    {
        return Y;
    }
}
