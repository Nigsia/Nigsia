package eu.nigsia.engine.interfaces;

import java.awt.event.KeyEvent;

/*
 * Stands for GlobalVariabes
 */
public interface GV {
	
	//Key stuff
	public static final int NUM_KEYS = KeyEvent.KEY_LAST;
	public static final int GAME_KEY_UP = KeyEvent.VK_UP;
	public static final int GAME_KEY_DOWN = KeyEvent.VK_DOWN;
	public static final int GAME_KEY_LEFT = KeyEvent.VK_LEFT;
	public static final int GAME_KEY_RIGHT = KeyEvent.VK_RIGHT;

	//Level stuff
	public static final int NUM_GAMESTATES = 1;
	public static final int MENU_STATE = 0;
	
	//Textures stuff
	public static final int T_TEST = 0;
	public static final int T_BOARD = 1;
	
	
}
