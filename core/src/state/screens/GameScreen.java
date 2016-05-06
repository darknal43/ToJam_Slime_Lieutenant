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



}


