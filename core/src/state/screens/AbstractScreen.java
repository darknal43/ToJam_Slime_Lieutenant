package state.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;
import tools.WorldFactory;

import java.util.ArrayList;

/**
 * This is the primary screen superclass.
 *
 * This will store elements common to all screens.
 *
 * Created by Hongyu Wang on 5/5/2016.
 */
public abstract class AbstractScreen implements Screen {

    protected Stage stage;

    protected ArrayList<Disposable> disposables;



    public AbstractScreen(){
        disposables = new ArrayList<>();
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
        update(delta);
    }

    @Override
    public void show() {
        init();

        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void resize(int width, int height) {
        //We need to maintain an aspect ratio.


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
