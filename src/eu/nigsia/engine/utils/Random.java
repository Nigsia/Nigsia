package eu.nigsia.engine.utils;

public class Random {

	public static int nextInt(){
		return (int) Math.random();
	}
	
	public static int nextInt(int aStart, int aEnd){
	    long range = aEnd - aStart + 1;
	    long fraction = (long)(range * Math.random());
	    int randomNumber =  (int)(fraction + aStart);
	    return randomNumber;
	}
	
	public static int nextInt(int aEnd){
		return (int) ((aEnd + 1) * Math.random());
	}
	
}
