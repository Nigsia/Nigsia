package eu.nigsia.engine.entities;

import eu.nigsia.engine.graphics.Texture;
import eu.nigsia.engine.utils.AABB;
import eu.nigsia.engine.utils.Vectors.Vector2Di;

enum EEntityType
{
	StaticEntity, KineticEntity;
}

public abstract class Entity 
{
	
	protected EEntityType type;
	protected AABB box;
	protected Texture tex;
	protected Vector2Di pos;
	
	public Entity(EEntityType type, AABB box, Texture tex, Vector2Di pos)
	{
		this.type = type;
		this.box = box;
		this.tex = tex;
		this.pos = pos;
	}
	
	public boolean intersects(Entity e)
	{
		return(this.pos.x > e.pos.x && this.pos.x+box.getWidth() < e.pos.x+e.box.getWidth() && 
		   this.pos.y > e.pos.y && this.pos.y+box.getHeight() < e.pos.y+e.box.getHeight());
	}
	
	public int getX(){return this.pos.x;}
	public int getY(){return this.pos.y;}
	public Vector2Di getPosition(){return this.pos;}
	public AABB getAABB(){return this.box;}
	public Texture getTexture(){return tex;}
	public EEntityType getType(){return this.type;}

}
