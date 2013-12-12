package dev.scroopid.crafexEngine.graphics;


import dev.scroopid.crafexEngine.AppActivity;
import dev.scroopid.crafexEngine.Crafex;
import dev.scroopid.crafexEngine.util.floatPoint;
import dev.scroopid.crafexEngine.util.intPoint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Sprite {
	
	/**the texture for the spriite*/
	private Bitmap _image;
	/**the destenation size for the game*/
	private intPoint _dstSize;
	/**the size of the texture*/
	private intPoint _frameSize;
	/**the default frame of the strip to goto*/
	private int _defaultFrame;
	/**the current frame the sprite is showing*/
	private int _frame;
	/**the amount of frames are in the texture*/
	private int _frames;
	/**the amount of sets of frames in the texture*/
	private int _cycle;
	/**amount to rotate the image*/
	private int _rotationDegrees;
	/**is the frames are incressing(if false the frames go backward)*/
	private boolean _frameForward;
	/**
	 * if true it uses frames of the animation sheet
	 * if false it uses rotaion with the whole image
	 */
	private boolean _frameAnimation;
	private float frameSpeed;
	private long lastUpdated = System.currentTimeMillis();

	public Sprite(Bitmap srcImage, float frameSpeed){
		setFullImage(srcImage);
		this._frames = 1;
		this._dstSize = new intPoint(srcImage.getWidth(), srcImage.getHeight());
		this._frameSize = new intPoint(_image.getWidth(), _image.getHeight());
		this._frameForward = true;
		this._defaultFrame = 0;
		this._frameAnimation = false;
		this.frameSpeed = frameSpeed;
	}
	
	public Sprite(Bitmap srcImage, intPoint dstSize, float frameSpeed){
		setFullImage(srcImage);
		this._frames = 1;
		this._dstSize = dstSize;
		this._frameSize = new intPoint(_image.getWidth(), _image.getHeight());
		this._frameForward = true;
		this._defaultFrame = 0;
		this._frameAnimation = false;
		this.frameSpeed = frameSpeed;
	}
	
	public Sprite(Bitmap srcImage, int frames, int cycles, intPoint dstSize, int defaultFrame, float frameSpeed){
		setFullImage(srcImage);
		this._frames = frames;
		this._dstSize = dstSize;
		this._frameSize = new intPoint(_image.getWidth()/frames, _image.getHeight()/ cycles);
		this._frameForward = true;
		this._defaultFrame = defaultFrame;
		this._frameAnimation = true;
		this.frameSpeed = frameSpeed;
	}
	
	public Sprite(Bitmap srcImage, int frames, int cycles, intPoint dstSize, int defaultFrame, boolean frameAnimation, float frameSpeed){
		setFullImage(srcImage);
		this._frames = frames;
		this._dstSize = dstSize;
		this._frameSize = new intPoint(_image.getWidth()/frames, _image.getHeight()/ cycles);
		this._frameForward = true;
		this._defaultFrame = defaultFrame;
		this._frameAnimation = frameAnimation;
		this.frameSpeed = frameSpeed;
	}
	
	/**gets the texture*/
	public Bitmap getFullImage() {
		return _image;
	}

	/**sets the texture*/
	public void setFullImage(Bitmap image) {
		this._image = image;
	}
	
	/**
	 * gets the dentenation width
	 * @return dstwidth
	 */
	public int getWidth(){
		return _dstSize.getX();
	}
	
	/**
	 * gets the dentenation height
	 * @return dstheight
	 */
	public int getHeight(){
		return _dstSize.getY();
	}
	
	public void draw(Canvas canvas, intPoint location){
		if(_frameAnimation){
			frameDraw(canvas, location);
		}else{
			rotationDraw(canvas, location);
		}
	}
	
	/**
	 * draws the sprite with frames
	 * @param canvas to draw to
	 * @param location on the canvas to draw to
	 */
	public void frameDraw(Canvas canvas, intPoint location){
		if((location.getX() + this._dstSize.getX()) - Crafex.levelMan.getLevel().getScroll().getX() >= 0 
				&& location.getX() - Crafex.levelMan.getLevel().getScroll().getX() <= Crafex.WINDOW_DIMENTIONS.getX()
				&& (location.getY() + this._dstSize.getY()) - Crafex.levelMan.getLevel().getScroll().getY() >= 0 
				&& location.getY() - Crafex.levelMan.getLevel().getScroll().getY() <= Crafex.WINDOW_DIMENTIONS.getY()){
			int srcX = _frame * _frameSize.getX();
			int srcY = _frameSize.getY() * _cycle;
			Rect src = new Rect(srcX, srcY, srcX + _frameSize.getX(), srcY + _frameSize.getY());
			Rect dst = new Rect((int) location.getX() - (int) Crafex.levelMan.getLevel().getScroll().getX(),
					(int) location.getY() - (int) Crafex.levelMan.getLevel().getScroll().getY(),
					(int) location.getX() + _dstSize.getX() - (int) Crafex.levelMan.getLevel().getScroll().getX(),
					(int) location.getY() + _dstSize.getY() - (int) Crafex.levelMan.getLevel().getScroll().getY());
			canvas.drawBitmap(_image, src, dst, null);
		}
	}
	
	/**
	 * 
	 * canvas to draw to
	 * @param location on the canvas to draw to
	 */
	public void rotationDraw(Canvas canvas, intPoint location){
		if((location.getX() + this._dstSize.getX()) - Crafex.levelMan.getLevel().getScroll().getX() >= 0 
				&& location.getX() - Crafex.levelMan.getLevel().getScroll().getX() <= Crafex.WINDOW_DIMENTIONS.getX() 
				&& (location.getY() + this._dstSize.getY()) - Crafex.levelMan.getLevel().getScroll().getY() >= 0 
				&& location.getY() - Crafex.levelMan.getLevel().getScroll().getY() <= Crafex.WINDOW_DIMENTIONS.getY()){
			canvas.rotate(_rotationDegrees, (int) (location.getX() - Crafex.levelMan.getLevel().getScroll().getX() + (_image.getWidth() * .5)), 
					(int) (location.getY() - Crafex.levelMan.getLevel().getScroll().getY() + (_image.getHeight() * .5)));
			Rect src = new Rect(0, 0, _image.getWidth(), _image.getHeight());
			Rect dst = new Rect((int) location.getX() - (int) Crafex.levelMan.getLevel().getScroll().getX(),
					(int) location.getY() - (int) Crafex.levelMan.getLevel().getScroll().getY(),
					(int) location.getX() + _dstSize.getX() - (int) Crafex.levelMan.getLevel().getScroll().getX(),
					(int) location.getY() + _dstSize.getY() - (int) Crafex.levelMan.getLevel().getScroll().getY());
			canvas.drawBitmap(_image, src, dst, null);
			canvas.rotate(-_rotationDegrees, (int) (location.getX() - Crafex.levelMan.getLevel().getScroll().getX() + (_image.getWidth() * .5)), 
					(int) (location.getY() - Crafex.levelMan.getLevel().getScroll().getY() + (_image.getHeight() * .5)));
		}
	}
	
	public void update(floatPoint movement, float deltaTime){
		if(_frameAnimation){
			frameUpdate(movement, deltaTime);
		}else{
			rotationUpdate(movement);
		}
	}
	
	/**
	 * updates the sprite(frames and cycles) used for frameAnimation
	 * @param movement
	 */
	public void frameUpdate(floatPoint movement, float deltaTime){
		if(movement.getX() != 0 && movement.getY() != 0){
			if(Math.abs(movement.getX()) > Math.abs(movement.getY())){
				if(movement.getX() > 0){
					if(_cycle != GraphicsUtil.directions.get("right")){
						_cycle = GraphicsUtil.directions.get("right");
						_frame = 0;
					}else{
						updateFrame(deltaTime);
					}
				}else{
					if(_cycle != GraphicsUtil.directions.get("left")){
						_cycle = GraphicsUtil.directions.get("left");
						_frame = 0;
					}else{
						updateFrame(deltaTime);
					}
				}
			}else {
				if(movement.getY() < 0){
					if(_cycle != GraphicsUtil.directions.get("up")){
						_cycle = GraphicsUtil.directions.get("up");
						_frame = 0;
					}else{
						updateFrame(deltaTime);
					}
				}else{
					if(_cycle != GraphicsUtil.directions.get("down")){
						_cycle = GraphicsUtil.directions.get("down");
						_frame = 0;
					}else{
						updateFrame(deltaTime);
					}
				}
			}
		}else {
			_frame = _defaultFrame;
		}
	}
	
	public void rotationUpdate(floatPoint movement){
		_rotationDegrees = (int) (Math.toDegrees(Math.atan2(movement.getY(), -movement.getX())));
	}
	
	/**
	 * updates the current frame
	 */
	public void updateFrame(float deltaTime){
		if((System.currentTimeMillis() - lastUpdated) >= frameSpeed * 1000){
			if(_frame >= _frames - 1){
				_frameForward = false;
			}else if(_frame <= 0){
				_frameForward = true;
			}
			
			if(_frameForward){
				_frame++;
			}else{
				_frame--;
			}
			lastUpdated = System.currentTimeMillis();
		}
		
	}
	
	/**
	 * gets the current frame
	 * @return frame
	 */
	public int getFrame(){
		return _frame;
	}
	
	/**
	 * gets the current cycle
	 * @return cycle
	 */
	public int getCycle(){
		return _cycle;
	}
}