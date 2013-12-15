package dev.scroopid.crafexEngine;

import dev.scroopid.crafexEngine.util.intPoint;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

public abstract class AppActivity extends Activity{
	
	public static final int TOUCH_STYLE_NONE = 0;
	public static final int TOUCH_STYLE_NORMAL = 1;
	
	public static Crafex crafex;
	private static intPoint defaultRes = new intPoint(1920, 1080);
	private static int defaultColor = Color.BLACK;
	private DisplayMetrics displaymetrics = new DisplayMetrics();
	protected intPoint resalution;
	private int touchStyle = 1;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		beforeCreated();
		
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		int height = displaymetrics.heightPixels;
		int width = displaymetrics.widthPixels;
		resalution = new intPoint(width, height);
		
		crafex = new Crafex(getAssets(), this, defaultColor, defaultRes, resalution, getFilesDir().toString());
		setContentView(crafex);
		
		afterCreated();
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(touchStyle > 0){
			return crafex.onTouchEvent(event);
		}
		return super.onTouchEvent(event);
	}
	
	public void setDefaultResalution(intPoint dimentions){
		defaultRes = dimentions;
	}
	
	public void setDefaultColor(int color){
		defaultColor = color;
	}
	
	public void setFullScreen(boolean fullscreen){
    	if(fullscreen){
    		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            		WindowManager.LayoutParams.FLAG_FULLSCREEN);
    	}else{
    		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,
            		WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
    	}
    }

    public void removeTitle(){
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    public void setLandscape(){
    	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    public void setPortrait(){
    	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
    
    public void disableTouch(){
    	touchStyle = TOUCH_STYLE_NONE;
    }

	public abstract void beforeCreated();
	public abstract void afterCreated();
}
