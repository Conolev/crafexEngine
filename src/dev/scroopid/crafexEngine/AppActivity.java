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

	public static final int TOUCH_STYLE_NONE = 0;

	public static final int TOUCH_STYLE_NORMAL = 1;

	public static Crafex crafex;

	private static intPoint defaultRes = new intPoint(1920, 1080);

	private static int defaultColor = Color.BLACK;

	private DisplayMetrics displaymetrics = new DisplayMetrics();

	protected intPoint resalution;

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
		this.beforeCreated();

		if (this.level == null) {
			this.level = new Level();
		}

		this.getWindowManager().getDefaultDisplay().getMetrics(this.displaymetrics);
		int height = this.displaymetrics.heightPixels;
		int width = this.displaymetrics.widthPixels;
		this.resalution = new intPoint(width, height);

		crafex =
					new Crafex(this.level, this.getAssets(), this, defaultColor, defaultRes, this.resalution, this.getFilesDir().toString());
		this.setContentView(crafex);

		this.afterCreated();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (this.touchStyle > 0) {
			return crafex.onTouchEvent(event);
		}
		return super.onTouchEvent(event);
	}

	public void removeTitle() {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	}

	public void setDefaultColor(int color) {
		defaultColor = color;
	}

	public void setDefaultResalution(intPoint dimentions) {
		defaultRes = dimentions;
	}

	public void setFullScreen(boolean fullscreen) {
		if (fullscreen) {
			this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		} else {
			this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN, WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
		}
	}

	public void setLandscape() {
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
	}

	public void setLevel(Level level) {

	}

	public void setPortrait() {
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}
}
