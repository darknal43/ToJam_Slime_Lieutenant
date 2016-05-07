package entities;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;
import driver.GameLoop;
import server.models.GameModel;
import state.screens.GameScreen;
import tools.Constants;

/**
 *
 * Created by Hongyu Wang on 5/5/2016.
 */
public class Player extends GameEntity {
    private InputHandler inputHandler;


    private static Animation animation;
    private static Animation specialEffects;

    //TODO Adjust this value based on size?
    private float speed = 10;

    private float totalDelta;




    private static void initiatePlayerAnimation(){
        if (animation != null) return;

        TextureAtlas textureAtlas = new TextureAtlas("player\\baseAnimation-packed\\pack.atlas");

        GameLoop.StaticDisposables.addDisposable(textureAtlas);
        Array<Sprite> assets = textureAtlas.createSprites();

        animation = new Animation(1F/12, assets);
        animation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);


    }

    private void initiateSprite(){
        totalDelta = 0;

        sprite = (Sprite)animation.getKeyFrame(totalDelta);

        sprite.setColor(1, 0, 0, 1);
    }


    @Override
    protected void init() {
        super.init();
        inputHandler = new InputHandler(this);
        initiatePlayerAnimation();
        initiateSprite();
    }


    public void shoot(){

    }


    //------------EVENT HANDLERS ------------------------------------------------------
    @Override
    public boolean fire(Event event) {
        return inputHandler.handleInput(event);
    }




    //-------- Your Update Loops ------------------------------------------------------
    @Override
    public void act(float delta) {
        move();
        updateSprite(delta);
        updateActor();
        super.act(delta);
    }



    private void move(){
        travelVector.setLength(speed);
        this.addAction(Actions.moveBy(travelVector.x, travelVector.y, 1));

    }

    private void updateSprite(float delta){
        sprite = (Sprite)animation.getKeyFrame(totalDelta);
        sprite.setPosition(getX() - sprite.getWidth() / 2, getY() - sprite.getHeight() / 2);
        sprite.setOriginCenter();
        sprite.setRotation(travelVector.angle());
        totalDelta += delta;
    }




    private void updateActor() {
        currentLocation.set(getX(), getY());
        GameScreen.CameraManager.updateCamera(currentLocation);
    }

    public void setMouseLocation(float x, float y){
        targetLocation = new Vector2(x, y);

        travelVector = targetLocation.sub(currentLocation);
    }


    //------------------------------------------------------------------------------------

    @Override
    public boolean remove() {

        return super.remove();
    }

    @Override
    public void serverUpdate() {

    }

    @Override
    public GameModel getModel() {
        return null;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        sprite.draw(batch);
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

