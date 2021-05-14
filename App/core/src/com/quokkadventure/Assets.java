package com.quokkadventure;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

/**
 * Cette classe est responsable de charger les différents assets
 * @author Herzig Melvyn
 * @date 14/05/2021
 */
public class Assets
{
   /**
    * Police de l'application.
    */
   public static BitmapFont font;

   /**
    * Image de fond. Ici commune à tous les écrans.
    */
   public static Sprite background;

   /**
    * Son joué au clique sur un bouton.
    */
   public static Sound clickSound;

   /**
    * Charge les assets en mémoire.
    */
   public static void load()
   {
      // Chargement de la police.
      FreeTypeFontGenerator.FreeTypeFontParameter fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
      fontParameter.size = 30;
      font = new FreeTypeFontGenerator(Gdx.files.internal("Font/bits.ttf")).generateFont(fontParameter);

      // Chargement de l'image de fond
      background = new Sprite(new Texture(Gdx.files.internal("UI/background.png")));

      // Chargement du clique
      clickSound = Gdx.audio.newSound(Gdx.files.internal("Sound/click.wav"));
   }

   /**
    * Libère les assets en mémoire.
    */
   public static void unload()
   {
      font.dispose();
      clickSound.dispose();
   }
}
