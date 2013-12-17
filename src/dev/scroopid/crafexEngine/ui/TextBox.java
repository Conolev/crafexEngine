package dev.scroopid.crafexEngine.ui;

import android.graphics.Bitmap;
import dev.scroopid.crafexEngine.util.floatPoint;

/**
 * 
 * @author DevBo
 * 
 */
public class TextBox extends UIObject {

	protected String Text = "";

	protected int letterWidth;

	protected int letterHeight;

	/**
	 * Character sheets should be assci charactersin a 16x8 table going from left to right, top to bottom, DEL being the
	 * cursor.
	 * 
	 * @param location
	 * @param layer
	 */
	public TextBox(Bitmap image, floatPoint location, int layer) {
		super(image, location, layer);
	}

	public String getText() {
		return this.Text;
	}

	public void setText(String text) {
		this.Text = text;
	}

}
