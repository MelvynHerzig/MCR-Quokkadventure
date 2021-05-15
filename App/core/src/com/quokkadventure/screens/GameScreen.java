package com.quokkadventure.screens;


import com.badlogic.gdx.Input;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.quokkadventure.QuokkAdventure;
import com.quokkadventure.actors.Quokka;

public class GameScreen extends AScreen
{
   private TiledMap map;
   private OrthogonalTiledMapRenderer renderer;
   private TiledMapTileLayer mapLayer;

   private Quokka quokka;

   public GameScreen(QuokkAdventure game, String musicName)
   {
      super(game, musicName);
   }

   @Override
   public void show()
   {
      super.show();
      map = new TmxMapLoader().load("Map/level1.tmx");
      renderer = new OrthogonalTiledMapRenderer(map);
      mapLayer = (TiledMapTileLayer) renderer.getMap().getLayers().get("staticMap");
      quokka = new Quokka(0,0);
      game.getStage().addActor(quokka);

   }

   /**
    * Méthode appelée lorsque l'écran doit s'afficher.
    * @param delta Temps écoulé depuis le dernier appel.
    */
   @Override
   public void render(float delta)
   {
      super.render(delta);

      game.getBatch().end();

      renderer.setView(camera);
      renderer.getBatch().begin();
      renderer.renderTileLayer(mapLayer);
      renderer.getBatch().end();

      game.getStage().draw();
   }

   @Override
   public void hide()
   {
      super.hide();
      dispose();
   }

   @Override
   public void dispose()
   {
      super.dispose();
      map.dispose();
      renderer.dispose();
   }

   @Override
   public boolean keyDown(int keycode)
   {

      if (keycode == Input.Keys.RIGHT || keycode == Input.Keys.D)
         quokka.moveToPosition(quokka.getPosX() + 1, quokka.getPosY());
      if (keycode == Input.Keys.UP || keycode == Input.Keys.W)
         quokka.moveToPosition(quokka.getPosX(), quokka.getPosY() + 1);
      if (keycode == Input.Keys.LEFT || keycode == Input.Keys.A)
         quokka.moveToPosition(quokka.getPosX() - 1, quokka.getPosY());
      if (keycode == Input.Keys.DOWN || keycode == Input.Keys.S)
         quokka.moveToPosition(quokka.getPosX(), quokka.getPosY() - 1);

      return true;
   }
}
