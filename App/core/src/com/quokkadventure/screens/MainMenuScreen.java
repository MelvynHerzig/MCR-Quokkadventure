package com.quokkadventure.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
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
    * Constructeur
    */
   public MainMenuScreen()
   {
      super( Assets.manager.get(Assets.musicMenu));

      // Bouton quitter
      Button btnQuit = new Button(new TextureRegionDrawable(Assets.manager.get(Assets.textBtnQuit)));
      btnQuit.setPosition(game.WIDTH /2 - btnQuit.getWidth()/2, 20); // Bas milieu
      btnQuit.addListener(new NoisyClickListener()
      {
         @Override
         public void clicked(InputEvent event, float x, float y)
         {
            super.clicked(event, x, y);
            game.quit();
         }
      });
      huds.addActor(btnQuit);

      // Bouton jouer
      Button btnPlay = new Button(new TextureRegionDrawable(Assets.manager.get(Assets.textBtnPlay)));
      btnPlay.setPosition(game.WIDTH / 2 - btnQuit.getWidth()/2, btnPlay.getHeight() + 40); // Bas milieu
      btnPlay.addListener(new NoisyClickListener()
      {
         @Override
         public void clicked(InputEvent event, float x, float y)
         {
            super.clicked(event, x, y);

            dispose();
            game.setScreen(new GameScreen(1));
         }
      });
      huds.addActor(btnPlay);

      // Titre
      Image title = new Image(Assets.manager.get(Assets.textGameTitle));
      title.setPosition(QuokkAdventure.WIDTH / 2  - title.getWidth()/2, QuokkAdventure.HEIGHT - title.getHeight() - 20);
      huds.addActor(title);
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
