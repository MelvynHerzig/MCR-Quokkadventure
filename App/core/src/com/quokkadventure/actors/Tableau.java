package com.quokkadventure.actors;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;
import com.quokkadventure.Vector2D;

/**
 * Cette classe implémente un tableau.
 * Un tableau est essentiellement une TiledMap.
 * Cette classe s'occupe de charger la carte, vérifier son état,
 * l'afficher et la libérer une fois finie.
 *
 * @author Herzig Melvyn, Forestier Quentin
 * @date 25/05/2021
 */
public class Tableau extends Group
{
    /**
     * Nombre de tuiles en largeur.
     */
    private static final int nbTileWidth = 25;

    /**
     * Nombre de tuiles en hauteur.
     */
    private static final int nbTileHeight = 15;

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
     * Référence sur la carte chargée.
     */
    private final TiledMap map;

    /**
     * Constructeur
     *
     * @param levelNum Numéro de la carte à charger.
     * @param width    Largeur du jeu
     * @param height   Hauteur du jeu
     */
    public Tableau(int levelNum, int width, int height)
    {
        setSize(width, height);

        // Initialisation des tables.
        acotrsOnTile = new ActorOnTile[nbTileWidth][nbTileHeight];
        ends = new Array<>();

        // Chargement de la carte.
        map = new TmxMapLoader().load("Map/level" + levelNum + ".tmx");

        // Récupérations des tuiles "spéciales"
        initMap();

        movesCounter = 0;
    }

    /**
     * Accesseur à la carte chargée.
     *
     * @return Retourne la carte chargée.
     */
    public TiledMap loadMap()
    {
        return map;
    }

    /**
     * Travaille sur la couche "specials" de la tiledMap.
     * Récupère est place les éléments dans actorsOnTile
     *
     * @throws RuntimeException Si le layer "specials" n'est pas trouvé.
     */
    private void initMap()
    {
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(
                "specials");

        if (layer == null) throw new RuntimeException("Unknown map layer");

        // Pour chaque colonne
        for (int x = 0; x < nbTileWidth; ++x)
        {
            // Pour chaque ligne
            for (int y = 0; y < nbTileHeight; ++y)
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
                            String type =
                                    tile.getProperties().get("type").toString();

                            Actor actor;
                            // En fonction de la valeur du type.
                            if (type.equals("start"))
                            {
                                player = new Quokka(new Vector2D(x, y));
                                acotrsOnTile[x][y] = player; // Ajout du
                                // personnage en position x, y
                                addActor(player);
                            }
                            else if (type.equals("wall"))
                            {
                                Wall wall = new Wall(new Vector2D(x, y));
                                acotrsOnTile[x][y] = wall; // Ajout d'un mur
                                // en position x,y
                                addActor(wall);
                            }
                            else if (type.equals("box"))
                            {
                                Box box = new Box(new Vector2D(x, y), 1);
                                acotrsOnTile[x][y] = box; // Ajout d'une
                                // boîte en position x,y
                                addActor(box);
                            }
                            else if (type.equals("heavyBox"))
                            {
                                Box box = new Box(new Vector2D(x, y), 2);
                                acotrsOnTile[x][y] = box; // Ajout d'une
                                // boîte lourde en position x,y
                                addActor(box);
                            }
                            else if (type.equals("apple"))
                            {
                                Apple apple = new Apple(new Vector2D(x, y));
                                acotrsOnTile[x][y] = apple; // Ajout d'une
                                // pomme en position x,y
                                addActor(apple);
                            }
                            else if (type.equals("end"))
                            {
                                End end = new End(new Vector2D(x, y));
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
     *
     * @param at position de l'acteur a récupérer
     * @return Retourne l'acteur (peut-être null).
     * @throws IndexOutOfBoundsException si x et y ne sont pas des numéros
     *                                   de tuiles valides.
     */
    public ActorOnTile getActor(Vector2D at)
    {
        return acotrsOnTile[at.getX()][at.getY()];
    }

    /**
     * Accesseur au joueur.
     *
     * @return Retourne le joueur.
     */
    public Quokka getPlayer()
    {
        return player;
    }

    /**
     * Déplace un actorOnTile.
     *
     * @param from   Position (tuile) de l'acteur à déplacer.
     * @param to     Position  (tuile) de destination.
     * @param isUndo Définit si le déplacement est une annulation.
     */
    public void move(Vector2D from, Vector2D to, boolean isUndo)
    {
        // Déplacement du personnage

        if (acotrsOnTile[from.getX()][from.getY()] != null)
        {
            acotrsOnTile[from.getX()][from.getY()].moveToPosition(to, this,
                    isUndo);
        }

        // Mise à jour du tableau 2d
        acotrsOnTile[to.getX()][to.getY()] =
                acotrsOnTile[from.getX()][from.getY()];
        acotrsOnTile[from.getX()][from.getY()] = null;
    }

    /**
     * Vérifie si le niveau est résolu.
     *
     * @return Retourne vrai si une boite se trouve sur chaque fin.
     */
    public boolean isSolved()
    {
        for (ActorOnTile aot : ends)
        {
            // Récupération du potientiel acteur sur l'arrivée
            ActorOnTile actorOnEnd = getActor(aot.getPosition());

            // Si personne ou n'est pas une boîte
            if (actorOnEnd == null || actorOnEnd.getType() != ActorType.BOX)
            {
                return false;
            }
        }

        // Une boite est présente sur chaque arrivée.
        return true;
    }

    /**
     * Accesseur au nombre de mouvements effectués.
     *
     * @return Retourne movesCounter.
     */
    public int getMovesCounter()
    {
        return movesCounter;
    }

    /**
     * Mutateur du nombre de coups. ++movesCounter
     *
     * @return Retourne movesCounter.
     */
    public void addMovesCounter()
    {
        ++movesCounter;
    }

    /**
     * Mutateur du nombre de coups. --movesCounter
     *
     * @return Retourne movesCounter.
     */
    public void subMovesCounter()
    {
        --movesCounter;
    }

    /**
     * Ajout un acteur sur le tableau
     *
     * @param pos   Position où l'acteur doit être ajouté
     * @param actor Acteur à ajouter
     */
    public void addActorOnTile(Vector2D pos, ActorOnTile actor)
    {
        acotrsOnTile[pos.getX()][pos.getY()] = actor;
    }
}
