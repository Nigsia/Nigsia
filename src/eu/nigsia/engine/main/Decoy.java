package eu.nigsia.engine.main;

import eu.nigsia.engine.graphics.Texture;
import eu.nigsia.engine.interfaces.GV;
import eu.nigsia.engine.levels.GameStateManager;
import eu.nigsia.engine.levels.Menu;
import eu.nigsia.engine.utils.Random;

public class Decoy extends Nigsia{
	
	private Texture box, map;
	
	public Decoy(GameStateManager gsm){
		super(gsm);
	}

	public static void main(String[] args) {
		new Decoy(new GameStateManager());
	}
	
	protected void init() {
		createDisplay("Decoy", 800, 600, 1.0);
		gsm.loadState(GV.MENU_STATE, new Menu(gsm, getWindow()));
		start();
	}

	protected void loadTextures(){
		Texture.loadTexture(GV.T_TEST, "test.png");
		Texture.loadTexture(GV.T_BOARD, "board.png");
		box = Texture.getTexture(GV.T_TEST);
		map = Texture.getTexture(GV.T_BOARD);
	}
	float x = 0;
	float bx = 0;
	float by = 0;
	int r;
	protected void update() {		
		keyPressed();
		gsm.update();
		x -= 1.1;
		bounce();
	}
	
	private boolean hasBounced = false;
	private void bounce(){
		if(!hasBounced) by += 3.2;
		if(hasBounced) by -= 3.2;
		bx += 1.2;
		if(box.getY() + box.getHeight() >= 600){
			r = Random.nextInt(150, 300);
			hasBounced = true;
		}
		if(box.getY() <= r) hasBounced = false;
	}
	
	protected void render() {
		clearScreen();
		drawImage(map, (int)x, 0);
		drawImage(box,(int) bx,(int) by);
		gsm.draw();
		show();
	}

	public void keyPressed() {
		
	}
}
