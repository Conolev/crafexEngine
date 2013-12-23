package dev.scroopid.crafexEngine.util;

import dev.scroopid.crafexEngine.save.ISavable;
import dev.scroopid.crafexEngine.save.Ignore;
import android.graphics.Rect;

public class intRectangle implements ISavable{
	
	/**coordinates of the top Left of the rectangle*/
	private intPoint center;
	/**coordinates of the bottom right of the rectangle*/
	private intPoint size;
	/**half of the size of the {@link floatRectangle}*/
	@Ignore
	intPoint half;
	/**rotation of the {@link floatRectangle} around the center point*/
	private int rotation;
	
	/**
	 * a custom {@link Rect} {@link Class} using ints
	 */
	public intRectangle() {
		center = new intPoint();
		size = new intPoint();
		rotation = 0;
		half = new intPoint(size.getX()/2, size.getY()/2);
	}
	
	/**
	 * a custom {@link Rect} {@link Class} using ints
	 * @param center
	 * @param size
	 */
	public intRectangle(intPoint center, intPoint size, int rotation){
		this.center = center;
		this.size = size;
		this.rotation = rotation;
		half = new intPoint(size.getX()/2, size.getY()/2);
	}
	
	/**
	 * a custom {@link Rect} {@link Class} using ints
	 * @param centerX
	 * @param centerY
	 * @param width
	 * @param height
	 */
	public intRectangle(int centerX, int centerY, int width, int height, int rotation){
		center = new intPoint(centerX, centerY);
		size = new intPoint(width, height);
		this.rotation = rotation;
		half = new intPoint(size.getX()/2, size.getY()/2);
	}
	
	/**
	 * returns the left most X coordinate
	 * @return
	 */
	public int getX(){
		return center.getX();
	}
	
	/**
	 * returns the top most Y coordinate
	 * @return
	 */
	public int getY(){
		return center.getY();
	}
	
	/**
	 * returns the right most X coordinate
	 * @return
	 */
	public int getWidth(){
		return size.getX();
	}
	
	/**
	 * returns the bottom most Y coordinate
	 * @return
	 */
	public int getHeight(){
		return size.getY();
	}

	/**
	 * returns the center {@link intPoint}
	 * @return center intPoint
	 */
	public intPoint getCenter() {
		return center;
	}
	
	/**
	 * sets the rectangle to new values
	 * @param rectangle
	 */
	public void set(intRectangle rectangle){
		center = rectangle.getCenter().clone();
		size = rectangle.getSize().clone();
	}
	
	/**
	 * sets the rectangle to new values
	 * @param center
	 * @param size
	 */
	public void set(intPoint center, intPoint size){
		this.center = center;
		this.size = size;
	}
	
	/**
	 * sets the rectangle to new values
	 * @param centerX
	 * @param centerY
	 * @param width
	 * @param height
	 */
	public void set(int centerX, int centerY, int width, int height){
		setX(centerX);
		setY(centerY);
		setWidth(width);
		setHeight(height);
	}

	/**
	 * sets the center {@link intPoint}
	 * @param center
	 */
	public void setCenter(intPoint center) {
		this.center = center;
	}

	/**
	 * returns the size {@link intPoint}
	 * @return size intPoint
	 */
	public intPoint getSize() {
		return size;
	}

	/**
	 * set the size {@link intPoint}
	 * @param size
	 */
	public void setSize(intPoint size) {
		this.size = size;
	}
	
	/**
	 * sets the left most value
	 */
	public void setX(int x){
		center.setX(x);
	}
	
	/**
	 * sets the bottom most value
	 */
	public void setY(int y){
		center.setY(y);
	}
	
	/**
	 * sets the bottom most value
	 */
	public void setWidth(int width){
		size.setX(width);
	}
	
	/**
	 * sets the bottom most value
	 */
	public void setHeight(int height){
		size.setY(height);
	}
	
	/**
	 * returns the rotation of the {@link intRectangle}
	 * @return rotation of the rectangle
	 */
	public int getRotation() {
		return rotation;
	}

	/**
	 * sets the rotation of the {@link intRectangle}
	 * @param rotation
	 */
	public void setRotation(int rotation) {
		this.rotation = rotation;
	}
	
	/**
	 * returns the left sides X coordinate
	 * @return left sides X coordinate
	 */
	public int getLeft(){
		return center.getX() - half.getX();
	}
	
	/**
	 * returns the right sides X coordinate
	 * @return right sides X coordinate
	 */
	public int getRight(){
		return center.getX() + half.getX();
	}
	
	/**
	 * returns the tops Y coordinate
	 * @return tops Y coordinate
	 */
	public int getTop(){
		return center.getY() - half.getY();
	}
	
	/**
	 * returns the bottoms Y coordinate
	 * @return bottoms Y coordinate
	 */
	public int getBottom(){
		return center.getX() + half.getX();
	}
	
	/**
	 * returns the area of the rectangle
	 * @return area of the rectangle
	 */
	public float getArea(){
		return size.getX() * size.getY();
	}
	
	/**
	 * returns top left {@link intPoint} corner
	 * @return top left intPoint
	 */
	public intPoint getCornerTopLeft(){
		double angle = Math.toRadians(rotation);
		double cosTheta = Math.cos(angle);
		double sinTheta = Math.sin(angle);
		int X = (int) (cosTheta * (getLeft() - center.getX()) - sinTheta * (getTop() - center.getY()) + center.getX());
		int Y = (int) (sinTheta * (getLeft() - center.getX()) + cosTheta * (getTop() - center.getY()) + center.getY());
		return new intPoint(X, Y);
	}
	
	/**
	 * returns top right {@link intPoint} corner
	 * @return top right intPoint
	 */
	public intPoint getCornerTopRight(){
		double angle = Math.toRadians(rotation);
		double cosTheta = Math.cos(angle);
		double sinTheta = Math.sin(angle);
		int X = (int) (cosTheta * (getRight() - center.getX()) - sinTheta * (getTop() - center.getY()) + center.getX());
		int Y = (int) (sinTheta * (getRight() - center.getX()) + cosTheta * (getTop() - center.getY()) + center.getY());
		return new intPoint(X, Y);
	}
	
	/**
	 * returns bottom left {@link intPoint} corner
	 * @return bottom left intPoint
	 */
	public intPoint getCornerBottomLeft(){
		double angle = Math.toRadians(rotation);
		double cosTheta = Math.cos(angle);
		double sinTheta = Math.sin(angle);
		int X = (int) (cosTheta * (getLeft() - center.getX()) - sinTheta * (getBottom() - center.getY()) + center.getX());
		int Y = (int) (sinTheta * (getLeft() - center.getX()) + cosTheta * (getBottom() - center.getY()) + center.getY());
		return new intPoint(X, Y);
	}
	
	/**
	 * returns bottom right {@link intPoint} corner
	 * @return bottom right intPoint
	 */
	public intPoint getCornerBottomRight(){
		double angle = Math.toRadians(rotation);
		double cosTheta = Math.cos(angle);
		double sinTheta = Math.sin(angle);
		int X = (int) (cosTheta * (getRight() - center.getX()) - sinTheta * (getBottom() - center.getY()) + center.getX());
		int Y = (int) (sinTheta * (getRight() - center.getX()) + cosTheta * (getBottom() - center.getY()) + center.getY());
		return new intPoint(X, Y);
	}
	
	/**
	 * checks if provided {@link intPoint} is inside {@link floatRectangle}
	 * @param point
	 * @return point is inside rectangle
	 */
	public boolean isInside(intPoint point){
		return Util.isInsidePolygon(point, new intPoint[]{
					getCornerTopLeft(), getCornerTopRight(), 
					getCornerBottomRight(), getCornerBottomLeft()
		});
	}
	
	/**
	 * checks if provided {@link floatPoint} is inside {@link floatRectangle}
	 * @param point
	 * @return
	 */
	public boolean isInside(floatPoint point){
		return Util.isInsidePolygon(point.toIntPoint(), new intPoint[]{
					getCornerTopLeft(), getCornerTopRight(), 
					getCornerBottomRight(), getCornerBottomLeft()
		});
	}
	
	/**
	 * checks if this {@link intPoint} is colliding with provided {@link intRectangle}
	 * @param rect
	 * @return is colliding with provided rectangle
	 */
	public boolean isCollided(intRectangle rect){
		return isInside(rect.getCornerTopRight()) || isInside(rect.getCornerTopLeft()) ||
					isInside(rect.getCornerBottomLeft()) || isInside(rect.getCornerBottomRight()) ||
					rect.isInside(getCornerTopRight()) || rect.isInside(getCornerTopLeft()) ||
					rect.isInside(getCornerBottomLeft()) || rect.isInside(getCornerBottomRight());
	}
	
	/**
	 * checks if this {@link intPoint} is colliding with provided {@link floatRectangle}
	 * @param rect
	 * @return is colliding with provided rectangle
	 */
	public boolean isCollided(floatRectangle rect){
		return isInside(rect.getCornerTopRight()) || isInside(rect.getCornerTopLeft()) ||
					isInside(rect.getCornerBottomLeft()) || isInside(rect.getCornerBottomRight()) ||
					rect.isInside(getCornerTopRight()) || rect.isInside(getCornerTopLeft()) ||
					rect.isInside(getCornerBottomLeft()) || rect.isInside(getCornerBottomRight());
	}

	/**
	 * returns this {@link intRectangle} as a {@link floatRectangle}
	 * @return this intRectangle as a floatRectangle
	 */
	public floatRectangle toFloatRectangle(){
		return new floatRectangle(center.toFloatPoint(), size.toFloatPoint(), rotation);
	}
	
	/**
	 * returns a {@link Rect} with the same coordinates
	 * @return
	 */
	public Rect getRect(){
		return new Rect((int) (center.getX() - half.getX()), (int) (center.getY() - half.getY()), 
					(int) (center.getX() + half.getX()), (int) (center.getY() + half.getY()));
	}
	
	/**
	 * returns a clone of this {@link intRectangle}
	 */
	public intRectangle clone(){
		return new intRectangle(center.clone(), size.clone(), rotation);
	}

	@Override
	public void postLoad() {
		half = new intPoint(size.getX()/2, size.getY()/2);
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
}
