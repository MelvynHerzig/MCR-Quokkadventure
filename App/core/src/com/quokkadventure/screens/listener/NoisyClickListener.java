package com.quokkadventure.screens.listener;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.quokkadventure.Assets;

/**
 * Implémente notre version des clickListener de libgs
 * Le comportement est le même sauf que l'ont ajoute un son qui sera
 * joué lors du click.
 */
public class NoisyClickListener extends ClickListener
{
   @Override
   public void clicked(InputEvent event, float x, float y)
   {
      super.clicked(event, x, y);
      Assets.clickSound.play();
   }
}
