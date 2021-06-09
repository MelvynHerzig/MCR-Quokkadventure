package com.quokkadventure.actors;

/**
 * Interface représentant un acteur qui peut être collecté par un autre acteur
 * @author Teo Ferrari
 * @date 09/06/2021
 */
public interface Collectible {

    /**
     * Définit la force donnée par cet acteur à l'acteur qui l'a collecté
     * @return force donnée
     */
    int strengthGiven();
}
