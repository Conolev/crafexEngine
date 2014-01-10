package dev.scroopid.crafexEngine.graphics;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Locale;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import dev.scroopid.crafexEngine.Crafex;
import dev.scroopid.crafexEngine.entity.Entity;
import dev.scroopid.crafexEngine.util.intPoint;

public class GraphicsUtil {
	/** direction for the {@link Entity} to move */
	public static Hashtable<String, Integer> directions;
	
	public static final int STRECH_NONE = 0;
	public static final int STRECH_NORMAL = 1;
	public static final int STRECH_CUT = 2;
	public static final int STRECH_CROSS = 3;

	/**
	 * combines to {@link Bitmap}s
	 * 
	 * @param firstlayer
	 * @param secondlayer
	 * @return combined bitmap
	 */
	public static Bitmap add(Bitmap firstlayer, Bitmap secondlayer) {
		Bitmap data = Bitmap.createBitmap(secondlayer.getWidth(), secondlayer.getHeight(), secondlayer.getConfig());
		Canvas temp = new Canvas(data);

		temp.drawBitmap(secondlayer, 0, 0, null);
		temp.drawBitmap(firstlayer, 0, 0, null);

		return data;
	}

	/**
	 * combines to {@link Bitmap}s
	 * 
	 * @param Images
	 *        to be combine
	 * @return combined bitmap
	 */
	public static Bitmap add(Bitmap[] images) {
		Bitmap data = Bitmap.createBitmap(images[0].getWidth(), images[0].getHeight(), images[0].getConfig());
		Canvas temp = new Canvas(data);

		for (int i = 0; i < images.length; i++) {
			temp.drawBitmap(images[i], 0, 0, null);
		}

		return data;
	}

	/**
	 * loads {@link Bitmap} from {@link AssetManager}
	 * 
	 * @param file
	 *        in assets to load
	 * @return bitmap
	 */
	public static Bitmap loadAssestImage(String file) {
		Bitmap temp = null;
		Bitmap data = null;
		try {
			temp = Crafex.fileMan.getAssestImage(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (temp != null) {
			data = scaleImage(temp, Crafex.SCALE);
			return data;
		}
		return null;
	}
	
	/**
	 * creates a button {@link Bitmap} with provided {@link String} from provide char chart and {@link Bitmap} template.
	 * 
	 * @param text
	 *        to be on button
	 * @param Buttonimage
	 *        or button template
	 * @param letters
	 *        or char chart
	 * @param grid
	 *        of char chart (ex. 6x6)
	 * @param scale
	 *        or to streach the button template to text size
	 * @return button image with text
	 */
	public static Bitmap makeTextButtonImage(String text, Bitmap Buttonimage, 
				Bitmap letters, intPoint grid, int scaleType) {
		text.toLowerCase(Locale.ENGLISH);
		char[] newtext = text.toCharArray();
		Bitmap data = null;
		Bitmap textmap =
					Bitmap.createBitmap((letters.getWidth() / grid.getX()) * newtext.length,
								(letters.getHeight() / grid.getY()), letters.getConfig());
		Canvas temp = null;
		Canvas textcanvas = new Canvas(textmap);
		for (int i = 0; i < newtext.length; i++) {
			int dstx = (letters.getWidth() / grid.getX()) * i;
			
			textcanvas.drawBitmap(letters, getCharFromMapRect(newtext[i], letters, grid), 
						new Rect(dstx, 0, dstx + (letters.getWidth() / grid.getX()),
									(letters.getHeight() / grid.getY())), null);
		}

		if (scaleType == STRECH_CUT) {
			Buttonimage = cutStrechImage(Buttonimage, textmap.getWidth(), textmap.getHeight());
			data = Bitmap.createBitmap(Buttonimage.getWidth(), Buttonimage.getHeight(), Buttonimage.getConfig());
			temp = new Canvas(data);
			temp.drawBitmap(Buttonimage, 0, 0, null);
		} else if(scaleType == STRECH_NORMAL) {
			Buttonimage = strechImage(Buttonimage, textmap.getWidth(), textmap.getHeight());
			data = Bitmap.createBitmap(Buttonimage.getWidth(), Buttonimage.getHeight(), Buttonimage.getConfig());
			temp = new Canvas(data);
			temp.drawBitmap(Buttonimage, 0, 0, null);
		} else if(scaleType == STRECH_CROSS) {
			Buttonimage = cleanStrechImage(Buttonimage, textmap.getWidth(), textmap.getHeight());
			data = Bitmap.createBitmap(Buttonimage.getWidth(), Buttonimage.getHeight(), Buttonimage.getConfig());
			temp = new Canvas(data);
			temp.drawBitmap(Buttonimage, 0, 0, null);
		} else {
			data = Bitmap.createBitmap(Buttonimage.getWidth(), Buttonimage.getHeight(), Buttonimage.getConfig());
			temp = new Canvas(data);
			temp.drawBitmap(Buttonimage, 0, 0, null);
		}
		temp.drawBitmap(textmap, (data.getWidth() - textmap.getWidth()) / 2,
					(data.getHeight() - textmap.getHeight()) / 2, null);

		return data;
	}
	
	/**
	 * returns the char from the map
	 * @param charToGet
	 * @param map of chars
	 * @param grid of map
	 * @return bitmap of char
	 */
	public static Rect getCharFromMapRect(char charToGet, Bitmap map, intPoint grid){
		int charX = 0;
		int charY = 0;
		int resX = map.getWidth() / grid.getX();
		int resY = map.getHeight() / grid.getY();
		
		int diff = charToGet - 32;
		
		charX = diff % grid.getX();
		charY = diff / grid.getX();

		int srcx = resX * charX;
		int srcy = resY * charY;
		
		return new Rect(srcx, srcy, srcx + resX, srcy + resY);
	}
	
	/**
	 * returns the char from the map
	 * @param charToGet
	 * @param map of chars
	 * @param grid of map
	 * @return bitmap of char
	 */
	public static Bitmap getCharFromMap(char charToGet, Bitmap map, intPoint grid){
		int resX = map.getWidth() / grid.getX();
		int resY = map.getHeight() / grid.getY();
		Bitmap data = Bitmap.createBitmap(resX, resY, map.getConfig());
		Canvas temp = new Canvas(data);
		temp.drawBitmap(map, getCharFromMapRect(charToGet, map, grid), 
					new Rect(0, 0, data.getWidth(), data.getHeight()), null);
		
		return data;
	}
	
	/**
	 * scales {@link Bitmap} to a decimal
	 * 
	 * @param image
	 *        to scale
	 * @param scale
	 *        amount
	 * @return scaled image
	 */
	public static Bitmap scaleImage(Bitmap image, float scale) {
		Bitmap data =
					Bitmap.createBitmap((int) (image.getWidth() * scale), (int) (image.getHeight() * scale),
								image.getConfig());
		Canvas canvas = new Canvas(data);
		canvas.drawBitmap(image, new Rect(0, 0, image.getWidth(), image.getHeight()),
					new Rect(0, 0, (int) (image.getWidth() * scale), (int) (image.getHeight() * scale)), null);
		return data;
	}

	/**
	 * Stretches {@link Bitmap} to a scale in each direction.
	 * 
	 * @param image
	 * @param xscale
	 * @param yscale
	 * @return
	 */
	public static Bitmap strechImage(Bitmap image, float xscale, float yscale) {
		Bitmap data =
					Bitmap.createBitmap((int) (image.getWidth() * xscale), (int) (image.getHeight() * yscale),
								image.getConfig());
		Canvas canvas = new Canvas(data);
		canvas.drawBitmap(image, new Rect(0, 0, image.getWidth(), image.getHeight()),
					new Rect(0, 0, (int) (image.getWidth() * xscale), (int) (image.getHeight() * yscale)), null);
		return data;
	}
	
	/**
	 * Stretches {@link Bitmap} to a scale in each direction.
	 * 
	 * @param image
	 * @param xsize
	 * @param ysize
	 * @return
	 */
	public static Bitmap strechImage(Bitmap image, int xsize, int ysize) {
		Bitmap data = Bitmap.createBitmap(xsize, ysize, image.getConfig());
		Canvas canvas = new Canvas(data);
		canvas.drawBitmap(image, new Rect(0, 0, image.getWidth(), image.getHeight()),
					new Rect(0, 0, xsize, ysize), null);
		return data;
	}

	/**
	 * cuts the {@link Bitmap} into four corners and stretches the space between them.
	 * 
	 * @param image
	 * @param xsize
	 * @param ysize
	 * @return
	 */
	public static Bitmap cutStrechImage(Bitmap image, int xsize, int ysize) {
		Bitmap data = Bitmap.createBitmap((int) (xsize), (int) (ysize), image.getConfig());
		Canvas canvas = new Canvas(data);
		canvas.drawBitmap(image, new Rect(0, 0, image.getWidth() / 2, image.getHeight() / 2),
					new Rect(0, 0, (int) (image.getWidth() / 2), (int) (image.getHeight() / 2)), 
					null);

		canvas.drawBitmap(image, new Rect(image.getWidth() / 2, 0, image.getWidth(), 
					image.getHeight() / 2), new Rect(
					xsize - (image.getWidth() / 2), 0, xsize, (int) (image.getHeight() / 2)), null);

		canvas.drawBitmap(image, new Rect(0, image.getHeight() / 2, image.getWidth() / 2, 
					image.getHeight()), new Rect(
					0, ysize - (image.getHeight() / 2), (int) (image.getWidth() / 2), ysize), null);

		canvas.drawBitmap(image,
					new Rect(image.getWidth() / 2, image.getHeight() / 2, image.getWidth(),
								image.getHeight()),
					new Rect(xsize - (image.getWidth() / 2), ysize - (image.getHeight() / 2),
								xsize, ysize), null);
		return data;
	}
	
	public static Bitmap cleanStrechImageX(Bitmap image, int xsize){
		Bitmap cutout = Bitmap.createBitmap((image.getWidth()/3), image.getHeight(),
					image.getConfig());
		Canvas cutoutc = new Canvas(cutout);
		Bitmap temp = Bitmap.createBitmap(xsize, image.getHeight(), image.getConfig());
		Canvas tempc = new Canvas(temp);
		
		cutoutc.drawBitmap(image, new Rect(image.getWidth() / 3, 0, (image.getWidth() / 3) * 2, 
					image.getHeight()), new Rect(0, 0, cutout.getWidth(),
								cutout.getHeight()), null);
		tempc.drawBitmap(image, new Rect(0, 0, image.getWidth() / 3, image.getHeight()), 
					new Rect(0, 0, image.getWidth() / 3, temp.getHeight()), null);
		tempc.drawBitmap(image, new Rect((image.getWidth() / 3) * 2, 0, image.getWidth(), 
					image.getHeight()), new Rect(temp.getWidth() - (image.getWidth() / 3), 
								0, temp.getWidth(), temp.getHeight()), null);
		
		cutout = strechImage(cutout, xsize - ((temp.getWidth() / 3) * 2), cutout.getHeight());
		
		tempc.drawBitmap(cutout, new Rect(0, 0, cutout.getWidth(), cutout.getHeight()), 
					new Rect(image.getWidth() / 3, 0, temp.getWidth() - (image.getWidth() / 3),
								image.getHeight()), null);
		
		return temp;
	}
	
	public static Bitmap cleanStrechImageY(Bitmap image, int ysize){
		Bitmap cutout = Bitmap.createBitmap(image.getWidth(), image.getHeight() / 3,
					image.getConfig());
		Canvas cutoutc = new Canvas(cutout);
		Bitmap temp = Bitmap.createBitmap(image.getWidth(), ysize, image.getConfig());
		Canvas tempc = new Canvas(temp);
		
		cutoutc.drawBitmap(image, new Rect(0, image.getHeight() / 3, image.getWidth(), 
					(image.getHeight() / 3) * 2), new Rect(0, 0, cutout.getWidth(),
								cutout.getHeight()), null);
		tempc.drawBitmap(image, new Rect(0, 0, image.getWidth(), image.getHeight() / 3), 
					new Rect(0, 0, temp.getWidth(), image.getHeight() / 3), null);
		tempc.drawBitmap(image, new Rect(0, (image.getHeight() / 3) * 2, image.getWidth(), 
					image.getHeight()), new Rect(0, temp.getHeight() - (image.getHeight() / 3), 
								temp.getWidth(), temp.getHeight()), null);
		
		cutout = strechImage(cutout, cutout.getWidth(), ysize - ((temp.getHeight() / 3) * 2));
		
		tempc.drawBitmap(cutout, new Rect(0, 0, cutout.getWidth(), cutout.getHeight()), 
					new Rect(0, image.getHeight() / 3, image.getWidth(),
								temp.getHeight() - (image.getHeight() / 3)), null);
		
		return temp;
	}
	
	public static Bitmap cleanStrechImage(Bitmap image, int xsize, int ysize){
		image = cleanStrechImageX(image, xsize);
		image = cleanStrechImageY(image, ysize);
		return image;
	}

	/**
	 * contains graphics shit. !!! use constructor before using this class please !!!
	 */
	public GraphicsUtil() {
		directions = new Hashtable<String, Integer>();
		directions.put("down", Integer.valueOf(0));
		directions.put("right", Integer.valueOf(1));
		directions.put("left", Integer.valueOf(2));
		directions.put("up", Integer.valueOf(3));
	}
}
