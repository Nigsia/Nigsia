package eu.nigsia.engine.levels;

import eu.nigsia.engine.graphics.Window;
import eu.nigsia.engine.interfaces.Renderable;
import eu.nigsia.engine.interfaces.Updatable;

public abstract class Level implements Renderable, Updatable{

	protected GameStateManager gsm;
	protected int width;
	protected int height;
	
	public Level(GameStateManager gsm, Window window){
		this.gsm = gsm;
		this.width = window.getWidth();
		this.height = window.getHeight();
	}
	
	protected abstract void init();
	public abstract void update();
	protected abstract void draw();
	protected abstract void handleInput();
	
	public String toString(){
		return "Level";
	}
}
