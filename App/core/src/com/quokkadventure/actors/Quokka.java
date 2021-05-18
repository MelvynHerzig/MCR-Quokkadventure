package com.quokkadventure.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.quokkadventure.Assets;

/**
 * Classe modélisant le quokka (joueur)
 * @author Herzig Melvyn
 * @date 15/05/2021
 */
public class Quokka extends ActorOnTile
{
   /**
    * Constructeur
    * @param posX Position de départ x.
    * @param posY Position de départ y.
    */
   public Quokka(int posX, int posY)
   {
      super(posX, posY, Assets.manager.get(Assets.textQuokka));
   }

   /**
    * Le joueur ne peut être poussé
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
    * Le joueur peut pousser d'autres éléments
    * @return Retourne faux.
    */
   @Override
   public boolean canPush()
   {
      return true;
   }

   /**
    * Retourne le type de l'acteur.
    * @return Retourne ActorType.QUOKKA
    */
   @Override
   public ActorType getType()
   {
      return ActorType.QUOKKA;
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
      super.moveToPosition(posX, posY, tableau, isUndo);

      if(isUndo)
      {
         tableau.subMovesCounter();
      }
      else
      {
         tableau.addMovesCounter();
      }
   }
}
