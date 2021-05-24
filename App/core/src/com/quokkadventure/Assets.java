package com.quokkadventure;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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

   /**
    * Image bouton audio on
    */
   public static final AssetDescriptor<Texture> textAudioOn = new AssetDescriptor<>("UI/btnMusicOn.png", Texture.class);

   /**
    * Image bouton audio off
    */
   public static final AssetDescriptor<Texture> textAudioOff = new AssetDescriptor<>("UI/btnMusicOff.png", Texture.class);

   /**
    * Image bouton quit
    */
   public static final AssetDescriptor<Texture> textBtnQuit = new AssetDescriptor<>("UI/btnQuit.png", Texture.class);

   /**
    * Image bouton play "UI/title.png"
    */
   public static final AssetDescriptor<Texture> textBtnPlay = new AssetDescriptor<>("UI/btnPlay.png", Texture.class);

   /**
    * Texture titre du jeu
    */
   public static final AssetDescriptor<Texture> textGameTitle = new AssetDescriptor<>("UI/title.png", Texture.class);

   /**
    * Texture bouton déplacement haut
    */
   public static final AssetDescriptor<Texture> textBtnUp = new AssetDescriptor<>("UI/upArrow.png", Texture.class);

   /**
    * Texture bouton déplacement bas
    */
   public static final AssetDescriptor<Texture> textBtnDown = new AssetDescriptor<>("UI/downArrow.png", Texture.class);

   /**
    * Texture bouton déplacement gauche
    */
   public static final AssetDescriptor<Texture> textBtnLeft = new AssetDescriptor<>("UI/leftArrow.png", Texture.class);

   /**
    * Texture bouton déplacement droite
    */
   public static final AssetDescriptor<Texture> textBtnRight = new AssetDescriptor<>("UI/rightArrow.png", Texture.class);

   /**
    * Texture boîte
    */
   public static final AssetDescriptor<Texture> textBox = new AssetDescriptor<>("Map/movable.png", Texture.class);

   /**
    * Texture emplacement de fin
    */
   public static final AssetDescriptor<Texture> textEnd = new AssetDescriptor<>("Map/end.png", Texture.class);

   /**
    * Texture personnage Quokka
    */
   public static final AssetDescriptor<Texture> textQuokka = new AssetDescriptor<>("Quokka/quokka.png", Texture.class);

   /**
    * Texture des murs
    */
   public static final AssetDescriptor<Texture> textWall = new AssetDescriptor<>("Map/border.png", Texture.class);

   /**
    * Texture du compteur de déplacements
    */
   public static final AssetDescriptor<Texture> textStepCounter = new AssetDescriptor<>("UI/stepCounter.png", Texture.class);

   /**
    * Texture du compteur de temps écoulé
    */
   public static final AssetDescriptor<Texture> textTimeCounter = new AssetDescriptor<>("UI/timeCounter.png", Texture.class);

   /**
    * Image bouton undo
    */
   public static final AssetDescriptor<Texture> textBtnUndo = new AssetDescriptor<>("UI/btnUndo.png", Texture.class);

   /* ********** Audio ************* */
   /**
    * Son joué au click sur les boutons
    */
   public static final AssetDescriptor<Sound> clickSound = new AssetDescriptor<>("Sound/click.wav", Sound.class);

   /**
    * Music du menu
    */
   public static final AssetDescriptor<Music> musicMenu = new AssetDescriptor<>("Music/menuLoop.wav", Music.class);

   /**
    * Music en jeu
    */
   public static final AssetDescriptor<Music> musicInGame = new AssetDescriptor<>("Music/inGameLoop.wav", Music.class);

   /**
    * Méthode appelé au début du jeu pour charger les assets.
    */
   public static void load()
   {

      // Texture
      manager.load(background);
      manager.load(textAudioOn);
      manager.load(textAudioOff);
      manager.load(textBtnQuit);
      manager.load(textBtnPlay);
      manager.load(textGameTitle);
      manager.load(textBtnUp);
      manager.load(textBtnDown);
      manager.load(textBtnLeft);
      manager.load(textBtnRight);
      manager.load(textBox);
      manager.load(textEnd);
      manager.load(textQuokka);
      manager.load(textWall);
      manager.load(textStepCounter);
      manager.load(textTimeCounter);
      manager.load(textBtnUndo);

      // Audio
      manager.load(clickSound);
      manager.load(musicMenu);
      manager.load(musicInGame);

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
