package com.quokkadventure.screens;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.quokkadventure.Assets;
import com.quokkadventure.QuokkAdventure;
import com.quokkadventure.actors.Tableau;
import com.quokkadventure.command.ACommand;
import com.quokkadventure.command.AMoveCommand;
import com.quokkadventure.command.MoveCommand;
import com.quokkadventure.command.MoveDirection;
import com.quokkadventure.scene2d.ArrowPad;
import com.quokkadventure.scene2d.DynamicCounter;
import com.quokkadventure.scene2d.LevelComplet;

import java.util.Stack;

/**
 * Classe représentant l'écran de jeu.
 * @author Herzig Melvyn
 * @date 15/05/2021
 */
public class GameScreen extends AScreen
{
   /**
    * Tableau qui est joué.
    */
   private final Tableau tableau;

   /**
    * Afficheur de la base de la map contenue dans tableau.
    */
   private final OrthogonalTiledMapRenderer renderer;

   /**
    * Calque de la map dans tableau.
    */
   private final TiledMapTileLayer mapLayer;

   /**
    * Commande à exécuter.
    */
   private AMoveCommand toExecute;

   /**
    * Historique des commande déroulée. Pas de limite. Limiter le nombre de coups
    * pour réussir un niveau ?
    */
   private final Stack<AMoveCommand> historic;

   /**
    * Label dynamique des mouvements,
    */
   private final DynamicCounter stepsCounter;

   /**
    * Label dynamique des mouvements,
    */
   private final DynamicCounter timeCounter;

   /**
    * Temps écoulé.
    */
   private float elapsedTime;

   /**
    * Définit si le jeu est en pause
    */
   boolean paused;

   /**
    * Overlay à afficher lorsque le niveau est completé.
    */
   LevelComplet endOverlay;

   /**
    * Numéro du niveau à jouer.
    */
   int levelNumber;

   /**
    * Constructeur
    * @param game Référence sur la classe principale du jeu.
    */
   public GameScreen(QuokkAdventure game, int levelNumber)
   {
      super(game, Assets.manager.get(Assets.musicInGame));

      this.levelNumber = levelNumber;
      endOverlay = new LevelComplet(this, game);

      // Préparation de la carte.
      tableau = new Tableau(1, QuokkAdventure.WIDTH, QuokkAdventure.HEIGHT);
      renderer = new OrthogonalTiledMapRenderer(tableau.loadMap());
      mapLayer = (TiledMapTileLayer) renderer.getMap().getLayers().get("staticMap");

      toExecute = null;
      historic = new Stack<>();

      // Compteur de mouvements
      stepsCounter = new DynamicCounter(new TextureRegionDrawable(Assets.manager.get(Assets.textStepCounter)), 0, 0);

      // Compteur de temps (secondes)
      timeCounter = new DynamicCounter(new TextureRegionDrawable(Assets.manager.get(Assets.textTimeCounter)), 0, 100);

      // ajout des éléments au hud
      huds.addActor(new ArrowPad(this, tableau));
      huds.addActor(stepsCounter);
      huds.addActor(timeCounter);
      huds.addActor(endOverlay);

      // Instantiation temps
      elapsedTime = 0;

      // N'est pas en pause au début
      paused = false;
   }

   /**
    * Méthode appelée lorsque l'écran doit s'afficher.
    * @param delta Temps écoulé depuis le dernier appel.
    */
   public void childRender(float delta)
   {
      // Affichage de la carte.
      renderer.setView(camera);
      renderer.getBatch().begin();
      renderer.renderTileLayer(mapLayer);
      renderer.getBatch().end();
      game.getBatch().begin();
      tableau.draw(game.getBatch(), 1.f);
      game.getBatch().end();

      // Si en pause on n'effectue pas la mise à jour du temps
      // ni l'exécution de commandes
      if(paused) return;

      elapsedTime += delta;

      // Exécution de la commande.
      if(toExecute != null)
      {
         if( toExecute.execute())
         {
            historic.push(toExecute);
         }
         toExecute = null;
      }

      // Niveau fini ?
      if(tableau.isSolved())
      {
         // Affichage de l'overlay
         endOverlay.show();
      }

      stepsCounter.update(tableau.getMovesCounter());
      timeCounter.update((int) elapsedTime);

   }

   /**
    * Méthode appelé lorsque l'écran n'est plus utilisé.
    */
   @Override
   public void dispose()
   {
      super.dispose();
      tableau.loadMap().dispose();
      renderer.dispose();
   }

   /**
    * Met le jeu en pause/Enlève le jeu de la pause.
    */
   @Override
   public void pause()
   {
      super.pause();
      paused = !paused;
   }

   /**
    * Méthode de récupération des inputs du clavier.
    * @param keycode Code de la touche.
    * @return Retourne vrai.
    */
   @Override
   public boolean keyDown(int keycode)
   {
      if (keycode == Input.Keys.RIGHT || keycode == Input.Keys.D)
         setCommand( new MoveCommand(tableau.getPlayer(), MoveDirection.RIGHT, tableau) );
      if (keycode == Input.Keys.UP    || keycode == Input.Keys.W)
         setCommand( new MoveCommand(tableau.getPlayer(), MoveDirection.UP, tableau) );
      if (keycode == Input.Keys.LEFT  || keycode == Input.Keys.A)
         setCommand( new MoveCommand(tableau.getPlayer(), MoveDirection.LEFT, tableau) );
      if (keycode == Input.Keys.DOWN  || keycode == Input.Keys.S)
         setCommand( new MoveCommand(tableau.getPlayer(), MoveDirection.DOWN, tableau) );
      if (keycode == Input.Keys.U)
         undoCommand();

      return true;
   }

   /**
    * Initialise une commande a éxécuter.
    * @param command Commande a exécuter
    */
   public void setCommand(AMoveCommand command)
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

   /**
    * Accesseur du numéro du niveau joué.
    * @return Retourne le numéro du niveau joué.
    */
   public int getLevelNumber()
   {
      return levelNumber;
   }
}
