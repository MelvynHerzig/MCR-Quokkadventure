package com.quokkadventure.actors;

import com.badlogic.gdx.Gdx;
import com.quokkadventure.Assets;
import com.quokkadventure.QuokkAdventure;

/**
 * Classe qui simule une boîte déplaçable par le quokka.
 * @author Herzig Melvyn
 * @date 15/05/2021
 */
public class Box extends ActorOnTile
{

   /**
    * Constructeur
    * @param posX Position x de la boîte.
    * @param posY Position y de la boîte.
    */
   public Box(int posX, int posY)
   {
      super(posX, posY, Assets.manager.get(Assets.textBox));
   }

   /**
    * Une boîte peut être poussée.
    * @param tableau Tableau dans lequel vérifier la progression
    * @param destX position X de destination
    * @param destY position Y de destination
    * @return Retourne vrai si la destination est libre.
    */
   @Override
   public boolean canBePushed(Tableau tableau, int destX, int destY)
   {
      return tableau.getActor(destX, destY) == null;
   }

   /**
    * Une boîte n'a pas de bras, elle ne peut rien pousser.
    * @return Retourne false.
    */
   @Override
   public boolean canPush()
   {
      return false;
   }

   /**
    * Retourne le type de l'acteur.
    * @return Retourne ActorType.Box
    */
   @Override
   public ActorType getType()
   {
      return ActorType.BOX;
   }
}
