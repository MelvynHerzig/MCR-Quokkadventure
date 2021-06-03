package com.quokkadventure.scene2d;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.quokkadventure.Assets;
import com.quokkadventure.QuokkAdventure;
import com.quokkadventure.command.AMoveCommand;
import com.quokkadventure.screens.GameScreen;
import com.quokkadventure.screens.listener.NoisyClickListener;

import java.util.Stack;

/**
 * Classe permettant d'afficher l'historique des commandes
 * @author Herzig Melvyn
 * @date 02/06/2021
 */
public class MoveHistoric extends Group
{
   /**
    * Group vertical utilisé pour ajouter les boutons
    * symbolisant les commandes
    */
   private final VerticalGroup buttons;

   /**
    * Scroller permettant de faire défiler buttons
    */
   private final ScrollPane scroller;

   /**
    * Historique des commande déroulée. Pas de limite. Limiter le nombre de coups
    * pour réussir un niveau ?
    */
   private final Stack<AMoveCommand> historic;


   /**
    * Constructeur
    * @param gameScreen Écran de jeu sur lequel l'élément est affiché.
    */
   public MoveHistoric(GameScreen gameScreen)
   {
      historic = new Stack<>();

      // Création du VerticalGroup, au début vide car pas de commande
      buttons = new VerticalGroup();
      buttons.space(5);
      buttons.bottom();
      buttons.columnRight();

      // Création du scroller
      scroller = new ScrollPane(buttons);
      scroller.setSize(200, 700);

      setPosition(QuokkAdventure.WIDTH - scroller.getWidth() - 20,QuokkAdventure.HEIGHT-scroller.getHeight());
      setColor(100,0,0,0.5f);

      addActor(scroller);
   }

   /**
    * Ajoute une commande à l'historique.
    * @param command Commande à ajouter.
    */
   public void addCommand(AMoveCommand command)
   {
      historic.push(command);

      final Label lbl = new Label( command.toString() + " " + historic.size(),
                                    new Label.LabelStyle(Assets.manager.get(Assets.font), Color.BLACK));
      lbl.addListener(new NoisyClickListener()
      {
         // Mémorisation de la position de la commande.
         private final int index = historic.size() - 1;
         @Override
         public void clicked(InputEvent event, float x, float y)
         {
            super.clicked(event, x, y);

            // On retire les commandes antérieurs et l'actuelle
            while(historic.size() > index)
            {
               undo();
            }
         }
      });

      buttons.addActor(lbl);

      // Scroll jusqu'au nouvel élément ajouté
      scroller.layout();
      scroller.scrollTo(0, 0, 0, 0);
   }

   /**
    * Annule la dernière commande si il y en a une.
    */
   public void undo()
   {
      if(!historic.empty())
      {
         buttons.removeActorAt(historic.size()-1, false);
         historic.pop().undo();
      }
   }

   Stack<AMoveCommand> getHistoric(){
      return historic;
   }
}
