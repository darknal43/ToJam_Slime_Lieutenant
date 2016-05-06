package state.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import tools.Constants;
import tools.WorldFactory;

/**
 * This is the primary screen superclass.
 *
 * This will store elements common to all screens.
 *
 * Created by Hongyu Wang on 5/5/2016.
 */
public abstract class AbstractScreen implements Screen, Constants {

    private float totalDelta;

    protected World world;

    protected Stage stage;

    protected Array<Disposable> disposables;



    public AbstractScreen(){
        disposables = new Array<>();
        world = WorldFactory.getWorld();

        init();
    }

    private void init(){
        disposables.clear();
        stage = new Stage();
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
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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

        totalDelta += delta;
        if (totalDelta >= 1F/FRAME_RATE) {
            update(delta);
            totalDelta = 0;
        }

    }

    @Override
    public void show() {
        init();

        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void resize(int width, int height) {
        //TODO We need to maintain an aspect ratio.
        //Consider making this resizeable??


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
}
