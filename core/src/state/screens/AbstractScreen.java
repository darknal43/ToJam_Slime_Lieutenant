package state.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;

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
        initSubclass();
    }


    /**
     * All disposable assets should be created here.
     */
    protected abstract void initSubclass();


    /**
     * All drawing should be done here.
     */
    protected abstract void draw();

    /**
     * All updating information should be done here.
     * @param delta the change in delta time.
     */
    protected abstract void update(float delta);







    @Override
    public void render(float delta) {
        draw();
        update(delta);
    }

    @Override
    public void show() {
        init();

        InputMultiplexer im = new InputMultiplexer();

        Gdx.input.setInputProcessor(stage);

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
        stage.dispose();
    }
}
