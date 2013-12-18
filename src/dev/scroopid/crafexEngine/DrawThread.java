package dev.scroopid.crafexEngine;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.View;

public class DrawThread extends GameThread{
	
	private SurfaceHolder view;
	private Crafex crafex;
	
	public Paint paint = new Paint();
	
	public DrawThread(Crafex view){
		super();
		crafex = view;
		this.view = view.getHolder();
	}
	
	public DrawThread(Crafex view, int fps) {
		super(fps);
		crafex = view;
		this.view = view.getHolder();
	}

	@Override
	public void threadStuff() {
		Canvas canvas = null;
		
		try {
			canvas = view.lockCanvas(null);
			synchronized (view) {
				if(canvas != null){
					crafex.draw(canvas);
				}
			}
		} finally {
			if (canvas != null) {
				view.unlockCanvasAndPost(canvas);
			}
		}
	}
	
}
