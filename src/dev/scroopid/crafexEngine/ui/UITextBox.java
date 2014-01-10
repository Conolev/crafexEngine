package dev.scroopid.crafexEngine.ui;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import dev.scroopid.crafexEngine.util.floatPoint;
import dev.scroopid.crafexEngine.util.intPoint;

/**
 * 
 * @author DevBo
 * 
 */
public class UITextBox extends UIObject {
	/** text in text box */
	protected String Text = "";

	/** letter width */
	protected int letterWidth;

	/** letter height */
	protected int letterHeight;
	
	protected int cursor = -1;

	/**
	 * Character sheets should be assci charactersin a 16x8 table going from left to right, top to bottom, DEL being the
	 * cursor.
	 * 
	 * @param location
	 * @param layer
	 */
	public UITextBox(Bitmap image, floatPoint location) {
		super(image, location, 0);
		//TODO uiTextBox
	}

	/**
	 * get {@link UITextBox}'s text
	 * 
	 * @return
	 */
	public String getText() {
		return this.Text;
	}

	/**
	 * set the text of the {@link UITextBox}
	 * 
	 * @param text
	 */
	public void setText(String text) {
		this.Text = text;
		//TODO graphics
	}
	
	public void setCursor(int index){
		
	}
	
	public void setCursor(intPoint point){
		
	}
	
	/**
	 * returns the cursor location
	 * @return cursor location
	 */
	public int getCursor(){
		return cursor;
	}
	
	/**
	 * adds a letter to text
	 * @param letter
	 */
	public void addLetter(String letter){
		String one;
		String two;
		if(cursor < 1){
			one = "";
			two = Text;
		} else {
			one = Text.substring(0, cursor - 1);
			two = Text.substring(cursor);
		}
		
		String data = one + letter + two;
		setText(data);
	}
	
	@Override
	public void update() {
		super.update();
	}
	
	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
	}

}
