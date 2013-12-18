package dev.scroopid.crafexEngine.ui;

import java.util.ArrayList;

import android.graphics.Canvas;
import dev.scroopid.crafexEngine.Touchable;
import dev.scroopid.crafexEngine.Updatable;
import dev.scroopid.crafexEngine.input.CrafexTouchEvent;

public class UIScreen implements Updatable, Touchable {

	private ArrayList<UILayer> uiLayers;

	private UIObject overlay;

	private long lastUpdateTime;

	public UIScreen() {
		uiLayers = new ArrayList<UILayer>();
	}

	public void addUILayer(UILayer uiobject) {
		this.uiLayers.add(uiobject);
	}

	public void addUIObject(UIObject object) {
		this.uiLayers.get(this.uiLayers.size() - 1).addUIObject(object);
	}

	public void draw(Canvas canvas) {
		for (int i = 0; i < this.uiLayers.size(); ++i) {
			this.uiLayers.get(i).draw(canvas);
		}
		if (this.overlay != null) {
			this.overlay.draw(canvas);
		}
	}

	@Override
	public long getLastUpdateTime() {
		return this.lastUpdateTime;
	}

	public int getNextLayer() {
		return this.uiLayers.size();
	}

	public UIObject getOverlay() {
		return this.overlay;
	}

	public ArrayList<UILayer> getUILayers() {
		return this.uiLayers;
	}
	
	public void removeLayer(){
		if(uiLayers.size() > 1){
			uiLayers.remove(uiLayers.size() - 1);
		}else{
			uiLayers.clear();
		}
	}

	@Override
	public float getUpdateTimeDelta() {
		return (System.currentTimeMillis() - this.lastUpdateTime) / 1000;
	}

	public boolean hasOverlay() {
		return this.overlay != null;
	}

	@Override
	public boolean isTouching(CrafexTouchEvent touch) {
		return true;
	}

	@Override
	public void setLastUpdateTime(long time) {
		this.lastUpdateTime = time;
	}

	public void setOverlay(UIObject overlay) {
		this.overlay = overlay;
	}

	@Override
	public void update() {
		for (int i = 0; i < this.uiLayers.size(); ++i) {
			for (int i2 = 0; i2 < this.uiLayers.get(i).getSize(); ++i2) {
				this.uiLayers.get(i).getObject(i2).update();
			}
		}
		this.setLastUpdateTime(System.currentTimeMillis());
	}

	@Override
	public void whenHeld(CrafexTouchEvent touch) {
		if(uiLayers.size() > 0 && uiLayers.get(uiLayers.size() - 1).isTouching(touch)){
			uiLayers.get(uiLayers.size() - 1).whenHeld(touch);
		}
	}

	@Override
	public void whenPressed(CrafexTouchEvent touch) {
		if(uiLayers.size() > 0 && uiLayers.get(uiLayers.size() - 1).isTouching(touch)){
			uiLayers.get(uiLayers.size() - 1).whenPressed(touch);
		}else{
			removeLayer();
		}
	}

	@Override
	public void whenReleased(CrafexTouchEvent touch) {
		if(uiLayers.size() > 0 && uiLayers.get(uiLayers.size() - 1).isTouching(touch)){
			uiLayers.get(uiLayers.size() - 1).whenReleased(touch);
		}
	}

}
