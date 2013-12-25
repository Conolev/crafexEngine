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
import dev.scroopid.crafexEngine.util.floatRectangle;
import dev.scroopid.crafexEngine.util.intPoint;
import dev.scroopid.crafexEngine.util.intRectangle;

public abstract class UIObject implements Updatable, Touchable, Drawable {

	/**sprite of the {@link UIObject}*/
	protected Sprite sprite;
	/**distance before the object is "dragging"(moving with the finder movement)*/
	protected int DRAG_DISTANCE;
	/**layer of {@link UIObject} is in*/
	private int layer;
	/**speed the {@link UIObject} rotates*/
	private int rotationSpeed;
	/**speed the {@link UIObject} when moving*/
	private int speed;
	/**location of the {@link UIObject} with size of the {@link UIObject} being displayed*/
	private floatRectangle location;
	/**target location of {@link UIObject}*/
	private intRectangle targetLocation;
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
	public UIObject(Bitmap image, floatPoint location, float rotation,  int layer) {
		this.sprite = new Sprite(image);
		this.targetLocation = new intRectangle(location.toIntPoint(), 
					new intPoint(sprite.getWidth(), sprite.getHeight()), (int) rotation);
		this.setLayer(layer);
		this.generateRect(location, rotation);
	}
	
	/**
	 * object used for UI
	 * @param image
	 * @param location
	 * @param layer of object
	 */
	public UIObject(String imageKey, floatPoint location, float rotation,  int layer) {
		this.sprite = new Sprite(imageKey);
		this.targetLocation = new intRectangle(location.toIntPoint(), 
					new intPoint(sprite.getWidth(), sprite.getHeight()), (int) rotation);
		this.setLayer(layer);
		this.generateRect(location, rotation);
	}

	/**
	 * adds a value or values to target location
	 * @param x value
	 * @param y value
	 */
	public void addTargetLocation(int x, int y) {
		this.targetLocation.getCenter().addX(x);
		this.targetLocation.getCenter().addY(y);
	}

	/**
	 * adds a value or values to target location
	 * @param values
	 */
	public void addTargetLocation(intPoint values) {
		this.targetLocation.getCenter().add(values);
	}

	@Override
	public void draw(Canvas canvas) {
		sprite.draw(canvas, location.toIntRectangle(), true);
	}
	
	/**
	 * generates size of the {@link UIObject}
	 */
	public void generateRect(floatPoint location, float rotation) {
		this.location = new floatRectangle(location, 
					new floatPoint(sprite.getWidth(), sprite.getHeight()), rotation);
	}
	
	/**
	 * returns the {@link intRectangle} of the {@link UIObject}
	 * @return rectangle of the object
	 */
	public floatRectangle getRectangle(){
		return location;
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
		return this.location.getCenter();
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
	public floatPoint getSize() {
		return this.location.getSize();
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
		return this.targetLocation.getCenter();
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

	public int getRotationSpeed() {
		return rotationSpeed;
	}

	public void setRotationSpeed(int rotationSpeed) {
		this.rotationSpeed = rotationSpeed;
	}

	/**
	 * is the {@link UIObject} active
	 * @return isActive?
	 */
	public boolean isActive() {
		return this.location.getCenter().isEqualTo(this.targetLocation.getCenter());
	}

	/**
	 * scrolls the x coordinate
	 * @param differance
	 */
	public void scrollX(int differance) {
		if (this.scroll.getX() + this.location.getSize().getX() < this.realSize.getX() && differance > 0) {
			if (this.realSize.getX() - (this.scroll.getX() + this.location.getSize().getX()) < differance) {
				this.scroll.setX(this.realSize.getX() - (int) this.location.getSize().getX());
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
		if (this.scroll.getY() + this.location.getSize().getY() < this.realSize.getY() && differance > 0) {
			if (this.realSize.getY() - (this.scroll.getY() + this.location.getSize().getY()) < differance) {
				this.scroll.setY(this.realSize.getY() - (int) this.location.getSize().getY());
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
		this.location.setCenter(new floatPoint(x, y));
	}

	/**
	 * sets the location of the {@link UIObject}
	 * @param location
	 */
	public void setLocation(floatPoint location) {
		this.location.setCenter(location);
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
		this.location.setSize(size.toFloatPoint());
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
		this.targetLocation.setCenter(new intPoint(x, y));
	}

	/**
	 * sets the target location of the {@link UIObject}
	 * @param loction
	 */
	public void setTargetLocation(intPoint loction) {
		this.targetLocation.setCenter(loction);
	}

	@Override
	public void update() {
		if (!this.location.getCenter().isEqualTo(this.targetLocation.getCenter())) {
			this.location.setCenter(Util.move(this.location.getCenter(), 
						this.targetLocation.getCenter().toFloatPoint(), this.speed * this.getUpdateTimeDelta()));
		}
		this.setLastUpdateTime(System.currentTimeMillis());
	}
	
	@Override
	public boolean isTouching(CrafexTouchEvent touch) {
		return isActive() && location.isInside(touch.getTouchLocation());
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
