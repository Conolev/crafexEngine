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

	/** keyboard */
	private static InputMethodManager IMM;

	/**
	 * handles inputs
	 */
	public InputHandler(InputMethodManager imm) {
		InputHandler.IMM = imm;
	}

	public static InputMethodManager getIMM() {
		return IMM;
	}

	public static void setIMM(InputMethodManager iMM) {
		IMM = iMM;
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
	
	public boolean handleKeyboardInput(String key){
		Crafex.uiHandler.handleKeyInput(key);
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
		((InputMethodManager)Crafex.CONTEXT.getSystemService(Context.INPUT_METHOD_SERVICE)).toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
	}

	/**
	 * hides the android keyboard
	 */
	public static void hideKeyBoard() {
		((InputMethodManager)Crafex.CONTEXT.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(AppActivity.crafex.getWindowToken(), 0);
	}

	public boolean onBackPressed() {
		return false;
	}

	@Override
	public void update() {

	}

}
