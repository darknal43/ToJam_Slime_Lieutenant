package state.screens;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import entities.GameEntity;
import entities.Player;
import tools.WorldFactory;
import com.badlogic.gdx.graphics.Texture;
/**
 *
 * Created by Hongyu Wang on 5/5/2016.
 */
public class GameScreen extends AbstractScreen{

    @Override
    protected void subclassInit() {

        GameEntity mainPlayer;
        Texture tex;
        Image image;
        image = new Image(tex = new Texture("BackgroundConcepts\\granit_texture4410.jpg"));
        stage.addActor(image);
        stage.addActor(mainPlayer = new Player("badlogic.jpg"));
        stage.setKeyboardFocus(mainPlayer);
        image.setPosition(0, 0);
        ((OrthographicCamera)stage.getCamera()).zoom = 1F;
        disposables.add(tex);
    }




    @Override
    protected void update(float delta) {
        super.update(delta);

        WorldFactory.getWorld().step(delta, 8, 3);


    }
}


