package state.stateManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import driver.GameLoopFactory;

/**
 *
 * Created by Hongyu Wang on 5/5/2016.
 */
public enum ScreenShell {

    TITLE_SCREEN("TitleScreen"),
    LEVEL_SELECTION_SCREEN("LevelSelectionScreen"),
    GAME_SCREEN("GameScreen"),
    END_SCREEN("EndScreen");


    private String name;
    private Screen screen;


    private Game game;

    ScreenShell(String name){
        this.name = "state.screens."+name;
        init();
    }


    private void init(){

        try {
            screen = (Screen)Class.forName(name).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            System.out.println(name + " is not initialized properly");
        }


        game = GameLoopFactory.getMainGameLoop();
    }


    public void setAsScreen(){
        game.setScreen(screen);
    }

    public static void dispose(){
        for (ScreenShell screenShell : ScreenShell.values()){
            screenShell.screen.dispose();
        }
    }


}
