package dev.scroopid.crafexEngine.ui;

import android.graphics.Bitmap;
import dev.scroopid.crafexEngine.console.Command;
import dev.scroopid.crafexEngine.input.CrafexTouchEvent;
import dev.scroopid.crafexEngine.util.floatPoint;
import dev.scroopid.crafexEngine.util.intPoint;

public class UIButton extends UIObject {
	/** stored {@link Command} */
	protected Command command;

	/** how much the button will scale to when pressed */
	private float scaler;

	/** the not pressed size */
	private intPoint temp;

	/** the pressed size */
	private intPoint temp2;

	/**
	 * {@link UIObject} meant to perform actions
	 * 
	 * @param image
	 * @param location
	 * @param scaler
	 * @param layer
	 */
	public UIButton(Bitmap image, floatPoint location, float scaler) {
		super(image, location, 0);
		this.scaler = scaler;
	}

	/**
	 * {@link UIObject} meant to perform actions
	 * 
	 * @param image
	 * @param location
	 * @param rotation
	 * @param scaler
	 * @param layer
	 */
	public UIButton(Bitmap image, floatPoint location, int rotation, float scaler) {
		super(image, location, rotation);
		this.scaler = scaler;
	}

	/**
	 * {@link UIObject} meant to perform actions
	 * 
	 * @param image
	 * @param location
	 * @param rotation
	 * @param scaler
	 * @param layer
	 * @param command
	 */
	public UIButton(Bitmap image, floatPoint location, int rotation, float scaler, Command command) {
		super(image, location, rotation);
		this.command = command;
		this.scaler = scaler;
	}

	@Override
	public void whenPressed(CrafexTouchEvent touch) {
		super.whenPressed(touch);
		if (temp == null)
			temp = new intPoint((int) getSize().getX(), (int) getSize().getY());
		if (temp2 == null) {
			temp2 = temp.clone();
			temp2.set((int) (temp.getX() * scaler), (int) (temp.getY() * scaler));
		}
		setSize(temp2);
	}

	@Override
	public void whenReleased(CrafexTouchEvent touch) {
		super.whenReleased(touch);
		if (temp == null)
			temp = new intPoint((int) getSize().getX(), (int) getSize().getY());
		if (temp2 == null) {
			temp2 = temp.clone();
			temp2.set((int) (temp.getX() * scaler), (int) (temp.getY() * scaler));
		}
		setSize(temp);
	}

}
