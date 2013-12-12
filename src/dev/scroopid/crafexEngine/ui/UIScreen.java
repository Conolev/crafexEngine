package dev.scroopid.crafexEngine.ui;

import java.util.ArrayList;

import android.graphics.Canvas;
import dev.scroopid.crafexEngine.Drawable;
import dev.scroopid.crafexEngine.Touchable;
import dev.scroopid.crafexEngine.Updatable;
import dev.scroopid.crafexEngine.input.CrafexTouchEvent;

public class UIScreen implements Updatable, Touchable{

	private ArrayList<UILayer> uiLayers;
	private UIObject overlay;
	private long lastUpdateTime;
	
	public UIScreen(){
		
	}
	
	public void addUILayer(UILayer uiobject){
		uiLayers.add(uiobject);
	}
	
	public int getNextLayer(){
		return uiLayers.size();
	}
	
	@Override
	public void update(){
		for(int i = 0; i < uiLayers.size(); ++i){
			for(int i2 = 0; i2 < uiLayers.get(i).getSize(); ++i2){
				uiLayers.get(i).getObject(i2).update();
			}
		}
		setLastUpdateTime(System.currentTimeMillis());
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
	
	public ArrayList<UILayer> getUILayers(){
		return uiLayers;
	}

	@Override
	public boolean isTouching(CrafexTouchEvent touch) {
		return true;
	}

	@Override
	public void whenPressed(CrafexTouchEvent touch) {
		
	}

	@Override
	public void whenReleased(CrafexTouchEvent touch) {
		
	}

	@Override
	public void whenHeld(CrafexTouchEvent touch) {
		
	}
	
	public void setOverlay(UIObject overlay){
		this.overlay = overlay;
	}
	
	public boolean hasOverlay(){
		return overlay != null;
	}
	
	public UIObject getOverlay(){
		return overlay;
	}

	public void draw(Canvas canvas) {
		for(int i = 0; i < uiLayers.size(); ++i){
			uiLayers.get(i).draw(canvas);
		}
		if(overlay != null){
			overlay.draw(canvas);
		}
	}

}
