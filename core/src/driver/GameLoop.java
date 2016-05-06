package driver;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import state.stateManager.ScreenShell;

/**
 * This is the primary driver class for this game.
 *
 * Created by Hongyu Wang on 5/5/2016.
 */
class GameLoop extends Game {


    @Override
    public void create () {
        ScreenShell.GAME_SCREEN.setAsScreen();

    }



    @Override
    public void render () {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        screen.render(Gdx.graphics.getDeltaTime());

    }

    @Override
    public void dispose() {

        ScreenShell.dispose();
    }
}