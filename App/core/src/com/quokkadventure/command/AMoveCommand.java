package com.quokkadventure.command;

import com.quokkadventure.actors.ActorOnTile;
import com.quokkadventure.actors.Tableau;

/**
 * Commande servant déplacer un acteur (par déplacement ou bousculade)
 * @author Herzig Melvyn
 * @date 24/05/2021
 */
public abstract class AMoveCommand extends ACommand
{
   /**
    * Acteur déplacé
    */
   protected ActorOnTile movedActor;

   /**
    * Ancienne position x de actor.
    */
   protected int oldX;

   /**
    * Ancienne position y de actor.
    */
   protected int oldY;

   /**
    * Nouvelle position x de actor.
    */
   protected int newX;

   /**
    * Nouvelle position y de actor.
    */
   protected int newY;

   /**
    * Direction du déplacement.
    */
   protected MoveDirection direction;

   /**
    * Tableau dans lequel l'acteur se déplace.
    */
   protected Tableau tableau;

   /**
    * Constructeur.
    * @param movedActor Acteur à déplacer.
    * @param direction Direction du déplacement.
    * @param tableau Tableau dans lequel l'acteur tente un déplacement.
    */
   public AMoveCommand(ActorOnTile movedActor, MoveDirection direction, Tableau tableau)
   {
      this.movedActor = movedActor;
      this.direction = direction;
      this.tableau = tableau;
   }

   /**
    * Méthode appelée pour exécuter la commande.
    * @return Retourne vrai si le processus s'est bien déroulé sinon faux.
    */
   @Override
   public boolean execute()
   {
      // On ne déplace pas un acteur qui n'existe pas
      if(movedActor == null)
      {
         return false;
      }

      // Sauvegarde de l'ancienne position pour undo.
      oldX = movedActor.getPosX();
      oldY = movedActor.getPosY();

      // Détermination du décalage
      int offsetX;
      int offsetY;

      offsetX = (direction == MoveDirection.LEFT ? -1 : (direction == MoveDirection.RIGHT ? +1 : 0));
      offsetY = (direction == MoveDirection.DOWN ? -1 : (direction == MoveDirection.UP    ? +1 : 0));

      newX = oldX + offsetX;
      newY = oldY + offsetY;
      return false;
   }

   /**
    * Annule la commande.
    */
   @Override
   public void undo()
   {
      tableau.move(newX, newY, oldX, oldY,true);
   }
}
