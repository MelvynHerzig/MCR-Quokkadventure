package com.quokkadventure.actors;

/**
 * Interface représentant un acteur qui peut pousser un autre acteur
 * @author Teo Ferrari
 * @date 09/06/2021
 */
public interface Pusher {

    /**
     * Définit si l'acteur peut pousser.
     * @return Retourne vrai si l'acteur peut en pousser d'autres.
     */
    boolean canPush();

    /**
     * Récupère la force de l'acteur
     *
     * @return Retourne la force
     */
    int getStrength();

    /**
     * Défini la force de l'acteur
     *
     * @param newStrength Nouvelle force
     */
    void setStrength(int newStrength);
}
