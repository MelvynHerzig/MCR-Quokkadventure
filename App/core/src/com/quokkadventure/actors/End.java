package com.quokkadventure.actors;

import com.quokkadventure.Assets;
import com.quokkadventure.Vector2D;

/**
 * Classe qui simule un emplacement final pour une boîte.
 *
 * @author Berney Alec
 * @author Ferrari Teo
 * @author Forestier Quentin
 * @author Herzig Melvyn
 * @author Janssens Emmanuel
 * @date 15/05/2021
 */
public class End extends ActorOnTile
{
   /**
    * Constructeur.
    * @param pos Position de la fin.
    */
   End(Vector2D pos)
   {
      super(pos,Assets.manager.get(Assets.textEnd));
   }

   /**
    * Retourne le type de l'acteur.
    * @return Retourne ActorType.End.
    */
   @Override
   public ActorType getType()
   {
      return ActorType.END;
   }
}
