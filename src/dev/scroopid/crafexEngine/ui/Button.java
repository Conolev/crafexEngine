package dev.scroopid.crafexEngine.ui;

import android.graphics.Bitmap;
import dev.scroopid.crafexEngine.console.Command;
import dev.scroopid.crafexEngine.util.floatPoint;

public class Button extends UIObject {
	
	protected Command command;

	public Button(Bitmap image, floatPoint location, int layer) {
		super(image, location, layer);
	}
	
	public Button(Bitmap image, floatPoint location, int layer, Command command) {
		super(image, location, layer);
		this.command = command;
	}
	
}
