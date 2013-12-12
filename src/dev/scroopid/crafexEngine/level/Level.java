package dev.scroopid.crafexEngine.level;

import dev.scroopid.crafexEngine.Updatable;
import dev.scroopid.crafexEngine.util.intPoint;

public class Level implements Updatable{
	
	protected long lastUpdateTime;
	protected intPoint scroll;
	
	public Level(intPoint scroll){
		
	}

	public intPoint getScroll() {
		return scroll;
	}

	public void setScroll(intPoint scroll) {
		this.scroll = scroll;
	}

	@Override
	public void update() {
		
	}

	@Override
	public void setLastUpdateTime(long time) {
		lastUpdateTime = time;
	}

	@Override
	public long getLastUpdateTime() {
		return lastUpdateTime;
	}

	@Override
	public float getUpdateTimeDelta() {
		return (System.currentTimeMillis() - lastUpdateTime)/1000;
	}
	
	
}
