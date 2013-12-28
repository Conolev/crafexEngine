package dev.scroopid.crafexEngine.graphics;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import dev.scroopid.crafexEngine.Crafex;
import dev.scroopid.crafexEngine.save.ISavable;
import dev.scroopid.crafexEngine.save.Ignore;
import dev.scroopid.crafexEngine.util.floatPoint;
import dev.scroopid.crafexEngine.util.intPoint;
import dev.scroopid.crafexEngine.util.intRectangle;

public class Sprite implements ISavable {

	@Ignore
	private Bitmap image;

	/** key for the bitmap */
	private String imageKey;

	/** the size of the texture */
	private intPoint frameSize;

	/** the current frame the sprite is showing */
	private int frame;

	/** the amount of frames are in the texture */
	private int frames;

	/** the amount of sets of frames in the texture */
	private int cycle;

	/** speed to change frame */
	private float frameSpeed;

	/** last time sprite updated */
	@Ignore
	private long lastUpdated = System.currentTimeMillis();

	/**
	 * sprite for an object.
	 */
	public Sprite() {
		this.frames = 1;
		this.frameSize = new intPoint(this.image.getWidth(), this.image.getHeight());
		this.frameSpeed = .2f;
	}

	/**
	 * sprite for an object.
	 * 
	 * @param srcImage
	 *        image for sprite.
	 */
	public Sprite(String imageKey) {
		this.imageKey = imageKey;
		this.image = Crafex.graphicsMan.getImageFromKey(imageKey);
		this.frames = 1;
		this.frameSize = new intPoint(this.image.getWidth(), this.image.getHeight());
		this.frameSpeed = .2f;
	}

	/**
	 * sprite for an object.
	 * 
	 * @param srcImage
	 *        - image for sprite.
	 * @param frames
	 *        - amount of frames
	 * @param cycles
	 *        - amount of cycles
	 * @param defaultFrame
	 *        index number
	 * @param frameSpeed
	 *        speed the frames change.
	 */
	public Sprite(String imageKey, int frames, int cycles, float frameSpeed) {
		this.imageKey = imageKey;
		this.image = Crafex.graphicsMan.getImageFromKey(imageKey);
		this.frames = frames;
		this.frameSize = new intPoint(this.image.getWidth() / frames, this.image.getHeight() / cycles);
		this.frameSpeed = frameSpeed;
	}

	/**
	 * sprite for an object.
	 * 
	 * @param srcImage
	 *        image for sprite.
	 */
	public Sprite(Bitmap srcImage) {
		this.setImage(srcImage);
		this.frames = 1;
		this.frameSize = new intPoint(this.image.getWidth(), this.image.getHeight());
		this.frameSpeed = .2f;
	}

	/**
	 * sprite for an object.
	 * 
	 * @param srcImage
	 *        - image for sprite.
	 * @param frames
	 *        - amount of frames
	 * @param cycles
	 *        - amount of cycles
	 * @param defaultFrame
	 *        index number
	 * @param frameSpeed
	 *        speed the frames change.
	 */
	public Sprite(Bitmap srcImage, int frames, int cycles, float frameSpeed) {
		this.setImage(srcImage);
		this.frames = frames;
		this.frameSize = new intPoint(this.image.getWidth() / frames, this.image.getHeight() / cycles);
		this.frameSpeed = frameSpeed;
	}

	public String getImageKey() {
		return imageKey;
	}

	public void setImageKey(String imageKey) {
		this.imageKey = imageKey;
	}

	public intPoint getFrameSize() {
		return frameSize;
	}

	public void setFrameSize(intPoint frameSize) {
		this.frameSize = frameSize;
	}

	public int getFrames() {
		return frames;
	}

	public void setFrames(int frames) {
		this.frames = frames;
	}

	public float getFrameSpeed() {
		return frameSpeed;
	}

	public void setFrameSpeed(float frameSpeed) {
		this.frameSpeed = frameSpeed;
	}

	public long getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(long lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public Bitmap getImage() {
		return image;
	}

	public void setFrame(int frame) {
		this.frame = frame;
	}

	public void setCycle(int cycle) {
		this.cycle = cycle;
	}

	/**
	 * draws the sprite to canvas at location
	 * 
	 * @param canvas
	 * @param location
	 */
	public void draw(Canvas canvas, intRectangle location) {
		if (image != null
					&& (location.getRight()) - Crafex.levelMan.getLevel().getScroll().getX() >= 0
					&& location.getLeft() - Crafex.levelMan.getLevel().getScroll().getX() <= Crafex.WINDOW_DIMENTIONS.getX()
					&& (location.getBottom()) - Crafex.levelMan.getLevel().getScroll().getY() >= 0
					&& location.getTop() - Crafex.levelMan.getLevel().getScroll().getY() <= Crafex.WINDOW_DIMENTIONS.getY()) {

			int srcX = this.frame * this.frameSize.getX();
			int srcY = this.frameSize.getY() * this.cycle;

			Rect src = new Rect(srcX, srcY, srcX + this.frameSize.getX(), srcY + this.frameSize.getY());
			intRectangle tempdst = location.clone();
			tempdst.getCenter().subtract(Crafex.levelMan.getLevel().getScroll());
			Rect dst = tempdst.getRect();

			canvas.drawBitmap(this.image, src, dst, null);
		}
	}

	/**
	 * draws the sprite to canvas at location
	 * 
	 * @param canvas
	 * @param location
	 */
	public void draw(Canvas canvas, intRectangle location, boolean rotate) {
		if ((location.getRight()) - Crafex.levelMan.getLevel().getScroll().getX() >= 0
					&& location.getLeft() - Crafex.levelMan.getLevel().getScroll().getX() <= Crafex.WINDOW_DIMENTIONS.getX()
					&& (location.getBottom()) - Crafex.levelMan.getLevel().getScroll().getY() >= 0
					&& location.getTop() - Crafex.levelMan.getLevel().getScroll().getY() <= Crafex.WINDOW_DIMENTIONS.getY()) {

			if (rotate) {
				canvas.rotate(location.getRotation(),
							(int) (location.getX() - Crafex.levelMan.getLevel().getScroll().getX()),
							(int) (location.getY() - Crafex.levelMan.getLevel().getScroll().getY()));
			}

			int srcX = this.frame * this.frameSize.getX();
			int srcY = this.frameSize.getY() * this.cycle;

			Rect src = new Rect(srcX, srcY, srcX + this.frameSize.getX(), srcY + this.frameSize.getY());
			intRectangle tempdst = location.clone();
			tempdst.getCenter().subtract(Crafex.levelMan.getLevel().getScroll());
			Rect dst = tempdst.getRect();

			canvas.drawBitmap(this.image, src, dst, null);

			if (rotate) {
				canvas.rotate(-location.getRotation(),
							(int) (location.getX() - Crafex.levelMan.getLevel().getScroll().getX()),
							(int) (location.getY() - Crafex.levelMan.getLevel().getScroll().getY()));
			}
		}
	}

	public void cycleUpdate(floatPoint movement) {
		if (movement.getX() != 0 && movement.getY() != 0) {
			if (Math.abs(movement.getX()) > Math.abs(movement.getY())) {
				if (movement.getX() > 0) {
					if (this.cycle != GraphicsUtil.directions.get("right")) {
						this.cycle = GraphicsUtil.directions.get("right");
						this.frame = 0;
					} else {
						updateTimedFrame();
					}
				} else {
					if (this.cycle != GraphicsUtil.directions.get("left")) {
						this.cycle = GraphicsUtil.directions.get("left");
						this.frame = 0;
					} else {
						updateTimedFrame();
					}
				}
			} else {
				if (movement.getY() < 0) {
					if (this.cycle != GraphicsUtil.directions.get("up")) {
						this.cycle = GraphicsUtil.directions.get("up");
						this.frame = 0;
					} else {
						updateTimedFrame();
					}
				} else {
					if (this.cycle != GraphicsUtil.directions.get("down")) {
						this.cycle = GraphicsUtil.directions.get("down");
						this.frame = 0;
					} else {
						updateTimedFrame();
					}
				}
			}
		} else {
			updateTimedFrame();
		}
	}

	/**
	 * returns the delta of the last time updated and current time
	 * 
	 * @return delta of the last time updated and current time
	 */
	public float getDeltaTime() {
		return (System.currentTimeMillis() - lastUpdated) / 1000;
	}

	/**
	 * gets the current cycle
	 * 
	 * @return cycle
	 */
	public int getCycle() {
		return this.cycle;
	}

	/**
	 * gets the current frame
	 * 
	 * @return frame
	 */
	public int getFrame() {
		return this.frame;
	}

	/**
	 * gets the texture
	 */
	public Bitmap getFullImage() {
		return this.image;
	}

	/**
	 * gets the dentenation height
	 * 
	 * @return dstheight
	 */
	public int getHeight() {
		return this.image.getHeight();
	}

	/**
	 * gets the dentenation width
	 * @return dstwidth
	 */
	public int getWidth() {
		return this.image.getWidth();
	}
	
	public intPoint getSize(){
		return new intPoint(image.getWidth(), image.getHeight());
	}

	@Override
	public void postLoad() {
		lastUpdated = System.currentTimeMillis();
		if (imageKey != null || imageKey != "") {
			image = Crafex.graphicsMan.getImageFromKey(imageKey);
		}
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

	/**
	 * sets the texture
	 */
	public void setImage(Bitmap image) {
		this.image = image;
	}

	/**
	 * updates values for the sprite
	 * 
	 * @param movement
	 */
	public void update(floatPoint movement) {
		if (movement.getX() > 0 || movement.getY() > 0) {
			cycleUpdate(movement);
		}
	}

	/**
	 * updates values for the sprite
	 * 
	 * @param movement
	 * @param maxSpeed
	 */
	public void update(floatPoint movement, float currentSpeed, float maxSpeed) {
		// TODO make speed Update
	}

	public void updateTimedFrame() {
		if (getDeltaTime() >= this.frameSpeed) {
			if (this.frame >= this.frames - 1) {
				this.frame = 0;
			} else {
				this.frame++;
			}
		}
	}
}
