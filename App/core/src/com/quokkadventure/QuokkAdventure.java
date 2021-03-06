package com.quokkadventure;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.quokkadventure.screens.LevelScreen;
import com.quokkadventure.screens.MainMenuScreen;

/**
 * Classe initiale du jeu.
 *
 * @author Berney Alec
 * @author Ferrari Teo
 * @author Forestier Quentin
 * @author Herzig Melvyn
 * @author Janssens Emmanuel
 * @date 15/05/2021
 */
public class QuokkAdventure extends Game
{
	/**
	 * Nombre de niveaux contenus dans le jeu.
	 */
	public static final int NB_LEVEL = 10;

	/**
	 * Titre du jeu.
	 */
	public static final String TITLE = "Quokk'adventure";

	/**
	 * Largeur initiale de la fenêtre de jeu. Utilisé pour initialisé les lanceurs.
	 */
	public static final int WIDTH = 1600;

	/**
	 * Hauteur initiale de la fenêtre de jeu. Utilisé pour initialisé les lanceurs.
	 */
	public static final int HEIGHT = 960;

	/**
	 * Batch employé pour afficher les éléments.
	 */
	private SpriteBatch batch;

	/**
	 * Stage du jeu. Sert principalement à ordoner les acteurs et les inputs.
	 */
	private Stage stage;

	/**
	 * ID du niveau courrant.
	 */
	private int currentLevelID;

	/**
	 * Écran d'affichage du niveau en cours.
	 */
	private LevelScreen currentScreen;

	/**
	 * Instance du Singleton.
	 */
	private static QuokkAdventure instance;

	/**
	 * Accesseur de l'instance du singleton QuokkAdventure.
	 *
	 * @return L'instance de QuokkAdventure.
	 */
	public static QuokkAdventure Get(){
		if(instance == null) {
			instance = new QuokkAdventure();
		}
		return instance;
	}

	/**
	 * Constructeur privé pour le Singleton.
	 */
	private QuokkAdventure(){
		super();
	}

	/**
	 * Méthode appelée lors de la création de l'application.
	 */
	@Override
	public void create ()
	{
		batch = new SpriteBatch();
		stage = new Stage(new StretchViewport(QuokkAdventure.Get().WIDTH, Get().HEIGHT));

		Assets.load();
		Assets.manager.finishLoading();

		setScreen(new MainMenuScreen());	}

	/**
	 * Méthode appelée pour libérer la mémoire.
	 */
	@Override
	public void dispose()
	{
		super.dispose();

		stage.dispose();
		batch.dispose();

		Assets.dispose();
	}

	/**
	 * Accesseur du batch.
	 * @return Retourne le batch.
	 */
	public SpriteBatch getBatch()
	{
		return batch;
	}

	/**
	 * Accesseur du stage.
	 * @return Retourne le stage.
	 */
	public Stage getStage()
	{
		return stage;
	}

	/**
	 * Retourne l'ID du niveau en cours.
	 * @return l'ID du niveau en cours.
	 */
	public int getCurrentLevelID()
	{
		return currentLevelID;
	}

	/**
	 * Definit l'id du niveau en cours.
	 * @param id l'ID du niveau à définir comme en cours.
	 */
	public void setCurrentLevelID(int id)
	{
		currentLevelID = id;
	}

	/**
	 * Retourne l'écran du niveau.
	 * @return écran du niveau.
	 */
	public LevelScreen getCurrentScreen()
	{
		return currentScreen;
	}

	/**
	 * Définit l'écran du niveau.
	 * @param screen écran a définir.
	 */
	public void setCurrentScreen(LevelScreen screen)
	{
		currentScreen = screen;
	}

	/**
	 * Termine l'application.
	 */
	public void quit()
	{
		Gdx.app.exit();
	}

	/**
	 * Méthode appelée lorsque le jeu est redimensionné.
	 * @param width Nouvelle largeur.
	 * @param height Nouvelle hauteur.
	 */
	@Override
	public void resize(int width, int height)
	{
		super.resize(width, height);
	}
}
