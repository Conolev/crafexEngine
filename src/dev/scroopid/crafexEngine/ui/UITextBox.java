package dev.scroopid.crafexEngine.ui;

import android.graphics.Bitmap;
import dev.scroopid.crafexEngine.util.floatPoint;

/**
 * 
 * @author DevBo
 * 
 */
public class UITextBox extends UIObject {
	/**text in text box*/
	protected String Text = "";
	/**letter width*/
	protected int letterWidth;
	/**letter height*/
	protected int letterHeight;

	/**
	 * Character sheets should be assci charactersin a 16x8 table going from left to right, 
	 * top to bottom, DEL being the cursor.
	 * @param location
	 * @param layer
	 */
	public UITextBox(Bitmap image, floatPoint location, int layer) {
		super(image, location, layer);
		//TODO finish textbox
	}

	/**
	 * get {@link UITextBox}'s text
	 * @return
	 */
	public String getText() {
		return this.Text;
	}

	/**
	 * set the text of the {@link UITextBox}
	 * @param text
	 */
	public void setText(String text) {
		this.Text = text;
	}

}
