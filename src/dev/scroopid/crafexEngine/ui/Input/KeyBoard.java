package dev.scroopid.crafexEngine.ui.Input;

import java.util.ArrayList;

import android.graphics.Bitmap;
import dev.scroopid.crafexEngine.Crafex;
import dev.scroopid.crafexEngine.input.CrafexInputer;
import dev.scroopid.crafexEngine.input.CrafexKeyInputEvent;
import dev.scroopid.crafexEngine.input.InputHandler;
import dev.scroopid.crafexEngine.ui.UIPanel;
import dev.scroopid.crafexEngine.util.floatPoint;

public class KeyBoard extends UIPanel implements CrafexInputer {

	/**height of keyboard*/
	public static int KEYBOARD_HEIGHT = 300;
	/**Key set: assci*/
	public static int KEYSET_ASSCI = 1;

	/**{@link CrafexKeyInputEvent} to be handled by {@link InputHandler}*/
	public static ArrayList<CrafexKeyInputEvent> inputs;

	/**
	 * a keyboard for human input in text form
	 * @param image
	 * @param layer
	 * @param keyset
	 */
	public KeyBoard(Bitmap image, int layer, int keyset) {
		super(image, new floatPoint(Crafex.WINDOW_DIMENTIONS.getX() - KEYBOARD_HEIGHT, 0), layer);
		inputs = new ArrayList<CrafexKeyInputEvent>();
		this.createKeyBoard(keyset);
		//TODO finish keyboards
	}

	@Override
	public void addInput(CrafexKeyInputEvent event) {
		inputs.add(event);
	}

	/**
	 * creates keyboard from key set
	 * @param keyset
	 */
	public void createKeyBoard(int keyset) {
		if (keyset == KEYSET_ASSCI) {

		}
	}

	@Override
	public CrafexKeyInputEvent[] getInputs() {
		CrafexKeyInputEvent[] data = (CrafexKeyInputEvent[]) inputs.toArray();
		inputs.clear();
		return data;
	}

	@Override
	public boolean isActive() {
		if (Crafex.uiHandler.hasOverlay() && Crafex.uiHandler.getOverlay() == this) {
			return true;
		}
		return false;
	}

}
