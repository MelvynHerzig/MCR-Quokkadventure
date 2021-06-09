package com.quokkadventure.command;

import com.quokkadventure.actors.ActorOnTile;
import com.quokkadventure.actors.Pushable;
import com.quokkadventure.actors.Pusher;
import com.quokkadventure.actors.Tableau;

/**
 * Commande servant à pousser un acteur.
 * @author Herzig Melvyn
 * @author Teo Ferrari
 * @date 24/05/2021
 */
public class PushCommand extends AMoveCommand
{
   /**
    * Acteur déplaceur
    */
   private Pusher pusher;

   /**
    * Constructeur.
    * @param pusher Acteur qui déplace.
    * @param pushed Acteur déplacé.
    * @param direction Direction du déplacement.
    * @param tableau Tableau dans lequel l'acteur tente un déplacement.
    */
   public PushCommand(ActorOnTile pusher, ActorOnTile pushed, MoveDirection direction, Tableau tableau)
   {
      super(pushed, direction, tableau);
      this.pusher = (Pusher) pusher;
   }

   /**
    * Méthode appelée pour exécuter la commande.
    * @return Retourne vrai si le processus s'est bien déroulé sinon faux.
    */
   @Override
   public boolean execute()
   {
      super.execute();

      if(!pusher.canPush() || !((Pushable)movedActor).canBePushed(tableau,to))
         return false;

      tableau.move(from,to,false);

      return true;
   }
}
