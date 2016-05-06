package entities;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;

/**
 *
 * Created by Hongyu Wang on 5/5/2016.
 */
public abstract class GameEntity extends Actor {

    public GameEntity(){
        this(0, 0, 0, 0);
    }



    public GameEntity(float x, float y, float width, float height){

    }


}