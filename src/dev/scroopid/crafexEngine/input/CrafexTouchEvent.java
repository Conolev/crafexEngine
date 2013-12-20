package dev.scroopid.crafexEngine.input;

import android.view.MotionEvent;
import dev.scroopid.crafexEngine.Crafex;
import dev.scroopid.crafexEngine.util.intPoint;

public class CrafexTouchEvent {

	/**action type: down*/
	public static final int ACTION_DOWN = MotionEvent.ACTION_DOWN;

	/**action type: up*/
	public static final int ACTION_UP = MotionEvent.ACTION_UP;

	/**action type: move*/
	public static final int ACTION_MOVE = MotionEvent.ACTION_MOVE;

	/**number of finger of event*/
	private int pointer;

	/**location of touch*/
	private intPoint touchLocation;

	/**action of touch performed*/
	private int action;

	/**
	 * a holder for info regarding touch events for {@link Crafex} based elements.
	 * @param pointer
	 * @param touchlocation
	 * @param action
	 */
	public CrafexTouchEvent(int pointer, intPoint touchlocation, int action) {
		this.setPointer(pointer);
		this.setTouchLocation(touchlocation);
		setAction(action);
	}

	/**
	 * returns the action number of {@link CrafexTouchEvent}
	 * @return action number of event
	 */
	public int getAction() {
		return this.action;
	}

	/**
	 * returns the pointer number of the {@link CrafexTouchEvent}
	 * @return pointer number of the event
	 */
	public int getPointer() {
		return this.pointer;
	}

	/**
	 * returns the touch location of {@link CrafexTouchEvent}
	 * @return touch location of event
	 */
	public intPoint getTouchLocation() {
		return this.touchLocation;
	}

	/**
	 * sets the action of the {@link CrafexTouchEvent}
	 * @param action
	 */
	public void setAction(int action) {
		this.action = action;
	}

	/**
	 * sets the pointer number of the {@link CrafexTouchEvent}
	 * @param pointer
	 */
	public void setPointer(int pointer) {
		this.pointer = pointer;
	}

	/**
	 * sets the location of the touch
	 * @param touchLocation
	 */
	public void setTouchLocation(intPoint touchLocation) {
		this.touchLocation = touchLocation;
	}
	
	@Override
	public String toString() {
		String data = "action: ";
		
		if(action == ACTION_DOWN){
			data += "DOWN";
		}else if(action == ACTION_MOVE){
			data += "MOVE";
		}else if(action == ACTION_UP){
			data += "UP";
		}
		
		data += ". pointer: " + pointer +  ". X: " + touchLocation.getX() 
					+ ". Y:" + touchLocation.getY() + ".";
		
		return data;
	}
}
