package dev.scroopid.crafexEngine.util;

import dev.scroopid.crafexEngine.save.ISavable;
import dev.scroopid.crafexEngine.save.Ignore;
import android.graphics.Rect;

public class floatRectangle implements ISavable {

	/** coordinates of the top Left of the {@link floatRectangle} */
	private floatPoint center;

	/** coordinates of the bottom right of the {@link floatRectangle} */
	private floatPoint size;

	/** half of the size of the {@link floatRectangle} */
	@Ignore
	floatPoint half;

	/** rotation of the {@link floatRectangle} around the center point */
	private float rotation;

	/**
	 * a custom {@link Rect} {@link Class} using ints
	 */
	public floatRectangle() {
		center = new floatPoint();
		size = new floatPoint();
		rotation = 0f;
		half = new floatPoint(size.getX() / 2, size.getY() / 2);
	}

	/**
	 * a custom {@link Rect} {@link Class} using ints
	 * 
	 * @param center
	 * @param size
	 */
	public floatRectangle(floatPoint center, floatPoint size, float rotation) {
		this.center = center;
		this.size = size;
		this.rotation = rotation;
		half = new floatPoint(size.getX() / 2, size.getY() / 2);
	}

	/**
	 * a custom {@link Rect} {@link Class} using ints
	 * 
	 * @param centerX
	 * @param centerY
	 * @param width
	 * @param height
	 */
	public floatRectangle(float centerX, float centerY, float width, float height, float rotation) {
		center = new floatPoint(centerX, centerY);
		size = new floatPoint(width, height);
		this.rotation = rotation;
		half = new floatPoint(size.getX() / 2, size.getY() / 2);
	}

	/**
	 * returns the left most X coordinate
	 * 
	 * @return
	 */
	public float getX() {
		return center.getX();
	}

	/**
	 * returns the top most Y coordinate
	 * 
	 * @return
	 */
	public float getY() {
		return center.getY();
	}

	/**
	 * returns the right most X coordinate
	 * 
	 * @return
	 */
	public float getWidth() {
		return size.getX();
	}

	/**
	 * returns the bottom most Y coordinate
	 * 
	 * @return
	 */
	public float getHeight() {
		return size.getY();
	}

	/**
	 * returns the center {@link intPoint}
	 * 
	 * @return center intPoint
	 */
	public floatPoint getCenter() {
		return center;
	}

	/**
	 * sets the rectangle to new values
	 * 
	 * @param rectangle
	 */
	public void set(floatRectangle rectangle) {
		center = rectangle.getCenter().clone();
		size = rectangle.getSize().clone();
	}

	/**
	 * sets the rectangle to new values
	 * 
	 * @param center
	 * @param size
	 */
	public void set(floatPoint center, floatPoint size) {
		this.center = center;
		this.size = size;
	}

	/**
	 * sets the rectangle to new values
	 * 
	 * @param centerX
	 * @param centerY
	 * @param width
	 * @param height
	 */
	public void set(float centerX, float centerY, float width, float height) {
		setX(centerX);
		setY(centerY);
		setWidth(width);
		setHeight(height);
	}

	/**
	 * sets the center {@link intPoint}
	 * 
	 * @param center
	 */
	public void setCenter(floatPoint center) {
		this.center = center;
	}

	/**
	 * returns the size {@link intPoint}
	 * 
	 * @return size intPoint
	 */
	public floatPoint getSize() {
		return size;
	}

	/**
	 * set the size {@link intPoint}
	 * 
	 * @param size
	 */
	public void setSize(floatPoint size) {
		this.size = size;
	}

	/**
	 * sets the left most value
	 */
	public void setX(float x) {
		center.setX(x);
	}

	/**
	 * sets the bottom most value
	 */
	public void setY(float y) {
		center.setY(y);
	}

	/**
	 * sets the bottom most value
	 */
	public void setWidth(float width) {
		size.setX(width);
	}

	/**
	 * sets the bottom most value
	 */
	public void setHeight(float height) {
		size.setY(height);
	}

	/**
	 * returns the rotation of the {@link intRectangle}
	 * 
	 * @return rotation of the rectangle
	 */
	public float getRotation() {
		return rotation;
	}

	/**
	 * returns the rotation of the {@link floatRectangle}
	 * 
	 * @return rotation of the rectangle
	 */
	public void setRotation(float rotation) {
		this.rotation = rotation;
	}

	/**
	 * sets the rotation of the {@link floatRectangle}
	 * 
	 * @param rotation
	 */
	public float getLeft() {
		return center.getX() - half.getX();
	}

	/**
	 * returns the left sides X coordinate
	 * 
	 * @return left sides X coordinate
	 */
	public float getRight() {
		return center.getX() + half.getX();
	}

	/**
	 * returns the tops Y coordinate
	 * 
	 * @return tops Y coordinate
	 */
	public float getTop() {
		return center.getY() - half.getY();
	}

	/**
	 * returns the bottoms Y coordinate
	 * 
	 * @return bottoms Y coordinate
	 */
	public float getBottom() {
		return center.getY() + half.getY();
	}

	/**
	 * returns the area of the rectangle
	 * 
	 * @return area of the rectangle
	 */
	public float getArea() {
		return size.getX() * size.getY();
	}

	/**
	 * returns top left {@link floatPoint} corner
	 * 
	 * @return top left floatPoint
	 */
	public floatPoint getCornerTopLeft() {
		double angle = Math.toRadians(rotation);
		double cosTheta = Math.cos(angle);
		double sinTheta = Math.sin(angle);
		float X =
					(float) (cosTheta * (getLeft() - center.getX()) - sinTheta * (getTop() - center.getY()) + center.getX());
		float Y =
					(float) (sinTheta * (getLeft() - center.getX()) + cosTheta * (getTop() - center.getY()) + center.getY());
		return new floatPoint(X, Y);
	}

	/**
	 * returns top right {@link floatPoint} corner
	 * 
	 * @return top right floatPoint
	 */
	public floatPoint getCornerTopRight() {
		double angle = Math.toRadians(rotation);
		double cosTheta = Math.cos(angle);
		double sinTheta = Math.sin(angle);
		float X =
					(float) (cosTheta * (getRight() - center.getX()) - sinTheta * (getTop() - center.getY()) + center.getX());
		float Y =
					(float) (sinTheta * (getRight() - center.getX()) + cosTheta * (getTop() - center.getY()) + center.getY());
		return new floatPoint(X, Y);
	}

	/**
	 * returns bottom left {@link floatPoint} corner
	 * 
	 * @return bottom left floatPoint
	 */
	public floatPoint getCornerBottomLeft() {
		double angle = Math.toRadians(rotation);
		double cosTheta = Math.cos(angle);
		double sinTheta = Math.sin(angle);
		float X =
					(float) (cosTheta * (getLeft() - center.getX()) - sinTheta * (getBottom() - center.getY()) + center.getX());
		float Y =
					(float) (sinTheta * (getLeft() - center.getX()) + cosTheta * (getBottom() - center.getY()) + center.getY());
		return new floatPoint(X, Y);
	}

	/**
	 * returns bottom right {@link floatPoint} corner
	 * 
	 * @return bottom right floatPoint
	 */
	public floatPoint getCornerBottomRight() {
		double angle = Math.toRadians(rotation);
		double cosTheta = Math.cos(angle);
		double sinTheta = Math.sin(angle);
		float X =
					(float) (cosTheta * (getRight() - center.getX()) - sinTheta * (getBottom() - center.getY()) + center.getX());
		float Y =
					(float) (sinTheta * (getRight() - center.getX()) + cosTheta * (getBottom() - center.getY()) + center.getY());
		return new floatPoint(X, Y);
	}

	/**
	 * checks if provided {@link intPoint} is inside {@link floatRectangle}
	 * 
	 * @param point
	 * @return point is inside rectangle
	 */
	public boolean isInside(intPoint point) {
		return Util.isInsidePolygon(point.toFloatPoint(), new floatPoint[] { getCornerTopLeft(), getCornerTopRight(),
					getCornerBottomRight(), getCornerBottomLeft() });
	}

	/**
	 * checks if provided {@link floatPoint} is inside {@link floatRectangle}
	 * 
	 * @param point
	 * @return point is inside rectangle
	 */
	public boolean isInside(floatPoint point) {
		return Util.isInsidePolygon(point, new floatPoint[] { getCornerTopLeft(), getCornerTopRight(),
					getCornerBottomRight(), getCornerBottomLeft() });
	}

	/**
	 * checks if this {@link floatPoint} is colliding with provided {@link intRectangle}
	 * 
	 * @param rect
	 * @return is colliding with provided rectangle
	 */
	public boolean isCollided(intRectangle rect) {
		return isInside(rect.getCornerTopRight()) || isInside(rect.getCornerTopLeft())
					|| isInside(rect.getCornerBottomLeft()) || isInside(rect.getCornerBottomRight())
					|| rect.isInside(getCornerTopRight()) || rect.isInside(getCornerTopLeft())
					|| rect.isInside(getCornerBottomLeft()) || rect.isInside(getCornerBottomRight());
	}

	/**
	 * checks if this {@link floatPoint} is colliding with provided {@link floatRectangle}
	 * 
	 * @param rect
	 * @return is colliding with provided rectangle
	 */
	public boolean isCollided(floatRectangle rect) {
		return isInside(rect.getCornerTopRight()) || isInside(rect.getCornerTopLeft())
					|| isInside(rect.getCornerBottomLeft()) || isInside(rect.getCornerBottomRight())
					|| rect.isInside(getCornerTopRight()) || rect.isInside(getCornerTopLeft())
					|| rect.isInside(getCornerBottomLeft()) || rect.isInside(getCornerBottomRight());
	}

	/**
	 * returns this {@link floatRectangle} as a {@link intRectangle}
	 * 
	 * @return this floatRectangle as a intRectangle
	 */
	public intRectangle toIntRectangle() {
		return new intRectangle(center.toIntPoint(), size.toIntPoint(), (int) rotation);
	}

	/**
	 * returns a {@link Rect} with the same coordinates
	 * 
	 * @return
	 */
	public Rect getRect() {
		return new Rect((int) (center.getX() - half.getX()), (int) (center.getY() - half.getY()),
					(int) (center.getX() + half.getX()), (int) (center.getY() + half.getY()));
	}

	/**
	 * returns a clone of this {@link intRectangle}
	 */
	public floatRectangle clone() {
		return new floatRectangle(center.clone(), size.clone(), rotation);
	}

	@Override
	public void postLoad() {
		half = new floatPoint(size.getX() / 2, size.getY() / 2);
	}

	@Override
	public void postSave() {

	}

	@Override
	public void preLoad() {

	}

	@Override
	public void preSave() {

	}
	
	@Override
	public String toString() {
		return "centerX: " + center.getX() + "centerY: " + center.getY() + "width: " + size.getX() + "height: " + size.getY();
	}
}
