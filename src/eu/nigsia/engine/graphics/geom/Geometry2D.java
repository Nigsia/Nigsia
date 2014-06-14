package eu.nigsia.engine.graphics.geom;

import eu.nigsia.engine.graphics.Color;
import eu.nigsia.engine.graphics.Screen;

public abstract class Geometry2D {

	public Geometry2D(int x, int y, int width, int height, Color col, Screen screen){
		
	}
	
	public abstract int getHeight();
	public abstract int getWidth();
	public abstract Color getColor();
	public abstract int getColorAt(int x, int y);
	public abstract int getX();
	public abstract int getY();
	public abstract boolean intersects(Geometry2D g2d);
	public abstract void draw();
	protected abstract void setPixels(int[] pixels);
}
