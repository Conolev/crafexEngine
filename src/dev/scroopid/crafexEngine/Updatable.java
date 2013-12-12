package dev.scroopid.crafexEngine;

public interface Updatable {
	public void update();
	public void setLastUpdateTime(long time);
	public long getLastUpdateTime();
	public float getUpdateTimeDelta();
}
