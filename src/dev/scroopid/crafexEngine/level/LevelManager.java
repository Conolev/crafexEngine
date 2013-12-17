package dev.scroopid.crafexEngine.level;

import dev.scroopid.crafexEngine.ui.UIHandler;

public class LevelManager {

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
	}

	public void setUiHandler(UIHandler uiHandler) {
		this.uiHandler = uiHandler;
	}

}
