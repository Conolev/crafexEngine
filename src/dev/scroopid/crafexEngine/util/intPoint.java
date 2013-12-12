package dev.scroopid.crafexEngine.util;

import android.graphics.Point;

public class intPoint {
	private int x;
	private int y;
	
	public intPoint(){
		setX(0);
		setY(0);
	}
	
	public intPoint(intPoint point){
		set(point);
	}
	
	public intPoint(Point point){
		set(point);
	}
	
	public intPoint(int x, int y){
		setX(x);
		setY(y);
	}
	
	public void set(intPoint point){
		x = point.x;
		y = point.y;
	}
	
	public void set(Point point){
		x = point.x;
		y = point.y;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public void addX(int value){
		x += value;
	}
	
	public void addY(int value){
		y += value;
	}
	
	public intPoint add(intPoint target){
		x += target.x;
		y += target.y;
		return this;
	}
	
	public intPoint subtract(intPoint target){
		x -= target.x;
		y -= target.y;
		return this;
	}
	
	public intPoint subtractNew(intPoint target){
		return new intPoint(x - target.x, y - target.y);
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
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public boolean isEqualTo(intPoint target){
		return this.x == target.x && this.y == target.y;
	}
	
	public int getDistance(intPoint target){
		return Util.getDistance(x, y, target.x, target.y);
	}
	
	public floatPoint getFloatPoint(){
		return new floatPoint(x, y);
	}
}
