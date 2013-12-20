package dev.scroopid.crafexEngine;

public abstract class GameThread extends Thread {
	
	/**frames per second to limit the thread to*/
	private long FPS;

	/**does the thread exists*/
	private boolean exists = false;

	/**is the thread running*/
	private boolean running = false;

	/**
	 * crafex thread.
	 */
	public GameThread() {
		FPS = 10;
	}
	
	/**
	 * crafex thread.
	 * @param fps to run at
	 */
	public GameThread(int fps) {
		FPS = fps;
	}

	/**
	 * creates the thread
	 */
	public void create() {
		this.exists = true;
	}

	/**
	 * kills the thread
	 */
	public void kill() {
		this.exists = false;
	}

	/**
	 * pauses the thread
	 */
	public void pause() {
		this.running = false;
	}

	/**
	 * plays the thread
	 */
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

	/**
	 * stuff to do in thread.
	 */
	public abstract void threadStuff();

}
