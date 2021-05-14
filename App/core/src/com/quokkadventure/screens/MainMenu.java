package com.quokkadventure.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.quokkadventure.Assets;
import com.quokkadventure.QuokkAdventure;
import com.quokkadventure.screens.listener.NoisyClickListener;


public class MainMenu extends AScreen
{

   /**
    * Titre de la page.
    */
   private Texture title;

   /**
    * Bouton pour quitter.
    */
   private Button btnQuit;
   Drawable btnQuitTexture;

   /**
    * Constructeur
    * @param game Instance de la classe du jeu.
    */
   public MainMenu(final QuokkAdventure game)
   {
      super(game, "Music/menuLoop.WAV");
      title = new Texture(Gdx.files.internal("UI/title.png"));

      // Bouton quitter
      btnQuitTexture = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("UI/btnQuit.png"))));
      btnQuit = new Button(btnQuitTexture);
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
   }

   /**
    * Méthode appelée lorsque l'écran doit s'afficher.
    * @param delta Temps écoulé depuis le dernier appel.
    */
   @Override
   public void render(float delta)
   {
      super.render(delta);

      // Centré horizontalement en haut de la page.
      game.getBatch().draw(title, QuokkAdventure.WIDTH /2 - title.getWidth()/2,QuokkAdventure.HEIGHT - title.getHeight() - 20);

      game.getBatch().end();

      game.getStage().draw();
   }

   /**
    * Méthode appelée quand l'écran doit libérer ses ressources
    */
   @Override
   public void dispose()
   {
      title.dispose();

   }
}
