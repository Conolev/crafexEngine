package dev.scroopid.crafexEngine.save;

/**
 * An Interface that allows a class to be saved to a file. Use annotation {@code dev.scroopid.crafexEngine.save.Ignore}
 * to have a field not save.<br/>
 * <b>Inheriting class must implement default constructor</b>
 * 
 * @author jameswomack
 * 
 */
public interface ISavable {

	/**
	 * Called after loading in the fields
	 */
	public void postLoad();

	/**
	 * Called after saving the data
	 */
	public void postSave();

	/**
	 * Called before loading in the fields
	 */
	public void preLoad();

	/**
	 * Called before saving the data
	 */
	public void preSave();
}
