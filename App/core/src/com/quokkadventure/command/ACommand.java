package com.quokkadventure.command;

/**
 * Classe abstraite d'une commande.
 * Une commande peut être exécuté ou annulée ( après exécution).
 *
 * @author Berney Alec
 * @author Ferrari Teo
 * @author Forestier Quentin
 * @author Herzig Melvyn
 * @author Janssens Emmanuel
 * @date 16/05/2021
 */
public abstract class ACommand
{
   /**
    * Exécute la commande.
    * @return Retourne vrai si l'exécution s'est déroulée correctement.
    */
   public abstract boolean execute();

   /**
    * Annule la commande.
    */
   public abstract void undo();
}
