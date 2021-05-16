package com.quokkadventure.actors;

import com.badlogic.gdx.Gdx;
import com.quokkadventure.QuokkAdventure;

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
    * @param tableau Tableau à mettre à jour (ignoré).
    * @param isUndo Définit si le déplacement est une annulation (ignoré).
    */
   @Override
   public void moveToPosition(int posX, int posY, Tableau tableau, boolean isUndo)
   { /* Surcharge. On ne déplace pas la fin.*/  }

   /**
    * Une fin ne peut être poussée
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
