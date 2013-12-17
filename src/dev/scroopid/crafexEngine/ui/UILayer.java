package dev.scroopid.crafexEngine.ui;

import java.util.ArrayList;

import android.graphics.Canvas;
import dev.scroopid.crafexEngine.Touchable;

public class UILayer {

	private ArrayList<UIObject> uiObjects = new ArrayList<UIObject>();

	public UILayer() {
		uiObjects = new ArrayList<UIObject>();
	}

	public void addUIObject(UIObject uiObject) {
		this.uiObjects.add(uiObject);
	}

	public void draw(Canvas canvas) {
		for (int i = 0; i < this.uiObjects.size(); ++i) {
			this.getObject(i).draw(canvas);
		}
	}

	public UIObject getObject(int index) {
		return this.uiObjects.get(index);
	}

	public int getSize() {
		return this.uiObjects.size();
	}

	public Touchable[] getTouchables() {
		return (Touchable[]) this.uiObjects.toArray();
	}

	public ArrayList<UIObject> getUiObjects() {
		return this.uiObjects;
	}

	public void setUiObjects(ArrayList<UIObject> uiObjects) {
		this.uiObjects = uiObjects;
	}

}
