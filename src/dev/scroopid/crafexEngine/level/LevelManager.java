package dev.scroopid.crafexEngine.level;

import android.graphics.Canvas;
import dev.scroopid.crafexEngine.Drawable;
import dev.scroopid.crafexEngine.Updatable;
import dev.scroopid.crafexEngine.ui.UIHandler;

public class LevelManager implements Updatable, Drawable {
	/** {@link UIHandler} of the {@link LevelManager} */
	public UIHandler uiHandler;

	/** current {@link Level} */
	public Level level;

	/**
	 * handles {@link Level}
	 * 
	 * @param uihandler
	 */
	public LevelManager(UIHandler uihandler) {
		this.uiHandler = uihandler;
	}

	/**
	 * returns the {@link Level} the {@link UIHandler} is using
	 * 
	 * @return level the handler is using
	 */
	public Level getLevel() {
		return this.level;
	}

	/**
	 * returns the {@link UIHandler} of the level manager
	 * 
	 * @return UIHandler of the level manager
	 */
	public UIHandler getUiHandler() {
		return this.uiHandler;
	}

	/**
	 * sets the current {@link Level} with a new {@link Level}
	 * 
	 * @param level
	 */
	public void setLevel(Level level) {
		this.level = level;
		uiHandler.setUIScreen(level.uiscreen);
	}

	public void setLevel(String key) {
		// TODO make the level hander use a hashmap like thing to access the levels for easy switching.
		// and finish this method
	}

	/**
	 * sets the {@link UIHandler} of the {@link LevelManager}
	 * 
	 * @param uiHandler
	 */
	public void setUiHandler(UIHandler uiHandler) {
		this.uiHandler = uiHandler;
	}

	@Override
	public void draw(Canvas canvas) {
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
		uiHandler.update();
	}

}
