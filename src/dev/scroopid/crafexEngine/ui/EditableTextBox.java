package dev.scroopid.crafexEngine.ui;

import android.graphics.Bitmap;
import dev.scroopid.crafexEngine.Crafex;
import dev.scroopid.crafexEngine.input.CrafexInputer;
import dev.scroopid.crafexEngine.input.CrafexTouchEvent;
import dev.scroopid.crafexEngine.input.Inputable;
import dev.scroopid.crafexEngine.util.floatPoint;
import dev.scroopid.crafexEngine.util.intPoint;

public class EditableTextBox extends TextBox implements Inputable{
	
	private int index = -1;

	public EditableTextBox(Bitmap image, floatPoint location, int layer) {
		super(image, location, layer);
	}

	@Override
	public void whenReleased(CrafexTouchEvent touch) {
		if(this.touch != null && this.touch.getPointer() == touch.getPointer()){
			if(index < 0){
				startInput();
			}else{
				setCursor(touch.getTouchLocation());
			}
		}
	}
	
	@Override
	public void startInput() {
		index = Text.length();
		Crafex.inputHandler.startInput(this, CrafexInputer.INPUT_TYPE_ASSCI_KEYBOARD);
	}

	@Override
	public void endInput() {
		index = -1;
	}

	@Override
	public String getInput() {
		return Text;
	}

	@Override
	public void setInput(String newInput) {
		setText(newInput);
	}

	@Override
	public int getInputLength() {
		return Text.length();
	}

	@Override
	public int getInputingIndex() {
		return index;
	}
	
	public void setCursor(intPoint location){
		index = location.getX()/letterWidth;
	}

}
