package com.quokkadventure.actors;


import com.quokkadventure.Assets;
import com.quokkadventure.Vector2D;

/**
 * Classe qui simule une pomme ramassable augmentant la force du Quokka
 *
 * @author Forestier Quentin
 * @author Teo Ferrari
 * @date 04/06/2021
 */
public class Apple extends ActorOnTile implements Collectible
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
    public ActorType getType()
    {
        return ActorType.APPLE;
    }

    @Override
    public int strengthGiven() {
        return 1;
    }
}
