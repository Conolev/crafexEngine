package dev.scroopid.crafexEngine.ui;

import java.util.ArrayList;

import android.graphics.Canvas;
import dev.scroopid.crafexEngine.Touchable;

public class UILayer {
	
	private ArrayList<UIObject> uiObjects = new ArrayList<UIObject>();
	
	public UILayer(){
		
	}

	public ArrayList<UIObject> getUiObjects() {
		return uiObjects;
	}

	public void setUiObjects(ArrayList<UIObject> uiObjects) {
		this.uiObjects = uiObjects;
	}
	
	public UIObject getObject(int index){
		return uiObjects.get(index);
	}
	
	public void addUIObject(UIObject uiObject){
		uiObjects.add(uiObject);
	}
	
	public int getSize(){
		return uiObjects.size();
	}
	
	public Touchable[] getTouchables(){
		return (Touchable[]) uiObjects.toArray();
	}
	
	public void draw(Canvas canvas){
		for(int i = 0; i < uiObjects.size(); ++i){
			getObject(i).draw(canvas);
		}
	}

}
