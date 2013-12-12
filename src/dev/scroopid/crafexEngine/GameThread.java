package dev.scroopid.crafexEngine;

public abstract class GameThread extends Thread {
	
	private boolean exists = false;
	private boolean running = false;
	
	public GameThread() {
		
	}
	
	@Override
	public synchronized void start() {
		create();
		super.start();
	}
	
	public void pause() {
		running = false;
	}
	
	public void play() {
		running = true;
	}
	
	public void create() {
		exists = true;
	}
	
	public void kill() {
		exists = false;
	}
	
	@Override
	public void run() {
		
		while(exists) {
			
			while(running) {
				threadStuff();
			}
			
		}
		
	}
	
	public abstract void threadStuff();

}
