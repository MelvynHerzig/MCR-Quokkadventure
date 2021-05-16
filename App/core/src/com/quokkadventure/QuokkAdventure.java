package com.quokkadventure;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.quokkadventure.screens.MainMenuScreen;

/**
 * Classe initiale du jeu.
 * @author Herzig Melvyn
 * @date 15/05/2021
 */
public class QuokkAdventure extends Game
{
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
	 * Batch employé pour afficher les éléments
	 */
	private SpriteBatch batch;

	/**
	 * Stage du jeu. Sert principalement à ordoner les acteurs et les inputs.
	 */
	protected Stage stage;

	/**
	 * Méthode appelée lors de la création de l'application.
	 */
	@Override
	public void create ()
	{
		batch = new SpriteBatch();
		stage = new Stage(new StretchViewport(QuokkAdventure.WIDTH, QuokkAdventure.HEIGHT));

		Assets.load();

		setScreen(new MainMenuScreen(this));
	}

	/**
	 * Méthode appelée pour libérer la mémoire.
	 */
	@Override
	public void dispose()
	{
		super.dispose();

		stage.dispose();
		batch.dispose();

		Assets.unload();
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
	 * Termine l'application.
	 */
	public void quit()
	{
		Gdx.app.exit();
	}

	/**
	 * Méthode appelée lorsque le jeu est redimensionné.
	 * @param width Nouvelle largeur.
	 * @param height Nouvelle hauteur
	 */
	@Override
	public void resize(int width, int height)
	{
		super.resize(width, height);
	}
}
