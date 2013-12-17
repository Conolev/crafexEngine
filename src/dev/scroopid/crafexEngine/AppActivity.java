package dev.scroopid.crafexEngine;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import dev.scroopid.crafexEngine.level.Level;
import dev.scroopid.crafexEngine.util.intPoint;

public abstract class AppActivity extends Activity {
	
	public static final Logger LOGGER = new Logger(AppActivity.class);

	public static final int TOUCH_STYLE_NONE = 0;

	public static final int TOUCH_STYLE_NORMAL = 1;

	public static Crafex crafex;

	private static intPoint defaultResolution = new intPoint(1920, 1080);

	private static int defaultColor = Color.BLACK;

	private DisplayMetrics displaymetrics = new DisplayMetrics();

	protected intPoint resolution;

	private int touchStyle = 1;

	private Level level;;

	public abstract void afterCreated();

	public abstract void beforeCreated();

	public void disableTouch() {
		this.touchStyle = TOUCH_STYLE_NONE;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		LOGGER.debug("Started App");
		
		this.beforeCreated();
		
		LOGGER.debug("Starting Creation");
		
		this.getWindowManager().getDefaultDisplay().getMetrics(this.displaymetrics);
		int height = this.displaymetrics.heightPixels;
		int width = this.displaymetrics.widthPixels;
		this.resolution = new intPoint(width, height);

		crafex = new Crafex(this.level, this.getAssets(), this, defaultColor, defaultResolution, 
					this.resolution, this.getFilesDir().toString());
		this.setContentView(crafex);

		LOGGER.debug("Finished Creation");
		
		this.afterCreated();
		
		LOGGER.info("Crafex Started");
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (this.touchStyle > 0) {
			return crafex.onTouchEvent(event);
		}
		return super.onTouchEvent(event);
	}

	/**
	 * removes the apps title on the top.
	 */
	public void removeTitle() {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	}

	/**
	 * sets the default background color of the crafex
	 * @param color
	 */
	public void setDefaultColor(int color) {
		if(crafex != null)
			Crafex.DEFAULT_COLOR = color;
		else
			defaultColor = color;
	}

	/**
	 * sets the apps expected resolution
	 * @param dimentions
	 */
	public void setDefaultResolution(intPoint dimentions) {
		if(crafex != null)
			Crafex.WINDOW_DEFAULT = dimentions;
		else
			defaultResolution = dimentions;
	}

	/**
	 * sets the app to fullscreen
	 * @param fullscreen
	 */
	public void setFullScreen(boolean fullscreen) {
		if (fullscreen) {
			this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
						WindowManager.LayoutParams.FLAG_FULLSCREEN);
		} else {
			this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN, 
						WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
		}
	}

	/**
	 * set app to landscape mode
	 */
	public void setLandscape() {
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
	}

	/**
	 * sets the level of crafex
	 * @param level
	 */
	public void setLevel(Level level) {
		if(crafex != null)
			Crafex.levelMan.setLevel(level);
		else
			this.level = level;
	}
	
	/**
	 * 
	 */
	public void setPortrait() {
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}
}
