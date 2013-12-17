package dev.scroopid.crafexEngine;

public interface Updatable {
	public long getLastUpdateTime();

	public float getUpdateTimeDelta();

	public void setLastUpdateTime(long time);

	public void update();
}
