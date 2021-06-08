package com.quokkadventure.screens;

import com.badlogic.gdx.Gdx;
import com.quokkadventure.command.*;

import java.util.Stack;

public class ReviewGame extends LevelScreen {

    Stack<AMoveCommand> historyToReplay;
    /**
     * Constructeur
     *
     * @param levelNumber
     * @param historic
     */
    public ReviewGame(int levelNumber, Stack<AMoveCommand> historic) {
        super(levelNumber);
        historyToReplay = historic;


    }

    float clock = 0;

    @Override
    public void render(float delta)
    {
        super.render(delta);

        clock += Gdx.graphics.getDeltaTime();
        if(clock > 0.2)
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