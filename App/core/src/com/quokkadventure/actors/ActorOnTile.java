package com.quokkadventure.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

/**
 * Classe qui représente les éléments de la carte tuilée.
 */
public class ActorOnTile extends Actor
{
   // Position de l'acteur sur la tuile (x,y)
   protected int x;
   protected int y;

   /**
    * Texture de l'acteur.
    */
   protected Texture img;

   ActorOnTile(int posX, int posY, FileHandle textLoc)
   {
      x = posX;
      y = posY;

      setSize(64,64);
      setPosition(x * 64, y * 64);

      img = new Texture(textLoc);
   }

   public void moveToPosition(int posX, int posY)
   {
      x = posX;
      y = posY;

      addAction(Actions.moveTo(x * 64, y * 64, 0.2f));
   }

   public int getPosX()
   {
      return x;
   }

   public int getPosY()
   {
      return y;
   }

   public void dispose()
   {
      img.dispose();
   }

   @Override
   public void draw(Batch batch, float parentAlpha)
   {
      batch.draw(img, x * 64, y * 64, 64 , 64);
   }
}
