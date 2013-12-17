package dev.scroopid.crafexEngine.util;

import java.io.IOException;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class FileManager {

	private String fileLocation;

	private AssetManager assets;

	public FileManager(AssetManager assets, String files) {
		this.fileLocation = files;
	}

	public Bitmap getAssestImage(String file) throws IOException {
		return BitmapFactory.decodeStream(this.assets.open(file));
	}

}
