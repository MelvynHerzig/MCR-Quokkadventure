package com.quokkadventure.scene2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
   Button btnUp, btnDown, btnLeft, btnRight;

   /**
    * Tableau dans l'écran de jeu (screen)
    */
   Tableau tableau;

   /**
    * Constructeur.
    * @param screen Écran de jeu invocateur.
    * @param tableau Tableau associé.
    */
   public ArrowPad(GameScreen screen, Tableau tableau)
   {
      this.screen = screen;
      this.tableau = tableau;

      init();

      // Placement des éléments
      int buttonSize = 60;
      defaults().size(buttonSize);
      add(btnUp).colspan(2).center().padBottom((int) (buttonSize / 3));
      row();
      add(btnLeft).left();
      add(btnRight).right().padLeft((int)(buttonSize * 1.5));
      row();
      add(btnDown).colspan(2).center().padTop((int)(buttonSize / 3));

      setPosition(QuokkAdventure.WIDTH - (int) (3.5 * buttonSize) - 20,20);
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
