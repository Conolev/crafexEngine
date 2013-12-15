package dev.scroopid.crafexEngine.save;

import java.util.List;

/**
 * An interface that allows classes that implement it
 * to save data in its own format. 
 * <br/><b>Any class that implements it must implement a default constructor</b>
 * @author jameswomack
 *
 */
public interface ISaveHandler {
	public void load(List<String> data);
	public List<String> save();
}
