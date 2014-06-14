package eu.nigsia.engine.interfaces;

import eu.nigsia.engine.graphics.Screen;

public interface Renderable {

	public int getWidth();
	
	public int getHeight();
	
	public int[] getPixels();
	
	public void render(int x, int y, Screen screen);
	
}
