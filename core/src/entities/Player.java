package entities;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import driver.GameLoop;
import driver.GameLoopFactory;
import tools.Constants;

/**
 *
 * Created by Hongyu Wang on 5/5/2016.
 */
public class Player extends GameEntity {
    private InputHandler inputHandler;
    private Vector2 targetVector;

    public Player(String spriteFilePath){
        super(spriteFilePath);



    }

    @Override
    protected void init() {
        super.init();
        inputHandler = new InputHandler(this);
        targetVector = new Vector2().setToRandomDirection().setLength(100);
    }


    public void shoot(){

    }


    //------------EVENT HANDLERS ------------------------------------------------------
    @Override
    public boolean fire(Event event) {
        return inputHandler.handleInput(event);
    }



    private void updateActorInfo(){




        body.setLinearVelocity(targetVector);
        setPosition(body.getPosition().x + sprite.getWidth() / 2, body.getPosition().y + sprite.getHeight() / 2);

        GameLoopFactory.getMainGameLoop().updateCamera(new Vector2(getX(), getY()));
        

        sprite.setPosition(body.getPosition().x, body.getPosition().y);


    }

    //-------- Your Update Loops ------------------------------------------------------
    @Override
    public void act(float delta) {
        super.act(delta);
        updateActorInfo();
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


    public void setMouseLocation(float x, float y){
        targetVector = new Vector2(x - getX(), y - getY()).setLength(1000);

    }


}



class InputHandler implements Constants {
    private Player player;

    public InputHandler(Player player){
        this.player = player;
    }

    public boolean handleInput(Event event){

        return event instanceof InputEvent && (Gdx.app.getType() == Application.ApplicationType.Desktop
                ? windowsHandleInput((InputEvent)event) : phoneHandleInput(event));
    }


    public boolean windowsHandleInput(InputEvent event){

        //This handles movement.
        if (event.getType() == InputEvent.Type.mouseMoved){
            player.setMouseLocation(event.getStageX(), event.getStageY());

            return true;
        }

        //This handles keyboard commands
        if (event.getType() == InputEvent.Type.keyDown){
            switch (event.getKeyCode()){
                //This is the shoot command XD.
                case Input.Keys.SPACE : player.shoot();
                    return true;


            }
        }

        return false;
    }

    /**
     * This should never be used for now
     * @param event The event to be handled
     * @return If it was handled or not.
     */
    boolean phoneHandleInput(Event event){
        throw new UnsupportedClassVersionError();
    }
}

