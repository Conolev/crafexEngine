package dev.scroopid.crafexEngine.entity;

import dev.scroopid.crafexEngine.Crafex;
import dev.scroopid.crafexEngine.graphics.GraphicsManager;
import dev.scroopid.crafexEngine.save.ISavable;
import dev.scroopid.crafexEngine.save.Ignore;
import dev.scroopid.crafexEngine.util.intRectangle;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Item implements ISavable {

	/**name of the item*/
	private String name;

	/**key of the image for the item*/
	private String imageKey;

	/**type of item it is (ex. WEAPON, FIELDITEM, ARMOR)*/
	private String type;

	@Ignore
	private Bitmap image;

	/**
	 * an container for an item in the game
	 */
	public Item(){
		this.imageKey = "MISSING";
		this.name = "";
		this.type = "NONE";
	}
	
	/**
	 * an container for an item in the game
	 * @param imageKey
	 * @param name
	 * @param type
	 */
	public Item(String imageKey, String name, String type) {
		this.imageKey = imageKey;
		this.name = name;
		this.type = type;
		image = Crafex.graphicsMan.getImageFromKey(imageKey);
	}

	/**
	 * returns the name of the {@link Item}
	 * @return name of the item
	 */
	public String getName() {
		return name;
	}

	/**
	 * sets the name of the item
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * returns the {@link Bitmap} for teh {@link Item}
	 * @return the bitmap for the item
	 */
	public Bitmap getImage() {
		return image;
	}

	/**
	 * sets the {@link Bitmap} of the {@link Item}
	 * @param image
	 */
	public void setImage(Bitmap image) {
		this.image = image;
	}
	
	/**
	 * uses the {@link Item} on the {@link Entity}
	 * @param entity
	 */
	public void use(Entity entity){
		
	}

	/**
	 * draws the {@link Item} on a location
	 * @param canvas
	 * @param location
	 */
	public void draw(Canvas canvas, intRectangle location) {
		if (image != null
					&& (location.getRight()) - Crafex.levelMan.getLevel().getScroll().getX() >= 0
					&& location.getLeft() - Crafex.levelMan.getLevel().getScroll().getX() <= Crafex.WINDOW_DIMENTIONS.getX()
					&& (location.getBottom()) - Crafex.levelMan.getLevel().getScroll().getY() >= 0
					&& location.getTop() - Crafex.levelMan.getLevel().getScroll().getY() <= Crafex.WINDOW_DIMENTIONS.getY()) {
			Rect src = new Rect(0, 0, image.getWidth(), image.getHeight());
			canvas.drawBitmap(image, src, location.getRect(), null);
		}
	}

	@Override
	public void postLoad() {
		image = Crafex.graphicsMan.getImageFromKey(imageKey);
	}

	@Override
	public void postSave() {

	}

	@Override
	public void preLoad() {

	}

	@Override
	public void preSave() {

	}
}
