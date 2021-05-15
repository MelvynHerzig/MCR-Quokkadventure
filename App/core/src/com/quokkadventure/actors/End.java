package com.quokkadventure.actors;

import com.badlogic.gdx.Gdx;

/**
 * Classe qui simule un emplacement final pour une boîte.
 * @author Herzig Melvyn
 * @date 15/05/2021
 */
public class End extends ActorOnTile
{
   /**
    * Constructeur.
    * @param posX Position x.
    * @param posY Position y.
    */
   End(int posX, int posY)
   {
      super(posX, posY, Gdx.files.internal("Map/end.png"));
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
