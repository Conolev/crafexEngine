package dev.scroopid.crafexEngine.graphics;

import java.util.ArrayList;

import android.graphics.Bitmap;

public class GraphicsManager {

	/** array containing bitmaps */
	private ArrayList<Bitmap> images = new ArrayList<Bitmap>();

	/** array containing strings in the index of the image of that key */
	private ArrayList<String> keys = new ArrayList<String>();

	public Bitmap getImageFromKey(String key) {
		for (int i = 0; i < keys.size(); ++i) {
			if (key == keys.get(i)) {
				return images.get(i);
			}
		}
		return null;
	}

	public void addImage(String key, Bitmap image) {
		keys.add(key);
		images.add(image);
	}

	public void removeImage(String key) {
		for (int i = 0; i < keys.size(); ++i) {
			if (key == keys.get(i)) {
				keys.remove(i);
				images.remove(i);
			}
		}
	}

	public GraphicsManager() {

	}
}
