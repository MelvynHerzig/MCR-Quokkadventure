package com.quokkadventure.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.quokkadventure.Assets;
import com.quokkadventure.QuokkAdventure;
import com.quokkadventure.command.AMoveCommand;
import com.quokkadventure.command.MoveCommand;
import com.quokkadventure.scene2d.DynamicCounter;
import com.quokkadventure.screens.listener.NoisyClickListener;

import java.text.DecimalFormat;
import java.util.Stack;

/**
 * Classe représentant le rejeu du dernier niveau réussi.
 * Rejoue tous les coups réalisés pour réussir le niveau.
 *
 * @author Berney Alec
 * @author Ferrari Teo
 * @author Forestier Quentin
 * @author Herzig Melvyn
 * @author Janssens Emmanuel
 * @date 14/05/2021
 */
public class ReviewGame extends LevelScreen {

    Stack<AMoveCommand> historyToReplay;

    Button accelerate;
    Button deccelerate;

    DynamicCounter speedDisplay;
    Float speed = 0.2f;
    Table t;

    /**
     * Constructeur.
     *
     * @param levelNumber Le numéro du niveau.
     * @param historic L'historique des mouvements.
     */
    public ReviewGame(int levelNumber, Stack<AMoveCommand> historic) {
        super(levelNumber);
        historyToReplay = historic;

        deccelerate =  new Button(new TextureRegionDrawable(Assets.manager.get(Assets.textBtnFastForward)));
        accelerate =  new Button(new TextureRegionDrawable(Assets.manager.get(Assets.textBtnUndo)));

        speedDisplay = new DynamicCounter(new TextureRegionDrawable(Assets.manager.get(Assets.textTimeCounter)),QuokkAdventure.Get().WIDTH - (int) (3.5 * 60) - 20,120);
        accelerate.addListener(new NoisyClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                super.clicked(event,x,y);
                speed += 0.1f;
            }
        });

        deccelerate.addListener(new NoisyClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                super.clicked(event,x,y);
                speed -= 0.1f;
            }
        });
        t = new Table();
        t.defaults().size(60);
        t.add(accelerate).left().padRight(10);
        t.add(deccelerate).right().padLeft(10);
        t.row();
        t.setPosition(QuokkAdventure.Get().WIDTH - (int) (2 * 60),100);
        huds.addActor(t);
        huds.addActor(speedDisplay);

    }

    float clock = 0;

    @Override
    public void render(float delta)
    {
        super.render(delta);

        clock += Gdx.graphics.getDeltaTime();
        if(clock > speed)
        {
            if(!historyToReplay.empty())
            {
                toExecute = historyToReplay.firstElement();

                setCommand( new MoveCommand(tableau.getPlayer(), ((AMoveCommand) toExecute).getDirection(), tableau) );

                if(toExecute.execute())
                {
                    historyToReplay.remove(historyToReplay.firstElement());
                    historic.addCommand((AMoveCommand) toExecute);
                }

            }
            clock = 0;
        }

        if(tableau.isSolved())
        {
            // Affichage de l'overlay
            endOverlay.show();
        }

        if(speed < 0)
            speed = 0.0f;

        speedDisplay.updateFloat(new DecimalFormat("#.##").format(speed));
    }
    @Override
    public boolean keyDown(int keycode)
    {
        return true;
    }

    /**
     * Méthode appelé lorsque l'écran n'est plus utilisé.
     */
    @Override
    public void dispose()
    {
        super.dispose();
        tableau.loadMap().dispose();
        renderer.dispose();
    }

}