package com.quokkadventure.actors;

import com.quokkadventure.Assets;
import com.quokkadventure.Vector2D;

/**
 * Classe qui simule une boîte déplaçable par le quokka.
 * @author Herzig Melvyn
 * @date 15/05/2021
 */
public class Box extends ActorOnTile
{
   private final int weigth;
   boolean isOnEnd;
   /**
    * Constructeur
    * @param pos Position de la boîte
    */
   public Box(Vector2D pos, int weigth)
   {
      super(pos, weigth > 1 ? Assets.manager.get(Assets.textHeavyBox) : Assets.manager.get(Assets.textBox));

      this.weigth = weigth;

      isOnEnd = false;
   }

   /**
    * Une boîte peut être poussée.
    * @param tableau Tableau dans lequel vérifier la progression
    * @param dest position de destination
    * @return Retourne vrai si la destination est libre.
    */
   @Override
   public boolean canBePushed(Tableau tableau, Vector2D dest)
   {
      return tableau.getActor(dest) == null && tableau.getPlayer().getStrength() >= getWeigth();
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

   /**
    * Retourne le poids de la boîte
    * @return Poids de la boîte
    */
   public int getWeigth()
   {
      return weigth;
   }
}
