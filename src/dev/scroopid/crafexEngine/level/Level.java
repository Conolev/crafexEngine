package dev.scroopid.crafexEngine.level;

import dev.scroopid.crafexEngine.Crafex;
import dev.scroopid.crafexEngine.Updatable;
import dev.scroopid.crafexEngine.ui.UILayer;
import dev.scroopid.crafexEngine.ui.UIObject;
import dev.scroopid.crafexEngine.ui.UIScreen;
import dev.scroopid.crafexEngine.util.intPoint;

public class Level implements Updatable {

	protected long lastUpdateTime;

	protected intPoint scroll;

	protected intPoint boundrys;

	protected UIScreen uiscreen;

	public Level() {
		this.scroll = new intPoint(0, 0);
		this.boundrys = Crafex.WINDOW_DIMENTIONS.clone();
		this.uiscreen = new UIScreen();
	}

	public Level(UIScreen uiscreen) {
		this();
		this.setUiscreen(uiscreen);
	}

	public Level(UIScreen uiscreen, intPoint scroll) {
		this();
		this.setScroll(scroll);
		this.setUiscreen(uiscreen);
	}

	public Level(UIScreen uiscreen, intPoint scroll, intPoint boundrys) {
		this();
		this.setScroll(scroll);
		this.setBoundrys(boundrys);
		this.setUiscreen(uiscreen);
	}

	public void addLayer(UILayer layer) {
		this.uiscreen.addUILayer(layer);
	}

	public void addUIObject(UIObject object) {
		this.uiscreen.addUIObject(object);
	}

	public intPoint getBoundrys() {
		return this.boundrys;
	}

	@Override
	public long getLastUpdateTime() {
		return this.lastUpdateTime;
	}

	public intPoint getScroll() {
		return this.scroll;
	}

	public UIScreen getUiscreen() {
		return this.uiscreen;
	}

	@Override
	public float getUpdateTimeDelta() {
		return (System.currentTimeMillis() - this.lastUpdateTime) / 1000;
	}

	public void setBoundrys(intPoint boundrys) {
		this.boundrys = boundrys;
	}

	@Override
	public void setLastUpdateTime(long time) {
		this.lastUpdateTime = time;
	}

	public void setScroll(intPoint scroll) {
		this.scroll = scroll;
	}

	public void setUiscreen(UIScreen uiscreen) {
		this.uiscreen = uiscreen;
	}

	@Override
	public void update() {

	}

}
