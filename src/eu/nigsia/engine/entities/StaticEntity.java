package eu.nigsia.engine.entities;

import eu.nigsia.engine.graphics.Texture;
import eu.nigsia.engine.utils.AABB;
import eu.nigsia.engine.utils.Vectors.Vector2Di;


public class StaticEntity extends Entity implements IStaticEntity
{
	
	protected boolean isPushable;

	public StaticEntity(AABB box, Texture tex, Vector2Di pos) 
	{
	    super(EEntityType.StaticEntity, box, tex, pos); 
    }

	@Override
    public void isPushable(boolean b)
    {
		this.isPushable = b;
    }

	@Override
    public boolean getPushable()
    {
	    return isPushable;
    }
	
}
