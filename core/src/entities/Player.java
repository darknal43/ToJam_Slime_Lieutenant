package entities;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import driver.GameLoop;
import driver.GameLoopFactory;
import state.screens.AbstractScreen;
import tools.Constants;

/**
 *
 * Created by Hongyu Wang on 5/5/2016.
 */
public class Player extends GameEntity {
    private InputHandler inputHandler;
    private Vector2 currentLocation;
    private Vector2 targetLocation;
    private Animation animation;
    private Animation specialEffects;

    private float totalDelta;

    public Player(String spriteFilePath){
        super(spriteFilePath);
        TextureAtlas textureAtlas = new TextureAtlas("player\\baseAnimation-packed\\pack.atlas");

        this.assets = textureAtlas.createSprites();

        animation = new Animation(1F/12, assets);
        animation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        totalDelta = 0;
    }

    @Override
    protected void init() {
        super.init();
        inputHandler = new InputHandler(this);
        currentLocation = new Vector2(getX(), getY());
        targetLocation = new Vector2().setToRandomDirection();
    }


    public void shoot(){

    }


    //------------EVENT HANDLERS ------------------------------------------------------
    @Override
    public boolean fire(Event event) {
        return inputHandler.handleInput(event);
    }


    private void move(){
        float speed = 5;
        travelVector.setLength(speed);
        this.addAction(Actions.moveBy(travelVector.x, travelVector.y, 1));

    }

    private void updateSprite(float delta){
        sprite = (Sprite)animation.getKeyFrame(totalDelta);
        sprite.setPosition(getX() - sprite.getWidth()/2, getY() - sprite.getHeight()/2);
        sprite.setOriginCenter();
        sprite.setRotation(travelVector.angle());
        totalDelta += delta;
    }




    private void updateActor() {
        currentLocation.set(getX(), getY());
        AbstractScreen.CameraManager.updateCamera(currentLocation);
    }

    //-------- Your Update Loops ------------------------------------------------------
    @Override
    public void act(float delta) {
        move();

        updateSprite(delta);
        updateActor();
        super.act(delta);
    }




    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        sprite.draw(batch);
    }

    //------------------------------------------------------------------------------------

    @Override
    public boolean remove() {

        return super.remove();
    }


    public void setMouseLocation(float x, float y){
        targetLocation = new Vector2(x, y);

        travelVector = targetLocation.sub(currentLocation);
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

