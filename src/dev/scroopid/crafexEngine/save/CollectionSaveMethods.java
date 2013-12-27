package dev.scroopid.crafexEngine.save;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

import dev.scroopid.crafexEngine.Logger;
import dev.scroopid.crafexEngine.save.util.SaveUtils;

public class CollectionSaveMethods {
	private static final Logger LOGGER = new Logger(CollectionSaveMethods.class);
	
	static{
		SaveManager.addSFM(SaveUtils.getSFM("saveCollection", CollectionSaveMethods.class, Collection.class));
		SaveManager.addSFM(SaveUtils.getSFM("saveMap", CollectionSaveMethods.class, Map.class));
	}
	
	/**
	 * Saves a collection type
	 * @param target The object to get information from
	 * @param field The field to get data from
	 * @param callsDeep how many calls deep we are
	 * @return The data in string[] form
	 */
	public static String[] saveCollection(Object target, Field field, int callsDeep){
		String[] data = null;
		
		return data;
	}
	
	/**
	 * Saves a map type
	 * @param target The object to get information from
	 * @param field The field to get data from
	 * @param callsDeep how many calls deep we are
	 * @return The data in string[] form
	 */
	public static String[] saveMap(Object target, Field field, int callsDeep){
		String[] data = null;
		
		return data;
	}
}
