package com.quokkadventure.screens;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.quokkadventure.Assets;
import com.quokkadventure.QuokkAdventure;
import com.quokkadventure.actors.Tableau;
import com.quokkadventure.command.ACommand;
import com.quokkadventure.scene2d.DynamicCounter;
import com.quokkadventure.scene2d.LevelComplet;

import java.util.Stack;

public class LevelScreen extends AScreen{

    /**
     * Commande à exécuter.
     */
    protected ACommand toExecute;

    /**
     * Historique des commande déroulée. Pas de limite. Limiter le nombre de coups
     * pour réussir un niveau ?
     */
    protected Stack<ACommand> historic;

    /**
     * Tableau qui est joué.
     */
    protected final Tableau tableau;

    /**
     * Afficheur de la base de la map contenue dans tableau.
     */
    protected final OrthogonalTiledMapRenderer renderer;

    /**
     * Calque de la map dans tableau.
     */
    protected final TiledMapTileLayer mapLayer;



    /**
     * Définit si le jeu est en pause
     */
    boolean paused;

    /**
     * Overlay à afficher lorsque le niveau est completé.
     */
    LevelComplet endOverlay;


    public LevelScreen(int levelNumber) {
        super(Assets.manager.get(Assets.musicInGame));

        tableau = new Tableau(1, game.WIDTH, game.HEIGHT);
        renderer = new OrthogonalTiledMapRenderer(tableau.loadMap());
        mapLayer = (TiledMapTileLayer) renderer.getMap().getLayers().get("staticMap");
        endOverlay = new LevelComplet(this);

        toExecute = null;
        historic = new Stack<>();

        game.getStage().addActor(tableau);
        game.getStage().addActor(endOverlay);

        QuokkAdventure.Get().setCurrentLevelID(levelNumber);

        paused = false;
    }

    @Override
    public void render(float delta)
    {
        super.render(delta);
        game.getBatch().end();

        // Affichage de la base de la carte.
        renderer.setView(camera);
        renderer.getBatch().begin();
        renderer.renderTileLayer(mapLayer);
        renderer.getBatch().end();

        // du reste du stage
        game.getStage().draw();

    }

    /**
     * Initialise une commande a éxécuter.
     * @param command Commande a exécuter
     */
    public void setCommand(ACommand command)
    {
        toExecute = command;
    }

    /**
     * Annule la dernière commande exécutée.
     */
    public void undoCommand()
    {
        if(!historic.empty())
            historic.pop().undo();
    }
}
