package eu.nigsia.engine.graphics.geom;

import eu.nigsia.engine.graphics.Color;
import eu.nigsia.engine.graphics.Screen;

public class Rectangle extends Geometry2D{
	
	int x, y, width, height;
	static Color col;
	static Screen screen;
	int[] pixels;
	
	public Rectangle(int x, int y, int width, int height, Color col, Screen screen){
		super(x, y, width, height, col, screen);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		Rectangle.col = col;
		Rectangle.screen = screen;
		this.pixels = screen.getPixels();
	}
	
	public Rectangle(int x, int y, int width, int height){
		super(x, y, width, height, col, screen);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.pixels = screen.getPixels();
	}
	
	public void draw(){
		pixels = screen.getPixels();
		for(int xx = x; xx <= x + width; xx++){
			for(int yy = y; yy <= y + height; yy++){
				if((xx + yy * screen.getWidth()) >= this.pixels.length) continue;
				if((xx + yy * screen.getWidth()) <= 0) continue;
				pixels[xx + yy * screen.getWidth()] = col.getColor();
			}
		}
		setPixels(pixels);
	}
	
	protected void setPixels(int[] pixels){
		screen.setPixels(pixels);
		pixels = this.pixels = null;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public Color getColor() {
		return col;
	}
	
	public int getColorAt(int x, int y){
		int r = pixels[x+y*screen.getWidth()];
		return r;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public boolean intersects(Geometry2D g2d) {
		return (g2d.getX() >= getX() && g2d.getWidth() <= getWidth() && g2d.getY() >= getY() && g2d.getHeight() <= getHeight());
	}
}
