package dev.scroopid.crafexEngine.input;

import android.view.MotionEvent;
import dev.scroopid.crafexEngine.util.intPoint;

public class CrafexTouchEvent {

	public static final int ACTION_DOWN = MotionEvent.ACTION_DOWN;

	public static final int ACTION_UP = MotionEvent.ACTION_UP;

	public static final int ACTION_MOVE = MotionEvent.ACTION_MOVE;

	private int pointer;

	private intPoint touchLocation;

	private int action;

	public CrafexTouchEvent(int pointer, intPoint touchlocation, int action) {
		this.setPointer(pointer);
		this.setTouchLocation(touchlocation);
	}

	public int getAction() {
		return this.action;
	}

	public int getPointer() {
		return this.pointer;
	}

	public intPoint getTouchLocation() {
		return this.touchLocation;
	}

	public void setAction(int action) {
		this.action = action;
	}

	public void setPointer(int pointer) {
		this.pointer = pointer;
	}

	public void setTouchLocation(intPoint touchLocation) {
		this.touchLocation = touchLocation;
	}
}
