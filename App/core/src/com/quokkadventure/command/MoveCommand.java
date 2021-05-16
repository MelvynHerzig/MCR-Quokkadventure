package com.quokkadventure.command;

import com.quokkadventure.actors.ActorOnTile;
import com.quokkadventure.actors.Tableau;

/**
 * Command servant à déplacer un acteur sur les tuiles.
 * @author Herzig Melvyn
 * @date 16/05/2021
 */
public class MoveCommand extends ACommand
{
   /**
    * Acteur déplacé
    */
   private ActorOnTile actor;

   /**
    * Ancienne position x de actor.
    */
   private int oldX;

   /**
    * Ancienne position y de actor.
    */
   private int oldY;

   /**
    * Nouvelle position x de actor.
    */
   private int newX;

   /**
    * Nouvelle position y de actor.
    */
   private int newY;

   /**
    * Direction du déplacement.
    */
   private MoveDirection direction;

   /**
    * Tableau dans lequel l'acteur se déplace.
    */
   private Tableau tableau;

   /**
    * Parfois pour se déplacer on peut pousser un acteur.
    * Cela crée une nouvelle commande qui sera référencée ici.
    * Exploitation du pattern MacroCommand.
    */
   private MoveCommand otherMovedCommand;

   /**
    * Constructeur.
    * @param actor Acteur à déplacer.
    * @param direction Direction du déplacement.
    * @param tableau Tableau dans lequel l'acteur tente un déplacement.
    */
   public MoveCommand(ActorOnTile actor, MoveDirection direction, Tableau tableau)
   {
      this.actor = actor;
      this.direction = direction;
      this.tableau = tableau;
      this.otherMovedCommand = null;
   }

   /**
    * Méthode appelée pour exécuter la commande.
    * @return Retourne vrai si le processus s'est bien déroulé sinon faux.
    */
   @Override
   public boolean execute()
   {
      // Sauvegarde de l'ancienne position pour undo.
      oldX = actor.getPosX();
      oldY = actor.getPosY();

      // Détermination du décalage
      int offsetX;
      int offsetY;

      offsetX = (direction == MoveDirection.LEFT ? -1 : (direction == MoveDirection.RIGHT ? +1 : 0));
      offsetY = (direction == MoveDirection.DOWN ? -1 : (direction == MoveDirection.UP    ? +1 : 0));

      newX = oldX + offsetX;
      newY = oldY + offsetY;

      // Récupération de l'acteur sur la destination
      ActorOnTile actorOnDestination = tableau.getActor(newX, newY);

      // Si case vide, départ.
      if(actorOnDestination == null)
      {
         tableau.move(oldX, oldY, newX,  newY, false);
         return true;
      }
      // Sinon , peut on pousser l'obstacle ?
      else if(actor.canPush() && actorOnDestination.canBePushed(tableau,
                                       actorOnDestination.getPosX() + offsetX,
                                       actorOnDestination.getPosY() + offsetY))
      {
         otherMovedCommand = new MoveCommand(actorOnDestination, direction, tableau);
         otherMovedCommand.execute();
         tableau.move(oldX, oldY, newX,  newY, false);
         return true;
      }

      return false; // L'acteur n'a pas été déplacé.
   }

   /**
    * Annule la commande.
    */
   @Override
   public void undo()
   {
      tableau.move(newX, newY, oldX, oldY,true);

      if(otherMovedCommand != null)
         otherMovedCommand.undo();
   }
}
