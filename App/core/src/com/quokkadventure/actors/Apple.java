package com.quokkadventure.actors;


import com.quokkadventure.Assets;
import com.quokkadventure.Vector2D;

/**
 * Classe qui simule une pomme ramassable augmentant la force du Quokka
 *
 * @author Forestier Quentin
 * @date 04/06/2021
 */
public class Apple extends ActorOnTile
{

    /**
     * Constructeur.
     *
     * @param pos Position .
     */
    public Apple(Vector2D pos)
    {
        super(pos, Assets.manager.get(Assets.textApple));
    }

    @Override
    public boolean canBePushed(Tableau tableau, Vector2D to)
    {
        return false;
    }

    @Override
    public boolean canPush()
    {
        return false;
    }

    @Override
    public ActorType getType()
    {
        return ActorType.APPLE;
    }
}
