package com.quokkadventure.scene2d;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.quokkadventure.Assets;
import com.quokkadventure.QuokkAdventure;
import com.quokkadventure.screens.GameScreen;
import com.quokkadventure.screens.MainMenuScreen;
import com.quokkadventure.screens.listener.NoisyClickListener;

/**
 * Scène qui affiche le menu de fin de niveau.
 *
 * @author Herzig Melvyn
 * @date 30/05/2021
 */
public class LevelComplet extends Group
{

   /**
    * Écran de jeu depuis lequel est affiché le menu.
    */
   private final GameScreen gameScreen;

   /**
    * Constructeur.
    * @param gameScreen Écran de jeu surlequel l'overlay est affiché.
    * @param game Instance du jeu manipulée pour changer les écrans.
    */
   public LevelComplet(final GameScreen gameScreen, final QuokkAdventure game)
   {
      this.gameScreen = gameScreen;

      // Récupération de l'image de fond.
      Image background = new Image(Assets.manager.get(Assets.imgBook));

      // Dimensionnement du groupe à la taille minimale c-à-d l'image de fond.
      setSize(background.getWidth(), background.getHeight());

      // On place le groupe au centre de l'écran
      setPosition((QuokkAdventure.WIDTH - getWidth()) / 2,  (QuokkAdventure.HEIGHT - getHeight()) / 2);

      // Affichage de l'image.
      addActor(new Image(Assets.manager.get(Assets.imgBook)));

      // Ajout du text (page gauche)
      // Création du label
      Label text = new Label("Congratulations \n You finished \n level " + gameScreen.getLevelNumber(), new Label.LabelStyle(Assets.manager.get(Assets.font), Color.BLACK));
      text.setAlignment(Align.center);
      text.setPosition(100,270);
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
           game.setScreen(new MainMenuScreen(game));
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
            gameScreen.dispose();
            game.setScreen(new GameScreen(game, gameScreen.getLevelNumber() + 1));
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
         }
      });

      // alignement et placement des boutons
      menu.defaults().expandX();
      menu.padLeft(380).padBottom(100);
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
      gameScreen.pause();
   }

   /**
    * Affiche lôverlay
    */
   public void show()
   {
      setVisible(true);
      gameScreen.pause();
   }
}
