package com.quokkadventure.scene2d;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.quokkadventure.Assets;
import com.quokkadventure.QuokkAdventure;
import com.quokkadventure.command.ACommand;
import com.quokkadventure.screens.GameScreen;
import com.quokkadventure.screens.LevelScreen;
import com.quokkadventure.screens.MainMenuScreen;
import com.quokkadventure.screens.ReviewGame;
import com.quokkadventure.screens.listener.NoisyClickListener;

import java.util.Stack;

/**
 * Scène qui affiche le menu de fin de niveau.
 *
 * @author Herzig Melvyn
 * @date 30/05/2021
 */
public class LevelComplet extends Group
{

   /**
    * Constructeur.
    * @param gameScreen Écran de jeu surlequel l'overlay est affiché.
    */
   public LevelComplet(final LevelScreen gameScreen)
   {
      QuokkAdventure.Get().setCurrentScreen(gameScreen);
      setSize(QuokkAdventure.Get().WIDTH, QuokkAdventure.Get().HEIGHT);

      addActor(new Image(Assets.manager.get(Assets.imgBook)));

      // Ajout du text (page gauche)
      // Création du label
      Label text = new Label("Congratulations \n You finished \n level " + QuokkAdventure.Get().getCurrentLevelID(), new Label.LabelStyle(Assets.manager.get(Assets.font), Color.BLACK));
      text.setAlignment(Align.center);
      text.setPosition(500,500);
      text.setFontScale(1.5f);
      addActor(text);

      // Ajout des boutons (page droite)
      Table menu = new Table();
      menu.setFillParent(true);

      // Création du bouton de retour au menu
      Button btnMenu = new Button(new TextureRegionDrawable(Assets.manager.get(Assets.textBtnMenu)));
      btnMenu.addListener(new NoisyClickListener()
      {
         @Override
         public void clicked(InputEvent event, float x, float y)
         {
           super.clicked(event, x, y);
           gameScreen.dispose();
           QuokkAdventure.Get().setScreen(new MainMenuScreen());
         }
      });

      // Création du bouton de changement de niveau
      Button btnNext = new Button(new TextureRegionDrawable(Assets.manager.get(Assets.textBtnNext)));
      btnNext.addListener(new NoisyClickListener()
      {
         @Override
         public void clicked(InputEvent event, float x, float y)
         {
            super.clicked(event, x, y);
            //TODO
         }
      });

      // Création du bouton qui démarre le replay.
      Button btnReview = new Button(new TextureRegionDrawable(Assets.manager.get(Assets.textBtnReview)));
      btnReview.addListener(new NoisyClickListener()
      {
         @Override
         public void clicked(InputEvent event, float x, float y)
         {
            super.clicked(event, x, y);
            //TODO
            gameScreen.dispose();

            Stack<ACommand> history = ((GameScreen) QuokkAdventure.Get().getCurrentScreen()).getHistoric();
            ReviewGame review = new ReviewGame(QuokkAdventure.Get().getCurrentLevelID(),history);
            QuokkAdventure.Get().setScreen(review);
         }
      });

      // alignement et placement des boutons
      menu.defaults().expandX();
      menu.padLeft(380).padBottom(150);
      menu.add(btnMenu).padTop(30);
      menu.row();
      menu.add(btnNext).padTop(30);
      menu.row();
      menu.add(btnReview).padTop(30);
      menu.row();

      addActor(menu);

      // A la création le menu est invisible.
      setVisible(false);
   }

   /**
    * Cache l'overlay
    */
   public void hide()
   {
      setVisible(false);
      QuokkAdventure.Get().getCurrentScreen().pause();
   }

   /**
    * Affiche lôverlay
    */
   public void show()
   {
      setVisible(true);
      QuokkAdventure.Get().getCurrentScreen().pause();
   }
}
