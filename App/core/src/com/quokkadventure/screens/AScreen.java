package com.quokkadventure.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.quokkadventure.Assets;
import com.quokkadventure.QuokkAdventure;
import com.quokkadventure.screens.listener.NoisyClickListener;

/**
 * Classe abstraite commune aux écrans de Quokk'adventure.
 * @author Herzig Melvyn
 * @date 14/05/2021
 */
public class AScreen extends InputAdapter implements Screen
{
   /**
    * Référence sur la classe de base du jeu. Permet de récupérer le batch
    * et le stage
    */
   protected final QuokkAdventure game;

   /**
    * Camera pour visualiser l'écran.
    */
   protected OrthographicCamera camera;

   /**
    * Musique de fond.
    */
   protected Music backMusic;

   /**
    * Constructeur
    * @param game Référence sur le jeu.
    * @param music Musique de fond.
    */
   public AScreen(QuokkAdventure game, Music music)
   {
      this.game = game;
      game.getStage().clear();

      // Création de la caméra
      camera = new OrthographicCamera();
      camera.setToOrtho(false, QuokkAdventure.WIDTH, QuokkAdventure.HEIGHT);

      // Chargement de la musique
      backMusic = music;
      backMusic.setLooping(true);
      backMusic.setVolume(0.3f);

      // TODO refactor, ne fonctionne pas dans GameScreen car overlap par le tableau
      // Bouton pour couper la music

      /**
       * Bouton pour couper la musique.
       */
      Button btnMusic = new Button(new TextureRegionDrawable(Assets.manager.get(Assets.textAudioOn)),
                                   new TextureRegionDrawable(Assets.manager.get(Assets.textAudioOff)),
                                   new TextureRegionDrawable(Assets.manager.get(Assets.textAudioOff)));
      btnMusic.setPosition(10, QuokkAdventure.HEIGHT - btnMusic.getHeight() - 10); // haut gauche
      btnMusic.addListener(new NoisyClickListener()
      {
         private boolean musicOn = true;

         @Override
         public void clicked(InputEvent event, float x, float y)
         {
            super.clicked(event, x, y);
            if(musicOn)
            {
               backMusic.pause();
            }
            else
               backMusic.play();

            musicOn = !musicOn;
         }
      });
      game.getStage().addActor(btnMusic);

      // Activation des input
      InputMultiplexer input = new InputMultiplexer(game.getStage(), this);
      Gdx.input.setInputProcessor(input);
   }

   /**
    * Méthode appelée lorsque l'écran devient l'écran principal.
    */
   @Override
   public void show()
   {
      backMusic.play();
   }

   /**
    * Méthode appelée lorsque l'écran doit s'afficher.
    * @param delta Temps écoulé depuis le dernier appel.
    */
   @Override
   public void render(float delta)
   {
      camera.update();
      game.getBatch().setProjectionMatrix(camera.combined);

      // Début de l'affichage des éléments
      game.getBatch().begin();

      game.getBatch().draw(Assets.manager.get(Assets.background),0,0);

      // Les enfants devront faire super.render et
      // game.getBatch().end(); pour lancer terminer l'affichage.
   }

   /**
    * Méthode appelée pour redimmensionner l'écran.
    * @param width Nouvelle largeur.
    * @param height Nouvelle hauteur.
    */
   @Override
   public void resize(int width, int height)
   {
      game.getStage().getViewport().update(width, height, true);
   }

   /**
    * Méthode appelée quand l'écran doit libérer ses ressources
    */
   @Override
   public void dispose()
   {
      backMusic.stop();
   }

   /**
    * Méthode appelée lorsque le jeu est en pause
    */
   @Override
   public void pause()
   { /* méthode auto générée. */ }

   /**
    * Méthode appelée lorsque le jeu reprend
    */
   @Override
   public void resume()
   { /* méthode auto générée. */ }

   /**
    * Appelé lorsque l'écran n'est plus l'écran principal du jeu.
    */
   @Override
   public void hide()
   { /* méthode auto générée. */ }
}
