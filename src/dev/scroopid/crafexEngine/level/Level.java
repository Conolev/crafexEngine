package dev.scroopid.crafexEngine.level;

import android.graphics.Canvas;
import dev.scroopid.crafexEngine.Crafex;
import dev.scroopid.crafexEngine.Drawable;
import dev.scroopid.crafexEngine.Updatable;
import dev.scroopid.crafexEngine.ui.UILayer;
import dev.scroopid.crafexEngine.ui.UIObject;
import dev.scroopid.crafexEngine.ui.UIScreen;
import dev.scroopid.crafexEngine.util.intPoint;

public class Level implements Updatable, Drawable {

	/** last time {@link Level} updated */
	protected long lastUpdateTime;

	/** amount the screen is "scrolled over" */
	protected intPoint scroll;

	/** Boundary's of the {@link Level}s field */
	protected intPoint boundarys;

	/** {@link UIScreen} of {@link Level} */
	protected UIScreen uiscreen;

	/**
	 * level for {@link Crafex}
	 */
	public Level() {
		this.scroll = new intPoint(0, 0);
		this.boundarys = Crafex.WINDOW_DIMENTIONS.clone();
		this.uiscreen = new UIScreen();
	}

	/**
	 * level for {@link Crafex}
	 * 
	 * @param uiscreen
	 *        for the level
	 */
	public Level(UIScreen uiscreen) {
		this();
		this.setUiscreen(uiscreen);
	}

	/**
	 * level for {@link Crafex}
	 * 
	 * @param uiscreen
	 *        for the {@link Level}
	 * @param scroll
	 *        amount
	 */
	public Level(UIScreen uiscreen, intPoint scroll) {
		this();
		this.setScroll(scroll);
		this.setUiscreen(uiscreen);
	}

	/**
	 * level for {@link Crafex}
	 * 
	 * @param uiscreen
	 *        for the {@link Level}
	 * @param scroll
	 *        amount
	 * @param boundarys
	 *        the {@link Level} has
	 */
	public Level(UIScreen uiscreen, intPoint scroll, intPoint boundarys) {
		this();
		this.setScroll(scroll);
		this.setBoundarys(boundarys);
		this.setUiscreen(uiscreen);
	}

	/**
	 * adds a {@link UILayer} the {@link UIScreen}
	 * 
	 * @param layer
	 */
	public void addLayer(UILayer layer) {
		this.uiscreen.addUILayer(layer);
	}

	/**
	 * adds a {@link UIObject} to the current {@link UILayer}
	 * 
	 * @param object
	 */
	public void addUIObject(UIObject object) {
		this.uiscreen.addUIObject(object);
	}

	/**
	 * returns the levels boundarys
	 * 
	 * @return levels boundarys
	 */
	public intPoint getBoundarys() {
		return this.boundarys;
	}

	@Override
	public long getLastUpdateTime() {
		return this.lastUpdateTime;
	}

	/**
	 * returns the scroll amounts of the {@link Level}
	 * 
	 * @return scroll amounts of the level
	 */
	public intPoint getScroll() {
		return this.scroll;
	}

	/**
	 * returns the {@link UIScreen} of the {@link Level}
	 * 
	 * @return UIScreen of the Level
	 */
	public UIScreen getUiscreen() {
		return this.uiscreen;
	}

	@Override
	public float getUpdateTimeDelta() {
		return (System.currentTimeMillis() - this.lastUpdateTime) / 1000;
	}

	/**
	 * scrolls the x coordinate
	 * 
	 * @param differance
	 */
	public void scrollX(int differance) {
		if (this.scroll.getX() + Crafex.WINDOW_DIMENTIONS.getX() < this.boundarys.getX() && differance > 0) {
			if (this.boundarys.getX() - (this.scroll.getX() + Crafex.WINDOW_DIMENTIONS.getX()) < differance) {
				this.scroll.setX(this.boundarys.getX() - Crafex.WINDOW_DIMENTIONS.getX());
			} else {
				this.scroll.addX(differance);
			}
		} else if (this.scroll.getX() > 0 && differance < 0) {
			if (this.scroll.getX() < -differance) {
				this.scroll.setX(0);
			} else {
				this.scroll.addX(differance);
			}
		}
	}

	/**
	 * scrolls the Y coordinate
	 * 
	 * @param differance
	 */
	public void scrollY(int differance) {
		if (this.scroll.getY() + Crafex.WINDOW_DIMENTIONS.getY() < this.boundarys.getY() && differance > 0) {
			if (this.boundarys.getY() - (this.scroll.getY() + Crafex.WINDOW_DIMENTIONS.getY()) < differance) {
				this.scroll.setY(this.boundarys.getY() - Crafex.WINDOW_DIMENTIONS.getY());
			} else {
				this.scroll.addY(differance);
			}
		} else if (this.scroll.getY() > 0 && differance < 0) {
			if (this.scroll.getY() < -differance) {
				this.scroll.setY(0);
			} else {
				this.scroll.addY(differance);
			}
		}
	}

	/**
	 * sets the {@link Level}s boundary's
	 * 
	 * @param boundarys
	 */
	public void setBoundarys(intPoint boundarys) {
		this.boundarys = boundarys;
	}

	@Override
	public void setLastUpdateTime(long time) {
		this.lastUpdateTime = time;
	}

	/**
	 * sets the scroll of the {@link Level}
	 * 
	 * @param scroll
	 */
	public void setScroll(intPoint scroll) {
		this.scroll = scroll;
	}

	/**
	 * sets the {@link UIScreen} of the level
	 * 
	 * @param uiscreen
	 */
	public void setUiscreen(UIScreen uiscreen) {
		this.uiscreen = uiscreen;
	}

	@Override
	public void update() {

	}

	@Override
	public void draw(Canvas canvas) {

	}

}
