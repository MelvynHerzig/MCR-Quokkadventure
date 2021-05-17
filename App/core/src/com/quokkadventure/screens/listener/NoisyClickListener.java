package com.quokkadventure.screens.listener;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.quokkadventure.Assets;

/**
 * Implémente notre version des clickListener de libgdx.
 * Le comportement est le même sauf que l'ont ajoute un son qui sera
 * joué lors du click.
 * @author Herzig Melvyn
 * @date 14/05/2021
 */
public class NoisyClickListener extends ClickListener
{
   /**
    * Méthode appelé lorsque l'élément est cliqué
    * @param event Événement.
    * @param x Position x.
    * @param y Position y.
    */
   @Override
   public void clicked(InputEvent event, float x, float y)
   {
      super.clicked(event, x, y);

      Sound clickSound =  Assets.manager.get(Assets.clickSound);
      clickSound.setVolume( clickSound.play(), 0.1f);
   }
}
