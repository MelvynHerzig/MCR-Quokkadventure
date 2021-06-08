package com.quokkadventure.command;

import com.quokkadventure.actors.ActorOnTile;
import com.quokkadventure.actors.ActorType;
import com.quokkadventure.actors.Tableau;

/**
 * Commande servant à déplacer un acteur sur les tuiles.
 *
 * @author Herzig Melvyn, Forestier Quentin
 * @date 16/05/2021
 */
public class MoveCommand extends AMoveCommand
{

    /**
     * Parfois pour se déplacer on peut pousser un acteur.
     * Cela crée une nouvelle commande qui sera référencée ici.
     * Exploitation du pattern MacroCommand.
     */
    private ACommand otherCommand;

    /**
     * Constructeur.
     *
     * @param actor     Acteur à déplacer.
     * @param direction Direction du déplacement.
     * @param tableau   Tableau dans lequel l'acteur tente un déplacement.
     */
    public MoveCommand(ActorOnTile actor, MoveDirection direction,
                       Tableau tableau)
    {
        super(actor, direction, tableau);
        this.otherCommand = null;
    }

    /**
     * Méthode appelée pour exécuter la commande.
     *
     * @return Retourne vrai si le processus s'est bien déroulé sinon faux.
     */
    @Override
    public boolean execute()
    {
        super.execute();

        // Récupération de l'acteur sur la destination
        ActorOnTile actorOnDestination = tableau.getActor(to);

        // On tente de déplacer le potentiel acteur sur la destination.
        PushCommand pushCommand = new PushCommand(movedActor,
                actorOnDestination, direction, tableau);

        // On tente de récupérer une pomme sur la destination
        CollectCommand collectCommand = new CollectCommand(to, tableau);

        // Si case vide ou  peut on pousser l'obstacle?
        if (actorOnDestination == null || pushCommand.execute() || collectCommand.execute())
        {
            // Si on est ici et que la destination n'était pas nul, on l'a
            // déplacé.
            if (actorOnDestination != null && actorOnDestination.getType() == ActorType.BOX)
            {
                otherCommand = pushCommand;
            }
            else if (actorOnDestination != null && actorOnDestination.getType() == ActorType.APPLE)
            {
                otherCommand = collectCommand;
            }

            tableau.move(from, to, false);
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
        super.undo();

        if (otherCommand != null)
            otherCommand.undo();
    }

    @Override
    public String toString()
    {
        switch (direction)
        {
            case LEFT:
                return "left";
            case RIGHT:
                return "right";
            case UP:
                return "up";
            case DOWN:
                return "down";
        }
        return "unknown";
    }

    public ACommand subCommand()
    {
        return otherCommand;
    }
}
