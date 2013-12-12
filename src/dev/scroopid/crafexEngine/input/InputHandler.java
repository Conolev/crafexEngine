package dev.scroopid.crafexEngine.input;

import java.util.ArrayList;

import android.content.Context;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import dev.scroopid.crafexEngine.Crafex;
import dev.scroopid.crafexEngine.Touchable;
import dev.scroopid.crafexEngine.Updatable;
import dev.scroopid.crafexEngine.ui.UIHandler;
import dev.scroopid.crafexEngine.ui.Input.KeyBoard;
import dev.scroopid.crafexEngine.util.Util;
import dev.scroopid.crafexEngine.util.floatPoint;
import dev.scroopid.crafexEngine.util.intPoint;

public class InputHandler implements Updatable{
	
	private Inputable input;
	private KeyBoard inputer;
	private ArrayList<Integer> touchIndex;
	
	public InputHandler(Context context){
		
		touchIndex = new ArrayList<Integer>();
	}
	
	public void startInput(Inputable input, int inputType){
		this.input = input;
		if(inputType == CrafexInputer.INPUT_TYPE_ASSCI_KEYBOARD){
			inputer = new KeyBoard(null ,0, KeyBoard.KEYSET_ASSCI);//TODO Change this
			Crafex.uiHandler.setOverlay(inputer);
		}
	}
	
	public void endInput(){
		inputer = null;
	}
	
	@Override
	public void update(){
		 if(inputer.isActive()){
			 CrafexKeyInputEvent[] inputs = inputer.getInputs();
			 for(int i = 0; i < inputs.length; ++i) {
				 crafexKeyboardInput(inputs[i]);
			 }
		 }else{
			 endInput();
		 }
	}
	
	public boolean handleTouchInput(MotionEvent event){
		boolean touched = false;
		CrafexTouchEvent touch = new CrafexTouchEvent(event.getActionIndex(), new intPoint((int) event.getX(event.getActionIndex()), 
				(int) event.getY(event.getActionIndex())), event.getActionMasked());
		if(Crafex.uiHandler != null){
			for(int i = 0; i < Crafex.uiHandler.getTouchables().length; ++i){
				if(Crafex.uiHandler.getTouchables()[i].isTouching(touch)){
					if(event.getActionMasked() == MotionEvent.ACTION_DOWN){
						Crafex.uiHandler.getTouchables()[i].whenPressed(touch);
					}
					else if(event.getActionMasked() == MotionEvent.ACTION_UP){
						Crafex.uiHandler.getTouchables()[i].whenReleased(touch);
					}
					else if(event.getActionMasked() == MotionEvent.ACTION_MOVE){
						Crafex.uiHandler.getTouchables()[i].whenHeld(touch);
					}
				}
			}
			if(!touched){
				Crafex.uiHandler.touchScreen(touch);
			}
		}
		return false;
	}
	
	public void crafexKeyboardInput(CrafexKeyInputEvent event){
		input.setInput(CrafexKeyInputEvent.doInput(event.getValue(), input.getInput(), input.getInputingIndex()));
	}

	@Override
	public void setLastUpdateTime(long time) {}

	@Override
	public long getLastUpdateTime() {
		return 0;
	}

	@Override
	public float getUpdateTimeDelta() {
		return 0;
	}

}
