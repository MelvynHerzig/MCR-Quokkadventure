package com.quokkadventure.actors;

import com.quokkadventure.Vector2D;

/**
 * Interface représentant un acteur qui peut être poussé par un autre acteur
 * @author Teo Ferrari
 * @date 09/06/2021
 */
public interface Pushable {

    /**
     * Définit si l'acteur peut être poussé.
     * @param tableau Tableau sur lequel l'acteur serait poussé.
     * @param to Destination  (tuile)
     * @return Retourne vrai si l'acteur peut être poussé à la destination.
     */
    boolean canBePushed(Tableau tableau, Vector2D to);

    /**
     * Retourne le poids de l'acteur
     * @return Poids de l'acteur
     */
    int getWeigth();
}
