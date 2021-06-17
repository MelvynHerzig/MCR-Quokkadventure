package com.quokkadventure.actors;

import com.quokkadventure.Assets;
import com.quokkadventure.Vector2D;

/**
 * Classe qui simule une pomme ramassable augmentant la force du Quokka.
 *
 * @author Berney Alec
 * @author Ferrari Teo
 * @author Forestier Quentin
 * @author Herzig Melvyn
 * @author Janssens Emmanuel
 * @date 04/06/2021
 */
public class Apple extends ActorOnTile
{

    /**
     * Constructeur.
     *
     * @param pos Position de la pomme.
     */
    public Apple(Vector2D pos)
    {
        super(pos, Assets.manager.get(Assets.textApple));
    }

    @Override
    public ActorType getType()
    {
        return ActorType.COLLECTIBLE;
    }

    @Override
    public int strengthGiven() {
        return 1;
    }
}
