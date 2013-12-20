package dev.scroopid.crafexEngine.ui;

import android.graphics.Bitmap;
import dev.scroopid.crafexEngine.console.Command;
import dev.scroopid.crafexEngine.util.floatPoint;

public class UIButton extends UIObject {
	/**stored {@link Command}*/
	protected Command command;

	/**
	 * {@link UIObject} meant to perform actions
	 * @param image
	 * @param location
	 * @param layer
	 */
	public UIButton(Bitmap image, floatPoint location, int layer) {
		super(image, location, layer);
	}
	
	/**
	 * {@link UIObject} meant to perform actions
	 * @param image
	 * @param location
	 * @param layer
	 * @param command
	 */
	public UIButton(Bitmap image, floatPoint location, int layer, Command command) {
		super(image, location, layer);
		this.command = command;
	}
}
