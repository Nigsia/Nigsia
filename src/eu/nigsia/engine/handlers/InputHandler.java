package eu.nigsia.engine.handlers;

import eu.nigsia.engine.interfaces.GV;

public class InputHandler {

	private static boolean[] keys = new boolean[GV.NUM_KEYS];
	private static boolean[] pkeys = new boolean[GV.NUM_KEYS];
	
	public static void keyPressed(int key, boolean b){
		keys[key] = b;
	}
	public static boolean isPressed(int key){
		if(keys[key] == false) return false;
		return keys[key];
	}

	public static boolean anyKeyPressed(){
		for(int i = 0; i < keys.length; i++){
			if(keys[i]) return true;
		}
		return false;
	}

	public static void update(){
		for(int i = 0; i < keys.length; i++){
			pkeys[i] = keys[i];
		}
	}
}
