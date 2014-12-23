package eu.nigsia.engine.levels;

import java.util.List;

import eu.nigsia.engine.graphics.*;
import eu.nigsia.engine.interfaces.*;
import eu.nigsia.engine.entities.enemies.*;

public abstract class Level implements Renderable, Updatable{

	protected GameStateManager gsm;
	protected int width;
	protected int height;
	protected String levelName;
	protected GUI gui;
	
	public Level(GameStateManager gsm, Window window){
		this.gsm = gsm;
		this.width = window.getWidth();
		this.height = window.getHeight();
	}
	
	protected abstract void init();
	@Override
    public abstract void update();
	protected abstract void draw();
	protected abstract void handleInput();
	
	protected void addEnemy(List<Enemy> enemyList, Enemy newEnemy){
		enemyList.add(newEnemy);
	}

	protected void setGui(GUI gui){
		this.gui = gui;
	}
	
	@Override
	public String toString(){
		return "Level " + levelName;
	}
}
