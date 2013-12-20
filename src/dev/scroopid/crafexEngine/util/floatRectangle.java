package dev.scroopid.crafexEngine.util;

import android.graphics.Rect;

public class floatRectangle {
	
	/**coordinates of the top Left of the rectangle*/
	private floatPoint topLeft;
	/**coordinates of the bottom right of the rectangle*/
	private floatPoint bottomRight;
	
	/**
	 * a custom {@link Rect} {@link Class} using floats
	 */
	public floatRectangle() {
		topLeft = new floatPoint();
		bottomRight = new floatPoint();
	}
	
	/**
	 * a custom {@link Rect} {@link Class} using floats
	 * @param topLeft
	 * @param bottomRight
	 */
	public floatRectangle(floatPoint topLeft, floatPoint bottomRight){
		this.topLeft = topLeft;
		this.bottomRight = bottomRight;
	}
	
	/**
	 * a custom {@link Rect} {@link Class} using floats
	 * @param left
	 * @param top
	 * @param right
	 * @param bottom
	 */
	public floatRectangle(float left, float top, float right, float bottom){
		topLeft = new floatPoint(left, top);
		bottomRight = new floatPoint(right, bottom);
	}
	
	/**
	 * returns the left most X coordinate
	 * @return
	 */
	public float getLeft(){
		return topLeft.getX();
	}
	
	/**
	 * returns the top most Y coordinate
	 * @return
	 */
	public float getTop(){
		return topLeft.getY();
	}
	
	/**
	 * returns the right most X coordinate
	 * @return
	 */
	public float getRight(){
		return bottomRight.getX();
	}
	
	/**
	 * returns the bottom most Y coordinate
	 * @return
	 */
	public float getBottom(){
		return bottomRight.getY();
	}

	/**
	 * returns the top left {@link floatPoint}
	 * @return top left intPoint
	 */
	public floatPoint getTopLeft() {
		return topLeft;
	}

	/**
	 * sets the top left {@link intPoint}
	 * @param topLeft
	 */
	public void setTopLeft(floatPoint topLeft) {
		this.topLeft = topLeft;
	}

	/**
	 * returns the bottom right {@link intPoint}
	 * @return bottom right intPoint
	 */
	public floatPoint getBottomRight() {
		return bottomRight;
	}

	/**
	 * set the bottom right {@link intPoint}
	 * @param bottomRight
	 */
	public void setBottomRight(floatPoint bottomRight) {
		this.bottomRight = bottomRight;
	}
	
	public intRectangle toIntRectangle(){
		return new intRectangle(topLeft.toIntPoint(), bottomRight.toIntPoint());
	}
	
	/**
	 * returns a {@link Rect} with the same coordinates
	 * @return
	 */
	public Rect getRect(){
		return new Rect((int) topLeft.getX(), (int) topLeft.getY(),
					(int) bottomRight.getX(), (int) bottomRight.getY());
	}
}