package com.quokkadventure.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
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
public abstract class AScreen extends InputAdapter implements Screen
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
    * Stage regroupant les hud's.
    */
   protected Stage huds;

   /**
    * Constructeur
    * @param game Référence sur le jeu.
    * @param music Musique de fond.
    */
   public AScreen(QuokkAdventure game, Music music)
   {
      this.game = game;
      game.getStage().clear();
      huds = game.getStage();

      // Création de la caméra
      camera = new OrthographicCamera();
      camera.setToOrtho(false, QuokkAdventure.WIDTH, QuokkAdventure.HEIGHT);

      // Chargement de la musique
      backMusic = music;
      backMusic.setLooping(true);
      backMusic.setVolume(0.3f);


      // Bouton pour couper la music
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
      huds.addActor(btnMusic);

      // Activation des inputs
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

      // 1) Début de l'affichage des éléments "basique"
      game.getBatch().begin();
      // background
      game.getBatch().draw(Assets.manager.get(Assets.background),0,0);
      game.getBatch().end();

      // 2) Affichage du contenu de l'enfant
      childRender(delta);

      // 3) Affichage de l'HUD
      huds.act();
      huds.draw();
   }

   /**
    * Méthode utilisée pour afficher le contenu spécifique à un écran fils.
    * @param delta Temps écoulé.
    */
   protected abstract void childRender(float delta);

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
   {
      backMusic.pause();
   }

   /**
    * Méthode appelée lorsque le jeu reprend
    */
   @Override
   public void resume()
   {
      backMusic.play();
   }

   /**
    * Appelé lorsque l'écran n'est plus l'écran principal du jeu.
    */
   @Override
   public void hide()
   { /* méthode auto générée. */ }
}
