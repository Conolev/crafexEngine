package dev.scroopid.crafexEngine;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import dev.scroopid.crafexEngine.input.InputHandler;
import dev.scroopid.crafexEngine.level.Level;
import dev.scroopid.crafexEngine.level.LevelManager;
import dev.scroopid.crafexEngine.ui.UIHandler;
import dev.scroopid.crafexEngine.util.FileManager;
import dev.scroopid.crafexEngine.util.intPoint;

public class Crafex extends SurfaceView implements SurfaceHolder.Callback{

	/**color of the background*/
	public static int DEFAULT_COLOR;
	
	/**file manager of the crafex*/
	public static FileManager fileMan;

	/**input handler of the crafex*/
	public static InputHandler inputHandler;

	/**UI handler of the crafex*/
	public static UIHandler uiHandler;

	/**level manager of the crafex*/
	public static LevelManager levelMan;
	
	/**Phone's screen resolution*/
	public static intPoint WINDOW_DIMENTIONS;
	
	/**the expected resolution*/
	public static intPoint WINDOW_DEFAULT;

	public static Paint paint = new Paint();
	
	/**update thread*/
	public static GameThread update;
	
	/**draw thread*/
	public static DrawThread draw;

	/**
	 * Game screen where update threads are.
	 * 
	 * @param context
	 *        of activity
	 */
	public Crafex(Level level, AssetManager assets, Context context, int backGroundColor, 
				intPoint defaultResalution, intPoint screenResalution, String files) {
		super(context);
		WINDOW_DIMENTIONS = screenResalution;
		WINDOW_DEFAULT = defaultResalution;
		DEFAULT_COLOR = backGroundColor;
		fileMan = new FileManager(assets, files);
		uiHandler = new UIHandler();
		levelMan = new LevelManager(uiHandler);
		inputHandler = new InputHandler();
		paint.setColor(backGroundColor);
		if(level != null){
			levelMan.setLevel(level);
		}else{
			levelMan.setLevel(new Level());
		}
		
		update = new GameThread(60) {
			
			@Override
			public void threadStuff() {
				update();
			}
		};
		
		draw = new DrawThread(this, 60);
		
		getHolder().addCallback(this);

        setFocusable(true);
	}
	
	/**
	 * Initializes crafex
	 */
	public void init(){
		update.start();
		draw.start();
	}
	
	/**
	 * update of crafex
	 */
	public void update(){
		levelMan.update();
	}
	
	/**
	 * draws the crafex to canvas
	 */
	public void draw(Canvas canvas) {
		canvas.drawRect(0, 0, WINDOW_DIMENTIONS.getX(), WINDOW_DIMENTIONS.getY(), paint);
		levelMan.draw(canvas);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return inputHandler.handleTouchInput(event);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		init();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		
	}
}
