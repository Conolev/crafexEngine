package dev.scroopid.crafexEngine;

import android.graphics.Canvas;

public interface Drawable {
	/**
	 * draws the {@link Object} to {@link Canvas}.
	 * 
	 * @param canvas
	 *        to draw on.
	 */
	public void draw(Canvas canvas);
}
