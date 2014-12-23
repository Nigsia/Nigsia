package eu.nigsia.engine.entities;

import eu.nigsia.engine.graphics.Texture;
import eu.nigsia.engine.utils.AABB;
import eu.nigsia.engine.utils.Vectors.Vector2Di;


public class DynamicEntity extends KineticEntity
{

	public DynamicEntity(AABB box, Texture tex, Vector2Di pos)
    {
	    super(box, tex, pos);
	    
    }
	
}
