package dev.scroopid.crafexEngine.ui;

import java.util.ArrayList;

import android.graphics.Canvas;
import dev.scroopid.crafexEngine.Drawable;
import dev.scroopid.crafexEngine.Touchable;
import dev.scroopid.crafexEngine.Updatable;
import dev.scroopid.crafexEngine.input.CrafexTouchEvent;

public class UIHandler implements Updatable, Drawable {

	private long lastUpdateTime;

	private UIScreen uiscreen;

	public UIHandler() {
		uiscreen = new UIScreen();
	}

	@Override
	public void draw(Canvas canvas) {
		this.uiscreen.draw(canvas);
	}

	@Override
	public long getLastUpdateTime() {
		return this.lastUpdateTime;
	}

	public UIObject getOverlay() {
		return this.uiscreen.getOverlay();
	}

	public Touchable[] getTouchables() {
		ArrayList<Touchable> data = new ArrayList<Touchable>();

		for (int i = 0; i < this.uiscreen.getUILayers().size(); ++i) {
			for (int i2 = 0; i2 < this.uiscreen.getUILayers().get(i).getSize(); ++i2) {
				data.add(this.uiscreen.getUILayers().get(i).getObject(i2));
			}
		}
		
		Touchable[] touchables = new Touchable[data.size()];
		
		for (int i = 0; i < data.size(); ++i){
			touchables[i] = data.get(i);
		}

		return touchables;
	}

	@Override
	public float getUpdateTimeDelta() {
		return (System.currentTimeMillis() - this.lastUpdateTime) / 1000;
	}

	public boolean hasOverlay() {
		return this.uiscreen.hasOverlay();
	}

	@Override
	public void setLastUpdateTime(long time) {
		this.lastUpdateTime = time;
	}

	public void setOverlay(UIObject overlay) {

	}

	public void setUIScreen(UIScreen uiscreen) {
		this.uiscreen = uiscreen;
	}

	public void touchScreen(CrafexTouchEvent touch) {
		if (touch.getAction() == CrafexTouchEvent.ACTION_DOWN) {
			this.uiscreen.whenPressed(touch);
		} else if (touch.getAction() == CrafexTouchEvent.ACTION_UP) {
			this.uiscreen.whenReleased(touch);
		} else if (touch.getAction() == CrafexTouchEvent.ACTION_MOVE) {
			this.uiscreen.whenHeld(touch);
		}
	}

	@Override
	public void update() {
		this.uiscreen.update();
		this.setLastUpdateTime(System.currentTimeMillis());
	}
}
