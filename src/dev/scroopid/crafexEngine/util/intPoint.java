package dev.scroopid.crafexEngine.util;

import android.graphics.Point;

public class intPoint {
	
	/**X coordinate*/
	private int x;
	/**Y coordinate*/
	private int y;

	/**
	 * a custom {@link Point} {@link Class} using ints
	 */
	public intPoint() {
		this.setX(0);
		this.setY(0);
	}

	/**
	 * a custom {@link Point} {@link Class} using ints
	 * @param x
	 * @param y
	 */
	public intPoint(int x, int y) {
		this.setX(x);
		this.setY(y);
	}

	/**
	 * a custom {@link Point} {@link Class} using ints
	 * @param point
	 */
	public intPoint(intPoint point) {
		this.set(point);
	}

	/**
	 * adds the values of another {@link intPoint} to this one
	 * @param target
	 * @return result of addition
	 */
	public intPoint add(intPoint target) {
		this.x += target.x;
		this.y += target.y;
		return this;
	}
	
	/**
	 * adds the values of another {@link intPoint} to this one
	 * @param target
	 * @return result of addition
	 */
	public intPoint add(int x, int y) {
		this.x += x;
		this.y += y;
		return this;
	}
	
	/**
	 * adds to the X coordinate
	 * @param value
	 */
	public void addX(int value) {
		this.x += value;
	}

	/**
	 * adds to the Y coordinate
	 * @param value
	 */
	public void addY(int value) {
		this.y += value;
	}

	/**
	 * clones this {@link intPoint}
	 */
	public intPoint clone() {
		return new intPoint(this.x, this.y);
	}

	/**
	 * gets the distance from this to another {@link intPoint}
	 * @param target
	 * @return distance from this to another intPoint
	 */
	public float getDistance(intPoint target) {
		return Util.getDistance(this.x, this.y, target.x, target.y);
	}

	/**
	 * gets the distance from this to a {@link floatPoint}
	 * @param target
	 * @return distance from this to a floatPoint
	 */
	public float getDistance(floatPoint target) {
		return Util.getDistance(this.x, this.y, target.getX(), target.getY());
	}

	/**
	 * returns the X coordinate
	 * @return X coordinate
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * returns the Y coordinate
	 * @return Y coordinate
	 */
	public int getY() {
		return this.y;
	}

	/**
	 * checks if this {@link intPoint} is equal to another
	 * @param target
	 * @return is this floatPoint is equal to another
	 */
	public boolean isEqualTo(intPoint target) {
		return this.x == target.x && this.y == target.y;
	}

	/**
	 * checks if this {@link intPoint} is equal to a {@link floatPoint}
	 * @param target
	 * @return is this intPoint is equal to a floatPoint
	 */
	public boolean isEqualTo(floatPoint target) {
		return this.x == target.getX() && this.y == target.getY();
	}

	/**
	 * return negates the coordinates
	 */
	public void negate() {
		this.x = -this.x;
		this.y = -this.y;
	}

	/**
	 * negates the X value
	 */
	public void negateX() {
		this.x = -this.x;
	}

	/**
	 * negates the Y value
	 */
	public void negateY() {
		this.y = -this.y;
	}
	
	/**
	 * sets the {@link intPoint} to another
	 * @param point
	 */
	public void set(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * sets the {@link intPoint} to another
	 * @param point
	 */
	public void set(intPoint point) {
		this.x = point.x;
		this.y = point.y;
	}

	/**
	 * sets the X coordinate value
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * sets the Y coordinate value
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * sets coordinates to 0,0
	 */
	public void reset(){
		x = 0;
		y = 0;
	}

	/**
	 * subtracts the values of this {@link intPoint} with another
	 * @param target
	 * @return values of this {@link intPoint} with another subtracted
	 */
	public intPoint subtract(intPoint target) {
		this.x -= target.x;
		this.y -= target.y;
		return this;
	}
	
	/**
	 * multiplys the coordinates by the given number
	 * @param multiplier
	 * @return this
	 */
	public intPoint multiply(int multiplier){
		this.x *= multiplier;
		this.y *= multiplier;
		return this;
	}
	
	/**
	 * multiplys the coordinates by the given numbers
	 * @param multiplierX
	 * @param multiplierY
	 * @return this
	 */
	public intPoint multiply(int multiplierX, int multiplierY){
		this.x *= multiplierX;
		this.y *= multiplierY;
		return this;
	}

	/**
	 * creates a new {@link intPoint} from 
	 * subtracting the values of this {@link intPoint} with another
	 * @param target
	 * @return values of this {@link intPoint} with another subtracted
	 */
	public intPoint subtractNew(intPoint target) {
		return new intPoint(this.x - target.x, this.y - target.y);
	}

	/**
	 * returns this {@link intPoint} as an {@link floatPoint}
	 * @return
	 */
	public floatPoint toFloatPoint() {
		return new floatPoint((int) this.x, (int) this.y);
	}
	
	@Override
	public String toString() {
		return "X: " + x + ". Y:" + y + ".";
	}
}
