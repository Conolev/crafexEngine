package dev.scroopid.crafexEngine.ui;

import android.graphics.Bitmap;
import dev.scroopid.crafexEngine.Crafex;
import dev.scroopid.crafexEngine.input.CrafexInputer;
import dev.scroopid.crafexEngine.input.CrafexTouchEvent;
import dev.scroopid.crafexEngine.input.Inputable;
import dev.scroopid.crafexEngine.util.floatPoint;
import dev.scroopid.crafexEngine.util.intPoint;

public class UIEditableTextBox extends UITextBox implements Inputable {
	/** index of input */
	private int index = -1;

	/**
	 * {@link UITextBox} that can have human input
	 * 
	 * @param image
	 * @param location
	 * @param layer
	 */
	public UIEditableTextBox(Bitmap image, floatPoint location, int layer) {
		super(image, location, layer);
	}

	@Override
	public void endInput() {
		this.index = -1;
	}

	@Override
	public String getInput() {
		return this.Text;
	}

	@Override
	public int getInputingIndex() {
		return this.index;
	}

	@Override
	public int getInputLength() {
		return this.Text.length();
	}

	public void setCursor(intPoint location) {
		this.index = location.getX() / this.letterWidth;
	}

	@Override
	public void setInput(String newInput) {
		this.setText(newInput);
	}

	@Override
	public void startInput() {
		this.index = this.Text.length();
	}

	@Override
	public void whenReleased(CrafexTouchEvent touch) {
		if (this.touch != null && this.touch.getPointer() == touch.getPointer()) {
			if (this.index < 0) {
				this.startInput();
			} else {
				this.setCursor(touch.getTouchLocation());
			}
		}
	}

}
