package dev.scroopid.crafexEngine;

public interface Updatable {
	/**
	 * returns last time of an update.
	 * @return last time of an update. 
	 */
	public long getLastUpdateTime();

	/**
	 * returns delta of last time and current time.
	 * @return last time of an update. 
	 */
	public float getUpdateTimeDelta();

	/**
	 * sets the last update time.
	 * @param time
	 */
	public void setLastUpdateTime(long time);

	/**
	 * updates the object.
	 */
	public void update();
}
