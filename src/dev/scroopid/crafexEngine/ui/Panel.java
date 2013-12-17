package dev.scroopid.crafexEngine.ui;

import java.util.ArrayList;

import android.graphics.Bitmap;
import dev.scroopid.crafexEngine.input.CrafexTouchEvent;
import dev.scroopid.crafexEngine.util.Util;
import dev.scroopid.crafexEngine.util.floatPoint;

public class Panel extends UIObject {

	protected ArrayList<UIObject> uiObjects;

	private int index;

	private boolean scrolled;

	protected int panelLayer = 0;

	public Panel(Bitmap image, floatPoint location, int layer) {
		super(image, location, layer);
		this.setUiObjects(new ArrayList<UIObject>());
		this.setSelected(0);
	}

	public void addPanelLayer(int value) {
		this.panelLayer += value;
	}

	public void addUIObject(UIObject uiobject) {
		this.uiObjects.add(uiobject);
	}

	public int getIndex() {
		return this.index;
	}

	public ArrayList<UIObject> getUiObjects() {
		return this.uiObjects;
	}

	public void setPanelLayer(int layer) {
		this.panelLayer = layer;
	}

	public void setSelected(int index) {
		this.index = index;
	}

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
			if (this.uiObjects.get(i).getLayer() == this.panelLayer
						&& Util.isBetween(touch.getTouchLocation().getX(), this.uiObjects.get(i).getX(), this.uiObjects.get(i).getSize().getX()
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
