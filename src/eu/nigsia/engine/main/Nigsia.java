package eu.nigsia.engine.main;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import eu.nigsia.engine.graphics.Color;
import eu.nigsia.engine.graphics.Display;
import eu.nigsia.engine.graphics.Screen;
import eu.nigsia.engine.graphics.Texture;
import eu.nigsia.engine.graphics.Window;
import eu.nigsia.engine.handlers.InputHandler;
import eu.nigsia.engine.interfaces.Renderable;
import eu.nigsia.engine.levels.GameStateManager;

public abstract class Nigsia implements Runnable, KeyListener, MouseListener{

	protected boolean isRunning = false;
	
	protected Screen screen;
	
	protected Display display;
	
	private Thread thread;
	
	private long startTimer = 0L;

	protected GameStateManager gsm;
	
	protected int frames = 0;
	protected int updates = 0;
	
	protected boolean hasElapsedCycle = false;
	
	public Nigsia(GameStateManager gsm){
		this.gsm = gsm;
		loadTextures();
		init();
	}
	
	protected final void start(){
		isRunning = true;
		if(thread == null){
			thread = new Thread(this, "Game");
			thread.start();
		}
	}
	
	protected final void createDisplay(String name, int width, int height, double scale){
		startTimer = System.nanoTime() / 1000000;
		display = new Display(new Window(name, width, height));
		screen = new Screen(width, height, scale);
		setInputHandler();
	}
	
	private void setInputHandler(){
		display.setInputHandler(this);
	}
	
	public Window getWindow(){
		return display.getWindow();
	}
	public final void run(){
		long lastTime = System.nanoTime();
		double delta = 0.0;
		double ns = Math.pow(10, 9) / 60.0;
		long timer = System.nanoTime() / 1000000;
		System.out.println("Took " + (((System.nanoTime() / 1000000) - startTimer) / 1000) + " to load");
		while(isRunning){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1){
				update();
				updates++;
				delta--;
			}
			render();
			frames++;
			if((System.nanoTime() / 1000000) - timer > 1000){
				timer += 1000;
				hasElapsedCycle = true;
				System.out.println(updates + " ups, " + frames + " fps");
				updates = 0;
				frames = 0;
			}
		}
	}
	
	protected final int[] getPixels() { 
		return screen.getPixels();
	}
	
	protected final void show(){
		screen.show();
		display.drawImage(screen.getImage());
		display.drawJavaImage(screen.getList());
		display.show();
	}
	protected Screen getScreen(){
		return screen;
	}
	
	protected final void clearScreen(Color col){
		screen.clear(col);
	}
	protected final void clearScreen(){
		screen.clear();
	}
	protected final void clearScreen(Texture tex){
		screen.clear(tex);
	}
	protected final void render(int x, int y, Renderable rend){
		rend.render(x, y, screen);
	}
	protected final void drawImage(Texture tex, int x, int y){
		tex.render(x, y, screen);
	}
	protected final void fillRect(int x, int y, int width, int height, Color col){
		screen.fillRect(x, y, width, height, col);
	}
	protected final void fillOval(int x, int y, int width, int height, Color col){
		screen.fillOval(x, y, width, height, col);
	}
	protected final void drawLine(int xOrigin, int yOrigin, int xEnd, int yEnd, Color col){
		screen.drawLine(xOrigin, yOrigin, xEnd, yEnd, col);
	}
	protected final void drawCircle(int x, int y, int radius, Color col){
		screen.drawCircle(x, y, radius, col);
	}
	protected final void drawPixels(int x, int y, int width, int height){
		screen.drawPixels(x,y,width,height);
	}
	protected final void drawString(String string, int x, int y, Font font, Color col){
		screen.drawString(string, x, y, font, col);
	}
	protected abstract void init();
	protected abstract void update();
	protected abstract void render();
	protected abstract void loadTextures();

	public final void keyPressed(KeyEvent e){
		InputHandler.keyPressed(e.getKeyCode(), true);
	}

	public final void keyReleased(KeyEvent e){
		InputHandler.keyPressed(e.getKeyCode(), false);
	}
	public abstract void keyPressed();
	
	public boolean isPressed(int key){
		return InputHandler.isPressed(key);
	}
	
	public void keyTyped(KeyEvent e) {
	}

	public void mouseClicked(MouseEvent arg0) {
	}

	public void mouseEntered(MouseEvent arg0) {
	}

	public void mouseExited(MouseEvent arg0) {
	}

	public void mousePressed(MouseEvent arg0) {
	}

	public void mouseReleased(MouseEvent arg0) {
	}


}
