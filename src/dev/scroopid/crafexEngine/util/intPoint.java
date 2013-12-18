package dev.scroopid.crafexEngine.util;

import android.graphics.Point;

public class intPoint {
	private int x;

	private int y;

	public intPoint() {
		this.setX(0);
		this.setY(0);
	}

	public intPoint(int x, int y) {
		this.setX(x);
		this.setY(y);
	}

	public intPoint(intPoint point) {
		this.set(point);
	}

	public intPoint(Point point) {
		this.set(point);
	}

	public intPoint add(intPoint target) {
		this.x += target.x;
		this.y += target.y;
		return this;
	}

	public void addX(int value) {
		this.x += value;
	}

	public void addY(int value) {
		this.y += value;
	}

	@Override
	public intPoint clone() {
		return new intPoint(this.x, this.y);
	}

	public int getDistance(intPoint target) {
		return Util.getDistance(this.x, this.y, target.x, target.y);
	}

	public floatPoint getFloatPoint() {
		return new floatPoint(this.x, this.y);
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public boolean isEqualTo(intPoint target) {
		return this.x == target.x && this.y == target.y;
	}

	public void negate() {
		this.x = -this.x;
		this.y = -this.y;
	}

	public void negateX() {
		this.x = -this.x;
	}

	public void negateY() {
		this.y = -this.y;
	}

	public void set(intPoint point) {
		this.x = point.x;
		this.y = point.y;
	}

	public void set(Point point) {
		this.x = point.x;
		this.y = point.y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public intPoint subtract(intPoint target) {
		this.x -= target.x;
		this.y -= target.y;
		return this;
	}

	public intPoint subtractNew(intPoint target) {
		return new intPoint(this.x - target.x, this.y - target.y);
	}
	
	@Override
	public String toString() {
		return "X: " + x + ". Y:" + y + ".";
	}
}
