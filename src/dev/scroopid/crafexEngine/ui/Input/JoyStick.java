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

	/**
	 * a input system for getting a humans desired direction for something
	 * 
	 * @param image
	 * @param location
	 * @param layer
	 */
	public JoyStick(Bitmap image, Bitmap stick, floatPoint location, int layer) {
		super(image, location, 0, layer);
		this.stick = new Sprite(stick);
		stickLocation = new intRectangle(location.toIntPoint(), this.stick.getSize(), 0);
	}
	
	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		stick.draw(canvas, stickLocation);
	}

	@Override
	public void update() {

	}
	
	public void move(intPoint touch){
		
	}
	
	@Override
	public void whenPressed(CrafexTouchEvent touch) {
		super.whenPressed(touch);
		move(touch.getTouchLocation());
	}
	
	@Override
	public void whenHeld(CrafexTouchEvent touch) {
		if(this.touch != null){
			move(touch.getTouchLocation());
		}
	}
	
	@Override
	public void whenReleased(CrafexTouchEvent touch) {
		move(getLocation().toIntPoint());
	}

}
