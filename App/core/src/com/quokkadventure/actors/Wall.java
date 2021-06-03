package com.quokkadventure.actors;

import com.quokkadventure.Assets;
import com.quokkadventure.Vector2D;

/**
 * Classe représentant un mur.
 * Est utilisé dans la liste des acteurs
 */
public class Wall extends ActorOnTile
{
   /**
    * Constructeur.
    * @param pos Position  du mur
    */
   Wall(Vector2D pos)
   {
      super(pos,Assets.manager.get(Assets.textWall));
   }

   /**
    * Méthode surchargée afin de ne pas pouvoir déplacer le mur.
    * @param pos Position  de la nouvelle position (ignorée).
    * @param tableau Tableau à mettre à jour (ignoré).
    * @param isUndo Définit si le déplacement est une annulation (ignoré).
    */
   @Override
   public void moveToPosition(Vector2D pos, Tableau tableau, boolean isUndo)
   { /* Surcharge. On ne déplace pas un mur.*/  }

   /**
    * Un mur ne peut pas être poussé.
    * @param tableau Tableau dans lequel vérifier la progression (ignoré)
    * @param dest position de destination (ignoré)
    * @return Retourne faux
    */
   @Override
   public boolean canBePushed(Tableau tableau, Vector2D dest)
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
