package eu.nigsia.engine.graphics.geom;

import eu.nigsia.engine.graphics.Color;
import eu.nigsia.engine.graphics.Screen;

public class Line extends Geometry2D{
	
	private int xOrigin, yOrigin, xEnd, yEnd;
	private Color col;
	private Screen screen;
	private int pixels[];

	public Line(int xOrigin, int yOrigin, int xEnd, int yEnd, Color col, Screen screen){
		super(xOrigin, yOrigin, xEnd, yEnd, col, screen);
		this.xOrigin = xOrigin;
		this.xEnd = xEnd;
		this.yOrigin = yOrigin;
		this.yEnd = yEnd;
		this.col = col;
		this.screen = screen;
		this.pixels = screen.getPixels();
	}

	public int getHeight() {
		int yy = yEnd - yOrigin;
		return yy;
	}

	public int getWidth() {
		int xx = xEnd - xOrigin;
		return xx;
	}

	public int getLength(){
		int yy = getHeight();
		int xx = getWidth();
		return (int) (Math.sqrt((xx*xx) + (yy*yy)));
	}
	
	public Color getColor() {
		return col;
	}

	public int getColorAt(int x, int y) {
		return pixels[x+y*screen.getWidth()];
	}

	public int getX() {
		return xOrigin;
	}

	public int getY() {
		return yOrigin;
	}

	public boolean intersects(Geometry2D g2d) {
		// TODO
		return false;
	}

	public void draw() {
		pixels = screen.getPixels();
		int xxx = xEnd - xOrigin;
		int yyy = yEnd - yOrigin;
		double d =  (Math.sqrt((xxx*xxx)+(yyy*yyy)));
		double xSeg = xxx/d;
		double ySeg = yyy/d;
		for(int i = 0; i < getLength(); i++){
			if(xOrigin <= 0 || xEnd >= screen.getWidth() || yOrigin <= 0 || yEnd >= screen.getHeight()) continue;
			int xx =(int)  (xSeg * i + xOrigin);
			int yy =(int) (ySeg * i + yOrigin);
			pixels[xx+yy*screen.getWidth()] = col.getColor();
		}
		setPixels(pixels);
	}
	
	protected void setPixels(int[] pixels){
		screen.setPixels(pixels);
		pixels = this.pixels = null;
	}
	
}
