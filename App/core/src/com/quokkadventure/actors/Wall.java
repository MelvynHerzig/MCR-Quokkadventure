package com.quokkadventure.actors;

import com.badlogic.gdx.Gdx;

/**
 * Classe représentant un mur.
 * Est utilisé dans la liste des acteurs
 */
public class Wall extends ActorOnTile
{
   /**
    * Constructeur.
    * @param posX Position x du mur.
    * @param posY Position y du mur.
    */
   Wall(int posX, int posY)
   {
      super(posX, posY, Gdx.files.internal("Map/border.png"));
   }

   /**
    * Méthode surchargée afin de ne pas pouvoir déplacer le mur.
    * @param posX Position x de la nouvelle position (ignorée).
    * @param posY Position y de la nouvelle position (ignorée).
    */
   @Override
   public void moveToPosition(int posX, int posY)
   { /* Surcharge. On ne déplace pas un mur.*/  }
}
