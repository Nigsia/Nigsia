package eu.nigsia.engine.graphics;

import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import eu.nigsia.engine.error.ErrorHandler;
import eu.nigsia.engine.interfaces.JavaImage;

public class Display {
	
	private static boolean shouldClearScreen = true;
	private Window window;
	private Graphics g;
	private BufferStrategy bs;
	
	public Display(Window window){
		this.window = window;
		createBufferStrategy();
		bs = window.getBufferStrategy();
		g = bs.getDrawGraphics();
	}
	
	protected Graphics getGraphics(){
		return g;
	}
	
	public void setInputHandler(Object obj){
		if(window == null){
			ErrorHandler.unableToSetInputHandler(this, " @ setInputHandler()");
			return;
		}
		window.addKeyListener((KeyListener) obj);
	}
	
	private void createBufferStrategy(){
		window.createBufferStrategy(3);
	}
	
	public Window getWindow(){
		return window;
	}
	
	public void drawImage(BufferedImage img){
		g = bs.getDrawGraphics();
		g.drawImage(img, 0, 0, (int) (window.getWidth()), (int) (window.getHeight()), null);
	}
	
	public void show(){
		g.dispose();
		bs.show();
	}

	public void drawJavaImage(LinkedList<JavaImage> list) {
		for(int i = 0; i < list.size();  i++){
			list.get(i).draw(g);
		}
		if(shouldClearScreen){
			list.clear();
		}
	}
	public static void shouldClear(boolean b){
		shouldClearScreen = b;
	}

}
