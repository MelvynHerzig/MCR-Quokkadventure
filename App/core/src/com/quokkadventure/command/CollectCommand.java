package com.quokkadventure.command;

import com.quokkadventure.Vector2D;
import com.quokkadventure.actors.ActorOnTile;
import com.quokkadventure.actors.ActorType;
import com.quokkadventure.actors.Apple;
import com.quokkadventure.actors.Tableau;

/**
 * Commande servant à récupérer les pommes sur le terrain
 *
 * @author Forestier Quentin
 * @date 04/06/2021
 */
public class CollectCommand extends ACommand
{

    private Vector2D pos;
    private Tableau tableau;

    /**
     * Constructeur
     *
     * @param pos     Position à collecter
     * @param tableau Tableau dans lequel il y a un potentiel collectible
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
        if (actor.getType() == ActorType.APPLE)
        {
            tableau.getPlayer().setStrength(tableau.getPlayer().getStrength() + 1);
            tableau.removeActor(actor);
            return true;
        }
        return false;
    }

    /**
     * Annule la commande
     */
    @Override
    public void undo()
    {
        tableau.getPlayer().setStrength(tableau.getPlayer().getStrength() - 1);
        Apple apple = new Apple(pos);
        tableau.addActor(apple);
        tableau.addActorOnTile(pos, apple);

    }
}
