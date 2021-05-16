package com.quokkadventure.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.quokkadventure.QuokkAdventure;

/**
 * Configure et démarre le lanceur Desktop.
 * @author Herzig Melvyn
 * @date 15/05/2021
 */
public class DesktopLauncher
{
	public static void main (String[] arg)
	{
		// Création et configuration du lanceur.
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle(QuokkAdventure.TITLE);
		config.setWindowedMode(QuokkAdventure.WIDTH, QuokkAdventure.HEIGHT);

		// Démarrage
		new Lwjgl3Application(new QuokkAdventure(), config);
	}
}
