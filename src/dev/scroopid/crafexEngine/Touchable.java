package dev.scroopid.crafexEngine;

import dev.scroopid.crafexEngine.input.CrafexTouchEvent;
import dev.scroopid.crafexEngine.util.intPoint;

public interface Touchable {
	public boolean isTouching(CrafexTouchEvent touch);
	public void whenPressed(CrafexTouchEvent touch);
	public void whenReleased(CrafexTouchEvent touch);
	public void whenHeld(CrafexTouchEvent touch);
}
