package eu.nigsia.engine.levels;

import eu.nigsia.engine.error.ErrorHandler;
import eu.nigsia.engine.interfaces.GV;

public class GameStateManager {
	
	private Level[] levels;
	private int currentLevel;
	

	public GameStateManager(){
		this.levels = new Level[GV.NUM_GAMESTATES];		//Array with the number of levels
		currentLevel = GV.MENU_STATE;					//Set the current level to the menu level
	}
	
	public void loadState(int state, Level level){
		levels[state] = level;							//Load the state {@type int} into the level;  
	}
 	
	public void switchLevel(int state, Level level){
		levels[currentLevel] = null;					//Switch the level from the current level to a new state (level)
		currentLevel = state;
		loadState(currentLevel, level); 
	}
	
	public void unloadState(int state){
		if(state > GV.NUM_GAMESTATES || state < 0) return;			//Unload the state, checking if the state passed as a parameter is not less than 0 nor bigger than the number of gamestates
		levels[state] = null;										//Sets the state from the level array to nullm and sets the currentLevel to the MenuState
		if(state == GV.MENU_STATE){
			currentLevel = GV.MENU_STATE;
		}
	}
	
	public void unloadCurrentState(){
		levels[currentLevel] = null;
	}
	
	public Level getLevel(int state){								//Get the level {@Italic state} as an actual Level
		if(levels[state] == null){
			ErrorHandler.unableToReachLevel(this, levels[state], "@ getLevel");
			return null;
		}
		return levels[state];
	}
	
	public void update(){											// Update the currentLevel
		if(levels[currentLevel] == null){
			ErrorHandler.unableToReachLevel(this, levels[currentLevel], " @ updating");
			return;
		}
		levels[currentLevel].update();
	}
	
	public void draw(){												//draw the currentLevel
		if(levels[currentLevel] == null){
			ErrorHandler.unableToReachLevel(this, levels[currentLevel], " @ drawing");
			return;
		}
		levels[currentLevel].draw();
	}
}
