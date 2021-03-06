package com.quokkadventure.actors;

import com.quokkadventure.Assets;
import com.quokkadventure.Vector2D;

/**
 * Classe représentant un mur.
 * Est utilisé dans la liste des acteurs.
 *
 * @author Berney Alec
 * @author Ferrari Teo
 * @author Forestier Quentin
 * @author Herzig Melvyn
 * @author Janssens Emmanuel
 * @date 17/06/2021
 */
public class Wall extends ActorOnTile
{
   /**
    * Constructeur.
    * @param pos Position  du mur.
    */
   Wall(Vector2D pos)
   {
      super(pos,Assets.manager.get(Assets.textWall));
   }

   /**
    * Retourne le type de l'acteur.
    * @return Retourne ActorType.WALL.
    */
   @Override
   public ActorType getType()
   {
      return ActorType.WALL;
   }
}
