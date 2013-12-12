package dev.scroopid.crafexEngine.ui.Input;

import dev.scroopid.crafexEngine.Crafex;
import dev.scroopid.crafexEngine.Logger;
import dev.scroopid.crafexEngine.console.Command;
import dev.scroopid.crafexEngine.input.CrafexInputer;
import dev.scroopid.crafexEngine.input.CrafexKeyInputEvent;
import dev.scroopid.crafexEngine.input.Inputable;
import dev.scroopid.crafexEngine.ui.Button;
import dev.scroopid.crafexEngine.ui.Panel;
import dev.scroopid.crafexEngine.ui.UIObject;
import dev.scroopid.crafexEngine.util.floatPoint;
import dev.scroopid.crafexEngine.util.intPoint;

public class KeyBoardCreator {
	
	final Logger logger = new Logger(this.getClass());
	
	public UIObject[] createASSCI(CrafexInputer input){
		Panel one = new Panel(null, new floatPoint(Crafex.WINDOW_DIMENTIONS.getX(), 0), 0){
			@Override
			public void setLayer(int layer) {
				panelLayer = layer;
				for(int i = 0; i < uiObjects.size(); ++i){
					if(panelLayer == 0){
						uiObjects.get(i).addTargetLocation(-Crafex.WINDOW_DIMENTIONS.getX(), 0);
					}
					else if(panelLayer == 1){
						uiObjects.get(i).addTargetLocation(Crafex.WINDOW_DIMENTIONS.getX(), 0);
					}
				}
			}
		};
		
		Panel two = new Panel(null, new floatPoint(Crafex.WINDOW_DIMENTIONS.getX(), 0), 1){
			@Override
			public void setLayer(int layer) {
				panelLayer = layer;
				for(int i = 0; i < uiObjects.size(); ++i){
					if(panelLayer == 0){
						uiObjects.get(i).addTargetLocation(-Crafex.WINDOW_DIMENTIONS.getX(), 0);
					}
					else if(panelLayer == 1){
						uiObjects.get(i).addTargetLocation(Crafex.WINDOW_DIMENTIONS.getX(), 0);
					}
				}
			}
		};
		
		try {
			//TODO keyboard setup
//			one.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			one.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			one.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			one.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			one.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			one.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			one.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			one.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			one.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			one.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			one.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			one.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			one.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			one.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			one.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			one.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			one.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			one.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			one.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			one.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			one.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			one.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			one.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			one.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			one.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			one.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			one.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			one.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			one.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			one.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			one.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			one.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			one.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			
//			one.addUIObject(new Button(new floatPoint(5, 5), 1, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			one.addUIObject(new Button(new floatPoint(5, 5), 1, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			one.addUIObject(new Button(new floatPoint(5, 5), 1, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			one.addUIObject(new Button(new floatPoint(5, 5), 1, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			one.addUIObject(new Button(new floatPoint(5, 5), 1, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			one.addUIObject(new Button(new floatPoint(5, 5), 1, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			one.addUIObject(new Button(new floatPoint(5, 5), 1, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			one.addUIObject(new Button(new floatPoint(5, 5), 1, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			one.addUIObject(new Button(new floatPoint(5, 5), 1, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			one.addUIObject(new Button(new floatPoint(5, 5), 1, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			one.addUIObject(new Button(new floatPoint(5, 5), 1, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			one.addUIObject(new Button(new floatPoint(5, 5), 1, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			one.addUIObject(new Button(new floatPoint(5, 5), 1, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			one.addUIObject(new Button(new floatPoint(5, 5), 1, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			one.addUIObject(new Button(new floatPoint(5, 5), 1, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			one.addUIObject(new Button(new floatPoint(5, 5), 1, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			one.addUIObject(new Button(new floatPoint(5, 5), 1, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			one.addUIObject(new Button(new floatPoint(5, 5), 1, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			one.addUIObject(new Button(new floatPoint(5, 5), 1, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			one.addUIObject(new Button(new floatPoint(5, 5), 1, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			one.addUIObject(new Button(new floatPoint(5, 5), 1, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			one.addUIObject(new Button(new floatPoint(5, 5), 1, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			one.addUIObject(new Button(new floatPoint(5, 5), 1, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			one.addUIObject(new Button(new floatPoint(5, 5), 1, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			one.addUIObject(new Button(new floatPoint(5, 5), 1, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			one.addUIObject(new Button(new floatPoint(5, 5), 1, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			one.addUIObject(new Button(new floatPoint(5, 5), 1, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			one.addUIObject(new Button(new floatPoint(5, 5), 1, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			one.addUIObject(new Button(new floatPoint(5, 5), 1, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			one.addUIObject(new Button(new floatPoint(5, 5), 1, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			one.addUIObject(new Button(new floatPoint(5, 5), 1, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			one.addUIObject(new Button(new floatPoint(5, 5), 1, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			one.addUIObject(new Button(new floatPoint(5, 5), 1, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//		
//			
//			two.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			two.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			two.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			two.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			two.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			two.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			two.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			two.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			two.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			two.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			two.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			two.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			two.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			two.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			two.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			two.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			two.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			two.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			two.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			two.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			two.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			two.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			two.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			two.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			two.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			two.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			two.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			two.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			two.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			two.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			two.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			two.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
//			two.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(), 
//					input.getClass().getMethod("addInput", CrafexKeyInputEvent.class), 
//					new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
		} catch (Exception e) {
			logger.error("couldn't create Keyboard.", e);
		}
		
		
		
		return new UIObject[]{one, two};
	}
	
}
