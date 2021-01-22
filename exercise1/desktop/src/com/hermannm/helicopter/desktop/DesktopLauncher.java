package com.hermannm.helicopter.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.hermannm.helicopter.Helicopter;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Helicopter.WIDTH;
		config.height = Helicopter.HEIGHT;
		config.title = Helicopter.TITLE;
		new LwjglApplication(new Helicopter(), config);
	}
}
