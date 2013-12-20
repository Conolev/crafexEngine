package dev.scroopid.crafexEngine.util;

import android.graphics.Point;
import android.media.audiofx.Equalizer;

public class floatPoint {
	
	/**X coordinate*/
	private float x;
	/**Y coordinate*/
	private float y;

	/**
	 * a custom {@link Point} {@link Class} using floats
	 */
	public floatPoint() {
		this.setX(0);
		this.setY(0);
	}

	/**
	 * a custom {@link Point} {@link Class} using floats
	 * @param x
	 * @param y
	 */
	public floatPoint(float x, float y) {
		this.setX(x);
		this.setY(y);
	}

	/**
	 * a custom {@link Point} {@link Class} using floats
	 * @param point
	 */
	public floatPoint(floatPoint point) {
		this.set(point);
	}

	/**
	 * adds the values of another {@link floatPoint} to this one
	 * @param target
	 * @return result of addition
	 */
	public floatPoint add(floatPoint target) {
		this.x += target.x;
		this.y += target.y;
		return this;
	}
	
	/**
	 * adds to the X coordinate
	 * @param value
	 */
	public void addX(float value) {
		this.x += value;
	}

	/**
	 * adds to the Y coordinate
	 * @param value
	 */
	public void addY(float value) {
		this.y += value;
	}

	/**
	 * clones this {@link floatPoint}
	 */
	public floatPoint clone() {
		return new floatPoint(this.x, this.y);
	}

	/**
	 * gets the distance from this to another {@link floatPoint}
	 * @param target
	 * @return distance from this to another floatPoint
	 */
	public float getDistance(floatPoint target) {
		return Util.getDistance(this.x, this.y, target.x, target.y);
	}

	/**
	 * gets the distance from this to a {@link intPoint}
	 * @param target
	 * @return distance from this to a intPoint
	 */
	public float getDistance(intPoint target) {
		return Util.getDistance(this.x, this.y, target.getX(), target.getY());
	}

	/**
	 * returns the X coordinate
	 * @return X coordinate
	 */
	public float getX() {
		return this.x;
	}

	/**
	 * returns the Y coordinate
	 * @return Y coordinate
	 */
	public float getY() {
		return this.y;
	}

	/**
	 * checks if this {@link floatPoint} is equal to another
	 * @param target
	 * @return is this floatPoint is equal to another
	 */
	public boolean isEqualTo(floatPoint target) {
		return this.x == target.x && this.y == target.y;
	}

	/**
	 * checks if this {@link floatPoint} is equal to a {@link intPoint}
	 * @param target
	 * @return is this floatPoint is equal to a intPoint
	 */
	public boolean isEqualTo(intPoint target) {
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
	 * sets the {@link floatPoint} to another
	 * @param point
	 */
	public void set(floatPoint point) {
		this.x = point.x;
		this.y = point.y;
	}

	/**
	 * sets the X coordinate value
	 * @param x
	 */
	public void setX(float x) {
		this.x = x;
	}

	/**
	 * sets the Y coordinate value
	 * @param y
	 */
	public void setY(float y) {
		this.y = y;
	}

	/**
	 * subtracts the values of this {@link floatPoint} with another
	 * @param target
	 * @return values of this {@link floatPoint} with another subtracted
	 */
	public floatPoint subtract(floatPoint target) {
		this.x -= target.x;
		this.y -= target.y;
		return this;
	}

	/**
	 * creates a new {@link floatPoint} from 
	 * subtracting the values of this {@link floatPoint} with another
	 * @param target
	 * @return values of this {@link floatPoint} with another subtracted
	 */
	public floatPoint subtractNew(floatPoint target) {
		return new floatPoint(this.x - target.x, this.y - target.y);
	}

	/**
	 * returns this {@link floatPoint} as an {@link intPoint}
	 * @return
	 */
	public intPoint toIntPoint() {
		return new intPoint((int) this.x, (int) this.y);
	}
	
	@Override
	public String toString() {
		return "X: " + x + ". Y:" + y + ".";
	}
}
