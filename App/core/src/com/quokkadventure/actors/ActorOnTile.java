package com.quokkadventure.actors;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

/**
 * Classe qui représente les éléments de la carte tuilée.
 * @author Herzig Melvyn
 * @date 16/05/2021
 */
public abstract class  ActorOnTile extends Actor
{
   private static final int dimension = 64;

   /**
    * Position x (tuile) de l'acteur.
    */
   protected int x;

   /**
    * Position y (tuile) de l'acteur.
    */
   protected int y;

   /**
    * Texture de l'acteur.
    */
   protected Texture img;

   /**
    * Constructeur.
    * @param posX Position x.
    * @param posY Position y.
    * @param textLoc Texture de l'acteur.
    */
   ActorOnTile(int posX, int posY, FileHandle textLoc)
   {
      x = posX;
      y = posY;

      setSize(dimension, dimension);
      setPosition(x * dimension, y * dimension);

      img = new Texture(textLoc);
   }

   /**
    * Déplace l'acteur à la poisition posX, posY
    * @param posX Nouvelle position X.
    * @param posY Nouvelle position Y.
    * @param tableau Tableau dans lequel l'acteur se déplace.
    * @param isUndo Définit si le déplacement est une annulation.
    */
   public void moveToPosition(int posX, int posY, Tableau tableau, boolean isUndo)
   {
      x = posX;
      y = posY;

      addAction(Actions.moveTo(x * 64, y * 64, 0f));
   }

   /**
    * Accesseur position x.
    * @return Retourne la position x (tuile) de l'acteur.
    */
   public int getPosX()
   {
      return x;
   }

   /**
    * Accesseur position y.
    * @return Retourne la position y (tuile) de l'acteur.
    */
   public int getPosY()
   {
      return y;
   }

   /**
    * Libère l'image
    */
   public void dispose()
   {
      img.dispose();
   }

   /**
    * Dessine l'acteur.
    * @param batch Batch à utiliser.
    * @param parentAlpha Canal alpha du parent.
    */
   @Override
   public void draw(Batch batch, float parentAlpha)
   {
      batch.draw(img, x * dimension, y * dimension,  dimension , dimension);
   }

   /**
    * Définit si l'acteur peut être poussé.
    * @param tableau Tableau sur lequel l'acteur serait poussé.
    * @param destX Destination x (tuile).
    * @param destY Destination y (tuile).
    * @return Retourne vrai si l'acteur peut être poussé à la destination.
    */
   public abstract boolean canBePushed(Tableau tableau, int destX, int destY);

   /**
    * Définit si l'acteur peut pousser.
    * @return Retourne vrai si l'acteur peut en pousser d'autres.
    */
   public abstract boolean canPush();

   /**
    * Accesseur type de l'acteur.
    * @return Retourne le type de l'acteur.
    */
   public abstract ActorType getType();
}


