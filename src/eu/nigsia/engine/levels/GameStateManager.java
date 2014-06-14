package eu.nigsia.engine.levels;

import eu.nigsia.engine.error.ErrorHandler;
import eu.nigsia.engine.interfaces.GV;

public class GameStateManager {
	
	private Level[] levels;
	private int currentLevel;
	

	public GameStateManager(){
		this.levels = new Level[GV.NUM_GAMESTATES];
		currentLevel = GV.MENU_STATE;
	}
	
	public void loadState(int state, Level level){
		levels[state] = level;
	}
 	
	public void switchLevel(int state, Level level){
		levels[currentLevel] = null;
		currentLevel = state;
		loadState(currentLevel, level); 
	}
	
	public void unloadState(int state){
		if(state > GV.NUM_GAMESTATES) return;
		levels[state] = null;
		currentLevel = GV.MENU_STATE;
	}
	
	public void unloadCurrentState(){
		levels[currentLevel] = null;
	}
	
	public Level getLevel(int state){
		if(levels[state] == null){
			ErrorHandler.unableToReachLevel(this, levels[state], "@ getLevel");
			return null;
		}
		return levels[state];
	}
	
	public void update(){
		if(levels[currentLevel] == null){
			ErrorHandler.unableToReachLevel(this, levels[currentLevel], " @ updating");
			return;
		}
		levels[currentLevel].update();
	}
	
	public void draw(){
		if(levels[currentLevel] == null){
			ErrorHandler.unableToReachLevel(this, levels[currentLevel], " @ drawing");
			return;
		}
		levels[currentLevel].draw();
	}
}
