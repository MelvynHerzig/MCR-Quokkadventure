package com.quokkadventure.screens;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.quokkadventure.Assets;
import com.quokkadventure.command.AMoveCommand;
import com.quokkadventure.command.MoveCommand;
import com.quokkadventure.command.MoveDirection;
import com.quokkadventure.scene2d.ArrowPad;
import com.quokkadventure.scene2d.DynamicCounter;

/**
 * Classe représentant l'écran de jeu.
 * @author Herzig Melvyn
 * @date 15/05/2021
 */
public class GameScreen extends LevelScreen
{


   /**
    * Label dynamique des mouvements,
    */
   private final DynamicCounter stepsCounter;

   /**
    * Label dynamique des mouvements,
    */
   private final DynamicCounter timeCounter;

   /**
    * Flèches directionelles
    */
   private final ArrowPad arrows;

   /**
    * Temps écoulé.
    */
   private float elapsedTime;

   /**
    * vérifie si le niveau est complet
    */
   private boolean isSolved = false;

   /**
    * Constructeur
    *
    * @param levelNumber Le numéro du niveau
    */
   public GameScreen( int levelNumber)
   {
      super(levelNumber);

      // Compteur de mouvements
      stepsCounter = new DynamicCounter(new TextureRegionDrawable(Assets.manager.get(Assets.textStepCounter)), 0, 0);

      // Compteur de temps (secondes)
      timeCounter = new DynamicCounter(new TextureRegionDrawable(Assets.manager.get(Assets.textTimeCounter)), 0, 100);

      arrows = new ArrowPad( tableau);

      // ajout des éléments au hud
      huds.addActor(arrows);
      huds.addActor(stepsCounter);
      huds.addActor(timeCounter);
      huds.addActor(historic);

      // Instantiation temps
      elapsedTime = 0;

   }

   /**
    * Méthode appelée lorsque l'écran doit s'afficher.
    * @param delta Temps écoulé depuis le dernier appel.
    */
   public void childRender(float delta)
   {
      super.childRender(delta);
      elapsedTime += delta;

      // Exécution de la commande.
      if(toExecute != null)
      {
         if( toExecute.execute())
         {
            historic.addCommand((AMoveCommand) toExecute);
         }
         toExecute = null;
      }

      //verfiier a chaque fin de rendu
      isSolved = tableau.isSolved();

      // Niveau fini ?
      if(isSolved)
      {
         // Affichage de l'overlay
         endOverlay.show();

         arrows.setTouchable(Touchable.disabled);
         historic.setTouchable(Touchable.disabled);
      }

      if(!isSolved){
         stepsCounter.update(tableau.getMovesCounter());
         timeCounter.update((int) elapsedTime);
      }

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
    * Met le jeu en pause
    */
   @Override
   public void pause()
   {
      super.pause();
      paused = true;
   }

   /**
    * Enlève le jeu de la pause
    */
   @Override
   public void resume()
   {
      super.resume();
      paused = false;
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
    * Annule la dernière commande exécutée.
    */
   public void undoCommand()
   {
      historic.undo();
   }
}
