package eu.nigsia.engine.utils;

import eu.nigsia.engine.entities.Entity;

public class AABB 
{
	private int width;
	private int height;
	private Entity e;
	
	public AABB(int width, int height, Entity e)
	{
		this.width = width;
		this.height = height;
		this.e = e;
	}
	
	public int getWidth() {return width;}
	public int getHeight(){return height;}
	public Entity getEntity(){return e;}
	
	
}
