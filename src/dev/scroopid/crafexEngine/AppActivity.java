package dev.scroopid.crafexEngine;

import java.lang.reflect.Method;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import dev.scroopid.crafexEngine.level.Level;
import dev.scroopid.crafexEngine.util.intPoint;

public abstract class AppActivity extends Activity {

	public static final Logger LOGGER = new Logger(AppActivity.class);

	/** touch style: none */
	public static final int TOUCH_STYLE_NONE = 0;

	/** touch style: normal */
	public static final int TOUCH_STYLE_NORMAL = 1;

	/** the applications {@link Crafex} */
	public static Crafex crafex;

	/** the expected resolution */
	private static intPoint defaultResolution = new intPoint(1920, 1080);

	/** background {@link Color} */
	private static int defaultColor = Color.BLACK;

	private DisplayMetrics displaymetrics = new DisplayMetrics();

	/** resolution of the phone's screen */
	protected intPoint resolution;

	/** current touch style */
	private int touchStyle = 1;

	/** default {@link Level} */
	private Level level;;

	/**
	 * {@link Method} called after created.
	 */
	public abstract void afterCreated();

	/**
	 * {@link Method} called before creation.
	 */
	public abstract void beforeCreated();

	/**
	 * sets touch style to return no touch
	 */
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

		crafex =
					new Crafex(this.level, this.getAssets(), this, defaultColor, defaultResolution, this.resolution,
								this.getFilesDir().toString());
		this.setContentView(crafex);

		LOGGER.debug("Finished Creation");

		this.afterCreated();

		LOGGER.info("Crafex Started");
	}

	/**
	 * removes the applications title on the top.
	 */
	public void removeTitle() {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	}

	/**
	 * sets the default background {@link Color} of the {@link Crafex}.
	 * 
	 * @param color
	 */
	public void setDefaultColor(int color) {
		if (crafex != null)
			Crafex.DEFAULT_COLOR = color;
		else
			defaultColor = color;
	}

	/**
	 * sets the applications expected resolution.
	 * 
	 * @param dimentions
	 */
	public void setDefaultResolution(intPoint dimentions) {
		if (crafex != null)
			Crafex.WINDOW_DEFAULT = dimentions;
		else
			defaultResolution = dimentions;
	}

	/**
	 * sets the application to full screen.
	 * 
	 * @param isFullscreen
	 *        ?
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
	 * set application to landscape mode.
	 */
	public void setLandscape() {
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
	}

	/**
	 * sets the level of {@link Crafex}.
	 * 
	 * @param level
	 */
	public void setLevel(Level level) {
		if (crafex != null)
			Crafex.levelMan.setLevel(level);
		else
			this.level = level;
	}

	/**
	 * set application to portrait mode.
	 */
	public void setPortrait() {
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}

	/**
	 * gets the touch style of the application.
	 * 
	 * @return
	 */
	public int getTouchStyle() {
		return touchStyle;
	}

	/**
	 * sets the touch style of the application.
	 * 
	 * @param touchStyle
	 */
	public void setTouchStyle(int touchStyle) {
		this.touchStyle = touchStyle;
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		if (!hasFocus) {
			System.exit(0);
		}
	}

	@Override
	public void onBackPressed() {
		if (!crafex.onBackPressed()) {
			super.onBackPressed();
		}
	}
}
