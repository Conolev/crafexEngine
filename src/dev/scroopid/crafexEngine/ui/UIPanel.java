package dev.scroopid.crafexEngine.ui;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import dev.scroopid.crafexEngine.input.CrafexTouchEvent;
import dev.scroopid.crafexEngine.util.Util;
import dev.scroopid.crafexEngine.util.floatPoint;

public class UIPanel extends UIObject {

	/** {@link UIObject} in the panel */
	protected ArrayList<UIObject> uiObjects;

	/** has scrolled at all */
	private boolean scrolled;

	/**
	 * a panel of {@link UIObject}s
	 * 
	 * @param image
	 * @param location
	 * @param layer
	 */
	public UIPanel(Bitmap image, floatPoint location) {
		super(image, location, 0);
		this.setUiObjects(new ArrayList<UIObject>());
	}

	/**
	 * adds a {@link UIObject} to the {@link UIPanel}
	 * 
	 * @param uiobject
	 */
	public void addUIObject(UIObject uiobject) {
		uiobject.getLocation().add(getLocation());
		this.uiObjects.add(uiobject);
	}
	
	@Override
	public void update() {
		super.update();
		for(int i = 0; i < uiObjects.size(); ++i){
			uiObjects.get(i).update();
		}
	}
	
	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		for(int i = 0; i < uiObjects.size(); ++i){
			uiObjects.get(i).draw(canvas);
		}
	}

	/**
	 * returns the {@link UIObject}s of the {@link UIPanel}
	 * 
	 * @return UIObjects of the UIPanel
	 */
	public ArrayList<UIObject> getUiObjects() {
		return this.uiObjects;
	}

	/**
	 * sets the {@link UIObject}s
	 * 
	 * @param uiObjects
	 */
	public void setUiObjects(ArrayList<UIObject> uiObjects) {
		this.uiObjects = uiObjects;
	}

	@Override
	public void whenHeld(CrafexTouchEvent touch) {
		if (touch.getTouchLocation().getDistance(touch.getTouchLocation()) > this.DRAG_DISTANCE) {
			if (this.realSize.getX() != getRectangle().getWidth()) {
				this.scrollX(this.touch.getTouchLocation().getX() - touch.getTouchLocation().getX());
				this.scrolled = true;
			}
			if (this.realSize.getY() != getRectangle().getHeight()) {
				this.scrollY(this.touch.getTouchLocation().getY() - touch.getTouchLocation().getY());
				this.scrolled = true;
			}
			this.touch = touch;
		}
	}

	@Override
	public void whenPressed(CrafexTouchEvent touch) {
		for (int i = 0; i < this.uiObjects.size(); i++) {
			if (uiObjects.get(i).isTouching(touch)) {
				this.uiObjects.get(i).whenPressed(touch);
			}
		}
	}

	@Override
	public void whenReleased(CrafexTouchEvent touch) {
		for (int i = 0; i < this.uiObjects.size(); i++) {
			if (uiObjects.get(i).touch != null && uiObjects.get(i).isTouching(touch)) {
				this.uiObjects.get(i).whenReleased(touch);
			}
		}
	}

}
