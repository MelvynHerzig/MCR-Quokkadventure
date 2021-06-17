package com.quokkadventure.command;

import com.quokkadventure.Vector2D;
import com.quokkadventure.actors.ActorOnTile;
import com.quokkadventure.actors.ActorType;
import com.quokkadventure.actors.Tableau;

/**
 * Commande servant à récupérer les pommes sur le terrain.
 *
 * @author Berney Alec
 * @author Ferrari Teo
 * @author Forestier Quentin
 * @author Herzig Melvyn
 * @author Janssens Emmanuel
 * @date 04/06/2021
 */
public class
CollectCommand extends ACommand
{

    private Vector2D pos;
    private Tableau tableau;
    ActorOnTile collectible;

    /**
     * Constructeur.
     *
     * @param pos Position à collecter.
     * @param tableau Tableau dans lequel il y a un potentiel collectible.
     */
    public CollectCommand(Vector2D pos, Tableau tableau)
    {
        this.pos = pos;
        this.tableau = tableau;
    }

    /**
     * Méthode appelée pour exécuter la commande.
     *
     * @return Retourne vrai si le processus s'est bien déroulé sinon faux.
     */
    @Override
    public boolean execute()
    {
        ActorOnTile actor = tableau.getActor(pos);
        if (actor.getType() == ActorType.COLLECTIBLE)
        {
            collectible = actor;
            tableau.getPlayer().setStrength(tableau.getPlayer().getStrength() + collectible.strengthGiven());
            tableau.removeActor(actor);
            return true;
        }
        return false;
    }

    /**
     * Annule la commande.
     */
    @Override
    public void undo()
    {
        tableau.getPlayer().setStrength(tableau.getPlayer().getStrength() - collectible.strengthGiven());
        tableau.addActor(collectible);
        tableau.addActorOnTile(pos, collectible);

    }
}
