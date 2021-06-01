package com.quokkadventure.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.quokkadventure.Assets;
import com.quokkadventure.QuokkAdventure;
import com.quokkadventure.screens.listener.NoisyClickListener;

/**
 * Classe représentant l'écran d'accueil
 * @author Herzig Melvyn
 * @date 14/05/2021
 */
public class MainMenuScreen extends AScreen
{
   /**
    * Bouton pour quitter.
    */
   private Button btnQuit;

   /**
    * Bouton pour joueur
    */
   private Button btnPlay;

   /**
    * Constructeur
    * @param game Instance de la classe du jeu.
    */
   public MainMenuScreen(final QuokkAdventure game)
   {
      super(game, Assets.manager.get(Assets.musicMenu));

      // Bouton quitter
      btnQuit = new Button(new TextureRegionDrawable(Assets.manager.get(Assets.textBtnQuit)));
      btnQuit.setPosition(QuokkAdventure.WIDTH /2 - btnQuit.getWidth()/2, 20); // Bas milieu
      btnQuit.addListener(new NoisyClickListener()
      {
         @Override
         public void clicked(InputEvent event, float x, float y)
         {
            super.clicked(event, x, y);
            game.quit();
         }
      });
      game.getStage().addActor(btnQuit);

      // Bouton quitter
      btnPlay = new Button(new TextureRegionDrawable(Assets.manager.get(Assets.textBtnPlay)));
      btnPlay.setPosition(QuokkAdventure.WIDTH / 2 - btnQuit.getWidth()/2, btnPlay.getHeight() + 40); // Bas milieu
      btnPlay.addListener(new NoisyClickListener()
      {
         @Override
         public void clicked(InputEvent event, float x, float y)
         {
            super.clicked(event, x, y);

            dispose();
            game.setScreen(new GameScreen(game, 1));
         }
      });
      game.getStage().addActor(btnPlay);

      // Titre
      Image title = new Image(Assets.manager.get(Assets.textGameTitle));
      title.setPosition(QuokkAdventure.WIDTH /2 - title.getWidth()/2, QuokkAdventure.HEIGHT - title.getHeight() - 20);
      game.getStage().addActor(title);

   }

   /**
    * Méthode appelée pour raffraîchir l'affichage.
    * Utilisé dans render de AScran. les élément affiché à ce moment seront
    * devant l'image de fond et derrière le stage.
    * @param delta Temps écoulé depuis le dernier appel.
    */
   public void childRender(float delta)
   {
      // Vide, aucun contenu spécifique
   }
}
