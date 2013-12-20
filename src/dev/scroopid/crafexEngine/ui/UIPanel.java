package dev.scroopid.crafexEngine.ui;

import java.util.ArrayList;

import android.graphics.Bitmap;
import dev.scroopid.crafexEngine.input.CrafexTouchEvent;
import dev.scroopid.crafexEngine.util.Util;
import dev.scroopid.crafexEngine.util.floatPoint;

public class UIPanel extends UIObject {

	/**{@link UIObject} in the panel*/
	protected ArrayList<UIObject> uiObjects;
	/**index of object being used*/
	private int index;
	/**has scrolled at all*/
	private boolean scrolled;

	/**
	 * a panel of {@link UIObject}s
	 * @param image
	 * @param location
	 * @param layer
	 */
	public UIPanel(Bitmap image, floatPoint location, int layer) {
		super(image, location, layer);
		this.setUiObjects(new ArrayList<UIObject>());
		this.setSelected(0);
	}

	/**
	 * adds a {@link UIObject} to the {@link UIPanel}
	 * @param uiobject
	 */
	public void addUIObject(UIObject uiobject) {
		this.uiObjects.add(uiobject);
	}

	/**
	 * get the index of the {@link UIObject} being used
	 * @return
	 */
	public int getIndex() {
		return this.index;
	}

	/**
	 * returns the {@link UIObject}s of the {@link UIPanel}
	 * @return UIObjects of the UIPanel
	 */
	public ArrayList<UIObject> getUiObjects() {
		return this.uiObjects;
	}

	/**
	 * sets the index of the selected {@link UIObject}
	 * @param index
	 */
	public void setSelected(int index) {
		this.index = index;
	}

	/**
	 * sets the {@link UIObject}s
	 * @param uiObjects
	 */
	public void setUiObjects(ArrayList<UIObject> uiObjects) {
		this.uiObjects = uiObjects;
	}

	@Override
	public void whenHeld(CrafexTouchEvent touch) {
		if (touch.getTouchLocation().getDistance(touch.getTouchLocation()) > this.DRAG_DISTANCE) {
			if (this.realSize.getX() != this.size.getX()) {
				this.scrollX(this.touch.getTouchLocation().getX() - touch.getTouchLocation().getX());
				this.scrolled = true;
			}
			if (this.realSize.getY() != this.size.getY()) {
				this.scrollY(this.touch.getTouchLocation().getY() - touch.getTouchLocation().getY());
				this.scrolled = true;
			}
			this.touch = touch;
		}
	}

	@Override
	public void whenPressed(CrafexTouchEvent touch) {
		for (int i = 0; i < this.uiObjects.size(); ++i) {
			if (Util.isBetween(touch.getTouchLocation().getX(), this.uiObjects.get(i).getX(), this.uiObjects.get(i).getSize().getX()
									+ this.uiObjects.get(i).getX())
						&& Util.isBetween(touch.getTouchLocation().getY(), this.uiObjects.get(i).getY(), this.uiObjects.get(i).getSize().getY()
									+ this.uiObjects.get(i).getY())) {
				touch.getTouchLocation().subtract(this.uiObjects.get(i).getLocation().toIntPoint());
				this.uiObjects.get(i).whenPressed(touch);
				this.index = i;
			}
		}
	}

	@Override
	public void whenReleased(CrafexTouchEvent touch) {
		if (!this.scrolled
					&& Util.isBetween(touch.getTouchLocation().getX(), this.uiObjects.get(this.index).getX(), this.uiObjects.get(this.index).getSize().getX())
					&& Util.isBetween(touch.getTouchLocation().getY(), this.uiObjects.get(this.index).getY(), this.uiObjects.get(this.index).getSize().getY())) {
			touch.getTouchLocation().subtract(this.uiObjects.get(this.index).getLocation().toIntPoint());
			this.uiObjects.get(this.index).whenPressed(touch);
		}
	}

}
