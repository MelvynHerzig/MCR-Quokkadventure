package com.quokkadventure.scene2d;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.quokkadventure.Assets;
import com.quokkadventure.QuokkAdventure;
import com.quokkadventure.actors.Tableau;
import com.quokkadventure.command.MoveCommand;
import com.quokkadventure.command.MoveDirection;
import com.quokkadventure.screens.GameScreen;
import com.quokkadventure.screens.listener.NoisyClickListener;

/**
 * Scène ajoutant un pad avec des flèches directionnelles
 * @author Herzig Melvyn
 * @date 16/05/2021
 */
public class ArrowPad extends Table
{
   /**
    * Écran de jeu qui a invoqué cette scène
    */
   GameScreen screen;

   /**
    * Liste des boutons qui y sont affichés.
    */
   Button btnUp, btnDown, btnLeft, btnRight, btnUndo;

   /**
    * Tableau dans l'écran de jeu (screen)
    */
   Tableau tableau;

   /**
    * Constructeur.
    * @param tableau Tableau associé.
    */
   public ArrowPad(Tableau tableau)
   {
      this.tableau = tableau;

      if((QuokkAdventure.Get().getCurrentScreen() instanceof GameScreen))
         screen = (GameScreen) QuokkAdventure.Get().getCurrentScreen();

      init();

      // Placement des éléments
      int buttonSize = 60;
      defaults().size(buttonSize);
      add(btnUp).colspan(3).center().padBottom((int) (buttonSize / 3));
      row();
      add(btnLeft).left().padRight(10);
      add(btnUndo).center();
      add(btnRight).right().padLeft(20);
      row();
      add(btnDown).colspan(3).center().padTop((int)(buttonSize / 3));

      setPosition(QuokkAdventure.Get().WIDTH - (int) (3.5 * buttonSize) - 20,20);
      pack();

      getColor().a = .8f;
   }

   /**
    * Création des boutons.
    */
   private void init()
   {

      btnUp = new Button(new TextureRegionDrawable(Assets.manager.get(Assets.textBtnUp)));
      btnUp.addListener(new NoisyClickListener()
      {
         @Override
         public void clicked(InputEvent event, float x, float y)
         {
            super.clicked(event, x, y);
            screen.setCommand(new MoveCommand(tableau.getPlayer(), MoveDirection.UP, tableau));
         }
      });

      btnDown = new Button(new TextureRegionDrawable(Assets.manager.get(Assets.textBtnDown)));
      btnDown.addListener(new NoisyClickListener()
      {
         @Override
         public void clicked(InputEvent event, float x, float y)
         {
            super.clicked(event, x, y);
            screen.setCommand(new MoveCommand(tableau.getPlayer(), MoveDirection.DOWN, tableau));
         }
      });

      btnUndo = new Button(new TextureRegionDrawable(Assets.manager.get(Assets.textBtnUndo)));
      btnUndo.addListener(new NoisyClickListener()
      {
         @Override
         public void clicked(InputEvent event, float x, float y)
         {
            super.clicked(event, x, y);
            screen.undoCommand();
         }
      });

      btnLeft = new Button(new TextureRegionDrawable(Assets.manager.get(Assets.textBtnLeft)));
      btnLeft.addListener(new NoisyClickListener()
      {
         @Override
         public void clicked(InputEvent event, float x, float y)
         {
            super.clicked(event, x, y);
            screen.setCommand(new MoveCommand(tableau.getPlayer(), MoveDirection.LEFT, tableau));
         }
      });

      btnRight = new Button(new TextureRegionDrawable(Assets.manager.get(Assets.textBtnRight)));
      btnRight.addListener(new NoisyClickListener()
      {
         @Override
         public void clicked(InputEvent event, float x, float y)
         {
            super.clicked(event, x, y);
            screen.setCommand(new MoveCommand(tableau.getPlayer(), MoveDirection.RIGHT, tableau));
         }
      });

   }
}
