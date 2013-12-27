package dev.scroopid.crafexEngine;

import dev.scroopid.crafexEngine.input.CrafexTouchEvent;

public interface Touchable {
	/**
	 * is the {@link CrafexTouchEvent} touching
	 * 
	 * @param touch
	 * @return isTouching
	 */
	public boolean isTouching(CrafexTouchEvent touch);

	/**
	 * actions to do when held.
	 * 
	 * @param touch
	 */
	public void whenHeld(CrafexTouchEvent touch);

	/**
	 * actions to do when pressed.
	 * 
	 * @param touch
	 */
	public void whenPressed(CrafexTouchEvent touch);

	/**
	 * actions to do when released.
	 * 
	 * @param touch
	 */
	public void whenReleased(CrafexTouchEvent touch);
}
