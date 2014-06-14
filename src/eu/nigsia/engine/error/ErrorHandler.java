package eu.nigsia.engine.error;

import eu.nigsia.engine.levels.Level;

public class ErrorHandler {
	
	private static String s;

	public static final void fileNotFound(Object obj, Object problem, String a){
		s = obj + ": not found on Object " + problem + " " + a;
		show();
	}
	
	public static final void unableToReachLevel(Object cls, Level level, String a){
		s = level + ": couldn't reach the level on " + cls + " " + a;
		show();
	}
	
	public static final void unableToSetInputHandler(Object obj, String a){
		s = obj + ": couldn't set the input handler " + a;
		show();
	}
	
	public static final void nullRenderableInterface(Object obj, String a){
		s = obj + ": interface is null " + a;
	}
	
	private static final void show(){
		System.err.println(s);
	}
	
}
