package dev.scroopid.crafexEngine.level;

import dev.scroopid.crafexEngine.ui.UIHandler;

public class LevelManager {
	
	public UIHandler uiHandler;
	public Level level;
	
	public LevelManager(UIHandler uihandler){
		this.uiHandler = uihandler;
	}

	public UIHandler getUiHandler() {
		return uiHandler;
	}

	public void setUiHandler(UIHandler uiHandler) {
		this.uiHandler = uiHandler;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}
	
	
}
