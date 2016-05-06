package entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.graphics.Texture;
import tools.Constants;
import tools.WorldFactory;

/**
 *
 * Created by Hongyu Wang on 5/5/2016.
 */
public class Player extends GameEntity {



    public Player(String spriteFilePath){
        super(spriteFilePath);


    }





    //------------EVENT HANDLERS ------------------------------------------------------
    @Override
    public boolean fire(Event event) {

        return InputHandler.handleInput(event);
    }



    //---------------------------------------------------------------------------------


    //-------- Your Update Loops ------------------------------------------------------
    @Override
    public void act(float delta) {
        super.act(delta);
        sprite.setX(getX());
        sprite.setY(getY());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        sprite.draw(batch, parentAlpha);
    }

    //------------------------------------------------------------------------------------

    @Override
    public boolean remove() {

        return super.remove();
    }

    static class InputHandler implements Constants {
        static boolean handleInput(Event event){
            return DESKTOP ? windowsHandleInput(event) : phoneHandleInput(event);

        }


        static boolean windowsHandleInput(Event event){
            //TODO HandleInput
            return true;
        }

        static boolean phoneHandleInput(Event event){
            throw new UnsupportedClassVersionError();
        }
    }

}



