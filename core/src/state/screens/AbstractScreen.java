package state.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.Viewport;
import driver.GameLoopFactory;
import tools.Constants;
import tools.WorldFactory;

import java.util.Vector;

/**
 * This is the primary screen superclass.
 *
 * This will store elements common to all screens.
 *
 * Created by Hongyu Wang on 5/5/2016.
 */
public abstract class AbstractScreen implements Screen, Constants {

    protected World world;

    protected Stage stage;

    protected Array<Disposable> disposables;



    public AbstractScreen(){
        disposables = new Array<>();
        world = WorldFactory.getWorld();

    }




    private void init(){
        disposables.clear();
        stage = new Stage(){
            @Override
            public boolean scrolled(int amount) {
                System.out.println(amount);

                return super.scrolled(amount);
            }

            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                if (getKeyboardFocus() == null) return false;

                Vector2 screenInfo = new Vector2(screenX, screenY);
                Vector2 stageInfo = this.screenToStageCoordinates(screenInfo);

                InputEvent mouseMovedEvent = new InputEvent();
                mouseMovedEvent.setType(InputEvent.Type.mouseMoved);
                mouseMovedEvent.setStage(this);
                mouseMovedEvent.setStageX(stageInfo.x);
                mouseMovedEvent.setStageY(stageInfo.y);


                getKeyboardFocus().fire(mouseMovedEvent);
                return super.mouseMoved(screenX, screenY);
            }
        };
        disposables.add(stage);
        subclassInit();
    }


    /**
     * All disposable assets should be created here.
     */
    protected abstract void subclassInit();




    /**
     * All drawing should be done here.
     */
    protected void draw() {
        stage.draw();
    }

    /**
     * All updating information should be done here.
     * @param delta the change in delta time.
     */
    protected void update(float delta) {

        stage.act(delta);


    }





    @Override
    public void render(float delta) {

        draw();
        update(delta);
    }

    @Override
    public void show() {
        init();
        ((OrthographicCamera)stage.getCamera()).zoom = 1F;
        Gdx.input.setInputProcessor(new InputMultiplexer(CameraManager.getInputManager(), stage));

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        for (Disposable disposable : disposables){
            disposable.dispose();
        }
    }

    public Stage getStage(){
        return stage;
    }


    /**
     * This is an internal static CameraManager class
     */
    static public class CameraManager extends InputAdapter{
        static InputAdapter inputManager;
        private static OrthographicCamera getCamera(){
            AbstractScreen screen = (AbstractScreen) GameLoopFactory.getMainGameLoop().getScreen();

            return (OrthographicCamera)(screen).getStage().getCamera();
        }

        public static void updateCamera(Vector2 travelVector){

            getCamera().position.set(new Vector3(travelVector, 0));
        }

        public static void resetCameraRatio(){
            getCamera().zoom = 1;
        }

        public static void zoom(int dir){
            System.out.println(getCamera().zoom);
            if (dir < 0 && getCamera().zoom <= 0.4F || dir > 0 && getCamera().zoom >= 4F)
                return;
            getCamera().zoom += .2F*dir;
        }

        static InputAdapter getInputManager(){
            if (inputManager == null){
                inputManager = new CameraManager();
            }

            return inputManager;
        }


        @Override
        public boolean scrolled(int amount) {
            zoom(amount > 0 ? 1 : -1);
            return true;
        }

        @Override
        public boolean keyDown(int keycode) {
            if (keycode == Input.Keys.R){
                resetCameraRatio();
                return true;
            }

            return false;
        }

    }


}


