package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import driver.GameLoopFactory;
import tools.Constants;


public class DesktopLauncher implements Constants {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        config.resizable = false;
        config.fullscreen = Constants.SCALE == 1.0F;
		config.width = (int)(WIDTH);
		config.height = (int)(HEIGHT);
		config.title = "Slime - Combat Evolved";
		config.samples = 8;
		new LwjglApplication(GameLoopFactory.getMainGameLoop(), config);
	}
}
