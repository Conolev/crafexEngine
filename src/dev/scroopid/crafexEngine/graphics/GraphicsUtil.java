package dev.scroopid.crafexEngine.graphics;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Locale;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import dev.scroopid.crafexEngine.Crafex;
import dev.scroopid.crafexEngine.entity.Entity;
import dev.scroopid.crafexEngine.util.intPoint;

public class GraphicsUtil {
	/** direction for the {@link Entity} to move */
	public static Hashtable<String, Integer> directions;
	
	public static final int STRETCH_NONE = 0;
	public static final int STRETCH_NORMAL = 1;
	public static final int STRETCH_CUT = 2;
	public static final int STRETCH_CROSS = 3;

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
	 * @param what type of stretch to do to the buttonimage
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

		if (scaleType == STRETCH_CUT) {
			Buttonimage = cutStretchImage(Buttonimage, textmap.getWidth(), textmap.getHeight());
			data = Bitmap.createBitmap(Buttonimage.getWidth(), Buttonimage.getHeight(), Buttonimage.getConfig());
			temp = new Canvas(data);
			temp.drawBitmap(Buttonimage, 0, 0, null);
		} else if(scaleType == STRETCH_NORMAL) {
			Buttonimage = stretchImage(Buttonimage, textmap.getWidth(), textmap.getHeight());
			data = Bitmap.createBitmap(Buttonimage.getWidth(), Buttonimage.getHeight(), Buttonimage.getConfig());
			temp = new Canvas(data);
			temp.drawBitmap(Buttonimage, 0, 0, null);
		} else if(scaleType == STRETCH_CROSS) {
			Buttonimage = cleanStretchImage(Buttonimage, textmap.getWidth(), textmap.getHeight());
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
	public static Bitmap stretchImage(Bitmap image, float xscale, float yscale) {
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
	public static Bitmap stretchImage(Bitmap image, int xsize, int ysize) {
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
	public static Bitmap cutStretchImage(Bitmap image, int xsize, int ysize) {
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
	
	/**
	 * stretches the middle section of the image on the X axis to avoid the borders looking stretched
	 * @param image
	 * @param xsize
	 * @return stretched image
	 */
	public static Bitmap crossStretchImageX(Bitmap image, int xsize){
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
		
		cutout = stretchImage(cutout, xsize - ((temp.getWidth() / 3) * 2), cutout.getHeight());
		
		tempc.drawBitmap(cutout, new Rect(0, 0, cutout.getWidth(), cutout.getHeight()), 
					new Rect(image.getWidth() / 3, 0, temp.getWidth() - (image.getWidth() / 3),
								image.getHeight()), null);
		
		return temp;
	}
	
	/**
	 * stretches the middle section of the image on the Y axis to avoid the borders looking stretched
	 * @param image
	 * @param ysize
	 * @return stretched image
	 */
	public static Bitmap cleanStretchImageY(Bitmap image, int ysize){
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
		
		cutout = stretchImage(cutout, cutout.getWidth(), ysize - ((temp.getHeight() / 3) * 2));
		
		tempc.drawBitmap(cutout, new Rect(0, 0, cutout.getWidth(), cutout.getHeight()), 
					new Rect(0, image.getHeight() / 3, image.getWidth(),
								temp.getHeight() - (image.getHeight() / 3)), null);
		
		return temp;
	}
	
	/**
	 * stretches the middle sections of the image in a cross to avoid the borders looking stretched
	 * @param image
	 * @param xsize
	 * @param ysize
	 * @return
	 */
	public static Bitmap cleanStretchImage(Bitmap image, int xsize, int ysize){
		image = crossStretchImageX(image, xsize);
		image = cleanStretchImageY(image, ysize);
		return image;
	}

	/**
	 * returns blank area of the image to the downward direction of the image
	 * @param img
	 * @return blank area of the image to the downward direction of the image
	 */
    public static int getTrimmedBottom(Bitmap img) {
        int width = img.getWidth();
        int height = img.getHeight();
        int data = 0;

        for(int i = 0; i < width; ++i) {
            for(int j = height - 1; j >= 0; --j) {
                if(img.getPixel(i, j) != Color.TRANSPARENT && j > data) {
                    data = j;
                    break;
                }
            }
        }
        
        return data;
    }
    
    /**
	 * returns blank area of the image to the downward direction of the image
	 * @param img
	 * @param border
	 * @return blank area of the image to the downward direction of the image
	 */
    public static int getTrimmedBottom(Bitmap img, int border) {
        int width = img.getWidth();
        int height = img.getHeight();
        int data = 0;

        for(int i = 0; i < width; ++i) {
            for(int j = height - 1; j >= 0; --j) {
                if(img.getPixel(i, j) != Color.TRANSPARENT && j > data) {
                    data = j;
                    break;
                }
            }
        }
        
        return data + border;
    }
    
    /**
	 * returns blank area of the image to the right direction of the image
	 * @param img
	 * @return blank area of the image to the right direction of the image
	 */
    public static int getTrimmedRight(Bitmap img) {
        int width = img.getWidth();
        int height = img.getHeight();
        int data = 0;

        for(int i = 0; i < height; ++i) {
            for(int j = width - 1; j >= 0; --j) {
                if(img.getPixel(j, i) != Color.TRANSPARENT && j > data) {
                    data = j;
                    break;
                }
            }
        }
        
        return data;
    }
    
    /**
	 * returns blank area of the image to the right direction of the image
	 * @param img
	 * @param border
	 * @return blank area of the image to the right direction of the image
	 */
    public static int getTrimmedRight(Bitmap img, int border) {
        int width = img.getWidth();
        int height = img.getHeight();
        int data = 0;

        for(int i = 0; i < height; ++i) {
            for(int j = width - 1; j >= 0; --j) {
                if(img.getPixel(j, i) != Color.TRANSPARENT && j > data) {
                    data = j;
                    break;
                }
            }
        }
        
        return data + border;
    }
    
    /**
	 * returns blank area of the image to the up direction of the image
	 * @param img
	 * @return blank area of the image to the up direction of the image
	 */
    public static int getTrimmedTop(Bitmap img) {
        int width = img.getWidth();
        int height = img.getHeight();
        int data = height;

        for(int i = 0; i < width; ++i) {
            for(int j = 0; j < height; ++j) {
                if(img.getPixel(i, j) != Color.TRANSPARENT && j < data) {
                    data = j;
                    break;
                }
            }
        }
        
        return data;
    }
    
    /**
	 * returns blank area of the image to the up direction of the image
	 * @param img
	 * @param border
	 * @return blank area of the image to the up direction of the image
	 */
    public static int getTrimmedTop(Bitmap img, int border) {
        int width = img.getWidth();
        int height = img.getHeight();
        int data = height;

        for(int i = 0; i < width; ++i) {
            for(int j = 0; j < height; ++j) {
                if(img.getPixel(i, j) != Color.TRANSPARENT && j < data) {
                    data = j;
                    break;
                }
            }
        }
        
        return data + border;
    }
    
    /**
	 * returns blank area of the image to the left direction of the image
	 * @param img
	 * @return blank area of the image to the left direction of the image
	 */
    public static int getTrimmedLeft(Bitmap img) {
        int width = img.getWidth();
        int height = img.getHeight();
        int data = width;

        for(int i = 0; i < height; ++i) {
            for(int j = 0; j < width; ++j) {
                if(img.getPixel(j, i) != Color.TRANSPARENT && j < data) {
                    data = j;
                    break;
                }
            }
        }
        
        return data;
    }
    
    /**
	 * returns blank area of the image to the left direction of the image
	 * @param img
	 * @param border
	 * @return blank area of the image to the left direction of the image
	 */
    public static int getTrimmedLeft(Bitmap img, int border) {
        int width = img.getWidth();
        int height = img.getHeight();
        int data = width;

        for(int i = 0; i < height; ++i) {
            for(int j = 0; j < width; ++j) {
                if(img.getPixel(j, i) != Color.TRANSPARENT && j < data) {
                    data = j;
                    break;
                }
            }
        }
        
        return data + border;
    }
    
    public static Bitmap trimWidth(Bitmap bitmap){
    	
    	//TODO
    	return null;
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
