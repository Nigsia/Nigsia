package eu.nigsia.engine.utils;

public class Timer {

	private float tick = 0.0f;
	long timer = System.nanoTime() / 1000000;
	private boolean hasElapsedCycle = false;

	public Timer(float tick){
		this.tick = tick;
	}
	
	public Timer(){
		
	}
	
	public void update(){
		if((getTime() / 1000000) - timer > tick * 1000){
			timer += 1000;
			hasElapsedCycle = true;
		}
	}

	public void setTick(float tick){
		this.tick = tick;
	}
	
	public boolean hasElapsed(){
		return hasElapsedCycle;
	}
	
	public long getTime(){
		return System.nanoTime();
	}
}
