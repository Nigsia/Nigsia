package eu.nigsia.engine.main;

import static eu.nigsia.engine.graphics.Texture.*;
import eu.nigsia.engine.graphics.Texture;
import eu.nigsia.engine.interfaces.GV;
import eu.nigsia.engine.levels.GameStateManager;
import eu.nigsia.engine.levels.Menu;

public class Decoy extends Nigsia
{
	
	private Texture box, map;
	
	public Decoy(GameStateManager gsm)
	{
		super(gsm);
	}

	public static void main(String[] args) 
	{
		new Decoy(new GameStateManager());
	}
	
	@Override
    protected void init() 
	{
		createDisplay("Decoy", 800, 600, 1.0);
		gsm.loadState(GV.MENU_STATE, new Menu(gsm, getWindow()));
		start();
	}

	@Override
    protected void loadTextures()
	{
		loadTexture(GV.T_TEST, "test.png");
		loadTexture(GV.T_BOARD, "board.png");
		box = getTexture(GV.T_TEST);
		map = getTexture(GV.T_BOARD);
	}
	
	float x = 0;
	float bx = (screen.getWidth() - box.getWidth()) / 2;
	float by = (screen.getHeight() - box.getHeight()) / 2;
	
	@Override
    protected void update()
	{		
		keyPressed();
		gsm.update();
		x -= 1.1;
	}
	
	@Override
    protected void render()
	{
		clearScreen();
		drawImage(map, (int)x, 0);
		drawImage(box,(int) bx,(int) by);
		gsm.draw();
		show();
	}

	@Override
    public void keyPressed()
	{
		
	}
}
