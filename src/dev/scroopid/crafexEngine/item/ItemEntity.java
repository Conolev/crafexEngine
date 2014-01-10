package dev.scroopid.crafexEngine.item;

import dev.scroopid.crafexEngine.entity.Entity;

public class ItemEntity extends Entity {

	/** item the be held in this entity */
	private Item item;

	/**
	 * an {@link Entity} the contains a {@link Item}
	 */
	public ItemEntity() {
		super();
	}

	/**
	 * an {@link Entity} the contains a {@link Item}
	 * 
	 * @param imageKey
	 * @param id
	 * @param item
	 */
	public ItemEntity(String imageKey, int id, Item item) {
		super(imageKey, id);
		this.item = item;
	}

	/**
	 * an {@link Entity} the contains a {@link Item}
	 * 
	 * @param imageKey
	 * @param frames
	 * @param cycles
	 * @param x
	 * @param y
	 * @param id
	 * @param item
	 */
	public ItemEntity(String imageKey, int frames, int cycles, float x, float y, int id, Item item) {
		super(imageKey, frames, cycles, x, y, id);
		this.item = item;
	}

	/**
	 * return the {@link Item} contained in this {@link ItemEntity}
	 * 
	 * @return item contained in this itemEntity
	 */
	public Item getItem() {
		return item;
	}

	/**
	 * sets the {@link Item} contained in this {@link ItemEntity}
	 * 
	 * @param item
	 */
	public void setItem(Item item) {
		this.item = item;
	}

	/**
	 * uses the {@link Item} on the provided {@link Entity}
	 * 
	 * @param entity
	 */
	public void use(Entity entity) {
		item.use(entity);
	}

}
