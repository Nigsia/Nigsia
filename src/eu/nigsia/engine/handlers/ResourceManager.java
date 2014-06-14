package eu.nigsia.engine.handlers;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import eu.nigsia.engine.error.ErrorHandler;

public class ResourceManager {

	private static HashMap<Integer, BufferedImage> textures = new HashMap<Integer, BufferedImage>();;
	
	public void loadTextue(int key, String path) throws IOException{
		BufferedImage img = null;
		img = ImageIO.read(getClass().getResourceAsStream("/" + path));
		if(img == null){
			ErrorHandler.fileNotFound(ResourceManager.class, img, "@ loadTexture");
			return;
		}
		textures.put(key, img);
	}
	
	public BufferedImage getTexture(int key){
		return textures.get(key);
	}
	
	public void disposeTexture(int key){
		textures.remove(key);
	}
}
