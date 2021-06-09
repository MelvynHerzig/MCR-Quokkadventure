package com.quokkadventure.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.quokkadventure.Vector2D;

import java.util.Vector;

/**
 * Classe qui représente les éléments de la carte tuilée.
 * @author Herzig Melvyn
 * @author Teo Ferrari
 * @date 16/05/2021
 */
public abstract class  ActorOnTile extends Actor
{
   private static final int dimension = 64;

   Vector2D position;


   /**
    * Texture de l'acteur.
    */
   protected Texture img;

   /**
    * Constructeur.
    * @param pos Position .
    * @param texture Texture de l'acteur.
    */
   ActorOnTile(Vector2D pos, Texture texture)
   {
      position = new Vector2D(pos.getX(),pos.getY());

      setSize(dimension, dimension);
      setPosition(position.getX() * dimension, position.getY() * dimension);

      img = texture;
   }

   /**
    * Déplace l'acteur à la poisition posX, posY
    * @param pos Nouvelle position X
    * @param tableau Tableau dans lequel l'acteur se déplace.
    * @param isUndo Définit si le déplacement est une annulation.
    */
   public void moveToPosition(Vector2D pos,Tableau tableau, boolean isUndo)
   {
      position = new Vector2D(pos.getX(),pos.getY());;
      addAction(Actions.moveTo(position.getX() * 64, position.getY() * 64, 0f));
   }

   public Vector2D getPosition(){
      return new Vector2D(position.getX(), position.getY());
   }

   /**
    * Dessine l'acteur.
    * @param batch Batch à utiliser.
    * @param parentAlpha Canal alpha du parent.
    */
   @Override
   public void draw(Batch batch, float parentAlpha)
   {
      batch.draw(img, position.getX() * dimension, position.getY() * dimension,  dimension , dimension);
   }


   /**
    * Accesseur type de l'acteur.
    * @return Retourne le type de l'acteur.
    */
   public abstract ActorType getType();
}


