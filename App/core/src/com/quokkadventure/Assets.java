package com.quokkadventure;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;

/**
 * Cette classe est responsable de charger les différents assets et de
 * les libérer à la fin du jeu.
 * Comme le jeu est léger et que les assets sont peut nombreux
 * TOUS les assets sont chargés au démarrage de l'application.
 * @author Herzig Melvyn
 * @date 14/05/2021
 */
public class Assets
{
   /**
    * Objet responsable du chargement et déchargement du contenu.
    */
   public static final AssetManager manager = new AssetManager();

   /* ********** Police ************* */
   /**
    * Police principale
    */
   private static final String fontName = "Font/bits.ttf";
   public static final AssetDescriptor<BitmapFont> font = new AssetDescriptor<>(fontName, BitmapFont.class);


   /* ********** Texture ************* */
   /**
    * Image de fond
    */
   public static final AssetDescriptor<Texture> background = new AssetDescriptor<>("UI/background.png", Texture.class);

   /* ********** Audio ************* */
   /**
    * Son joué au click sur les boutons
    */
   public static final AssetDescriptor<Sound> clickSound = new AssetDescriptor<>("Sound/click.wav", Sound.class);

   /**
    * Méthode appelé au début du jeu pour charger les assets.
    */
   public static void load()
   {

      // Texture
      manager.load(background);

      // Audio
      manager.load(clickSound);

      // Police
      FileHandleResolver resolver = new InternalFileHandleResolver();
      manager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
      manager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));
      FreetypeFontLoader.FreeTypeFontLoaderParameter params = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
      params.fontFileName = fontName;
      params.fontParameters.size = 30;
      manager.load(fontName, BitmapFont.class, params);
   }

   /**
    * Méthode appelé à la fermeture de l'application pour libérer les assets.
    */
   public static void dispose()
   {
      manager.dispose();
   }
}
