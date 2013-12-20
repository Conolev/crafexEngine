package dev.scroopid.crafexEngine.ui;

import java.util.ArrayList;

import android.graphics.Canvas;
import dev.scroopid.crafexEngine.Touchable;
import dev.scroopid.crafexEngine.input.CrafexTouchEvent;
import dev.scroopid.crafexEngine.util.Util;
import dev.scroopid.crafexEngine.util.intRectangle;

public class UILayer implements Touchable{

	private ArrayList<UIObject> uiObjects = new ArrayList<UIObject>();
	private intRectangle touchArea;

	public UILayer() {
		uiObjects = new ArrayList<UIObject>();
	}
	
	public UILayer(intRectangle touchArea) {
		uiObjects = new ArrayList<UIObject>();
		this.touchArea = touchArea;
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

	@Override
	public boolean isTouching(CrafexTouchEvent touch) {
		
		for(int i = 0; i < uiObjects.size(); ++i){
			if(uiObjects.get(i).isTouching(touch)){
				return true;
			}
		}
		
		if(touchArea != null && Util.isBetween(touch.getTouchLocation(), touchArea)){
			return true;
		}
		
		return false;
	}

	@Override
	public void whenHeld(CrafexTouchEvent touch) {
		for(int i = 0; i < uiObjects.size(); ++i){
			if(uiObjects.get(i).isTouching(touch)){
				uiObjects.get(i).whenHeld(touch);
			}
		}
	}

	@Override
	public void whenPressed(CrafexTouchEvent touch) {
		for(int i = 0; i < uiObjects.size(); ++i){
			if(uiObjects.get(i).isTouching(touch)){
				uiObjects.get(i).whenPressed(touch);
			}
		}
	}

	@Override
	public void whenReleased(CrafexTouchEvent touch) {
		System.out.println(touch);
		for(int i = 0; i < uiObjects.size(); ++i){
			if(uiObjects.get(i).isTouching(touch)){
				uiObjects.get(i).whenReleased(touch);
			}
		}
	}

}
