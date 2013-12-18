package dev.scroopid.crafexEngine.util;

import android.graphics.Rect;

public class intRectangle {
	
	private intPoint topLeft;
	private intPoint bottomRight;
	
	public intRectangle() {
		topLeft = new intPoint();
		bottomRight = new intPoint();
	}
	
	public intRectangle(intPoint topLeft, intPoint bottomRight){
		this.topLeft = topLeft;
		this.bottomRight = bottomRight;
	}
	
	public intRectangle(int left, int top, int right, int bottom){
		topLeft = new intPoint(left, top);
		bottomRight = new intPoint(right, bottom);
	}
	
	public int getLeft(){
		return topLeft.getX();
	}
	
	public int getTop(){
		return topLeft.getY();
	}
	
	public int getRight(){
		return bottomRight.getX();
	}
	
	public int getBottom(){
		return bottomRight.getY();
	}

	public intPoint getTopLeft() {
		return topLeft;
	}

	public void setTopLeft(intPoint topLeft) {
		this.topLeft = topLeft;
	}

	public intPoint getBottomRight() {
		return bottomRight;
	}

	public void setBottomRight(intPoint bottomRight) {
		this.bottomRight = bottomRight;
	}
	
	public Rect getRect(){
		return new Rect(topLeft.getX(), topLeft.getY(), bottomRight.getX(), bottomRight.getY());
	}
	
}