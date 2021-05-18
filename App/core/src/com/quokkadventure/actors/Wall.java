package com.quokkadventure.actors;

import com.badlogic.gdx.Gdx;
import com.quokkadventure.Assets;

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
      super(posX, posY, Assets.manager.get(Assets.textWall));
   }

   /**
    * Méthode surchargée afin de ne pas pouvoir déplacer le mur.
    * @param posX Position x de la nouvelle position (ignorée).
    * @param posY Position y de la nouvelle position (ignorée).
    * @param tableau Tableau à mettre à jour (ignoré).
    * @param isUndo Définit si le déplacement est une annulation (ignoré).
    */
   @Override
   public void moveToPosition(int posX, int posY, Tableau tableau, boolean isUndo)
   { /* Surcharge. On ne déplace pas un mur.*/  }

   /**
    * Un mur ne peut pas être poussé.
    * @param tableau Tableau dans lequel vérifier la progression (ignoré)
    * @param destX position X de destination (ignoré)
    * @param destY position Y de destination (ignoré)
    * @return Retourne faux
    */
   @Override
   public boolean canBePushed(Tableau tableau, int destX, int destY)
   {
      return false;
   }

   /**
    * Un mur ne peut pas se déplacer. Comment pourrait-il déplacer d'autres acteurs?
    * @return Retourne faux.
    */
   @Override
   public boolean canPush()
   {
      return false;
   }

   /**
    * Retourne le type de l'acteur.
    * @return Retourne ActorType.WALL
    */
   @Override
   public ActorType getType()
   {
      return ActorType.WALL;
   }
}
