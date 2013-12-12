package dev.scroopid.crafexEngine.ui.Input;

import java.util.ArrayList;

import android.graphics.Bitmap;
import dev.scroopid.crafexEngine.Crafex;
import dev.scroopid.crafexEngine.input.CrafexInputer;
import dev.scroopid.crafexEngine.input.CrafexKeyInputEvent;
import dev.scroopid.crafexEngine.ui.Panel;
import dev.scroopid.crafexEngine.util.floatPoint;
import dev.scroopid.crafexEngine.util.intPoint;

public class KeyBoard extends Panel implements CrafexInputer{
	
	public static int KEYBOARD_HEIGHT = 300;
	public static int KEYSET_ASSCI = 1;
	public static ArrayList<CrafexKeyInputEvent> inputs;
	
	public KeyBoard(Bitmap image, int layer, int keyset){
		super(image, new floatPoint(Crafex.WINDOW_DIMENTIONS.getX() - KEYBOARD_HEIGHT, 0), layer);
		inputs = new ArrayList<CrafexKeyInputEvent>();
		createKeyBoard(keyset);
		
	}
	
	public void createKeyBoard(int keyset){
		if(keyset == KEYSET_ASSCI){
			
		}
	}
	
	@Override
	public void addPanelLayer(int value) {
		for(int i = 0; i < uiObjects.size(); ++i){
			if(uiObjects.get(i).getLayer() == panelLayer){
				uiObjects.get(i).setTargetLocation(Crafex.WINDOW_DIMENTIONS);
			}
			else if(uiObjects.get(i).getLayer() == panelLayer + value){
				uiObjects.get(i).setTargetLocation(Crafex.WINDOW_DIMENTIONS.subtractNew(size));
			}
		}
		panelLayer += value;
	}
	
	@Override
	public void setPanelLayer(int layer) {
		for(int i = 0; i < uiObjects.size(); ++i){
			if(uiObjects.get(i).getLayer() == panelLayer){
				uiObjects.get(i).setTargetLocation(Crafex.WINDOW_DIMENTIONS);
			}
			else if(uiObjects.get(i).getLayer() == layer){
				uiObjects.get(i).setTargetLocation(Crafex.WINDOW_DIMENTIONS.subtractNew(size));
			}
		}
		panelLayer = layer;
	}
	
	@Override
	public CrafexKeyInputEvent[] getInputs() {
		CrafexKeyInputEvent[] data = (CrafexKeyInputEvent[]) inputs.toArray();
		inputs.clear();
		return data;
	}

	@Override
	public boolean isActive() {
		if(Crafex.uiHandler.hasOverlay() && Crafex.uiHandler.getOverlay() == this){
			return true;
		}
		return false;
	}

	@Override
	public void addInput(CrafexKeyInputEvent event) {
		inputs.add(event);
	}

}
