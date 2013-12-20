package dev.scroopid.crafexEngine;

public abstract class GameThread extends Thread {
	
	/**frames per second to limit the thread to*/
	private long FPS;

	/**does the {@link GameThread} exists*/
	private boolean exists = false;

	/**is the {@link GameThread} running*/
	private boolean running = false;

	/**
	 * {@link Crafex} {@link Thread}.
	 */
	public GameThread() {
		FPS = 10;
	}
	
	/**
	 * {@link Crafex} {@link Thread}.
	 * @param fps to run at
	 */
	public GameThread(int fps) {
		FPS = fps;
	}

	/**
	 * creates the {@link GameThread}
	 */
	public void create() {
		this.exists = true;
	}

	/**
	 * kills the {@link GameThread}
	 */
	public void kill() {
		this.exists = false;
	}

	/**
	 * pauses the {@link GameThread}
	 */
	public void pause() {
		this.running = false;
	}

	/**
	 * plays the {@link GameThread}
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
	 * stuff to do in {@link GameThread}.
	 */
	public abstract void threadStuff();

}
