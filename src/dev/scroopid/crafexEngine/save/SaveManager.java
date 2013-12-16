package dev.scroopid.crafexEngine.save;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import dev.scroopid.crafexEngine.Logger;


public class SaveManager {
	private static final String DATA_END = "}";
	private static final String DATA_START = "{";
	private static final String SAVE_TYPE_FORMAT = "(%s : %s : %s)";
	private static final String I_SAVE_HANDLER = "ISaveHandler";
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
			// TODO: Iterate through the objects
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
				
				// Get its Type introspection info and create object header
				Class<?> klass = object.getClass();
				String objectHeader = String.format(SAVE_TYPE_FORMAT, klass.getName(), I_SAVE_HANDLER, SaveUtils.uuidObjectHash(klass));
				objectData.add(SaveUtils.addMultipleString(TAB, callsDeep - 1) + objectHeader);
				objectData.add(SaveUtils.addMultipleString(TAB, callsDeep - 1) + DATA_START);
				
				// Get the object's SaveData
				List<String> saveData = ((ISaveHandler)object).save();
				
				// Tabify the saveData and add it to the file
				for (String line : saveData){
					
				}
				
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
	 * @param object The object to save.
	 * @param callsDeep The amount of calls deep.
	 * @param objectData The list of strings to save the data to.
	 */
	private void saveISavable(ISavable object, int callsDeep, List<String> objectData) {
		LOGGER.trace("object is type ISavable");
		// Call preSave
		object.preSave();
		
		// Create type introspection and reflection variable
		Class<?> klass = object.getClass();
		
		// Get class name and uuid hash.
		String objectHeader = String.format(SAVE_TYPE_FORMAT, klass.getName(), I_SAVABLE, SaveUtils.uuidObjectHash(klass));
		LOGGER.trace("Created objectHeader: " + objectHeader);
		
		// Add it to the file header along with {
		objectData.add(SaveUtils.addMultipleString(TAB, callsDeep - 1) + objectHeader);
		objectData.add(SaveUtils.addMultipleString(TAB, callsDeep - 1) + DATA_START);
		
		
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
		objectData.add(SaveUtils.addMultipleString(TAB, callsDeep - 1) + DATA_END);

		// We are done saving, call postSave
		LOGGER.trace("Calling object postSave");
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
			
			String data = savePrimitiveField(object, field, fieldType);
			objectData.add(SaveUtils.addMultipleString(TAB, callsDeep) + data);
			
		} else if (fieldType.isAssignableFrom(ISavable.class) || fieldType.isAssignableFrom(ISaveHandler.class)){
			
			saveSavableField(object, callsDeep, objectData, field);
			
		} else if (fieldType.isAssignableFrom(String.class)){
			
			String data = saveStringField(object, field, fieldType);
			objectData.add(SaveUtils.addMultipleString(TAB, callsDeep) + data);
			
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
	 * Saves a primitive type field and returns it as a String
	 * Saves it in this format <br/>
	 * <b>&lt;fieldName : type : "data"&gt;</b>
	 * @param object The object being saved
	 * @param field The field to save
	 * @param fieldType The type of the field
	 * @return The Formatted string of the primitive field
	 */
	private String savePrimitiveField(ISavable object, Field field,Class<?> fieldType) {
		String data = "";
		try{
			
			// How I wish I could use a switch on this...
			// Sees what primitive type it is and tries to save it.
			if (fieldType.equals(int.class)){
				
				LOGGER.trace("Trying to save int variable named " + field.getName());
				
				// Save data in the format
				data = SaveUtils.formatPrimitive(field.getName(), fieldType.getName(), field.get(object) + "");
				LOGGER.trace("Saved int Field: " + data);
				
			} else if (fieldType.equals(short.class)){
				
				LOGGER.trace("Trying to save short variable named " + field.getName());
				
				// Save data in the format
				data = SaveUtils.formatPrimitive(field.getName(), fieldType.getName(), field.get(object) + "");
				LOGGER.trace("Saved short Field: " + data);
				
			} else if (fieldType.equals(long.class)){
				
				LOGGER.trace("Trying to save long variable named " + field.getName());
				
				// Save data in the format
				data = SaveUtils.formatPrimitive(field.getName(), fieldType.getName(), field.get(object) + "");
				LOGGER.trace("Saved long Field: " + data);

				
			} else if (fieldType.equals(float.class)){
				
				LOGGER.trace("Trying to save float variable named " + field.getName());
				
				// Save data in the format
				data = SaveUtils.formatPrimitive(field.getName(), fieldType.getName(), field.get(object) + "");
				LOGGER.trace("Saved float Field: " + data);

				
			} else if (fieldType.equals(double.class)){
				
				LOGGER.trace("Trying to save double variable named " + field.getName());
				
				// Save data in the format
				data = SaveUtils.formatPrimitive(field.getName(), fieldType.getName(), field.get(object) + "");
				LOGGER.trace("Saved double Field: " + data);

				
			} else if (fieldType.equals(char.class)){
				
				LOGGER.trace("Trying to save char variable named " + field.getName());
				
				// Save data in the format
				data = SaveUtils.formatPrimitive(field.getName(), fieldType.getName(), field.get(object) + "");
				LOGGER.trace("Saved char Field: " + data);

				
			} else if (fieldType.equals(byte.class)){
				
				LOGGER.trace("Trying to save byte variable named " + field.getName());
				
				// Save data in the format
				data = SaveUtils.formatPrimitive(field.getName(), fieldType.getName(), field.get(object) + "");
				LOGGER.trace("Saved byte Field: " + data);

				
			} else if (fieldType.equals(boolean.class)){
				
				LOGGER.trace("Trying to save boolean variable named " + field.getName());
				
				// Save data in the format
				data = SaveUtils.formatPrimitive(field.getName(), fieldType.getName(), field.get(object) + "");
				LOGGER.trace("Saved boolean Field: " + data);
				
			} else {
				
				LOGGER.debug("Must have forgotten a primitive type...");
				
			}
		} catch (IllegalArgumentException e){
			
			// Unable to get primitive Data
			LOGGER.error("Was not able to retrieve primitive Type object!", e);
			throw new SaveException(String.format("Unable to save: %s", field.getName()));
			
		} catch(IllegalAccessException e){
			
			// Unable to get primitive Data
			LOGGER.error("Was not able to retrieve primitive Type object!", e);
			throw new SaveException(String.format("Unable to save: %s", field.getName()));
			
		}
		
		return data;
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
	private String saveStringField(ISavable object, Field field, Class<?> fieldType) {
		String data = "";
		
		// Try to save the data into the string
		try {
			
			// Save data in the format
			data = SaveUtils.formatPrimitive(field.getName(), fieldType.getName(), (String)field.get(object));
			LOGGER.trace("Saved String Field: " + data);
						
		} catch (IllegalArgumentException e) {
			
			// Unable to get String Data
			LOGGER.error("Was not able to retrieve String Type object!", e);
			throw new SaveException(String.format("Unable to save: %s", field.getName()));
			
		} catch (IllegalAccessException e) {
			
			// Unable to get String Data
			LOGGER.error("Was not able to retrieve String Type object!", e);
			throw new SaveException(String.format("Unable to save: %s", field.getName()));
			
		}
		
		// Return the data
		return data;
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
