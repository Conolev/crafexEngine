package dev.scroopid.crafexEngine.level;

import dev.scroopid.crafexEngine.Crafex;
import dev.scroopid.crafexEngine.Updatable;
import dev.scroopid.crafexEngine.ui.UILayer;
import dev.scroopid.crafexEngine.ui.UIObject;
import dev.scroopid.crafexEngine.ui.UIScreen;
import dev.scroopid.crafexEngine.util.intPoint;

public class Level implements Updatable{
	
	protected long lastUpdateTime;
	protected intPoint scroll;
	protected intPoint boundrys; 
	protected UIScreen uiscreen;
	
	public Level(){
		scroll = new intPoint(0, 0);
		boundrys = Crafex.WINDOW_DIMENTIONS.clone();
		uiscreen = new UIScreen();
	}
	
	public Level(UIScreen uiscreen){
		this();
		setUiscreen(uiscreen);
	}
	
	public Level(UIScreen uiscreen, intPoint scroll){
		this();
		setScroll(scroll);
		setUiscreen(uiscreen);
	}
	
	public Level(UIScreen uiscreen, intPoint scroll, intPoint boundrys){
		this();
		setScroll(scroll);
		setBoundrys(boundrys);
		setUiscreen(uiscreen);
	}

	public intPoint getScroll() {
		return scroll;
	}

	public void setScroll(intPoint scroll) {
		this.scroll = scroll;
	}

	public intPoint getBoundrys() {
		return boundrys;
	}

	public void setBoundrys(intPoint boundrys) {
		this.boundrys = boundrys;
	}

	public UIScreen getUiscreen() {
		return uiscreen;
	}

	public void setUiscreen(UIScreen uiscreen) {
		this.uiscreen = uiscreen;
	}
	
	public void addLayer(UILayer layer){
		uiscreen.addUILayer(layer);
	}
	
	public void addUIObject(UIObject object){
		uiscreen.addUIObject(object);
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
