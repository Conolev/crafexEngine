package dev.scroopid.crafexEngine.ui.Input;

import java.util.ArrayList;

import android.graphics.Bitmap;
import dev.scroopid.crafexEngine.Crafex;
import dev.scroopid.crafexEngine.input.CrafexInputer;
import dev.scroopid.crafexEngine.input.CrafexKeyInputEvent;
import dev.scroopid.crafexEngine.input.InputHandler;
import dev.scroopid.crafexEngine.ui.UIPanel;
import dev.scroopid.crafexEngine.util.floatPoint;

public class KeyBoard implements CrafexInputer {

	/** {@link CrafexKeyInputEvent} to be handled by {@link InputHandler} */
	public static ArrayList<CrafexKeyInputEvent> inputs;

	/**
	 * a keyboard for human input in text form
	 * 
	 * @param image
	 * @param layer
	 * @param keyset
	 */
	public KeyBoard() {
		inputs = new ArrayList<CrafexKeyInputEvent>();
	}

	@Override
	public void addInput(CrafexKeyInputEvent event) {
		inputs.add(event);
	}

	@Override
	public CrafexKeyInputEvent[] getInputs() {
		CrafexKeyInputEvent[] data = (CrafexKeyInputEvent[]) inputs.toArray();
		inputs.clear();
		return data;
	}

	@Override
	public boolean isActive() {
		return false;
	}
}
