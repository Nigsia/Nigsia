package eu.nigsia.engine.graphics;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

public class Window extends Canvas{
	private static final long serialVersionUID = 1L;
	
	private String title;
	private int width, height;
	private JFrame frame;
	
	public Window(String title, int width, int height){
		this.title = title;
		this.width = width;
		this.height = height;
		create();
	}
	
	private void create(){
		this.frame = new JFrame();
		Dimension size = new Dimension(width, height);
		setMinimumSize(size);
		setMaximumSize(size);
		setPreferredSize(size);
		frame.setResizable(false);
		frame.setTitle(title);
		frame.add(this);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setFocusable(true);
		frame.setVisible(true);
	}
	
	public int getHeight(){
		return height;
	}
	
	public int getWidth(){
		return width;
	}
}
