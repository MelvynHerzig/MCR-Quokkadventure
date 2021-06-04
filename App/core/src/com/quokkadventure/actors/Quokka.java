package com.quokkadventure.actors;

import com.quokkadventure.Assets;
import com.quokkadventure.Vector2D;

/**
 * Classe modélisant le quokka (joueur)
 *
 * @author Herzig Melvyn, Forestier Quentin
 * @date 15/05/2021
 */
public class Quokka extends ActorOnTile
{
    private int strength = 1;

    /**
     * Constructeur
     *
     * @param pos Position de départ .
     */
    public Quokka(Vector2D pos)
    {
        super(pos, Assets.manager.get(Assets.textQuokka));
    }

    /**
     * Le joueur ne peut être poussé
     *
     * @param tableau Tableau dans lequel vérifier la progression (ignoré)
     * @param dest    position  de destination (ignoré)
     * @return Retourne faux
     */
    @Override
    public boolean canBePushed(Tableau tableau, Vector2D dest)
    {
        return false;
    }

    /**
     * Le joueur peut pousser d'autres éléments
     *
     * @return Retourne faux.
     */
    @Override
    public boolean canPush()
    {
        return true;
    }

    /**
     * Retourne le type de l'acteur.
     *
     * @return Retourne ActorType.QUOKKA
     */
    @Override
    public ActorType getType()
    {
        return ActorType.QUOKKA;
    }

    /**
     * Déplace l'acteur à la poisition posX, posY
     *
     * @param pos     Nouvelle position
     * @param tableau Tableau dans lequel l'acteur se déplace.
     * @param isUndo  Définit si le déplacement est une annulation.
     */
    public void moveToPosition(Vector2D pos, Tableau tableau, boolean isUndo)
    {
        super.moveToPosition(pos, tableau, isUndo);

        if (isUndo)
        {
            tableau.subMovesCounter();
        }
        else
        {
            tableau.addMovesCounter();
        }
    }

    /**
     * Récupère la force du Quokka
     *
     * @return Retourne la force
     */
    public int getStrength()
    {
        return strength;
    }

    /**
     * Défini la force du Quokka
     *
     * @param newStrength Nouvelle force
     */
    public void setStrength(int newStrength)
    {
        strength = newStrength;
    }
}
