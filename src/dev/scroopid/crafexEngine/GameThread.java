package dev.scroopid.crafexEngine;

public abstract class GameThread extends Thread {

	private boolean exists = false;

	private boolean running = false;

	public GameThread() {

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

		while (this.exists) {

			while (this.running) {
				this.threadStuff();
			}

		}

	}

	@Override
	public synchronized void start() {
		this.create();
		super.start();
	}

	public abstract void threadStuff();

}
