package dev.scroopid.crafexEngine;

public abstract class GameThread extends Thread {
	
	private long FPS;

	private boolean exists = false;

	private boolean running = false;

	public GameThread() {
		FPS = 10;
	}
	
	public GameThread(int fps) {
		FPS = fps;
	}

	public void create() {
		this.exists = true;
	}

	public void kill() {
		this.exists = false;
	}

	public void pause() {
		this.running = false;
	}

	public void play() {
		this.running = true;
	}

	@Override
	public void run() {
		
		long frameLength = 1000 / FPS;
		long startingTime;
		long sleepTime;

		while (this.exists) {

			while (this.running) {
				startingTime = System.currentTimeMillis();
				
				this.threadStuff();
				
				sleepTime = frameLength - (System.currentTimeMillis() - startingTime);
				
				if(FPS > 0){
					try {
						if (sleepTime > 0)
							sleep(sleepTime);
						else
							sleep(15);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		}

	}

	@Override
	public synchronized void start() {
		this.play();
		this.create();
		super.start();
	}

	public abstract void threadStuff();

}
