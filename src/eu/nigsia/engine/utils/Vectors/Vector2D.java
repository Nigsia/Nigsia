package eu.nigsia.engine.utils.Vectors;

public class Vector2D {

	private float x, y;
	
	public Vector2D(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public static float vectorialProduct(Vector2D v1, Vector2D v2){
		return (v1.x * v2.y - v1.y * v2.x); 
	}
	
	
}
