package dev.scroopid.crafexEngine.ui;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import dev.scroopid.crafexEngine.Drawable;
import dev.scroopid.crafexEngine.Touchable;
import dev.scroopid.crafexEngine.Updatable;
import dev.scroopid.crafexEngine.graphics.Sprite;
import dev.scroopid.crafexEngine.input.CrafexTouchEvent;
import dev.scroopid.crafexEngine.util.Util;
import dev.scroopid.crafexEngine.util.floatPoint;
import dev.scroopid.crafexEngine.util.intPoint;
import dev.scroopid.crafexEngine.util.intRectangle;

public abstract class UIObject implements Updatable, Touchable, Drawable {

	/**sprite of the {@link UIObject}*/
	protected Sprite sprite;
	/**distance before the object is "dragging"(moving with the finder movement)*/
	protected int DRAG_DISTANCE;
	/**layer of {@link UIObject} is in*/
	private int layer;
	/**speed the {@link UIObject} when moving*/
	private int speed;
	/**location of the {@link UIObject}*/
	private floatPoint location;
	/**target location of {@link UIObject}*/
	private intPoint targetLocation;
	/**size of the {@link UIObject} being displayed*/
	protected intPoint size;
	/**real size of the {@link UIObject}*/
	protected intPoint realSize;
	/**amount the screen is "scrolled over"*/
	protected intPoint scroll;
	/**time {@link CrafexTouchEvent} started*/
	protected long touchTime;
	/**last time was updated*/
	protected long lastUpdateTime;
	/**last {@link CrafexTouchEvent} to effect this {@link UIObject}*/
	protected CrafexTouchEvent touch;

	/**
	 * object used for UI
	 * @param image
	 * @param location
	 * @param layer of object
	 */
	public UIObject(Bitmap image, floatPoint location, int layer) {
		this.sprite = new Sprite(image, 0);
		this.setLocation(location);
		this.setTargetLocation(location.toIntPoint());
		this.setLayer(layer);
		this.generateSize();
	}

	/**
	 * adds a value or values to target location
	 * @param x value
	 * @param y value
	 */
	public void addTargetLocation(int x, int y) {
		this.targetLocation.addX(x);
		this.targetLocation.addY(y);
	}

	/**
	 * adds a value or values to target location
	 * @param values
	 */
	public void addTargetLocation(intPoint values) {
		this.targetLocation.add(values);
	}

	@Override
	public void draw(Canvas canvas) {
		sprite.draw(canvas, location.toIntPoint());
	}
	
	/**
	 * generates size of the {@link UIObject}
	 */
	public void generateSize() {
		size = new intPoint(sprite.getWidth(), sprite.getHeight());
	}
	
	/**
	 * returns the {@link intRectangle} of the {@link UIObject}
	 * @return rectangle of the object
	 */
	public intRectangle getRectangle(){
		return new intRectangle((int) location.getX(), (int) location.getY(), 
					(int) location.getX() + size.getX(), (int) location.getY() + size.getY());
	}

	@Override
	public long getLastUpdateTime() {
		return this.lastUpdateTime;
	}

	/**
	 * returns the layer of the object
	 * @return
	 */
	public int getLayer() {
		return this.layer;
	}

	/**
	 * returns the location of the {@link UIObject}
	 * @return location of the object
	 */
	public floatPoint getLocation() {
		return this.location;
	}

	/**
	 * returns the real size of the {@link UIObject}
	 * @return realSize of the object
	 */
	public intPoint getRealSize() {
		return this.realSize;
	}

	/**
	 * returns the size of the {@link UIObject}
	 * @return size of the object
	 */
	public intPoint getSize() {
		return this.size;
	}

	/**
	 * returns the speed of the {@link UIObject}
	 * @return speed of the object
	 */
	public int getSpeed() {
		return this.speed;
	}

	/**
	 * returns the target location of the {@link UIObject}
	 * @return target location of the object
	 */
	public intPoint getTargetLocation() {
		return this.targetLocation;
	}

	/**
	 * returns the target locations X coordinate
	 * @return target locations X coordinate
	 */
	public int getTargetX() {
		return this.targetLocation.getX();
	}

	/**
	 * returns the target locations Y coordinate
	 * @return target locations Y coordinate
	 */
	public int getTargetY() {
		return this.targetLocation.getY();
	}

	@Override
	public float getUpdateTimeDelta() {
		return (System.currentTimeMillis() - this.lastUpdateTime) / 1000f;
	}

	/**
	 * returns the locations X coordinate
	 * @return locations X coordinate
	 */
	public float getX() {
		return this.location.getX();
	}

	/**
	 * returns the locations Y coordinate
	 * @return locations Y coordinate
	 */
	public float getY() {
		return this.location.getY();
	}

	/**
	 * is the {@link UIObject} active
	 * @return isActive?
	 */
	public boolean isActive() {
		return this.location.isEqualTo(this.targetLocation);
	}

	/**
	 * scrolls the x coordinate
	 * @param differance
	 */
	public void scrollX(int differance) {
		if (this.scroll.getX() + this.size.getX() < this.realSize.getX() && differance > 0) {
			if (this.realSize.getX() - (this.scroll.getX() + this.size.getX()) < differance) {
				this.scroll.setX(this.realSize.getX() - this.size.getX());
			} else {
				this.scroll.addX(differance);
			}
		} else if (this.scroll.getX() > 0 && differance < 0) {
			if (this.scroll.getX() < -differance) {
				this.scroll.setX(0);
			} else {
				this.scroll.addX(differance);
			}
		}
	}

	/**
	 * scrolls the Y coordinate
	 * @param differance
	 */
	public void scrollY(int differance) {
		if (this.scroll.getY() + this.size.getY() < this.realSize.getY() && differance > 0) {
			if (this.realSize.getY() - (this.scroll.getY() + this.size.getY()) < differance) {
				this.scroll.setY(this.realSize.getY() - this.size.getY());
			} else {
				this.scroll.addY(differance);
			}
		} else if (this.scroll.getY() > 0 && differance < 0) {
			if (this.scroll.getY() < -differance) {
				this.scroll.setY(0);
			} else {
				this.scroll.addY(differance);
			}
		}
	}

	@Override
	public void setLastUpdateTime(long time) {
		this.lastUpdateTime = time;
	}

	/**
	 * sets the layer of the {@link UIObject}
	 * @param layer
	 */
	public void setLayer(int layer) {
		this.layer = layer;
	}

	/**
	 * sets the location of the {@link UIObject}
	 * @param x
	 * @param y
	 */
	public void setLocation(float x, float y) {
		this.location = new floatPoint(x, y);
	}

	/**
	 * sets the location of the {@link UIObject}
	 * @param location
	 */
	public void setLocation(floatPoint location) {
		this.location = location;
	}

	/**
	 * sets the real size of the {@link UIObject}
	 * @param realSize
	 */
	public void setRealSize(intPoint realSize) {
		this.realSize = realSize;
	}

	/**
	 * sets the size of the {@link UIObject}
	 * @param size
	 */
	public void setSize(intPoint size) {
		this.size = size;
	}

	/**
	 * sets the speed of the {@link UIObject}
	 * @param speed
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	/**
	 * sets the target location of the {@link UIObject}
	 * @param x
	 * @param y
	 */
	public void setTargetLocation(int x, int y) {
		this.targetLocation = new intPoint(x, y);
	}

	/**
	 * sets the target location of the {@link UIObject}
	 * @param loction
	 */
	public void setTargetLocation(intPoint loction) {
		this.targetLocation = loction;
	}

	@Override
	public void update() {
		System.out.println(location);
		if (!this.location.isEqualTo(this.targetLocation)) {
			this.location = Util.move(this.location, this.targetLocation.toFloatPoint(), 
						this.speed * this.getUpdateTimeDelta());
		}
		this.setLastUpdateTime(System.currentTimeMillis());
	}
	
	@Override
	public boolean isTouching(CrafexTouchEvent touch) {
		return isActive() && Util.isBetween(touch.getTouchLocation(), getRectangle());
	}

	@Override
	public void whenHeld(CrafexTouchEvent touch) {
		this.touch = touch;
	}

	@Override
	public void whenPressed(CrafexTouchEvent touch) {
		this.touch = touch;
		this.touchTime = System.currentTimeMillis();
	}

	@Override
	public void whenReleased(CrafexTouchEvent touch) {
		this.touch = null;
	}
}
