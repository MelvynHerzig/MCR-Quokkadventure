package com.quokkadventure.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
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
    * Bannière de la page.
    */
   private Texture title;

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
      title = Assets.manager.get(Assets.textGameTitle);

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
            backMusic.stop();
            game.setScreen(new GameScreen(game));
         }
      });
      game.getStage().addActor(btnPlay);

   }

   /**
    * Méthode appelée pour raffraîchir l'affichage
    * @param delta Temps écoulé depuis le dernier appel.
    */
   @Override
   public void render(float delta)
   {
      super.render(delta);

      // Centré horizontalement en haut de la page.
      game.getBatch().draw(title, QuokkAdventure.WIDTH /2 - title.getWidth()/2, QuokkAdventure.HEIGHT - title.getHeight() - 20);
      game.getBatch().end();

      game.getStage().draw();
   }
}
