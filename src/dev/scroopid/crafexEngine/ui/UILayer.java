package dev.scroopid.crafexEngine.ui;

import java.util.ArrayList;

import android.graphics.Canvas;
import dev.scroopid.crafexEngine.Drawable;
import dev.scroopid.crafexEngine.Touchable;
import dev.scroopid.crafexEngine.input.CrafexTouchEvent;
import dev.scroopid.crafexEngine.util.Util;
import dev.scroopid.crafexEngine.util.intRectangle;

public class UILayer implements Touchable, Drawable {

	/** {@link UIObject} in the layer */
	private ArrayList<UIObject> uiObjects = new ArrayList<UIObject>();

	/** area of the screen touch doesn't remove this layer */
	private intRectangle touchArea;

	/**
	 * a layer of {@link UIObject} for a {@link UIScreen}
	 */
	public UILayer() {
		uiObjects = new ArrayList<UIObject>();
	}

	/**
	 * a layer of {@link UIObject} for a {@link UIScreen}
	 * 
	 * @param touchArea
	 *        where layer doesn't get removed
	 */
	public UILayer(intRectangle touchArea) {
		uiObjects = new ArrayList<UIObject>();
		this.touchArea = touchArea;
	}

	/**
	 * adds an {@link UIObject} to the layer
	 * 
	 * @param uiObject
	 */
	public void addUIObject(UIObject uiObject) {
		this.uiObjects.add(uiObject);
	}

	@Override
	public void draw(Canvas canvas) {
		for (int i = 0; i < this.uiObjects.size(); ++i) {
			this.getObject(i).draw(canvas);
		}
	}

	/**
	 * gets the {@link UIObject} at index number provided
	 * 
	 * @param index
	 * @return {@link UIObject} at index number provided
	 */
	public UIObject getObject(int index) {
		return this.uiObjects.get(index);
	}

	/**
	 * returns the amount of {@link UIObject}s in layer
	 * 
	 * @return amount of {@link UIObject}s in layer
	 */
	public int getSize() {
		return this.uiObjects.size();
	}

	/**
	 * returns {@link Touchable} objects
	 * 
	 * @return touchable objects
	 */
	public Touchable[] getTouchables() {
		return (Touchable[]) this.uiObjects.toArray();
	}

	/**
	 * returns all {@link UIObject} of the layer
	 * 
	 * @return all {@link UIObject} of the layer
	 */
	public ArrayList<UIObject> getUiObjects() {
		return this.uiObjects;
	}

	/**
	 * sets the {@link UIObject} of the layer
	 * 
	 * @param uiObjects
	 */
	public void setUiObjects(ArrayList<UIObject> uiObjects) {
		this.uiObjects = uiObjects;
	}

	@Override
	public boolean isTouching(CrafexTouchEvent touch) {

		for (int i = 0; i < uiObjects.size(); ++i) {
			if (uiObjects.get(i).isTouching(touch)) {
				return true;
			}
		}

		if (touchArea != null && Util.isBetween(touch.getTouchLocation(), touchArea)) {
			return true;
		}

		return false;
	}

	@Override
	public void whenHeld(CrafexTouchEvent touch) {
		for (int i = 0; i < uiObjects.size(); ++i) {
			if (uiObjects.get(i).isTouching(touch)) {
				uiObjects.get(i).whenHeld(touch);
			}
		}
	}

	@Override
	public void whenPressed(CrafexTouchEvent touch) {
		for (int i = 0; i < uiObjects.size(); ++i) {
			if (uiObjects.get(i).isTouching(touch)) {
				uiObjects.get(i).whenPressed(touch);
			}
		}
	}

	@Override
	public void whenReleased(CrafexTouchEvent touch) {
		for (int i = 0; i < uiObjects.size(); ++i) {
			if (uiObjects.get(i).isTouching(touch)) {
				uiObjects.get(i).whenReleased(touch);
			}
		}
	}

}
