package dev.scroopid.crafexEngine.entity;

import dev.scroopid.crafexEngine.Crafex;
import dev.scroopid.crafexEngine.graphics.GraphicsManager;
import dev.scroopid.crafexEngine.save.ISavable;
import dev.scroopid.crafexEngine.save.Ignore;
import dev.scroopid.crafexEngine.util.intRectangle;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Item implements ISavable{
	
	private String name;
	private String imageKey;
	private String type;
	@Ignore
	private Bitmap image;
	
	
	public Item(String imageKey, String name, String type){
		this.imageKey = imageKey;
		this.name = name;
		this.type = type;
		image = Crafex.graphicsMan.getImageFromKey(imageKey);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Bitmap getImage() {
		return image;
	}

	public void setImage(Bitmap image) {
		this.image = image;
	}

	public void draw(Canvas canvas, intRectangle location){
		if(image != null && (location.getRight()) - Crafex.levelMan.getLevel().getScroll().getX() >= 0
					&& location.getLeft() - Crafex.levelMan.getLevel().getScroll().getX() <= Crafex.WINDOW_DIMENTIONS.getX()
					&& (location.getBottom()) - Crafex.levelMan.getLevel().getScroll().getY() >= 0
					&& location.getTop() - Crafex.levelMan.getLevel().getScroll().getY() <= Crafex.WINDOW_DIMENTIONS.getY()){
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
