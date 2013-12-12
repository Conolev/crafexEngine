package dev.scroopid.crafexEngine.graphics;

import java.util.ArrayList;

import android.graphics.Bitmap;

public class Graphics {
	
	private static ArrayList<Bitmap> images = new ArrayList<Bitmap>();
	private static ArrayList<String> keys = new ArrayList<String>();
	
	public Graphics(){
		
	}
	
	public static Bitmap getImageFromKey(String key){
		for(int i = 0; i < keys.size(); ++i){
			if(key == keys.get(i)){
				return images.get(i);
			}
		}
		
		return null;
	}
}
