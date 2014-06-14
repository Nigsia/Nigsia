package eu.nigsia.engine.graphics;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.LinkedList;

import eu.nigsia.engine.error.ErrorHandler;
import eu.nigsia.engine.graphics.geom.Line;
import eu.nigsia.engine.graphics.geom.Rectangle;
import eu.nigsia.engine.interfaces.JavaImage;
import eu.nigsia.engine.interfaces.Renderable;
import eu.nigsia.engine.utils.Random;

public class Screen {

	private int width, height;
	private double scale = 1.0;
	private int[] pixels, imagePixels;
	private LinkedList<JavaImage> render = new LinkedList<JavaImage>();
	private BufferedImage img;
	
	public Screen(int width, int height, double scale){
		this.scale = scale;
		this.width = (int)(width / scale);
		this.height = (int)(height / scale);
		pixels = new int[this.width * this.height];
		img = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
		imagePixels = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();
	}
	
	public void clear(Color col){
		for(int i = 0; i < pixels.length; i++){
			pixels[i] = col.getColor();
		}
	}
	public void clear(){
		clear(Color.BLACK);
	}
	public void clear(Texture tex){
		for(int y = 0; y < tex.getHeight(); y++){
			for(int x = 0; y < tex.getWidth(); x++){
				if(x < 0 || x > this.width || y < 0 || y > this.height) continue;
				pixels[x + y * width] = tex.getPixels(Texture.FORMAT_RGB)[x+y*tex.getWidth()];
			}
		}
	}
	
	public int[] getPixels(){
		return pixels;
	}
	
	public int getHeight(){
		return height;
	}
	public int getWidth(){
		return width;
	}
	public BufferedImage getImage(){
		return img;
	}
	public void show(){
		for(int i = 0; i < pixels.length; i++){
			imagePixels[i] = pixels[i];
		}
	}
	public void fillRect(int x, int y, int width, int height, Color col){
		Rectangle rectangle = new Rectangle(x, y, width, height, col, this);
		rectangle.draw();
	}
	
	public void drawLine(int xO, int yO, int xE, int yE, Color col){
		new Line(xO, yO, xE, yE, col, this).draw();
	}
	public void drawPixels(int x, int y, int width, int height){
		for(int xx = x; xx < x + width; xx++){
			for(int yy = y; yy < y + height; yy++){
				if(xx+yy*this.width > pixels.length -1 || xx+yy*this.width <= 0) continue;
				pixels[xx+yy*this.width] = Random.nextInt(0xffffff, 0x000000);
			}
		}
	}
	
	public void drawCircle(int x, int y, int radius, Color col){
		for(int i = 0; i < 360; i++){
			double angle = i * Math.PI / 180;
			double x1 = radius * Math.sin(angle);
			double y1 = radius * Math.cos(angle);
			int xx = (int) (x1 + x);
			int yy = (int) (y1 + y);
			if((xx+yy*this.width) > pixels.length -1 || (xx+yy*this.width) <= 0) continue;
			pixels[xx+yy*this.width] = col.getColor();
		}
	}
	
	public void drawString(final String s,final int x,final int y,final Font font,final Color col){
		render.add(new JavaImage(){
			public void draw(Graphics graphics){
				int[] rgb = col.getRGB();
				graphics.setFont(font);
				graphics.setColor(new java.awt.Color(rgb[0], rgb[1], rgb[2]));
				graphics.drawString(s, x, y);
			}
		});
	}

	public void fillOval(final int x,final int y,final int width,final int height,final Color col){
		render.add(new JavaImage(){
			public void draw(Graphics graphics){
				int[] rgb = col.getRGB();
				graphics.setColor(new java.awt.Color(rgb[0], rgb[1], rgb[2]));
				graphics.fillOval(x, y, width, height);
			}
		});
	}
	
	public void drawImage(Texture tex, int x, int y){
		if(tex == null){
			ErrorHandler.fileNotFound(this, tex, " @ draw Image");
			return;
		}
		for(int yy = 0; yy < tex.getHeight(); yy++){
			int yp = yy+y;
			for(int xx = 0; xx < tex.getWidth(); xx++){
				int xp = xx + x;
				if(xp < 0 || xp >= this.width ||yp < 0 || yp >= this.height) continue;
				if(xp + yp * this.width > pixels.length) continue;
				pixels[xp + yp * this.width] = tex.getPixels(Texture.FORMAT_RGB)[xx + yy * tex.getWidth()];
			}
		}
	}
	
	public void drawImage(Texture tex, int x, int y, int width, int height){
		if(tex == null){
			ErrorHandler.fileNotFound(this, tex, " @ draw Image");
			return;
		}
		for(int yy = 0; yy < height; yy++){
			int yp = yy+y;
			for(int xx = 0; xx < width; xx++){
				int xp = xx + x;
				if(xp < 0 || xp >= this.width ||yp < 0 || yp >= this.height) continue;
				if(xp + yp * this.width > pixels.length) continue;
				pixels[xp + yp * this.width] = tex.getPixels(Texture.FORMAT_RGB)[xx + yy * tex.getWidth()];
			}
		}
	}
	
	public void drawImage(Texture tex, float xf, float yf, float widthf, float heightf){
		if(tex == null){
			ErrorHandler.fileNotFound(this, tex, " @ draw Image");
			return;
		}
		int x = (int) xf;
		int y = (int) yf;
		int width = (int) widthf;
		int height = (int) heightf;
		for(int yy = 0; yy < height; yy++){
			int yp = yy+y;
			for(int xx = 0; xx < width; xx++){
				int xp = xx + x;
				if(xp < 0 || xp >= width ||yp < 0 || yp >= height) continue;
				if(xp + yp * width > pixels.length) continue;
				pixels[xp + yp * width] = tex.getPixels(Texture.FORMAT_RGB)[xx + yy * tex.getWidth()];
			}
		}
	}
	
	public void render(int x, int y,  Renderable rend){
		if(rend == null){
			ErrorHandler.nullRenderableInterface(this, "@ render (line ~90");
			return;
		}
		for(int yy = 0; yy <= rend.getHeight(); yy++){
			int yp = yy + y;
			for(int xx = 0; xx <= rend.getWidth(); x++){
				int xp = xx + x;
				if(xp < 0 || xp > this.width ||yp < 0 || yp > this.height) continue;
				pixels[xp + yp * this.width] = rend.getPixels()[xx + yy * rend.getWidth()];
			}
		}
	}
	
	public final void setPixels(int[] pix){
		for(int i = 0; i < pix.length; i++){
			pixels[i] = pix[i];
		}
	}
	
	public LinkedList<JavaImage> getList(){
		return render;
	}
	
	public double getScale(){
		return scale;
	}
}
