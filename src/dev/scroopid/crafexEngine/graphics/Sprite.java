package dev.scroopid.crafexEngine.graphics;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import dev.scroopid.crafexEngine.Crafex;
import dev.scroopid.crafexEngine.save.ISavable;
import dev.scroopid.crafexEngine.save.Ignore;
import dev.scroopid.crafexEngine.util.floatPoint;
import dev.scroopid.crafexEngine.util.intPoint;

public class Sprite implements ISavable {

	/** the texture for the spriite */
	@Ignore
	private Bitmap _image;

	/** the destenation size for the game */
	private intPoint _dstSize;

	/** the size of the texture */
	private intPoint _frameSize;

	/** the default frame of the strip to goto */
	private int _defaultFrame;

	/** the current frame the sprite is showing */
	private int _frame;

	/** the amount of frames are in the texture */
	private int _frames;

	/** the amount of sets of frames in the texture */
	private int _cycle;

	/** amount to rotate the image */
	private int _rotationDegrees;

	/** is the frames are incressing(if false the frames go backward) */
	private boolean _frameForward;

	/**
	 * if true it uses frames of the animation sheet if false it uses rotaion with the whole image
	 */
	private boolean _frameAnimation;

	/**speed to change frame*/
	private float frameSpeed;

	/**last time sprite updated*/
	private long lastUpdated = System.currentTimeMillis();

	/**
	 * sprite for an object.
	 * @param srcImage image for sprite.
	 * @param frameSpeed speed the frames change.
	 */
	public Sprite(Bitmap srcImage, float frameSpeed) {
		this.setFullImage(srcImage);
		this._frames = 1;
		this._dstSize = new intPoint(srcImage.getWidth(), srcImage.getHeight());
		this._frameSize = new intPoint(this._image.getWidth(), this._image.getHeight());
		this._frameForward = true;
		this._defaultFrame = 0;
		this._frameAnimation = false;
		this.frameSpeed = frameSpeed;
	}

	/**
	 * sprite for an object.
	 * @param srcImage - image for sprite.
	 * @param frames - amount of frames
	 * @param cycles - amount of cycles
	 * @param dstSize - size of the individual images on map.
	 * @param defaultFrame index number
	 * @param frameAnimation - is animated of sheet
	 * @param frameSpeed speed the frames change.
	 */
	public Sprite(Bitmap srcImage, int frames, int cycles, intPoint dstSize, int defaultFrame, boolean frameAnimation, float frameSpeed) {
		this.setFullImage(srcImage);
		this._frames = frames;
		this._dstSize = dstSize;
		this._frameSize = new intPoint(this._image.getWidth() / frames, this._image.getHeight() / cycles);
		this._frameForward = true;
		this._defaultFrame = defaultFrame;
		this._frameAnimation = frameAnimation;
		this.frameSpeed = frameSpeed;
	}

	/**
	 * sprite for an object.
	 * @param srcImage - image for sprite.
	 * @param frames - amount of frames
	 * @param cycles - amount of cycles
	 * @param dstSize - size of the individual images on map.
	 * @param defaultFrame index number
	 * @param frameSpeed speed the frames change.
	 */
	public Sprite(Bitmap srcImage, int frames, int cycles, intPoint dstSize, int defaultFrame, float frameSpeed) {
		this.setFullImage(srcImage);
		this._frames = frames;
		this._dstSize = dstSize;
		this._frameSize = new intPoint(this._image.getWidth() / frames, this._image.getHeight() / cycles);
		this._frameForward = true;
		this._defaultFrame = defaultFrame;
		this._frameAnimation = true;
		this.frameSpeed = frameSpeed;
	}

	/**
	 * sprite for an object.
	 * @param srcImage - image for sprite.
	 * @param dstSize - size of the individual images on map.
	 * @param frameSpeed speed the frames change.
	 */
	public Sprite(Bitmap srcImage, intPoint dstSize, float frameSpeed) {
		this.setFullImage(srcImage);
		this._frames = 1;
		this._dstSize = dstSize;
		this._frameSize = new intPoint(this._image.getWidth(), this._image.getHeight());
		this._frameForward = true;
		this._defaultFrame = 0;
		this._frameAnimation = false;
		this.frameSpeed = frameSpeed;
	}

	/**
	 * draws the sprite to canvas at location
	 * @param canvas
	 * @param location
	 */
	public void draw(Canvas canvas, intPoint location) {
		if (this._frameAnimation) {
			this.frameDraw(canvas, location);
		} else {
			this.rotationDraw(canvas, location);
		}
	}

	/**
	 * draws the sprite with frames
	 * !!! use draw(Canvas, intPoint) !!!
	 * @param canvas 
	 * @param location
	 */
	public void frameDraw(Canvas canvas, intPoint location) {
		if ((location.getX() + this._dstSize.getX()) - Crafex.levelMan.getLevel().getScroll().getX() >= 0
					&& location.getX() - Crafex.levelMan.getLevel().getScroll().getX() <= Crafex.WINDOW_DIMENTIONS.getX()
					&& (location.getY() + this._dstSize.getY()) - Crafex.levelMan.getLevel().getScroll().getY() >= 0
					&& location.getY() - Crafex.levelMan.getLevel().getScroll().getY() <= Crafex.WINDOW_DIMENTIONS.getY()) {
			int srcX = this._frame * this._frameSize.getX();
			int srcY = this._frameSize.getY() * this._cycle;
			Rect src = new Rect(srcX, srcY, srcX + this._frameSize.getX(), srcY + this._frameSize.getY());
			Rect dst =
						new Rect(location.getX() - Crafex.levelMan.getLevel().getScroll().getX(), location.getY()
									- Crafex.levelMan.getLevel().getScroll().getY(), location.getX()
									+ this._dstSize.getX() - Crafex.levelMan.getLevel().getScroll().getX(), location.getY()
									+ this._dstSize.getY() - Crafex.levelMan.getLevel().getScroll().getY());
			canvas.drawBitmap(this._image, src, dst, null);
		}
	}

	/**
	 * updates the sprite(frames and cycles) used for frameAnimation
	 * @param movement
	 */
	public void frameUpdate(floatPoint movement, float deltaTime) {
		if (movement.getX() != 0 && movement.getY() != 0) {
			if (Math.abs(movement.getX()) > Math.abs(movement.getY())) {
				if (movement.getX() > 0) {
					if (this._cycle != GraphicsUtil.directions.get("right")) {
						this._cycle = GraphicsUtil.directions.get("right");
						this._frame = 0;
					} else {
						this.updateFrame(deltaTime);
					}
				} else {
					if (this._cycle != GraphicsUtil.directions.get("left")) {
						this._cycle = GraphicsUtil.directions.get("left");
						this._frame = 0;
					} else {
						this.updateFrame(deltaTime);
					}
				}
			} else {
				if (movement.getY() < 0) {
					if (this._cycle != GraphicsUtil.directions.get("up")) {
						this._cycle = GraphicsUtil.directions.get("up");
						this._frame = 0;
					} else {
						this.updateFrame(deltaTime);
					}
				} else {
					if (this._cycle != GraphicsUtil.directions.get("down")) {
						this._cycle = GraphicsUtil.directions.get("down");
						this._frame = 0;
					} else {
						this.updateFrame(deltaTime);
					}
				}
			}
		} else {
			this._frame = this._defaultFrame;
		}
	}

	/**
	 * gets the current cycle
	 * @return cycle
	 */
	public int getCycle() {
		return this._cycle;
	}

	/**
	 * gets the current frame
	 * @return frame
	 */
	public int getFrame() {
		return this._frame;
	}

	/** 
	 * gets the texture 
	 */
	public Bitmap getFullImage() {
		return this._image;
	}

	/**
	 * gets the dentenation height
	 * @return dstheight
	 */
	public int getHeight() {
		return this._dstSize.getY();
	}

	/**
	 * gets the dentenation width
	 * @return dstwidth
	 */
	public int getWidth() {
		return this._dstSize.getX();
	}

	@Override
	public void postLoad() {

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
	 * draws the sprite with rotation
	 * !!! use draw(Canvas, intPoint) !!!
	 * @param canvas
	 * @param location
	 */
	public void rotationDraw(Canvas canvas, intPoint location) {
		if ((location.getX() + this._dstSize.getX()) - Crafex.levelMan.getLevel().getScroll().getX() >= 0
					&& location.getX() - Crafex.levelMan.getLevel().getScroll().getX() <= Crafex.WINDOW_DIMENTIONS.getX()
					&& (location.getY() + this._dstSize.getY()) - Crafex.levelMan.getLevel().getScroll().getY() >= 0
					&& location.getY() - Crafex.levelMan.getLevel().getScroll().getY() <= Crafex.WINDOW_DIMENTIONS.getY()) {
			canvas.rotate(this._rotationDegrees, (int) (location.getX() - Crafex.levelMan.getLevel().getScroll().getX() + (this._image.getWidth() * .5)), (int) (location.getY()
						- Crafex.levelMan.getLevel().getScroll().getY() + (this._image.getHeight() * .5)));
			Rect src = new Rect(0, 0, this._image.getWidth(), this._image.getHeight());
			Rect dst =
						new Rect(location.getX() - Crafex.levelMan.getLevel().getScroll().getX(), location.getY()
									- Crafex.levelMan.getLevel().getScroll().getY(), location.getX()
									+ this._dstSize.getX() - Crafex.levelMan.getLevel().getScroll().getX(), location.getY()
									+ this._dstSize.getY() - Crafex.levelMan.getLevel().getScroll().getY());
			canvas.drawBitmap(this._image, src, dst, null);
			canvas.rotate(-this._rotationDegrees, (int) (location.getX()
						- Crafex.levelMan.getLevel().getScroll().getX() + (this._image.getWidth() * .5)), (int) (location.getY()
						- Crafex.levelMan.getLevel().getScroll().getY() + (this._image.getHeight() * .5)));
		}
	}

	/**
	 * updates values for rotation use.
	 * @param movement
	 */
	public void rotationUpdate(floatPoint movement) {
		this._rotationDegrees = (int) (Math.toDegrees(Math.atan2(movement.getY(), -movement.getX())));
	}

	/** 
	 * sets the texture 
	 */
	public void setFullImage(Bitmap image) {
		this._image = image;
	}

	/**
	 * updates values for the sprite
	 * @param movement
	 * @param deltaTime
	 */
	public void update(floatPoint movement, float deltaTime) {
		if (this._frameAnimation) {
			this.frameUpdate(movement, deltaTime);
		} else {
			this.rotationUpdate(movement);
		}
	}

	/**
	 * updates the current frame
	 */
	public void updateFrame(float deltaTime) {
		if ((System.currentTimeMillis() - this.lastUpdated) >= this.frameSpeed * 1000) {
			if (this._frame >= this._frames - 1) {
				this._frameForward = false;
			} else if (this._frame <= 0) {
				this._frameForward = true;
			}

			if (this._frameForward) {
				this._frame++;
			} else {
				this._frame--;
			}
			this.lastUpdated = System.currentTimeMillis();
		}
	}
}
