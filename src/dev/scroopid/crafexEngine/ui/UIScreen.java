package dev.scroopid.crafexEngine.ui;

import java.util.ArrayList;

import android.graphics.Canvas;
import dev.scroopid.crafexEngine.Drawable;
import dev.scroopid.crafexEngine.Touchable;
import dev.scroopid.crafexEngine.Updatable;
import dev.scroopid.crafexEngine.input.CrafexTouchEvent;

public class UIScreen implements Drawable, Updatable, Touchable {

	/** {@link UILayer} of the {@link UIScreen} */
	private ArrayList<UILayer> uiLayers;

	/** overlay {@link UIObject} */
	private UIObject overlay;

	/** last time updated */
	private long lastUpdateTime;
	
	/**
	 * a screen of {@link UIObject}
	 */
	public UIScreen() {
		uiLayers = new ArrayList<UILayer>();
	}

	/**
	 * adds a {@link UILayer} of {@link UIObject} to the {@link UIScreen}
	 * 
	 * @param {@link UIObject}
	 */
	public void addUILayer(UILayer uiobject) {
		this.uiLayers.add(uiobject);
	}

	/**
	 * adds a {@link UIObject} to the current {@link UILayer}
	 * 
	 * @param object
	 */
	public void addUIObject(UIObject object) {
		this.uiLayers.get(this.uiLayers.size() - 1).addUIObject(object);
	}

	@Override
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

	/**
	 * returns the current {@link UILayer} of the {@link UIScreen}
	 * 
	 * @return
	 */
	public int getLayer() {
		return uiLayers.size() - 1;
	}

	/**
	 * returns the overlay of the {@link UIScreen}
	 * 
	 * @return
	 */
	public UIObject getOverlay() {
		return this.overlay;
	}

	/**
	 * returns the {@link UILayer}
	 * 
	 * @return UILayers
	 */
	public ArrayList<UILayer> getUILayers() {
		return this.uiLayers;
	}

	/**
	 * removes the current {@link UILayer} from {@link UIScreen} if possible
	 */
	public void removeLayer() {
		if (uiLayers.size() > 1) {
			uiLayers.remove(uiLayers.size() - 1);
		} else {
			uiLayers.clear();
		}
	}

	@Override
	public float getUpdateTimeDelta() {
		return (System.currentTimeMillis() - this.lastUpdateTime) / 1000;
	}

	/**
	 * does the {@link UIScreen} have an overlay
	 * 
	 * @return
	 */
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

	/**
	 * sets the overlay of the {@link UIScreen}
	 * 
	 * @param overlay
	 */
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
		if (uiLayers.size() > 0 && uiLayers.get(uiLayers.size() - 1).isTouching(touch)) {
			uiLayers.get(uiLayers.size() - 1).whenHeld(touch);
		}
	}

	@Override
	public void whenPressed(CrafexTouchEvent touch) {
		if (uiLayers.size() > 0 && uiLayers.get(uiLayers.size() - 1).isTouching(touch)) {
			uiLayers.get(uiLayers.size() - 1).whenPressed(touch);
		} else {
			removeLayer();
		}
	}

	@Override
	public void whenReleased(CrafexTouchEvent touch) {
		if (uiLayers.size() > 0 && uiLayers.get(uiLayers.size() - 1).isTouching(touch)) {
			uiLayers.get(uiLayers.size() - 1).whenReleased(touch);
		}
	}
	
	public void keyboardInput(String input){
		if (uiLayers.size() > 0) {
			uiLayers.get(uiLayers.size() - 1).keyboardInput(input);
		}
	}

}
