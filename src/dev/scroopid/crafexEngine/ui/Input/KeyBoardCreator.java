package dev.scroopid.crafexEngine.ui.Input;

import dev.scroopid.crafexEngine.Crafex;
import dev.scroopid.crafexEngine.Logger;
import dev.scroopid.crafexEngine.input.CrafexInputer;
import dev.scroopid.crafexEngine.ui.Panel;
import dev.scroopid.crafexEngine.ui.UIObject;
import dev.scroopid.crafexEngine.util.floatPoint;

public class KeyBoardCreator {

	final Logger logger = new Logger(this.getClass());

	public UIObject[] createASSCI(CrafexInputer input) {
		Panel one = new Panel(null, new floatPoint(Crafex.WINDOW_DIMENTIONS.getX(), 0), 0) {
			@Override
			public void setLayer(int layer) {
				this.panelLayer = layer;
				for (int i = 0; i < this.uiObjects.size(); ++i) {
					if (this.panelLayer == 0) {
						this.uiObjects.get(i).addTargetLocation(-Crafex.WINDOW_DIMENTIONS.getX(), 0);
					} else if (this.panelLayer == 1) {
						this.uiObjects.get(i).addTargetLocation(Crafex.WINDOW_DIMENTIONS.getX(), 0);
					}
				}
			}
		};

		Panel two = new Panel(null, new floatPoint(Crafex.WINDOW_DIMENTIONS.getX(), 0), 1) {
			@Override
			public void setLayer(int layer) {
				this.panelLayer = layer;
				for (int i = 0; i < this.uiObjects.size(); ++i) {
					if (this.panelLayer == 0) {
						this.uiObjects.get(i).addTargetLocation(-Crafex.WINDOW_DIMENTIONS.getX(), 0);
					} else if (this.panelLayer == 1) {
						this.uiObjects.get(i).addTargetLocation(Crafex.WINDOW_DIMENTIONS.getX(), 0);
					}
				}
			}
		};

		try {
			// TODO keyboard setup
			// one.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// one.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// one.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// one.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// one.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// one.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// one.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// one.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// one.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// one.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// one.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// one.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// one.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// one.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// one.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// one.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// one.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// one.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// one.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// one.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// one.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// one.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// one.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// one.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// one.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// one.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// one.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// one.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// one.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// one.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// one.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// one.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// one.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			//
			// one.addUIObject(new Button(new floatPoint(5, 5), 1, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// one.addUIObject(new Button(new floatPoint(5, 5), 1, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// one.addUIObject(new Button(new floatPoint(5, 5), 1, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// one.addUIObject(new Button(new floatPoint(5, 5), 1, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// one.addUIObject(new Button(new floatPoint(5, 5), 1, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// one.addUIObject(new Button(new floatPoint(5, 5), 1, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// one.addUIObject(new Button(new floatPoint(5, 5), 1, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// one.addUIObject(new Button(new floatPoint(5, 5), 1, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// one.addUIObject(new Button(new floatPoint(5, 5), 1, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// one.addUIObject(new Button(new floatPoint(5, 5), 1, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// one.addUIObject(new Button(new floatPoint(5, 5), 1, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// one.addUIObject(new Button(new floatPoint(5, 5), 1, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// one.addUIObject(new Button(new floatPoint(5, 5), 1, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// one.addUIObject(new Button(new floatPoint(5, 5), 1, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// one.addUIObject(new Button(new floatPoint(5, 5), 1, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// one.addUIObject(new Button(new floatPoint(5, 5), 1, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// one.addUIObject(new Button(new floatPoint(5, 5), 1, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// one.addUIObject(new Button(new floatPoint(5, 5), 1, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// one.addUIObject(new Button(new floatPoint(5, 5), 1, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// one.addUIObject(new Button(new floatPoint(5, 5), 1, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// one.addUIObject(new Button(new floatPoint(5, 5), 1, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// one.addUIObject(new Button(new floatPoint(5, 5), 1, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// one.addUIObject(new Button(new floatPoint(5, 5), 1, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// one.addUIObject(new Button(new floatPoint(5, 5), 1, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// one.addUIObject(new Button(new floatPoint(5, 5), 1, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// one.addUIObject(new Button(new floatPoint(5, 5), 1, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// one.addUIObject(new Button(new floatPoint(5, 5), 1, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// one.addUIObject(new Button(new floatPoint(5, 5), 1, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// one.addUIObject(new Button(new floatPoint(5, 5), 1, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// one.addUIObject(new Button(new floatPoint(5, 5), 1, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// one.addUIObject(new Button(new floatPoint(5, 5), 1, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// one.addUIObject(new Button(new floatPoint(5, 5), 1, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// one.addUIObject(new Button(new floatPoint(5, 5), 1, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			//
			//
			// two.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// two.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// two.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// two.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// two.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// two.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// two.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// two.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// two.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// two.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// two.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// two.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// two.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// two.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// two.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// two.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// two.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// two.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// two.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// two.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// two.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// two.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// two.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// two.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// two.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// two.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// two.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// two.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// two.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// two.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// two.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// two.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
			// two.addUIObject(new Button(new floatPoint(5, 5), 0, new Command("input q", input.getClass(),
			// input.getClass().getMethod("addInput", CrafexKeyInputEvent.class),
			// new CrafexKeyInputEvent('q', System.currentTimeMillis()))));
		} catch (Exception e) {
			this.logger.error("couldn't create Keyboard.", e);
		}

		return new UIObject[] { one, two };
	}

}
