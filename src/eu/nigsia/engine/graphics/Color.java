package eu.nigsia.engine.graphics;

public class Color {
	
	public static final Color BLACK = new Color(0x000000);
	public static final Color WHITE = new Color(0xffffff);
	public static final Color RED = new Color(0xff0000);
	public static final Color GREEN = new Color(0x00ff00);
	public static final Color BLUE = new Color(0x0000ff);
	public static final Color ORANGE = new Color(0xffb300);
	public static final Color YELLOW = new Color(0xffff00);
	public static final Color TEST = new Color(0xf0d000);
	
	private int color;
	
	public Color(int col){
		this.color = col;
	}
	
	public Color(int r, int g, int b){
		this.color = getHex(r, g, b);
	}
	
	public int[] getRGB(){
		return getRGB(color);
	}
	
	public static int getHex(int r, int g, int b){
		return r << 16 | g << 8 | b;
	}
	
	public static int getHex(int a, int r, int g, int b){
		return a << 24 | r << 16 | g << 8 | b;
	}
	
	public static int[] getRGB(int hex){
		int r = (hex & 0xff0000) >> 16;
		int g = (hex & 0xff00) >> 8;
		int b = (hex & 0xff);
		return new int[] {r, b, g};
	}

	public static int[] getRGBA(int hex){
		int a = (hex & 0xff000000) >> 24;
		int r = (hex & 0xff0000) >> 16;
		int g = (hex & 0xff00) >> 8;
		int b = (hex & 0xff);
		return new int[] {a, r, b, g};
	}
	
	public int getColor(){
		return color;
	}
	public int toInt(){
		return (int) color;
	}
}
