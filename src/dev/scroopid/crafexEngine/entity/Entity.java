package dev.scroopid.crafexEngine.entity;

import dev.scroopid.crafexEngine.Crafex;
import dev.scroopid.crafexEngine.Drawable;
import dev.scroopid.crafexEngine.Updatable;
import dev.scroopid.crafexEngine.graphics.Sprite;
import dev.scroopid.crafexEngine.save.ISavable;
import dev.scroopid.crafexEngine.save.Ignore;
import dev.scroopid.crafexEngine.util.floatPoint;
import dev.scroopid.crafexEngine.util.floatRectangle;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Entity implements Updatable, Drawable, ISavable {

	/** id */
	protected int id;

	/** the last movement by the entity */
	protected floatPoint lastMovement = new floatPoint(0, 0);

	/** the current movement of the entity for this loop cycle */
	protected floatPoint movement;

	/** Desired rotation to apply */
	protected float rotation;

	/** the movement applied to movement every loop */
	protected floatPoint repeatedMovement;

	/** the coordinates, rotation and size of the {@link Entity} in the world */
	protected floatRectangle rectangle;

	/** the amount of pixels the entity can travel in one cycle */
	protected float speed;

	/** the speed this entity rotates at */
	private float rotationSpeed;

	/** the health of the entity */
	protected float health;

	/** last time entity was updated */
	@Ignore
	protected long LastTimeUpdated;

	/** the {@link Entity} {@link Sprite} */
	protected Sprite sprite;

	/**
	 * an entity for the world
	 */
	public Entity() {
		start();
		this.id = 0;
	}

	/**
	 * an entity for the world
	 * 
	 * @param image
	 * @param id
	 */
	public Entity(String imageKey, int id) {
		start();
		setSprite(new Sprite(imageKey));
		this.id = id;
	}

	/**
	 * an entity for the world
	 * 
	 * @param image
	 * @param x
	 * @param y
	 * @param id
	 */
	public Entity(String imageKey, float x, float y, int id) {
		start();
		setSprite(new Sprite(imageKey));
		rectangle = new floatRectangle(x, y, sprite.getWidth(), sprite.getHeight(), id);
		this.id = id;
	}

	/**
	 * an entity for the world
	 * 
	 * @param image
	 * @param frames
	 * @param cycles
	 * @param id
	 */
	public Entity(String imageKey, int frames, int cycles, int id) {
		start();
		setSprite(new Sprite(imageKey, frames, cycles, .2f));
		rectangle = new floatRectangle(0, 0, sprite.getWidth(), sprite.getHeight(), id);
		this.id = id;
	}

	/**
	 * an entity for the world
	 * 
	 * @param image
	 * @param frames
	 * @param cycles
	 * @param x
	 * @param y
	 * @param id
	 */
	public Entity(String imageKey, int frames, int cycles, float x, float y, int id) {
		start();
		setSprite(new Sprite(imageKey, frames, cycles, .2f));
		rectangle = new floatRectangle(x, y, sprite.getWidth(), sprite.getHeight(), id);
		this.id = id;
	}

	/**
	 * Initializes the entity
	 */
	public void start() {
		movement = new floatPoint();
		lastMovement = new floatPoint();
		repeatedMovement = new floatPoint();
	}

	/**
	 * resets the entity
	 */
	public void reset() {
		rectangle = new floatRectangle();
		movement = new floatPoint();
		repeatedMovement = new floatPoint();
	}

	/**
	 * returns the entity's id
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * get the pixel location in the center of the entity
	 * 
	 * @return center pixel
	 */
	public floatPoint getCenter() {
		floatPoint data =
					new floatPoint(this.getCoordinates().getX() + (int) (this.getSprite().getWidth() * .5),
								this.getCoordinates().getY() + (int) (this.getSprite().getHeight() * .5));
		return data;
	}

	/**
	 * gets the amount of pixels the entity can move per cycle
	 * 
	 * @return entity speed
	 */
	public float getSpeed() {
		return speed;
	}

	/**
	 * sets the amount of pixels the entity can move per cycle
	 * 
	 * @param speed
	 */
	public void setSpeed(float speed) {
		this.speed = speed;
	}

	/**
	 * gets the sprite of the entity
	 * 
	 * @return entity sprite
	 */
	public Sprite getSprite() {
		return sprite;
	}

	/**
	 * sets the entity's sprite
	 * 
	 * @param sprite
	 */
	private void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	/**
	 * sets the last movement
	 * 
	 * @param lastMovement
	 */
	protected void setLastMovement(floatPoint lastMovement) {
		this.lastMovement = lastMovement;
	}

	/**
	 * gets the movement for this cycle
	 * 
	 * @return movement
	 */
	public floatPoint getMovement() {
		return movement;
	}

	/**
	 * sets the amount of movement the entity will do this cycle
	 * 
	 * @param movement
	 */
	public void setMovement(floatPoint movement) {
		this.movement = movement;
	}

	/**
	 * returns the last movement made by this entity
	 * 
	 * @return last movement made by this entity
	 */
	public floatPoint getLastMovement() {
		return lastMovement;
	}

	/**
	 * gets the entity's health
	 * 
	 * @return health
	 */
	public float getHealth() {
		return health;
	}

	/**
	 * sets the amount of health the entity has
	 * 
	 * @param health
	 */
	public void setHealth(float health) {
		this.health = health;
	}

	/**
	 * adds the amount provided to the entitys health (use negative to subtract)
	 * 
	 * @param amount
	 *        to add
	 */
	public void addToHealth(float health) {
		this.health += health;
	}

	/**
	 * updates values for rotation use.
	 * 
	 * @param movement
	 */
	public void rotationUpdate(floatPoint movement) {
		this.rotation = (int) (Math.toDegrees(Math.atan2(movement.getY(), -movement.getX())));
	}

	/**
	 * gets the entity's coordinates
	 * 
	 * @return coordinates
	 */
	public floatPoint getCoordinates() {
		return rectangle.getCenter();
	}

	/**
	 * sets the entity's coordinates
	 * 
	 * @param coordinates
	 */
	public void setCoordinates(floatPoint coordinates) {
		rectangle.setCenter(coordinates);
	}

	/**
	 * returns the rectangle surrounding the entity
	 * 
	 * @return
	 */
	public floatRectangle getMyRectangle() {
		return rectangle;
	}

	public float getRotationSpeed() {
		return rotationSpeed;
	}

	public void setRotationSpeed(float rotationSpeed) {
		this.rotationSpeed = rotationSpeed;
	}

	/**
	 * moves the entity based on the movement variable
	 */
	public void move() {
		if (repeatedMovement != null) {
			movement.addX(repeatedMovement.getX() * getUpdateTimeDelta());
			movement.addY(repeatedMovement.getY() * getUpdateTimeDelta());
		}
		rectangle.getCenter().addX(movement.getX() * getUpdateTimeDelta());
		rectangle.getCenter().addY(movement.getY() * getUpdateTimeDelta());
		if (movement.getX() != 0 && movement.getY() != 0) {
			lastMovement = new floatPoint(movement.getX(), movement.getY());
		}

		movement.reset();
	}

	/**
	 * holds default updating methods for Entity.
	 */
	public void update() {
		sprite.update(movement);

		move();

		rectangle.set((int) rectangle.getX(), (int) rectangle.getY(), (int) rectangle.getX() + getSprite().getWidth(),
					(int) rectangle.getY() + getSprite().getHeight());
	}

	/**
	 * holds default drawing methods for Entity. and should not be overriddin. use drawFirst() or drawLast()
	 * 
	 * @param canvas
	 */
	public void draw(Canvas canvas) {
		if (sprite != null) {
			sprite.draw(canvas, rectangle.toIntRectangle(), true);
		}
	}

	@Override
	public long getLastUpdateTime() {
		return LastTimeUpdated;
	}

	@Override
	public float getUpdateTimeDelta() {
		return (System.currentTimeMillis() - LastTimeUpdated) / 1000;
	}

	@Override
	public void setLastUpdateTime(long time) {
		LastTimeUpdated = time;
	}

	@Override
	public void postLoad() {
		LastTimeUpdated = System.currentTimeMillis();
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
