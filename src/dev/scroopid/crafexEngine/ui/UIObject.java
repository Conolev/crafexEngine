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

	public UIObject(Bitmap image, floatPoint location, int layer) {
		this.sprite = new Sprite(image, 0);
		this.setLocation(location);
		// TODO add height and width setting
		this.setTargetLocation(location.toIntPoint());
		this.setLayer(layer);
		this.generateRect();
	}

	public UIObject(floatPoint location, intPoint size, int layer) {
		this.setLocation(location);
		this.size = size;
		this.setTargetLocation(location.toIntPoint());
		this.setLayer(layer);
		this.generateRect();
	}

	public void addLayer(int value) {
		this.layer = value;
	}

	public void addTargetLocation(int x, int y) {
		this.targetLocation.addX(x);
		this.targetLocation.addY(y);
	}

	public void addTargetLocation(intPoint values) {
		this.targetLocation.add(values);
	}

	@Override
	public void draw(Canvas canvas) {
		sprite.draw(canvas, location.toIntPoint());
	}

	public void generateRect() {
		size = new intPoint(sprite.getWidth(), sprite.getHeight());
	}
	
	public intRectangle getRectangle(){
		return new intRectangle((int) location.getX(), (int) location.getY(), 
					(int) location.getX() + size.getX(), (int) location.getY() + size.getY());
	}

	@Override
	public long getLastUpdateTime() {
		return this.lastUpdateTime;
	}

	public int getLayer() {
		return this.layer;
	}

	public floatPoint getLocation() {
		return this.location;
	}

	public intPoint getRealSize() {
		return this.realSize;
	}

	public intPoint getSize() {
		return this.size;
	}

	public int getSpeed() {
		return this.speed;
	}

	public intPoint getTargetLocation() {
		return this.targetLocation;
	}

	public int getTargetX() {
		return this.targetLocation.getX();
	}

	public int getTargetY() {
		return this.targetLocation.getY();
	}

	@Override
	public float getUpdateTimeDelta() {
		return (System.currentTimeMillis() - this.lastUpdateTime) / 1000f;
	}

	public float getX() {
		return this.location.getX();
	}

	public float getY() {
		return this.location.getY();
	}

	public boolean isActive() {
		return this.location.isEqualTo(this.targetLocation);
	}

	@Override
	public boolean isTouching(CrafexTouchEvent touch) {
		return isActive() && Util.isBetween(touch.getTouchLocation(), getRectangle());
	}

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

	public void setLayer(int layer) {
		this.layer = layer;
	}

	public void setLocation(float x, float y) {
		this.location = new floatPoint(x, y);
	}

	public void setLocation(floatPoint location) {
		this.location = location;
	}

	public void setRealSize(intPoint realSize) {
		this.realSize = realSize;
	}

	public void setSize(intPoint size) {
		this.size = size;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void setTargetLocation(int x, int y) {
		this.targetLocation = new intPoint(x, y);
	}

	public void setTargetLocation(intPoint loction) {
		this.targetLocation = loction;
	}

	@Override
	public void update() {
		if (!this.location.isEqualTo(this.targetLocation)) {
			this.location = Util.move(this.location, this.targetLocation.getFloatPoint(), 
						this.speed * this.getUpdateTimeDelta());
		}
		this.setLastUpdateTime(System.currentTimeMillis());
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
		touch = null;
	}
}
