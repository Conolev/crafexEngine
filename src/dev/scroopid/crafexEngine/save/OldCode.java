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
import dev.scroopid.crafexEngine.save.util.SaveUtils;

public class OldCode {
	private static final Logger LOGGER = new Logger(OldCode.class);

	private static final Class<? extends Annotation> IGNORE = Ignore.class;

	

	private void saveArrayField(ISavable object, Field field, Class<?> fieldType) {

		// Lets get how many demensions the array is!
		// And get its component Type!
		// Create List for data
		int dimensions = 1;
		Class<?> baseType = fieldType.getComponentType();
		List<String> arrayData = new ArrayList<String>();

		// Desend Type Hiarchy, used insted of lastIndexOf because we need to know
		// The declaring type.
		while (baseType.isArray()) {
			// Add one to deminsions and get the array componentType
			dimensions++;
			baseType = fieldType.getComponentType();
		}

		LOGGER.trace(String.format("Dealing with a %d demension array of type %s", dimensions, baseType.getName()));

		// Create the array header
		String arrayHeader =
					String.format(Locale.US, SaveConstants.ARRAY_TYPE_FORMAT, field.getName(), SaveConstants.ARRAY,
								baseType.getName(), dimensions);
		LOGGER.trace("Array header: " + arrayHeader);
		arrayData.add(arrayHeader);

		// Lets get the base array object
		Object array = null;

		try {

			// Lets try to get it...
			array = field.get(object);

		} catch (IllegalArgumentException e) {

			// Unable to get the Array...
			LOGGER.error("Unable to retrieve array from field: " + field.getName(), e);
			throw new SaveException("Unable to retrieve array from field: " + field.getName());

		} catch (IllegalAccessException e) {

			LOGGER.error("Unable to retrieve array from field: " + field.getName(), e);
			throw new SaveException("Unable to retrieve array from field: " + field.getName());

		}

		// Lets go through the wormhole! (Dimensions)
		int curDimension = 1;
		arrayData.addAll(walkArray(array, baseType, curDimension));

	}

	private List<String> walkArray(Object object, Class<?> baseType, int curDimension) {
		List<String> arrayData = new ArrayList<String>();

		// Add opening {
		arrayData.add(SaveUtils.addMultipleString(SaveConstants.TAB, curDimension - 1) + SaveConstants.DATA_START);

		if (object == null || Array.getLength(object) < 1) {

			// TODO: Seperate conditions based on Zero Length/Null Array?
			// Array is null or Zero Length
			LOGGER.debug(String.format("Null array in dimension %d", curDimension));

			// Write null token
			arrayData.add(SaveUtils.addMultipleString(SaveConstants.TAB, curDimension) + SaveConstants.NULL);

		} else {

			// Lets walk this beotch
			int length = Array.getLength(object);
			if (Array.get(object, 0).getClass().isArray()) {

				// Array within array...
				LOGGER.trace("Going walking an Array of an Array");
				for (int i = 0; i < length; ++i) {
					// Walk the array at index i
					walkArray(Array.get(object, i), baseType, curDimension + 1);
				}

			} else {

				// Lets save the data
				LOGGER.trace("Saving data from an Array");
				if (baseType.isPrimitive()) {

				} else if (ISavable.class.isAssignableFrom(baseType) || ISaveHandler.class.isAssignableFrom(baseType)) {

					// For every element in the array, save it.
					for (int i = 0; i < length; ++i) {
						// Save each object in the array and then add a , if needed
						List<String> data = null;

						// Add each line to the array data
						for (String line : data) {
							arrayData.add(SaveUtils.addMultipleString(SaveConstants.TAB, curDimension) + line);
						}

						String needSeperator = i == length - 1 ? "" : SaveConstants.ARRAY_DATA_SEPERATOR;
						arrayData.add(SaveUtils.addMultipleString(SaveConstants.TAB, curDimension) + needSeperator);
					}

				} else if (Collection.class.isAssignableFrom(baseType)) {

					// IF you really did this....

				} else if (Map.class.isAssignableFrom(baseType)) {

					// IF you really did this...

				}

			}

		}

		// Add Closing }
		arrayData.add(SaveUtils.addMultipleString(SaveConstants.TAB, curDimension - 1) + SaveConstants.DATA_END);

		// Return the data
		return arrayData;
	}

	

	

}
