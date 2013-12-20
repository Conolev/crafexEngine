package dev.scroopid.crafexEngine.util;

import android.graphics.Rect;

public class intRectangle {
	
	//TODO make rotatable rectangles
	
	/**coordinates of the top Left of the rectangle*/
	private intPoint topLeft;
	/**coordinates of the bottom right of the rectangle*/
	private intPoint bottomRight;
	
	/**
	 * a custom {@link Rect} {@link Class} using ints
	 */
	public intRectangle() {
		topLeft = new intPoint();
		bottomRight = new intPoint();
	}
	
	/**
	 * a custom {@link Rect} {@link Class} using ints
	 * @param topLeft
	 * @param bottomRight
	 */
	public intRectangle(intPoint topLeft, intPoint bottomRight){
		this.topLeft = topLeft;
		this.bottomRight = bottomRight;
	}
	
	/**
	 * a custom {@link Rect} {@link Class} using ints
	 * @param left
	 * @param top
	 * @param right
	 * @param bottom
	 */
	public intRectangle(int left, int top, int right, int bottom){
		topLeft = new intPoint(left, top);
		bottomRight = new intPoint(right, bottom);
	}
	
	/**
	 * returns the left most X coordinate
	 * @return
	 */
	public int getLeft(){
		return topLeft.getX();
	}
	
	/**
	 * returns the top most Y coordinate
	 * @return
	 */
	public int getTop(){
		return topLeft.getY();
	}
	
	/**
	 * returns the right most X coordinate
	 * @return
	 */
	public int getRight(){
		return bottomRight.getX();
	}
	
	/**
	 * returns the bottom most Y coordinate
	 * @return
	 */
	public int getBottom(){
		return bottomRight.getY();
	}

	/**
	 * returns the top left {@link intPoint}
	 * @return top left intPoint
	 */
	public intPoint getTopLeft() {
		return topLeft;
	}
	
	/**
	 * sets the rectangle to new values
	 * @param rectangle
	 */
	public void set(intRectangle rectangle){
		topLeft = rectangle.getTopLeft().clone();
		bottomRight = rectangle.getBottomRight().clone();
	}
	
	/**
	 * sets the rectangle to new values
	 * @param topLeft
	 * @param bottomRight
	 */
	public void set(intPoint topLeft, intPoint bottomRight){
		this.topLeft = topLeft;
		this.bottomRight = bottomRight;
	}
	
	/**
	 * sets the rectangle to new values
	 * @param left
	 * @param top
	 * @param right
	 * @param bottom
	 */
	public void set(int left, int top, int right, int bottom){
		setLeft(left);
		setTop(top);
		setRight(right);
		setBottom(bottom);
	}

	/**
	 * sets the top left {@link intPoint}
	 * @param topLeft
	 */
	public void setTopLeft(intPoint topLeft) {
		this.topLeft = topLeft;
	}

	/**
	 * returns the bottom right {@link intPoint}
	 * @return bottom right intPoint
	 */
	public intPoint getBottomRight() {
		return bottomRight;
	}

	/**
	 * set the bottom right {@link intPoint}
	 * @param bottomRight
	 */
	public void setBottomRight(intPoint bottomRight) {
		this.bottomRight = bottomRight;
	}
	
	/**
	 * sets the left most value
	 */
	public void setLeft(int value){
		topLeft.setX(value);
	}
	
	/**
	 * sets the bottom most value
	 */
	public void setTop(int value){
		topLeft.setY(value);
	}
	
	/**
	 * sets the bottom most value
	 */
	public void setRight(int value){
		bottomRight.setX(value);
	}
	
	/**
	 * sets the bottom most value
	 */
	public void setBottom(int value){
		bottomRight.setY(value);
	}
	
	public floatRectangle toFloatRectangle(){
		return new floatRectangle(topLeft.toFloatPoint(), bottomRight.toFloatPoint());
	}
	
	/**
	 * returns a {@link Rect} with the same coordinates
	 * @return
	 */
	public Rect getRect(){
		return new Rect(topLeft.getX(), topLeft.getY(), bottomRight.getX(), bottomRight.getY());
	}
}