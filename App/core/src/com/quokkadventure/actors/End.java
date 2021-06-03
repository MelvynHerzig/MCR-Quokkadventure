package com.quokkadventure.actors;

import com.badlogic.gdx.Gdx;
import com.quokkadventure.Assets;
import com.quokkadventure.QuokkAdventure;
import com.quokkadventure.Vector2D;

/**
 * Classe qui simule un emplacement final pour une boîte.
 * @author Herzig Melvyn
 * @date 15/05/2021
 */
public class End extends ActorOnTile
{
   /**
    * Constructeur.
    * @param pos Position
    */
   End(Vector2D pos)
   {
      super(pos,Assets.manager.get(Assets.textEnd));
   }

   /**
    * Méthode surchargée afin de ne pas pouvoir déplacer le mur.
    * @param pos Position  de la nouvelle position (ignorée)
    * @param tableau Tableau à mettre à jour (ignoré).
    * @param isUndo Définit si le déplacement est une annulation (ignoré).
    */
   @Override
   public void moveToPosition(Vector2D pos,Tableau tableau, boolean isUndo)
   { /* Surcharge. On ne déplace pas la fin.*/  }

   /**
    * Une fin ne peut être poussée
    * @param tableau Tableau dans lequel vérifier la progression (ignoré)
    * @param dest position  de destination (ignoré)
    * @return Retourne faux
    */
   @Override
   public boolean canBePushed(Tableau tableau, Vector2D dest)
   {
      return false;
   }

   /**
    * Une fin ne peut pas pousser.
    * @return Retourne false.
    */
   @Override
   public boolean canPush()
   {
      return false;
   }

   /**
    * Retourne le type de l'acteur.
    * @return Retourne ActorType.End
    */
   @Override
   public ActorType getType()
   {
      return ActorType.END;
   }
}
