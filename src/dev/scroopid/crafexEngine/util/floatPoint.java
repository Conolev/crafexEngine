package dev.scroopid.crafexEngine.util;

public class floatPoint {
	private float x;

	private float y;

	public floatPoint() {
		this.setX(0);
		this.setY(0);
	}

	public floatPoint(float x, float y) {
		this.setX(x);
		this.setY(y);
	}

	public floatPoint(floatPoint point) {
		this.set(point);
	}

	public floatPoint add(floatPoint target) {
		this.x += target.x;
		this.y += target.y;
		return this;
	}

	public void addX(float value) {
		this.x += value;
	}

	public void addY(float value) {
		this.y += value;
	}

	@Override
	public floatPoint clone() {
		return new floatPoint(this.x, this.y);
	}

	public float getDistance(floatPoint target) {
		return Util.getDistance(this.x, this.y, target.x, target.y);
	}

	public float getDistance(intPoint target) {
		return Util.getDistance(this.x, this.y, target.getX(), target.getY());
	}

	public float getX() {
		return this.x;
	}

	public float getY() {
		return this.y;
	}

	public boolean isEqualTo(floatPoint target) {
		return this.x == target.x && this.y == target.y;
	}

	public boolean isEqualTo(intPoint target) {
		return this.x == target.getX() && this.y == target.getY();
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

	public void set(floatPoint point) {
		this.x = point.x;
		this.y = point.y;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public floatPoint subtract(floatPoint target) {
		this.x -= target.x;
		this.y -= target.y;
		return this;
	}

	public floatPoint subtractNew(floatPoint target) {
		return new floatPoint(this.x - target.x, this.y - target.y);
	}

	public intPoint toIntPoint() {
		return new intPoint((int) this.x, (int) this.y);
	}
	
	@Override
	public String toString() {
		return "X: " + x + ". Y:" + y + ".";
	}
}
