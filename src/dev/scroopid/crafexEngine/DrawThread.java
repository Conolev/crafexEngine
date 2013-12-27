package dev.scroopid.crafexEngine;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;

public class DrawThread extends GameThread {

	/** {@link SurfaceHolder} to draw to */
	private SurfaceHolder view;

	/** {@link Crafex} of thread */
	private Crafex crafex;

	public Paint paint = new Paint();

	/**
	 * a {@link GameThread} to handle the drawing of a {@link Crafex}.
	 * 
	 * @param view
	 *        to draw
	 */
	public DrawThread(Crafex view) {
		super();
		crafex = view;
		this.view = view.getHolder();
	}

	/**
	 * a {@link GameThread} to handle the drawing of a {@link Crafex}.
	 * 
	 * @param view
	 *        to draw
	 * @param fps
	 *        lock
	 */
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
				if (canvas != null) {
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
