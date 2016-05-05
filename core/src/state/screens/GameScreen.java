package state.screens;

import entities.GameEntity;

/**
 *
 * Created by Hongyu Wang on 5/5/2016.
 */
public class GameScreen extends AbstractScreen{


    @Override
    protected void subclassInit() {
        GameEntity ge;
        stage.addActor(ge = new GameEntity());
        stage.setKeyboardFocus(ge);
    }

    @Override
    protected void draw() {

    }

    @Override
    protected void update(float delta) {

    }


}
