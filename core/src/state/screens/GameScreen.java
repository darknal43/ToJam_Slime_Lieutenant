package state.screens;

import entities.GameEntity;
import entities.Player;

/**
 *
 * Created by Hongyu Wang on 5/5/2016.
 */
public class GameScreen extends AbstractScreen{
    //TODO Remove this

    @Override
    protected void subclassInit() {

        GameEntity mainPlayer;

        stage.addActor(mainPlayer = new Player("badlogic.jpg"));
        stage.setKeyboardFocus(mainPlayer);


    }




    @Override
    protected void update(float delta) {
        super.update(delta);



    }
}


