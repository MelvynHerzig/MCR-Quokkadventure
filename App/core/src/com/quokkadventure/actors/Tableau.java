package com.quokkadventure.actors;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;
import com.quokkadventure.QuokkAdventure;

/**
 * Cette classe implémente un tableau.
 * Un tableau est essentiellement une TiledMap.
 * Cette classe s'occupe de charger la carte, vérifier son état,
 * l'afficher et la libérer une fois finie
 * @author Herzig Melvyn
 * @date 25/05/2021
 */
public class Tableau extends Group
{
   /**
    * Un tableau fais 25 * 15 tuiles. Ce tableau positionne les acteurs.
    * Cela inclut le quokka, les caisses, les arrivés et les murs.
    * (0,0) est considéré comme étant en bas à gauche.
    */
   private final Array<Array<ActorOnTile>> acotrsOnTile;

   /**
    * Compte le nombre de déplacement effecté par le joueur.
    * N'est jamais décérmenté même avec une annulation d'action.
    */
   int movesCounter;

   /**
    * Numéro du niveau. Sert à la récupération de la carte tuilée.
    */
   int levelNum;

   /**
    * Référence sur la carte chargée.
    */
   TiledMap map;

   /**
    * Constructeur
    * @param levelNum Numéro de la carte à charger.
    */
   public Tableau(int levelNum)
   {
      setSize(QuokkAdventure.WIDTH, QuokkAdventure.HEIGHT);

      // Dimensionnement des tableaux
      acotrsOnTile = new Array<>(25);
      for(Array<ActorOnTile> col : acotrsOnTile)
         col = new Array<>(15);

      // Chargement de la carte.
      map = new TmxMapLoader().load("Map/level" + levelNum +".tmx");

      // Récupérations des tuiles "spéciales"
      initMap();

      this.levelNum = levelNum;
   }

   /**
    * Accesseur à la carte chargée. Cette fonction doit être appelée
    * au moins une fois par le gameScreen pour dispose la carte.
    * @return Retourne la carte chargée.
    */
   TiledMap loadMap()
   {
      return map;
   }

   /**
    * Travaille sur la couche "specials" de la tiledMap.
    * Récupère est place les éléments dans actorsOnTile
    * @throws RuntimeException Si le layer "specials" n'est pas trouvé.
    */
   private void initMap()
   {
      TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get("specials");

      if (layer == null) throw new RuntimeException("Unknown map layer");

      // Pour chaque colonne
      for (int x = 0; x < 25; ++x)
      {
         // Pour chaque ligne
         for (int y = 0; y < 15; ++y)
         {
            // Récupération de la tuile.
            Cell cell = layer.getCell(x, y);
            if (cell != null)
            {
               TiledMapTile tile = cell.getTile();

               // Récupération de la propriété "type"
               if (tile.getProperties() != null)
               {
                  if (tile.getProperties().containsKey("type"))
                  {
                     String type = tile.getProperties().get("type").toString();

                     // En fonction de la valeur du type.

                     if (type.equals("start"))
                     {
                        // Todo créer le personnage
                     }
                     else if (type.equals("wall"))
                     {
                        acotrsOnTile.get(x).insert(y, new Wall(x, y)); // Ajout d'un mur en position x,y
                     }
                     else if (type.equals("box"))
                     {
                        acotrsOnTile.get(x).insert(y, new Box(x, y)); // Ajout d'une boîte en position x,y
                     }
                     else if (type.equals("end"))
                     {
                        acotrsOnTile.get(x).insert(y, new End(x, y)); // Ajout d'une case de fin en position x,y
                     }
                  }
               }
            }
         }
      }
   }

   @Override
   public void act(float delta)
   {
      super.act(delta);
   }
}
