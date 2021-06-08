package com.quokkadventure.screens;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.quokkadventure.Assets;
import com.quokkadventure.QuokkAdventure;
import com.quokkadventure.actors.Tableau;
import com.quokkadventure.command.ACommand;
import com.quokkadventure.scene2d.LevelComplet;
import com.quokkadventure.scene2d.MoveHistoric;

public class LevelScreen extends AScreen
{

    /**
     * Commande à exécuter.
     */
    protected ACommand toExecute;

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

    /**
     * Historique des mouvements
     */
    protected MoveHistoric historic;

    public LevelScreen(int levelNumber)
    {
        super(Assets.manager.get(Assets.musicInGame));
        QuokkAdventure.Get().setCurrentLevelID(levelNumber);

        tableau = new Tableau(levelNumber, QuokkAdventure.WIDTH, QuokkAdventure.HEIGHT);
        renderer = new OrthogonalTiledMapRenderer(tableau.loadMap());
        mapLayer = (TiledMapTileLayer) renderer.getMap().getLayers().get("staticMap");
        endOverlay = new LevelComplet(this);

        toExecute = null;

        huds.addActor(endOverlay);

        paused = false;

        // Création de l'historique des déplacement
        historic = new MoveHistoric(this);
    }

    @Override
    protected void childRender(float delta)
    {
        // Affichage de la base de la carte.
        renderer.setView(camera);
        renderer.getBatch().begin();
        renderer.renderTileLayer(mapLayer);
        renderer.getBatch().end();

        game.getBatch().begin();
        tableau.draw(game.getBatch(), 1.f);
        game.getBatch().end();
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
     * Get the current leve move historic
     * @return
     */
    public MoveHistoric getHistoric()
    {
        return historic;
    }
}
