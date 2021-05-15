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
	 * Largeur initiale de la fenêtre.
	 */
	public static final int WIDTH = 1600;

	/**
	 * Heuteur initiale de la fenêtre.
	 */
	public static final int HEIGHT = 960 ;

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
		stage = new Stage(new StretchViewport(WIDTH, HEIGHT));

		Assets.load();

		setScreen(new MainMenuScreen(this));
	}

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
}
