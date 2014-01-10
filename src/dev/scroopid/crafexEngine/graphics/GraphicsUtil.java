package dev.scroopid.crafexEngine.graphics;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Locale;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import dev.scroopid.crafexEngine.Crafex;
import dev.scroopid.crafexEngine.util.intPoint;

public class GraphicsUtil {
	// TODO link entitys to entity class
	/** direction for the entitys to move */
	public static Hashtable<String, Integer> directions;
	
	public static final int STRECH_NONE = 0;
	public static final int STRECH_NORMAL = 1;
	public static final int STRECH_CUT = 2;
	public static final int STRECH_CLEAN = 3;

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
			int carx = 0;
			int cary = 0;

			for (char tester = 'a'; tester != newtext[i]; tester++) {
				if (carx < grid.getX() - 1) {
					carx++;
				} else {
					carx = 0;
					cary++;
				}
			}
			int srcx = (letters.getWidth() / grid.getX()) * carx;
			int srcy = (letters.getHeight() / grid.getY()) * cary;
			int dstx = (letters.getWidth() / grid.getX()) * i;
			textcanvas.drawBitmap(letters, new Rect(srcx, srcy, srcx + (letters.getWidth() / grid.getX()), srcy
						+ (letters.getHeight() / grid.getY())),
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
		} else if(scaleType == STRECH_CLEAN) {
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
		canvas.drawBitmap(image, new Rect(0, 0, image.getWidth() / 2, image.getHeight() / 2), new Rect(0, 0,
					(int) (image.getWidth() / 2), (int) (image.getHeight() / 2)), null);

		canvas.drawBitmap(image, new Rect(image.getWidth() / 2, 0, image.getWidth(), image.getHeight() / 2), new Rect(
					xsize - (image.getWidth() / 2), 0, xsize, (int) (image.getHeight() / 2)), null);

		canvas.drawBitmap(image, new Rect(0, image.getHeight() / 2, image.getWidth() / 2, image.getHeight()), new Rect(
					0, ysize - (image.getHeight() / 2), (int) (image.getWidth() / 2), ysize), null);

		canvas.drawBitmap(image,
					new Rect(image.getWidth() / 2, image.getHeight() / 2, image.getWidth(), image.getHeight()),
					new Rect(xsize - (image.getWidth() / 2), ysize - (image.getHeight() / 2), xsize, ysize), null);
		return data;
	}
	
	public static Bitmap cleanStrechImage(Bitmap image, int xsize, int ysize){
		Bitmap data = Bitmap.createBitmap((int) (xsize), (int) (ysize), image.getConfig());
		Canvas canvas = new Canvas(data);
		canvas.drawBitmap(image, new Rect(0, 0, image.getWidth() / 2, image.getHeight() / 2), new Rect(0, 0,
					(int) (image.getWidth() / 2), (int) (image.getHeight() / 2)), null);

		canvas.drawBitmap(image, new Rect(image.getWidth() / 2, 0, image.getWidth(), image.getHeight() / 2), new Rect(
					xsize - (image.getWidth() / 2), 0, xsize, (int) (image.getHeight() / 2)), null);

		canvas.drawBitmap(image, new Rect(0, image.getHeight() / 2, image.getWidth() / 2, image.getHeight()), new Rect(
					0, ysize - (image.getHeight() / 2), (int) (image.getWidth() / 2), ysize), null);

		canvas.drawBitmap(image,
					new Rect(image.getWidth() / 2, image.getHeight() / 2, image.getWidth(), image.getHeight()),
					new Rect(xsize - (image.getWidth() / 2), ysize - (image.getHeight() / 2), xsize, ysize), null);
		return data;
	}

	/**
	 * contains graphics shit. !!! use constructor before using this class please !!!
	 */
	public GraphicsUtil() {
		directions = new Hashtable<String, Integer>();
		directions.put("down", new Integer(0));
		directions.put("right", new Integer(1));
		directions.put("left", new Integer(2));
		directions.put("up", new Integer(3));
	}
}
