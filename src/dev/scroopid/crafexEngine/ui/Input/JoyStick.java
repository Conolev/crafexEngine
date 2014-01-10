package dev.scroopid.crafexEngine.ui.Input;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import dev.scroopid.crafexEngine.graphics.Sprite;
import dev.scroopid.crafexEngine.input.CrafexTouchEvent;
import dev.scroopid.crafexEngine.ui.UIObject;
import dev.scroopid.crafexEngine.util.floatPoint;
import dev.scroopid.crafexEngine.util.intPoint;
import dev.scroopid.crafexEngine.util.intRectangle;

public class JoyStick extends UIObject {
	
	private Sprite stick;
	private intRectangle stickLocation;
	private floatPoint movement;

	/**
	 * a input system for getting a humans desired direction for something
	 * @param image
	 * @param location
	 * @param layer
	 */
	public JoyStick(Bitmap image, Bitmap stick, floatPoint location) {
		super(image, location, 0);
		this.stick = new Sprite(stick);
		stickLocation = new intRectangle(location.toIntPoint(), this.stick.getSize(), 0);
		movement = new floatPoint();
	}
	
	public floatPoint getMovement(){
		return movement;
	}
	
	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		stick.draw(canvas, stickLocation);
	}

	@Override
	public void update() {
		
	}
	
	@Override
	public boolean isTouching(CrafexTouchEvent touch) {
		if(this.touch != null){
			return true;
		}
		return super.isTouching(touch);
	}
	
	public void move(intPoint touch){
		if(touch.getX() < getLeft()){
			touch.setX((int) getLeft());
		}else if(touch.getX() > getRight()){
			touch.setX((int) getRight());
		}
		if(touch.getY() < getTop()){
			touch.setY((int) getTop());
		}else if(touch.getY() > getBottom()){
			touch.setY((int) getBottom());
		}
		stickLocation.setCenter(touch);
		movement.setX(((touch.getX() - getX()) / (getWidth() / 2)));
		movement.setY(((touch.getY() - getY()) / (getHeight() / 2)));
		System.out.println(movement);
	}
	
	@Override
	public void whenPressed(CrafexTouchEvent touch) {
		super.whenPressed(touch);
		move(touch.getTouchLocation().clone());
	}
	
	@Override
	public void whenHeld(CrafexTouchEvent touch) {
		if(this.touch != null){
			move(touch.getTouchLocation().clone());
		}
	}
	
	@Override
	public void whenReleased(CrafexTouchEvent touch) {
		super.whenReleased(touch);
		move(getLocation().toIntPoint());
	}

}
