package com.quokkadventure.actors;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;


/**
 * Cette classe implémente un tableau.
 * Un tableau est essentiellement une TiledMap.
 * Cette classe s'occupe de charger la carte, vérifier son état,
 * l'afficher et la libérer une fois finie.
 * @author Herzig Melvyn
 * @date 25/05/2021
 */
public class Tableau extends Group
{
   /**
    * Nombre de tuiles en largeur.
    */
   private static int nbTileWidth = 25;

   /**
    * Nombre de tuiles en hauteur.
    */
   private static int nbTileHeight = 15;

   /**
    * Un tableau fais 25 * 15 tuiles. Ce tableau positionne les acteurs.
    * Cela inclut le quokka, les caisses et les murs.
    * (0,0) est considéré comme étant en bas à gauche.
    */
   private ActorOnTile[][] acotrsOnTile;

   /**
    * Ce tableau garde les points d'arrivées qui seront interrogé à chaque fin
    * de tick pour savoir si une caise de actorsOnTile les recouvre.
    */
   private final Array<End> ends;

   /**
    * Référence sur le joueur.
    */
   private Quokka player;

   /**
    * Compte le nombre de déplacement effecté par le joueur.
    * N'est jamais décérmenté même avec une annulation d'action.
    */
   private int movesCounter;

   /**
    * Numéro du niveau. Sert à la récupération de la carte tuilée.
    */
   private int levelNum;

   /**
    * Référence sur la carte chargée.
    */
   private TiledMap map;

   /**
    * Constructeur
    * @param levelNum Numéro de la carte à charger.
    * @param width Largeur du jeu
    * @param height Hauteur du jeu
    */
   public Tableau(int levelNum, int width, int height)
   {
      setSize(width, height);

      // Initialisation des tables.
      acotrsOnTile = new ActorOnTile[25][15];
      ends = new Array<>();

      // Chargement de la carte.
      map = new TmxMapLoader().load("Map/level" + levelNum +".tmx");

      // Récupérations des tuiles "spéciales"
      initMap();

      this.levelNum = levelNum;

      movesCounter = 0;
   }

   /**
    * Accesseur à la carte chargée.
    * @return Retourne la carte chargée.
    */
   public TiledMap loadMap()
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

                     Actor actor;
                     // En fonction de la valeur du type.
                     if (type.equals("start"))
                     {
                        player = new Quokka(x, y);
                        acotrsOnTile[x][y] = player; // Ajout du personnage en position x, y
                        addActor(player);
                     }
                     else if (type.equals("wall"))
                     {
                        Wall wall = new Wall(x, y);
                        acotrsOnTile[x][y] = wall; // Ajout d'un mur en position x,y
                        addActor(wall);
                     }
                     else if (type.equals("box"))
                     {
                        Box box = new Box(x, y);
                        acotrsOnTile[x][y] = box; // Ajout d'une boîte en position x,y
                        addActor(box);
                     }
                     else if (type.equals("end"))
                     {
                        End end = new End(x, y);
                        ends.add(end); // Ajout d'une case de fin
                        addActor(end);
                     }
                  }
               }
            }
         }
      }
   }

   /**
    * Récupère l'acteur en position x,y
    * @param x Position x.
    * @param y Position y.
    * @return Retourne l'acteur (peut-être null).
    * @throws  IndexOutOfBoundsException si x et y ne sont pas des numéros
    *          de tuiles valides.
    */
   public ActorOnTile getActor(int x, int y)
   {
      return acotrsOnTile[x][y];
   }

   /**
    * Accesseur au joueur.
    * @return Retourne le joueur.
    */
   public Quokka getPlayer()
   {
      return player;
   }

   /**
    * Déplace un actorOnTile.
    * @param oldX Position x (tuile) de l'acteur à déplacer.
    * @param oldY Position y (tuile) de l'acteur à déplacer.
    * @param newX Position x (tuile) de destination.
    * @param newY Position y (tuile) de destination.
    * @param isUndo Définit si le déplacement est une annulation.
    */
   public void move(int oldX, int oldY, int newX, int newY, boolean isUndo)
   {
      // Déplacement du personnage
      if(acotrsOnTile[oldX][oldY] != null)
      {
         acotrsOnTile[oldX][oldY].moveToPosition(newX, newY, this, isUndo);
      }

      // Mise à jour du tableau 2d
      acotrsOnTile[newX][newY] = acotrsOnTile [oldX][oldY];
      acotrsOnTile [oldX][oldY] = null;
   }

   /**
    * Vérifie si le niveau est résolu.
    * @return Retourne vrai si une boite se trouve sur chaque fin.
    */
   public boolean isSolved()
   {
      for(ActorOnTile aot : ends)
      {
         // Récupération du potientiel acteur sur l'arrivée
         ActorOnTile actorOnEnd = getActor(aot.getPosX(), aot.getPosY());

         // Si personne ou n'est pas une boîte
         if(actorOnEnd == null || actorOnEnd.getType() != ActorType.BOX)
         {
            return false;
         }
      }

      // Une boite est présente sur chaque arrivée.
      return true;
   }

   /**
    * Accesseur au nombre de mouvements effectués.
    * @return Retourne movesCounter.
    */
   public int getMovesCounter()
   {
      return movesCounter;
   }

   /**
    * Mutateur du nombre de coups. ++movesCounter
    * @return Retourne movesCounter.
    */
   public void addMovesCounter()
   {
      ++movesCounter;
   }

   /**
    * Mutateur du nombre de coups. --movesCounter
    * @return Retourne movesCounter.
    */
   public void subMovesCounter()
   {
      --movesCounter;
   }

}
