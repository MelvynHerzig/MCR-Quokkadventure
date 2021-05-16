package com.quokkadventure.scene2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
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
   GameScreen screen;

   Button btnUp, btnDown, btnLeft, btnRight;
   Drawable btnUpTexture, btnDownTexture, btnLeftTexture, btnRightTexture;

   Tableau tableau;

   public ArrowPad(GameScreen screen, Tableau tableau)
   {
      this.screen = screen;
      this.tableau = tableau;

      getColor().a = .6f;
      init();

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
   }

   private void init()
   {
      btnUpTexture = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("UI/upArrow.png"))));
      btnUp = new Button(btnUpTexture);
      btnUp.addListener(new NoisyClickListener()
      {
         @Override
         public void clicked(InputEvent event, float x, float y)
         {
            super.clicked(event, x, y);
            screen.setCommand(new MoveCommand(tableau.getPlayer(), MoveDirection.UP, tableau));
         }
      });

      btnDownTexture = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("UI/downArrow.png"))));
      btnDown = new Button(btnDownTexture);
      btnDown.addListener(new NoisyClickListener()
      {
         @Override
         public void clicked(InputEvent event, float x, float y)
         {
            super.clicked(event, x, y);
            screen.setCommand(new MoveCommand(tableau.getPlayer(), MoveDirection.DOWN, tableau));
         }
      });

      btnLeftTexture = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("UI/leftArrow.png"))));
      btnLeft = new Button(btnLeftTexture);
      btnLeft.addListener(new NoisyClickListener()
      {
         @Override
         public void clicked(InputEvent event, float x, float y)
         {
            super.clicked(event, x, y);
            screen.setCommand(new MoveCommand(tableau.getPlayer(), MoveDirection.LEFT, tableau));
         }
      });

      btnRightTexture = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("UI/rightArrow.png"))));
      btnRight = new Button(btnRightTexture);
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
