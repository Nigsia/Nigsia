package eu.nigsia.engine.entities.enemies;

import eu.nigsia.engine.entities.KineticEntity;
import eu.nigsia.engine.graphics.Texture;
import eu.nigsia.engine.utils.AABB;
import eu.nigsia.engine.utils.Vectors.Vector2Di;

public class Enemy extends KineticEntity{

	public Enemy(AABB box, Texture tex, Vector2Di pos) {
	    super(box, tex, pos);
    }
}
