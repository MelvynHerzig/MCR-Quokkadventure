package com.quokkadventure.actors;

import com.badlogic.gdx.Gdx;

/**
 * Classe qui simule une boîte déplaçable par le quokka.
 * @author Herzig Melvyn
 * @date 15/05/2021
 */
public class Box extends ActorOnTile
{
   /**
    * La boîte est elle sur une case d'arrivée ?
    */
   private boolean isOnEnd;

   /**
    * Constructeur
    * @param posX Position x de la boîte.
    * @param posY Position y de la boîte.
    */
   public Box(int posX, int posY)
   {
      super(posX, posY, Gdx.files.internal("Map/movable.png"));

      isOnEnd = false;
   }

   /**
    * Accesseur de isOnEnd()
    * @return Retourne isOnEnd().
    */
   public boolean isOnEnd()
   {
      return isOnEnd;
   }

   /**
    * Mutateur de isOnEnd()
    * @param onEnd Valeur qui est ajoutée à isOnEnd().
    */
   public void setOnEnd(boolean onEnd)
   {
      isOnEnd = onEnd;
   }
}
