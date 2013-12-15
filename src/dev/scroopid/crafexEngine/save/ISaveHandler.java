package dev.scroopid.crafexEngine.save;

/**
 * An interface that allows classes that implement it
 * to save data in its own format. 
 * <br/><b>Any class that implements it must implement a default constructor</b>
 * @author jameswomack
 *
 */
public interface ISaveHandler {
	public void load(String data);
	public String save();
}
