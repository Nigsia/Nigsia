package eu.nigsia.engine.entities;

import eu.nigsia.engine.graphics.Texture;
import eu.nigsia.engine.utils.AABB;
import eu.nigsia.engine.utils.Vectors.Vector2Di;


public class KineticEntity extends Entity implements IKineticEntity
{

	protected double g;
	
	public KineticEntity(AABB box, Texture tex, Vector2Di pos) 
	{
	    super(EEntityType.KineticEntity, box, tex, pos);
    }
	
	public void setGravity(double g){this.g = g;}

	@Override
    public double getGravity()
    {
	    return g;
    }

	@Override
    public void move()
    {}

	@Override
    public void setSpeed(double sp)
    {}
	
}
