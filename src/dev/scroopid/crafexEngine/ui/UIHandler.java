package dev.scroopid.crafexEngine.ui;

import java.util.ArrayList;

import android.graphics.Canvas;
import dev.scroopid.crafexEngine.Drawable;
import dev.scroopid.crafexEngine.Touchable;
import dev.scroopid.crafexEngine.Updatable;
import dev.scroopid.crafexEngine.input.CrafexTouchEvent;

public class UIHandler implements Updatable, Drawable{
	
	private long lastUpdateTime;
	private UIScreen uiscreen;
	
	public UIHandler(){
		
	}
	
	public void setUIScreen(UIScreen uiscreen){
		this.uiscreen = uiscreen;
	}
	
	public void setOverlay(UIObject overlay){
		
	}
	
	public boolean hasOverlay(){
		return uiscreen.hasOverlay();
	}
	
	public UIObject getOverlay(){
		return uiscreen.getOverlay();
	}

	@Override
	public void update(){
		uiscreen.update();
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
	
	public void touchScreen(CrafexTouchEvent touch){
		if(touch.getAction() == CrafexTouchEvent.ACTION_DOWN){
			uiscreen.whenPressed(touch);
		}
		else if(touch.getAction() == CrafexTouchEvent.ACTION_UP){
			uiscreen.whenReleased(touch);
		}
		else if(touch.getAction() == CrafexTouchEvent.ACTION_MOVE){
			uiscreen.whenHeld(touch);
		}
	}

	@Override
	public void draw(Canvas canvas) {
		uiscreen.draw(canvas);
	}

	public Touchable[] getTouchables() {
		ArrayList<Touchable> data = new ArrayList<Touchable>();
		
		for(int i = 0; i < uiscreen.getUILayers().size(); ++i){
			for(int i2 = 0; i2 < uiscreen.getUILayers().get(i).getSize(); ++i2){
				data.add(uiscreen.getUILayers().get(i).getObject(i2));
			}
		}
		
		return (Touchable[]) data.toArray();
	}
}
