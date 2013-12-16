package dev.scroopid.crafexEngine.util;

public class floatPoint {
	private float x;
	private float y;
	
	public floatPoint(){
		setX(0);
		setY(0);
	}
	
	public floatPoint(floatPoint point){
		set(point);
	}
	
	public floatPoint(float x, float y){
		setX(x);
		setY(y);
	}
	
	public void set(floatPoint point){
		x = point.x;
		y = point.y;
	}
	
	public void setX(float x){
		this.x = x;
	}
	
	public void setY(float y){
		this.y = y;
	}
	
	public void addX(float value){
		x += value;
	}
	
	public void addY(float value){
		y += value;
	}
	
	public floatPoint add(floatPoint target){
		x += target.x;
		y += target.y;
		return this;
	}
	
	public floatPoint subtract(floatPoint target){
		x -= target.x;
		y -= target.y;
		return this;
	}
	
	public floatPoint subtractNew(floatPoint target){
		return new floatPoint(x - target.x, y - target.y);
	}
	
	public void negateX(){
		x = -x;
	}
	
	public void negateY(){
		y = -y;
	}
	
	public void negate(){
		x = -x;
		y = -y;
	}
	
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
	}
	
	public boolean isEqualTo(floatPoint target){
		return this.x == target.x && this.y == target.y;
	}
	
	public boolean isEqualTo(intPoint target){
		return this.x == (float)target.getX() && this.y == (float)target.getY();
	}
	
	public float getDistance(intPoint target){
		return Util.getDistance(x, y, (float)target.getX(), (float)target.getY());
	}
	
	public float getDistance(floatPoint target){
		return Util.getDistance(x, y, target.x, target.y);
	}
	
	public intPoint toIntPoint(){
		return new intPoint((int) x, (int) y);
	}
	
	public floatPoint clone(){
		return new floatPoint(x, y);
	}
}
