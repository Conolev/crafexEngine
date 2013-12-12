package dev.scroopid.crafexEngine.ui;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import dev.scroopid.crafexEngine.Crafex;
import dev.scroopid.crafexEngine.Drawable;
import dev.scroopid.crafexEngine.Touchable;
import dev.scroopid.crafexEngine.Updatable;
import dev.scroopid.crafexEngine.graphics.Sprite;
import dev.scroopid.crafexEngine.input.CrafexTouchEvent;
import dev.scroopid.crafexEngine.util.Util;
import dev.scroopid.crafexEngine.util.floatPoint;
import dev.scroopid.crafexEngine.util.intPoint;

public abstract class UIObject implements Updatable, Touchable, Drawable{
	
	protected Sprite sprite;
	protected int DRAG_DISTANCE;
	private int layer;
	private int speed;
	private floatPoint location;
	private intPoint targetLocation;
	protected intPoint size;
	protected intPoint realSize;
	protected intPoint scroll;
	protected long touchTime;
	protected long lastUpdateTime;
	protected CrafexTouchEvent touch;
	
	public UIObject(Bitmap image, floatPoint location, int layer){
		sprite = new Sprite(image, 0);
		setLocation(location);
		//TODO add height and width setting
		setTargetLocation(location.toIntPoint());
		setLayer(layer);
		generateRect();
	}
	
	public UIObject(floatPoint location, intPoint size, int layer){
		setLocation(location);
		this.size = size;
		setTargetLocation(location.toIntPoint());
		setLayer(layer);
		generateRect();
	}
	
	public boolean isActive(){
		return location.isEqualTo(targetLocation);
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public floatPoint getLocation() {
		return location;
	}
	
	public float getX(){
		return location.getX();
	}
	
	public float getY(){
		return location.getY();
	}
	
	public void generateRect(){
		//TODO add generate rect to UIObject.
	}
	
	public intPoint getSize() {
		return size;
	}

	public void setSize(intPoint size) {
		this.size = size;
	}

	public intPoint getRealSize() {
		return realSize;
	}

	public void setRealSize(intPoint realSize) {
		this.realSize = realSize;
	}

	public void setLocation(float x, float y){
		this.location = new floatPoint(x, y);
	}

	public void setLocation(floatPoint location) {
		this.location = location;
	}
	
	public int getLayer(){
		return layer;
	}
	
	public void setLayer(int layer){
		this.layer = layer;
	}
	
	public void addLayer(int value){
		this.layer = value;
	}
	
	public intPoint getTargetLocation() {
		return targetLocation;
	}
	
	public int getTargetX(){
		return targetLocation.getX();
	}
	
	public int getTargetY(){
		return targetLocation.getY();
	}
	
	public void setTargetLocation(int x, int y){
		this.targetLocation = new intPoint(x, y);
	}

	public void setTargetLocation(intPoint loction) {
		this.targetLocation = loction;
	}
	
	public void addTargetLocation(int x, int y){
		this.targetLocation.addX(x);
		this.targetLocation.addY(y);
	}
	
	public void addTargetLocation(intPoint values){
		this.targetLocation.add(values);
	}
	
	public void scrollX(int differance){
		if(scroll.getX() + size.getX() < realSize.getX() && differance > 0){
			if(realSize.getX() - (scroll.getX() + size.getX()) < differance){
				scroll.setX(realSize .getX() - size.getX());
			}else{
				scroll.addX(differance);
			}
		}
		else if(scroll.getX() > 0 && differance < 0){
			if(scroll.getX() < -differance){
				scroll.setX(0);
			}else{
				scroll.addX(differance);
			}
		}
	}
	
	public void scrollY(int differance){
		if(scroll.getY() + size.getY() < realSize.getY() && differance > 0){
			if(realSize.getY() - (scroll.getY() + size.getY()) < differance){
				scroll.setY(realSize .getY() - size.getY());
			}else{
				scroll.addY(differance);
			}
		}
		else if(scroll.getY() > 0 && differance < 0){
			if(scroll.getY() < -differance){
				scroll.setY(0);
			}else{
				scroll.addY(differance);
			}
		}
	}
	
	@Override
	public boolean isTouching(CrafexTouchEvent touch) {
		return Util.isBetween(touch.getTouchLocation().getX(), getX(), getSize().getX() + getX()) &&
				Util.isBetween(touch.getTouchLocation().getY(), getY(), getSize().getY() + getY());
	}
	
	@Override
	public void whenPressed(CrafexTouchEvent touch){
		this.touch = touch;
		touchTime = System.currentTimeMillis();
	}
	
	@Override
	public void whenReleased(CrafexTouchEvent touch){
		touch = null;
	}
	
	@Override
	public void whenHeld(CrafexTouchEvent touch){
		this.touch = touch;
	}
	
	public void update(){
		if(!location.isEqualTo(targetLocation)){
			Util.move(location, targetLocation.getFloatPoint(), speed * getUpdateTimeDelta());
		}
		setLastUpdateTime(System.currentTimeMillis());
	}
	
	@Override
	public void setLastUpdateTime(long time) {
		lastUpdateTime = time;		
	}
	
	@Override
	public long getLastUpdateTime() {
		return lastUpdateTime;
	}
	
	@Override
	public float getUpdateTimeDelta() {
		return (System.currentTimeMillis() - lastUpdateTime)/1000;
	}
	
	@Override
	public void draw(Canvas canvas) {
		
	}
}
