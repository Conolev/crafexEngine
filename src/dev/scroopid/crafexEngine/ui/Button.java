package dev.scroopid.crafexEngine.ui;

import android.graphics.Bitmap;
import dev.scroopid.crafexEngine.console.Command;
import dev.scroopid.crafexEngine.input.CrafexTouchEvent;
import dev.scroopid.crafexEngine.util.floatPoint;

public class Button extends UIObject {

	public Button(Bitmap image, floatPoint location, int layer) {
		super(image, location, layer);
	}

	@Override
	public void whenHeld(CrafexTouchEvent touch) {

	}

	@Override
	public void whenPressed(CrafexTouchEvent touch) {

	}

	@Override
	public void whenReleased(CrafexTouchEvent touch) {

	}

}
