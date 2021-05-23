package com.quokkadventure.screens;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Timer;
import com.quokkadventure.Assets;
import com.quokkadventure.QuokkAdventure;
import com.quokkadventure.actors.Tableau;
import com.quokkadventure.command.ACommand;
import com.quokkadventure.command.MoveCommand;
import com.quokkadventure.command.MoveDirection;
import com.quokkadventure.scene2d.ArrowPad;
import com.quokkadventure.scene2d.DynamicCounter;

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
   private ACommand toExecute;

   /**
    * Historique des commande déroulée. Pas de limite. Limiter le nombre de coups
    * pour réussir un niveau ?
    */
   private final Stack<ACommand> historic;

   /**
    * Label dynamique des mouvements,
    */
   private DynamicCounter stepsCounter;

   /**
    * Label dynamique des mouvements,
    */
   private DynamicCounter timeCounter;

   /**
    * Temps écoulé.
    */
   private float elapsedTime;

   /**
    * Constructeur
    * @param game Référence sur la classe principale du jeu.
    */
   public GameScreen(QuokkAdventure game)
   {
      super(game, Assets.manager.get(Assets.musicInGame));

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

      game.getStage().addActor(tableau);
      game.getStage().addActor(new ArrowPad(this, tableau));
      game.getStage().addActor(stepsCounter);
      game.getStage().addActor(timeCounter);

      // Instantiation temps
      elapsedTime = 0;
   }

   /**
    * Méthode appelée lorsque l'écran doit s'afficher.
    * @param delta Temps écoulé depuis le dernier appel.
    */
   @Override
   public void render(float delta)
   {
      elapsedTime += delta;

      super.render(delta);
      game.getBatch().end();

      // Exécution de la commande.
      if(toExecute != null)
      {
        if( toExecute.execute())
        {
           historic.push(toExecute);
        }
        toExecute = null;
      }

      // Affichage de la base de la carte.
      renderer.setView(camera);
      renderer.getBatch().begin();
      renderer.renderTileLayer(mapLayer);
      renderer.getBatch().end();

      // du reste du stage
      game.getStage().draw();

      // Niveau fini ?
      if(tableau.isSolved())
      {
         // TODO Afficher un vrai écran digne de ce nom.
         System.out.println("Level solved!");
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
      {
         if(!historic.empty())
            historic.pop().undo();
      }

      return true;
   }

   /**
    * Initialise une commande a éxécuter.
    * @param command Commande a exécuter
    */
   public void setCommand(ACommand command)
   {
      toExecute = command;
   }
}
