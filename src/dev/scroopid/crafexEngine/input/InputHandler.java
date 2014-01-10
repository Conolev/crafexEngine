package dev.scroopid.crafexEngine.input;

import android.app.Activity;
import android.content.Context;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;
import dev.scroopid.crafexEngine.AppActivity;
import dev.scroopid.crafexEngine.Crafex;
import dev.scroopid.crafexEngine.Updatable;
import dev.scroopid.crafexEngine.util.intPoint;

public class InputHandler implements Updatable {

	/** input of keyboard */
	private Inputable input;

	/** keyboard */
	private static InputMethodManager imm;

	/**
	 * handles inputs
	 */
	public InputHandler(InputMethodManager imm) {
		this.imm = imm;
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
	 * 
	 * @param event
	 * @return handled
	 */
	public boolean handleTouchInput(MotionEvent event) {
		CrafexTouchEvent touch =
					new CrafexTouchEvent(event.getActionIndex(), new intPoint((int) event.getX(event.getActionIndex()),
								(int) event.getY(event.getActionIndex())), event.getAction());

		if (Crafex.uiHandler != null) {
			Crafex.uiHandler.touchScreen(touch);
		}

		return true;
	}
	
	public boolean handleKeyboardInput(int keyCode, KeyEvent event){
		String test = String.valueOf(event.getKeyCode());
		System.out.println(test);
		return true;
	}

	@Override
	public void setLastUpdateTime(long time) {}

	/**
	 * shows the android keyboard
	 */
	public static void showKeyBoard() {
		imm.showSoftInput(AppActivity.crafex, InputMethodManager.SHOW_FORCED);
	}

	/**
	 * hides the android keyboard
	 */
	public static void hideKeyBoard() {
		imm.hideSoftInputFromWindow(AppActivity.crafex.getApplicationWindowToken(), 0);
	}

	public boolean onBackPressed() {
		return false;
	}

	@Override
	public void update() {

	}

}
