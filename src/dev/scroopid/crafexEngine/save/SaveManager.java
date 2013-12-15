package dev.scroopid.crafexEngine.save;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import dev.scroopid.crafexEngine.Logger;


public class SaveManager {
	private static final String I_SAVABLE = "ISavable";
	private static final Logger LOGGER = new Logger(SaveManager.class);
	private static final String TAB = "    ";
	private static final Class<? extends Annotation> IGNORE = Ignore.class;
	
	/**
	 * Saves a list of ISavable, ISaveHandler objects
	 * @param objects The objects to save
	 * @return The file in the format of a list of Strings
	 */
	public List<String> saveObjects(List<Object> objects){
		// Create the file that we will save to.
		List<String> file = new ArrayList<String>();
		LOGGER.trace(String.format("Saving %d objects", objects.size()));
		
		// Create file header
		LOGGER.trace("Creating header for file");
		file.add(SaveUtils.createDateComment());
		
		// Iterate through objects
		LOGGER.trace("Iterating through objects");
		for(Object object : objects){
			
		}
		
		return file;
	}
	
	/**
	 * Saves an object of type ISavable or ISaveHandler
	 * @param object The object to save
	 * @param callsDeep How many calls deep this method is
	 * @return The Data to append to the save file
	 */
	private List<String> saveObject(Object object, int callsDeep){
		// Create String list for Saving purposes
		List<String> objectData = new ArrayList<String>();
		LOGGER.trace(String.format("We are %d calls deep!", callsDeep));
		
		// If the object is not null, then save it's ass
		if(object != null){
			if (object instanceof ISavable){
				
				// ISavable, go through and save each of it's fields
				saveISavable((ISavable)object, callsDeep, objectData);
				
			} else if (object instanceof ISaveHandler){
				
				// ISaveHandler, call it's save method
				LOGGER.trace("object is type ISaveHandler");
				objectData.addAll(((ISaveHandler)object).save());
				
			} else {
				
				// Not of type ISavable or ISaveHandler
				LOGGER.debug("object is not of type ISavable or ISaveHandler");
				
			}
		} else {
			LOGGER.debug("Cannot save a null object!");
		}
		
		return objectData;
	}

	/**
	 * Saves an ISavable type variable, calls are recursive to saveObject
	 * if a field of type ISavable or ISaveHandler is run upon.
	 * 
	 * @param object The object to save
	 * @param callsDeep The amount of calls deep
	 * @param objectData The list of strings to save the data to.
	 */
	private void saveISavable(ISavable object, int callsDeep, List<String> objectData) {
		LOGGER.trace("object is type ISavable");
		// Call preSave
		object.preSave();
		
		// Create type introspection and reflection variable
		Class<?> klass = object.getClass();
		
		// Get class name and uuid hash.
		String objectHeader = String.format("%s : %s : %s", klass.getName(), I_SAVABLE, SaveUtils.uuidObjectHash(klass));
		LOGGER.trace("Created objectHeader: " + objectHeader);
		
		// Add it to the file header along with {
		objectData.add(SaveUtils.addMultipleString(TAB, callsDeep - 1) + objectHeader);
		objectData.add(SaveUtils.addMultipleString(TAB, callsDeep - 1) + "{");
		
		
		// Lets save the fields!
		for (Field field : klass.getDeclaredFields()){
			// If the Ignore annotation is not present then save field
			if (!field.isAnnotationPresent(IGNORE)){
				saveField(object, callsDeep, objectData, field);
				
			} else {
				// Ignore the field
				LOGGER.trace("Ignoring field: " + field.getName());
			}
		}
		
		// Add Closing }
		objectData.add(SaveUtils.addMultipleString(TAB, callsDeep - 1) + "}");

		// We are done saving, call postSave
		object.postSave();
	}

	/**
	 * Saves a field from the object.
	 * @param object The object being saved
	 * @param callsDeep The amount of calls deep from saveObject
	 * @param objectData The list of Strings to save data to
	 * @param field The field in question
	 */
	private void saveField(ISavable object, int callsDeep, List<String> objectData, Field field) {
		LOGGER.trace("Saving field: " + field.getName());
		Class<?> fieldType = field.getType();
		
		if (fieldType.isPrimitive()){
			
			// TODO: primitive, Save it
			
			
		} else if (fieldType.isAssignableFrom(ISavable.class) || fieldType.isAssignableFrom(ISaveHandler.class)){
			
			saveSavableField(object, callsDeep, objectData, field);
			
		} else if (fieldType.isAssignableFrom(String.class)){
			
			saveStringField(object, objectData, field, fieldType);
			
		} else if(fieldType.isArray()){
			
			// TODO: Array, save it!
			
		} else if(fieldType.isAssignableFrom(Collection.class)){
			
			// TODO: Collection, save it!
			
		} else if(fieldType.isAssignableFrom(Map.class)){
			
			// TODO: Map, Save it!
			
		} else {
			LOGGER.debug(String.format("Unable to save field of type %s named %s", fieldType.getName(), field.getName()));
		}
	}

	/**
	 * Saves a String field from the ISavable Object,
	 * 
	 * Saves in this format<br/>
	 * <b>&lt;fieldName : java.lang.String : "data"&gt;</b>
	 * @param object The object being saved
	 * @param objectData The List to add the save data to
	 * @param field The field in question
	 * @param fieldType The type of the field
	 */
	private void saveStringField(ISavable object, List<String> objectData, Field field, Class<?> fieldType) {
		try {
			// Save data in the format
			String data = String.format("<%s : %s : \"%s\">", field.getName(), fieldType.getName(), field.get(object));
			LOGGER.trace("Saved String Field: " + data);
			
			objectData.add(data);
			
		} catch (IllegalArgumentException e) {
			// Unable to get String Data
			LOGGER.error("Was not able to retrieve String Type object!", e);
			throw new SaveException(String.format("Unable to save: %s", field.getName()));
			
		} catch (IllegalAccessException e) {
			// Unable to get String Data
			LOGGER.error("Was not able to retrieve String Type object!", e);
			throw new SaveException(String.format("Unable to save: %s", field.getName()));
			
		}
	}

	/**
	 * Saves a Savable type field by recursively calling the saveObject method
	 * 
	 * @param object The object being saved
	 * @param callsDeep The amount of calls deep the object is being saved
	 * @param objectData The String list to add the Save data to
	 * @param field The field in question
	 */
	private void saveSavableField(ISavable object, int callsDeep, List<String> objectData, Field field) {
		// Field is a Savable type, save it too!!
		try {
			LOGGER.trace("Trying to save Savable Type: " + field.getName());
			
			// Recursively call saveObject to save every field.
			List<String> fieldData = saveObject(field.get(object), callsDeep + 1);
			LOGGER.trace(String.format("Saved %s field, was %d calls deep", field.getName(), callsDeep));
			
			objectData.addAll(fieldData);
			
		} catch (IllegalArgumentException e) {
			LOGGER.error("Was not able to retrieve Savable Type object!", e);
			throw new SaveException(String.format("Unable to save: %s", field.getName()));
			
		} catch (IllegalAccessException e) {
			LOGGER.error("Was not able to retrieve Savable Type object!", e);
			throw new SaveException(String.format("Unable to save: %s", field.getName()));
			
		}
	}
	
	
}
