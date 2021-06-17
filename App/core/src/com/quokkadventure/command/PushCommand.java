package com.quokkadventure.command;

import com.quokkadventure.actors.ActorOnTile;
import com.quokkadventure.actors.Tableau;

/**
 * Commande servant à pousser un acteur.
 *
 * @author Berney Alec
 * @author Ferrari Teo
 * @author Forestier Quentin
 * @author Herzig Melvyn
 * @author Janssens Emmanuel
 * @date 24/05/2021
 */
public class PushCommand extends AMoveCommand
{
   /**
    * Acteur déplaceur
    */
   private ActorOnTile pusher;

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
      this.pusher = pusher;
   }

   /**
    * Méthode appelée pour exécuter la commande.
    * @return Retourne vrai si le processus s'est bien déroulé sinon faux.
    */
   @Override
   public boolean execute()
   {
      super.execute();

      if(!pusher.canPush() || !movedActor.canBePushed(tableau,to))
         return false;

      tableau.move(from,to,false);

      return true;
   }
}
