package entities;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;

/**
 *
 * Created by Hongyu Wang on 5/5/2016.
 */
public class GameEntity extends Actor {

    @Override
    public boolean fire(Event event) {
        System.out.println(event.getClass().getCanonicalName());
        return false;
    }
}
