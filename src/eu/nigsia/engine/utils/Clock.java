package eu.nigsia.engine.utils;

public class Clock {

	private long timer = 0;
	private int seconds = 0, minutes = 0, hours = 0;
	private boolean isCounterClockwise = false;
	
	public Clock(){
		timer = getTime();
	}
	
	public Clock(long time){
		timer = getTime();
		isCounterClockwise = true;
		seconds = (int) time;
	}
	
	public void update(){
		if(!isCounterClockwise){
			if((getTime()) - timer > 1000){
				timer += 1000;
				seconds++;
			}
			timerLogic();
		}else{
			if((getTime()) - timer > 1000){
				timer += 1000;
				seconds--;
			}
		}
	}
	
	private void timerLogic(){
		if(seconds >= 60){
			minutes++;
			seconds = 0;
		}
		if(minutes >= 60){
			hours++;
			minutes = 0;
		}
	}
	
	private long getTime(){
		return System.nanoTime() / 1000000;
	}

	public int getSeconds(){
		return seconds;
	}
	
	public int getMinutes(){
		return minutes;
	}
	
	public int getHours(){
		return hours;
	}
}
