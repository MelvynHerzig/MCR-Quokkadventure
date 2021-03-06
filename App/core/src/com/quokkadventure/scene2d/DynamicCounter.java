package com.quokkadventure.scene2d;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.quokkadventure.Assets;

/**
 * Cette classe affiche un compteur et un image.
 * la valeur du compteur peut être mise à jour.
 * Typiquement utilisé pour compter les déplacement et le temps de jeu.
 *
 * @author Berney Alec
 * @author Ferrari Teo
 * @author Forestier Quentin
 * @author Herzig Melvyn
 * @author Janssens Emmanuel
 * @date 23/05/2021
 */
public class DynamicCounter extends Table
{
   /**
    * Label utilisé pour afficher la valeur du compteur.
    */
   Label value;

   /**
    * Constructeur.
    * @param background Texture à utiliser.
    * @param posX Position x de l'objet.
    * @param posY Position y de l'objet.
    */
   public DynamicCounter(TextureRegionDrawable background, int posX, int posY)
   {
      this.setBounds(posX, posY, 216, 96);
      setBackground(background);

      // Création du label
      value = new Label("", new LabelStyle(Assets.manager.get(Assets.font), Color.BLACK));
      value.setFontScale(1f);
      add(value);

      center();
      padLeft(80);
      padBottom(0);

      getColor().a = 0.8f;
   }

   /**
    * Mise à jour du label.
    * @param newValue Nouvelle valeur du label.
    */
   public void update(int newValue)
   {
      value.setText(newValue);
   }

   public void updateFloat(String newValue){
      value.setText(newValue);
   }
}
