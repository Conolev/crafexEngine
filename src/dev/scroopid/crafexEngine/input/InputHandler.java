package dev.scroopid.crafexEngine.input;

import android.app.Activity;
import android.view.MotionEvent;
import dev.scroopid.crafexEngine.Crafex;
import dev.scroopid.crafexEngine.Updatable;
import dev.scroopid.crafexEngine.ui.Input.KeyBoard;
import dev.scroopid.crafexEngine.util.intPoint;

public class InputHandler implements Updatable {

	/**input of keyboard*/
	private Inputable input;

	/**keyboard*/
	private KeyBoard inputer;

	/**
	 * handles inputs
	 */
	public InputHandler() {

	}

	/**
	 * handles a {@link Crafex} {@link KeyBoard} {@link CrafexKeyInputEvent}
	 * @param event
	 */
	public void crafexKeyboardInput(CrafexKeyInputEvent event) {
		this.input.setInput(CrafexKeyInputEvent.doInput(event.getValue(), this.input.getInput(), this.input.getInputingIndex()));
	}

	/**
	 * ends input from {@link CrafexInputer}
	 */
	public void endInput() {
		this.inputer = null;
	}

	@Override
	public long getLastUpdateTime() {
		return 0;
	}

	@Override
	public float getUpdateTimeDelta() {
		return 0;
	}

	/**
	 * handles {@link MotionEvent}s from {@link Activity}
	 * @param event
	 * @return handled
	 */
	public boolean handleTouchInput(MotionEvent event) {
		CrafexTouchEvent touch = new CrafexTouchEvent(event.getActionIndex(), 
					new intPoint((int) event.getX(event.getActionIndex()), 
								(int) event.getY(event.getActionIndex())), 
								event.getAction());
		
		
		if (Crafex.uiHandler != null) {
			Crafex.uiHandler.touchScreen(touch);
		}
		
		return true;
	}

	@Override
	public void setLastUpdateTime(long time) {}

	/**
	 * starts {@link CrafexInputer}
	 * @param input
	 * @param inputType
	 */
	public void startInput(Inputable input, int inputType) {
		this.input = input;
		if (inputType == CrafexInputer.INPUT_TYPE_ASSCI_KEYBOARD) {
			this.inputer = new KeyBoard(null, 0, KeyBoard.KEYSET_ASSCI);// TODO Change this
			Crafex.uiHandler.setOverlay(this.inputer);
		}
	}

	@Override
	public void update() {
		if (this.inputer.isActive()) {
			CrafexKeyInputEvent[] inputs = this.inputer.getInputs();
			for (int i = 0; i < inputs.length; ++i) {
				this.crafexKeyboardInput(inputs[i]);
			}
		} else {
			this.endInput();
		}
	}

}
