package com.quokkadventure.scene2d;

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
import com.quokkadventure.screens.GameScreen;
import com.quokkadventure.screens.LevelScreen;
import com.quokkadventure.screens.MainMenuScreen;
import com.quokkadventure.screens.ReviewGame;
import com.quokkadventure.screens.listener.NoisyClickListener;

/**
 * Scène qui affiche le menu de fin de niveau.
 *
 * @author Berney Alec
 * @author Ferrari Teo
 * @author Forestier Quentin
 * @author Herzig Melvyn
 * @author Janssens Emmanuel
 * @date 30/05/2021
 */
public class LevelComplet extends Group
{
   /**
    * Constructeur.
    * @param gameScreen Écran de jeu sur lequel l'overlay est affiché.
    */
   public LevelComplet(final LevelScreen gameScreen)
   {
      QuokkAdventure.Get().setCurrentScreen(gameScreen);
      setSize(QuokkAdventure.Get().WIDTH, QuokkAdventure.Get().HEIGHT);

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
      Label text = new Label("Congratulations \n You finished \n level " + QuokkAdventure.Get().getCurrentLevelID(), new Label.LabelStyle(Assets.manager.get(Assets.font), Color.BLACK));
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
           QuokkAdventure.Get().setScreen(new MainMenuScreen());
         }
      });


         // Création du bouton de changement de niveau
         Button btnNext = new Button(new TextureRegionDrawable(Assets.manager.get(Assets.textBtnNext)));
         btnNext.addListener(new NoisyClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
               super.clicked(event, x, y);
               gameScreen.dispose();
               QuokkAdventure.Get().setScreen(new GameScreen(QuokkAdventure.Get().getCurrentLevelID() + 1));
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

            gameScreen.dispose();
            MoveHistoric history = (QuokkAdventure.Get().getCurrentScreen()).getHistoric();
            ReviewGame review = new ReviewGame(QuokkAdventure.Get().getCurrentLevelID(),history.getHistoric());
            QuokkAdventure.Get().setScreen(review);
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

      btnNext.setVisible(QuokkAdventure.Get().getCurrentLevelID() < QuokkAdventure.NB_LEVEL);
   }

   /**
    * Affiche l'overlay.
    */
   public void show()
   {
      setVisible(true);
      QuokkAdventure.Get().getCurrentScreen().pause();
   }
}
