package driver;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import state.screens.AbstractScreen;
import state.stateManager.ScreenShell;

/**
 * This is the primary driver class for this game.
 *
 * Created by Hongyu Wang on 5/5/2016.
 */
public class GameLoop extends Game {

    @Override
    public void create () {
        ScreenShell.initiate();


    }



    @Override
    public void render () {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        super.render();



    }

    @Override
    public void dispose() {

        screen.dispose();
    }

    public void updateCamera(Vector2 travelVector){
        ((AbstractScreen)screen).updateCamera(travelVector);
    }

}