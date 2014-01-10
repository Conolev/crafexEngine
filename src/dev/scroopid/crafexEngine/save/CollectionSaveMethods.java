package dev.scroopid.crafexEngine.save;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import dev.scroopid.crafexEngine.Logger;
import dev.scroopid.crafexEngine.save.util.SaveConstants;
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
		
		// Create Data Header
		data.add(SaveUtils.formatCollection(field.getName(), field.getType().getName(), SaveUtils.getGenericType(field).getName()));
		data.add(SaveConstants.DATA_START);
		
		// put null token if the object is NULL
		if (rawObject == null){
			data.add(SaveUtils.tabify(SaveConstants.NULL, callsDeep));
			data.add(SaveConstants.DATA_END);
			return (String[])data.toArray();
		}
		
		return (String[])walkCollection((Collection<?>)rawObject, field,callsDeep).toArray();
	}
	
	private static List<String> walkCollection(Collection<?> collection, Field field, int callsDeep){
		List<String> data = new ArrayList<String>();
		
		LOGGER.info(String.format("Walking %s field when we are %d calls deep", field.getName(), callsDeep));
		
		for (Object obj : collection){
			if (obj == null){
				// Data is null, Dont Care
				data.add(SaveConstants.NULL);
				LOGGER.debug("Saved NULL Variable");
			} else{
				if (SaveUtils.canCastTo(obj.getClass(), Collection.class)){
					// This is a Collection Within a Collection, lets walk this!
					try{
						// Try and get the innerGenericType and create the collection header
						Class<?> innerGenericType = SaveUtils.getGenericType((Collection<?>)obj);
						
						// Walk the inner Collection!
						data.add(SaveUtils.tabify(SaveUtils.formatCollection(field.getName(), obj.getClass().getName(), innerGenericType.getName()), callsDeep));
						data.add(SaveUtils.tabify(SaveConstants.DATA_START, callsDeep));
						data.addAll(walkCollection((Collection<?>)obj, field, callsDeep + 1));
						data.add(SaveConstants.DATA_END);
						
					} catch(SaveException e){
						LOGGER.debug("Unable to get the generic type of the inner Collection, saving it as null.");
						data.add(SaveConstants.NULL);
					}
				} else if (SaveUtils.canCastTo(obj.getClass(), Map.class)){
					// This is a Map within a Collection, lets Walk this!
					
				} else {
					
				}
			}
		}
		
		return data;
	}
	
	private static List<String> walkMap(Map<?, ?> map, int callsDeep){
		List<String> data = new ArrayList<String>();
		
		
		return data;
	}

}
