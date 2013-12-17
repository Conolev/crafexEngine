package dev.scroopid.crafexEngine.level;

import android.graphics.Canvas;
import dev.scroopid.crafexEngine.Drawable;
import dev.scroopid.crafexEngine.Updatable;
import dev.scroopid.crafexEngine.ui.UIHandler;

public class LevelManager implements Updatable, Drawable{

	public UIHandler uiHandler;

	public Level level;

	public LevelManager(UIHandler uihandler) {
		this.uiHandler = uihandler;
	}

	public Level getLevel() {
		return this.level;
	}

	public UIHandler getUiHandler() {
		return this.uiHandler;
	}

	public void setLevel(Level level) {
		this.level = level;
		uiHandler.setUIScreen(level.uiscreen);
	}

	public void setUiHandler(UIHandler uiHandler) {
		this.uiHandler = uiHandler;
	}
	
	public void draw(Canvas canvas){
		level.draw(canvas);
		uiHandler.draw(canvas);
	}

	@Override
	public long getLastUpdateTime() {
		return 0;
	}

	@Override
	public float getUpdateTimeDelta() {
		return 0;
	}

	@Override
	public void setLastUpdateTime(long time) {
		
	}

	@Override
	public void update() {
		
	}

}
