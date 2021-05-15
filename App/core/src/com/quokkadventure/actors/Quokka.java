package com.quokkadventure.actors;

import com.badlogic.gdx.Gdx;

public class Quokka extends ActorOnTile
{
   // Movement velocity
   public Quokka(int posX, int posY)
   {
      super(posX, posY, Gdx.files.internal("Quokka/quokka.png"));

   }
}
