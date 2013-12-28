package dev.scroopid.crafexEngine.save;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import dev.scroopid.crafexEngine.Logger;
import dev.scroopid.crafexEngine.save.util.SaveException;
import dev.scroopid.crafexEngine.save.util.SaveUtils;

public class CollectionSaveMethods {
	private static final String SAVE_MAP = "saveMap";

	private static final String SAVE_COLLECTION = "saveCollection";

	private static final Logger LOGGER = new Logger(CollectionSaveMethods.class);

	static {
		SaveManager.addSFM(SaveUtils.getSFM(SAVE_COLLECTION, CollectionSaveMethods.class, Collection.class));
		SaveManager.addSFM(SaveUtils.getSFM(SAVE_MAP, CollectionSaveMethods.class, Map.class));
	}

	/**
	 * Saves a map type
	 * 
	 * @param target
	 *        The object to get information from
	 * @param field
	 *        The field to get data from
	 * @param callsDeep
	 *        how many calls deep we are
	 * @return The data in string[] form
	 */
	public static String[] saveMap(Object target, Field field, int callsDeep) {
		String[] data = null;

		return data;
	}

	/**
	 * Saves a collection type
	 * 
	 * @param target
	 *        The object to get information from
	 * @param field
	 *        The field to get data from
	 * @param callsDeep
	 *        how many calls deep we are
	 * @return The data in string[] form
	 */
	public static String[] saveCollection(Object target, Field field, int callsDeep) {
		List<String> data = new ArrayList<String>();
		Object rawObject = SaveUtils.getData(target, field);

		// Lets get some basic type info
		Class<?> klass = rawObject.getClass();
		
		

		// Create collection header

		return (String[])data.toArray();
	}
	
	private static List<String> walkCollection(Collection<?> collection, int callsDeep){
		List<String> data = new ArrayList<String>();
		
		
		return data;
	}

}
