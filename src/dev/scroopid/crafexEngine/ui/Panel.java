package dev.scroopid.crafexEngine.ui;

import java.util.ArrayList;

import android.graphics.Bitmap;
import dev.scroopid.crafexEngine.input.CrafexTouchEvent;
import dev.scroopid.crafexEngine.util.Util;
import dev.scroopid.crafexEngine.util.floatPoint;
import dev.scroopid.crafexEngine.util.intPoint;

public class Panel extends UIObject{
	
	protected ArrayList<UIObject> uiObjects;
	
	private int index;
	private boolean scrolled;
	protected int panelLayer = 0;

	public Panel(Bitmap image, floatPoint location, int layer) {
		super(image, location, layer);
		setUiObjects(new ArrayList<UIObject>());
		setSelected(0);
	}
	
	public ArrayList<UIObject> getUiObjects() {
		return uiObjects;
	}

	public void setUiObjects(ArrayList<UIObject> uiObjects) {
		this.uiObjects = uiObjects;
	}
	
	public int getIndex(){
		return index;
	}
	
	public void setSelected(int index){
		this.index = index;
	}
	
	public void addUIObject(UIObject uiobject){
		uiObjects.add(uiobject);
	}
	
	public void addPanelLayer(int value){
		panelLayer += value;
	}
	
	public void setPanelLayer(int layer){
		panelLayer = layer;
	}

	@Override
	public void whenPressed(CrafexTouchEvent touch) {
		for(int i = 0; i < uiObjects.size(); ++i){
			if(uiObjects.get(i).getLayer() == panelLayer && 
					Util.isBetween(touch.getTouchLocation().getX(), uiObjects.get(i).getX(), uiObjects.get(i).getSize().getX() + uiObjects.get(i).getX()) &&
					Util.isBetween(touch.getTouchLocation().getY(), uiObjects.get(i).getY(), uiObjects.get(i).getSize().getY() + uiObjects.get(i).getY())){
				touch.getTouchLocation().subtract(uiObjects.get(i).getLocation().toIntPoint());
				uiObjects.get(i).whenPressed(touch);
				index = i;
			}
		}
	}

	@Override
	public void whenReleased(CrafexTouchEvent touch) {
		if(!scrolled && Util.isBetween(touch.getTouchLocation().getX(), uiObjects.get(index).getX(), uiObjects.get(index).getSize().getX()) &&
				Util.isBetween(touch.getTouchLocation().getY(), uiObjects.get(index).getY(), uiObjects.get(index).getSize().getY())){
			touch.getTouchLocation().subtract(uiObjects.get(index).getLocation().toIntPoint());
			uiObjects.get(index).whenPressed(touch);
		}
	}

	@Override
	public void whenHeld(CrafexTouchEvent touch) {
		if(touch.getTouchLocation().getDistance(touch.getTouchLocation()) > DRAG_DISTANCE){
			if(realSize.getX() != size.getX()){
				scrollX(this.touch.getTouchLocation().getX() - touch.getTouchLocation().getX());
				scrolled = true;
			}
			if(realSize.getY() != size.getY()){
				scrollY(this.touch.getTouchLocation().getY() - touch.getTouchLocation().getY());
				scrolled = true;
			}
			this.touch = touch;
		}
	}

}
