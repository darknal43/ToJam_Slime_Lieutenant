package state.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import entities.GameEntity;
import entities.Player;
import tools.WorldFactory;

/**
 *
 * Created by Hongyu Wang on 5/5/2016.
 */
public class GameScreen extends AbstractScreen{


    @Override
    protected void subclassInit() {

        GameEntity player;
        stage.addActor(player = new Player());
        stage.setKeyboardFocus(player);

    }

    @Override
    protected void draw() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

    @Override
    protected void update(float delta) {
        stage.act(delta);
        WorldFactory.getWorld().step(delta, 6, 2);
    }

}


