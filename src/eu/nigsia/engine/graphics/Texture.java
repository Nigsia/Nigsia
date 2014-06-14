package eu.nigsia.engine.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import eu.nigsia.engine.error.ErrorHandler;
import eu.nigsia.engine.graphics.geom.Geometry2D;
import eu.nigsia.engine.graphics.geom.Rectangle;
import eu.nigsia.engine.handlers.ResourceManager;
import eu.nigsia.engine.interfaces.Renderable;

public class Texture implements Renderable{
	
	private int x, y, width, height;
	private int[] pixels, pixelsrgb;
	private static ResourceManager rm;
	
	public static final int FORMAT_RGB = 0x0;
	public static final int FORMAT_ARGB = 0x1;
	
	private Texture(int width, int height, int[] pixels){
		this.width = width; 
		this.height = height;
		this.pixels = pixels;
	}
	
	public static void loadTexture(int key, String path){
		if(rm == null) rm = new ResourceManager();
		try{
			rm.loadTextue(key, path);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static Texture getTexture(int key){
		int width = 0, height = 0;
		int[] pixels = null;
		if(rm.getTexture(key) == null){
			ErrorHandler.fileNotFound(Texture.class, null, "@ get tex");
			return null;
		}
		BufferedImage img = rm.getTexture(key);
		width = img.getWidth();
		height = img.getHeight();
		pixels = new int[width * height];
		img.getRGB(0,0,width, height, pixels, 0, width);
		return new Texture(width, height, pixels);
	}
	
	public int[] getPixels(int format){
		if(format == FORMAT_ARGB) return pixels;
		if(format == FORMAT_RGB && pixelsrgb != null) return pixelsrgb;
		if(width == 0 && height == 0){
			System.err.println("Load a texture first");
			return null;
		}
		pixelsrgb = new int[width * height];
		for(int i = 0; i < pixelsrgb.length; i++){
			int r = (pixels[i] & 0xff0000) >> 16;
			int g = (pixels[i] & 0xff00) >> 8;
			int b = (pixels[i] & 0xff);
			pixelsrgb[i] = r << 16 | g  << 8 | b;
		}
		return pixelsrgb;
	}
	
	public Texture splitTexture(Texture tex, int x, int y, int width, int height){
		int tempPix[] = tex.getPixels(FORMAT_RGB);
		int pixels[] = new int[tempPix.length];
		for(int xx = x; xx < x + width; xx++){
			for(int yy = y; yy < y + height; yy++){
				int px = (xx-x)+(yy-y)*width;
				System.out.println(px);
				pixels[px] = tempPix[xx+yy*width];
			}
		}
		return new Texture(width, height, pixels);
	}
	
	public Texture splitTexture(float xf, float yf, float widthf, float heightf){
		int x = (int)xf, y = (int)yf, width = (int)widthf, height = (int)heightf;
		int tempPix[] = this.getPixels(FORMAT_RGB);
		int pixels[] = new int[tempPix.length];
		for(int yy = 0; yy < height; yy++){
			int yp = yy + Math.abs(y);
			for(int xx = 0; xx < width; xx++){
				int xp = xx + Math.abs(x);
				pixels[xp+yp*width] = tempPix[x+y*this.getWidth()];
			}
		}
		return new Texture(width, height, pixels);
	}

	public int[] getPixels() {
		if(width == 0 && height == 0){
			System.err.println("Load a texture first");
			return null;
		}
		pixelsrgb = new int[width * height];
		for(int i = 0; i < pixelsrgb.length; i++){
			int r = (pixels[i] & 0xff0000) >> 16;
			int g = (pixels[i] & 0xff00) >> 8;
			int b = (pixels[i] & 0xff);
			pixelsrgb[i] = r << 16 | g  << 8 | b;
		}
		return pixelsrgb;
	}
	
	public void render(int x, int y, Screen screen) {
		this.x = x;
		this.y = y;
		screen.drawImage(this, x, y);
	}

	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public boolean intersects(Geometry2D g2d){
		return(new Rectangle(x, y, width, height).intersects(g2d));
	}
	
	public Rectangle bounds(){
		return new Rectangle(x, y, width, height);
	}
}
