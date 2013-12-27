package dev.scroopid.crafexEngine.save;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import dev.scroopid.crafexEngine.Logger;
import dev.scroopid.crafexEngine.save.util.SaveConstants;
import dev.scroopid.crafexEngine.save.util.SaveException;
import dev.scroopid.crafexEngine.save.util.SavableFieldMethod;
import dev.scroopid.crafexEngine.save.util.SaveUtils;
/**
 * This class handles the saving of classes within the crafexEngine
 * TODO: Optimize the handling of primitive types, boilerplate code between Arrays and single fields
 * TODO: Check runtime of code
 * TODO: Test
 * @author jameswomack
 *
 */
public class SaveManager {
	private static final Logger LOGGER = new Logger(SaveManager.class);
	
	private static final SaveManager instance = new SaveManager();

	private static final Class<? extends Annotation> IGNORE = Ignore.class;
	
	private static List<SavableFieldMethod> saveMethods;

	static{
		saveMethods = new ArrayList<SavableFieldMethod>();
	}
	
	public static SaveManager getInstance(){
		return instance;
	}
	
	/**
	 * Saves an ISavable object, saving all field data
	 * Method is recursive
	 * @param object The object to save
	 * @return The object's data in List form
	 */
	public List<String> saveISavable(ISavable object){
		return saveISavable(object, 1);
	}
	
	/**
	 * Saves an ISavable object, saving all field data
	 * Method is recursive
	 * @param object The object to save all fields from
	 * @param callsDeep How many calls deep we are
	 * @return The data in List form
	 */
	private List<String> saveISavable(ISavable object, int callsDeep){
		List<String> data = new ArrayList<String>();
		
		LOGGER.trace("Saving ISavable Object");
		
		// Can we save this?
		if (callsDeep < 1){
			LOGGER.error("Calls deep is less than 1: " + callsDeep);
			throw new IllegalArgumentException("Cannot save a 0 or less deep called ISavable");
		} else if (object == null && callsDeep == 1){
			LOGGER.error("Object is null when we are on the first call!");
			throw new IllegalArgumentException("Object is null on first call!");
		}
		
		// If the object is null, then add null tag and return that!
		if (object == null){
			LOGGER.trace("Dealing with null object");
			data.add(SaveUtils.addMultipleString(SaveConstants.TAB, callsDeep - 1) + SaveConstants.NULL);
		} else {
			
			// Call preSave
			object.preSave();
			
			// Lets save all of its fields, that are not ignored.
			for (Field field : object.getClass().getDeclaredFields()){
				// Set field to accessible
				field.setAccessible(true);
				
				// if the ignore annotation is present, igore it
				if (field.isAnnotationPresent(IGNORE)){
					// Logger trace statement
					LOGGER.trace("Ignoring field: " + field.getName());
				} else {
					LOGGER.trace("Saving field");
					
					// The field data from the SFM
					List<String> fieldData = new ArrayList<String>();
					
					// Save its ass and add it to the data
					for (SavableFieldMethod sfm : saveMethods){
						if (SaveUtils.canCastTo(field.getType(), sfm.getClass())){
							// Save that shit
							fieldData.addAll(sfm.save(object, field, callsDeep));
						}
					}
					
					// If the fieldData is still empty, we did not find a compatable SFM
					if (fieldData.isEmpty()){
						LOGGER.debug("No compatable SFM for: " + field.getName());
					}
					
					data.addAll(fieldData);
				}
			}
			
			LOGGER.trace("Calling post save, calls deep: " + callsDeep);
			
			// Call postSave
			object.postSave();
			
		}
		
		return data;
	}
	
	/**
	 * Simply calls save method of saveHandler object
	 * @param object The object to call the save method
	 * @return The data returned from the save method.
	 */
	public List<String> saveSaveHandler(ISaveHandler object){
		if (object == null){
			LOGGER.error("Object is null in ISaveHandler save method");
			throw new IllegalArgumentException("Null Object, when calling saveHandler");
		}
		
		return object.save();
	}
	
	/**
	 * Adds the SFM to the savableFieldMethods if it doesn't already exist,
	 * have the same saving type as another, and as long as its not null.
	 * @param sfm The sfm to add.
	 */
	public static void addSavableFieldMethod(SavableFieldMethod sfm){
		// sfm cannot be null, be existant...
		if (sfm == null){
			LOGGER.error("SFM is null!");
			throw new IllegalArgumentException("sfm is null!");
		} else if (saveMethods.contains(sfm)){
			LOGGER.error("SFM already exists!");
			throw new IllegalArgumentException("sfm already exists: " + sfm.getMethodName());
		}
		
		if (saveMethods.size() > 1){
			// And already have a saving type in the saveMethods
			for (int i = 0; i < saveMethods.size() - 1; ++i){
				for (int j = 1; j < saveMethods.size(); ++j){
					if (saveMethods.get(i).getType().equals(saveMethods.get(j).getType())){
						LOGGER.error("SFM with saving type already exists!");
						throw new IllegalArgumentException("SFM with saving type already exists");
					}
				}
			}
		}
		
		// Add it to the saveMethods if sanity checks are passed.
		saveMethods.add(sfm);
	}
}
