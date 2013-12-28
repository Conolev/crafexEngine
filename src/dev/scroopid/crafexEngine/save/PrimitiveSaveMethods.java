package dev.scroopid.crafexEngine.save;

import java.lang.reflect.Field;

import dev.scroopid.crafexEngine.Logger;
import dev.scroopid.crafexEngine.save.util.SavableFieldMethod;
import dev.scroopid.crafexEngine.save.util.SaveConstants;
import dev.scroopid.crafexEngine.save.util.SaveException;
import dev.scroopid.crafexEngine.save.util.SaveUtils;

/**
 * Saves primitive types:
 * int, float, double, boolean, short, long, byte, char
 * @author jameswomack
 *
 */
public class PrimitiveSaveMethods {
	private static final String SAVE_STRING = "saveString";

	private static final String SAVE_PRIMITIVE = "savePrimitive";

	private static final Logger LOGGER = new Logger(PrimitiveSaveMethods.class);

	static {
		// Add all of the save handlers for each primitive Type
		SaveManager.addSFM(SaveUtils.getSFM(SAVE_PRIMITIVE, PrimitiveSaveMethods.class, int.class));
		SaveManager.addSFM(SaveUtils.getSFM(SAVE_PRIMITIVE, PrimitiveSaveMethods.class, short.class));
		SaveManager.addSFM(SaveUtils.getSFM(SAVE_PRIMITIVE, PrimitiveSaveMethods.class, long.class));
		SaveManager.addSFM(SaveUtils.getSFM(SAVE_PRIMITIVE, PrimitiveSaveMethods.class, byte.class));
		SaveManager.addSFM(SaveUtils.getSFM(SAVE_PRIMITIVE, PrimitiveSaveMethods.class, double.class));
		SaveManager.addSFM(SaveUtils.getSFM(SAVE_PRIMITIVE, PrimitiveSaveMethods.class, float.class));
		SaveManager.addSFM(SaveUtils.getSFM(SAVE_PRIMITIVE, PrimitiveSaveMethods.class,	boolean.class));
		SaveManager.addSFM(SaveUtils.getSFM(SAVE_PRIMITIVE, PrimitiveSaveMethods.class, char.class));
		SaveManager.addSFM(SaveUtils.getSFM(SAVE_STRING, PrimitiveSaveMethods.class, String.class));
	}

	/**
	 * Saves the string type field
	 * @param target The String field to save from
	 * @param field The field to save
	 * @param callsDeep How many calls deep we are
	 * @return The formatted primitive string
	 */
	public static String[] saveString(Object target, Field field, int callsDeep){
		String rawStr = (String)SaveUtils.getData(target, field);
		
		return new String[]{SaveUtils.tabify(SaveUtils.formatPrimitive(field.getName(), field.getClass().getName(), rawStr), callsDeep)};
	}
	
	/**
	 * Saves a primitive type field and returns it as a String Saves it in this format <br/>
	 * <b>&lt;fieldName : type : "data"&gt;</b>
	 * 
	 * @param target
	 *        The object being saved
	 * @param field
	 *        The field to save
	 * @param callsDeep
	 *        The amount of calls deep we are
	 * @return The Formatted string of the primitive field
	 */
	public static String[] savePrimitive(Object target, Field field, int callsDeep) {
		String data = "";
		Class<?> fieldType = field.getType();

		try {

			// How I wish I could use a switch on this...
			// Sees what primitive type it is and tries to save it.
			if (fieldType.equals(int.class)) {

				LOGGER.trace("Trying to save int variable named " + field.getName());

				// Save data in the format
				data = SaveUtils.formatPrimitive(field.getName(), fieldType.getName(), field.get(target) + "");
				LOGGER.trace("Saved int Field: " + data);

			} else if (fieldType.equals(short.class)) {

				LOGGER.trace("Trying to save short variable named " + field.getName());

				// Save data in the format
				data = SaveUtils.formatPrimitive(field.getName(), fieldType.getName(), field.get(target) + "");
				LOGGER.trace("Saved short Field: " + data);

			} else if (fieldType.equals(long.class)) {

				LOGGER.trace("Trying to save long variable named " + field.getName());

				// Save data in the format
				data = SaveUtils.formatPrimitive(field.getName(), fieldType.getName(), field.get(target) + "");
				LOGGER.trace("Saved long Field: " + data);

			} else if (fieldType.equals(float.class)) {

				LOGGER.trace("Trying to save float variable named " + field.getName());

				// Save data in the format
				data = SaveUtils.formatPrimitive(field.getName(), fieldType.getName(), field.get(target) + "");
				LOGGER.trace("Saved float Field: " + data);

			} else if (fieldType.equals(double.class)) {

				LOGGER.trace("Trying to save double variable named " + field.getName());

				// Save data in the format
				data = SaveUtils.formatPrimitive(field.getName(), fieldType.getName(), field.get(target) + "");
				LOGGER.trace("Saved double Field: " + data);

			} else if (fieldType.equals(char.class)) {

				LOGGER.trace("Trying to save char variable named " + field.getName());

				// Save data in the format
				data = SaveUtils.formatPrimitive(field.getName(), fieldType.getName(), field.get(target) + "");
				LOGGER.trace("Saved char Field: " + data);

			} else if (fieldType.equals(byte.class)) {

				LOGGER.trace("Trying to save byte variable named " + field.getName());

				// Save data in the format
				data = SaveUtils.formatPrimitive(field.getName(), fieldType.getName(), field.get(target) + "");
				LOGGER.trace("Saved byte Field: " + data);

			} else if (fieldType.equals(boolean.class)) {

				LOGGER.trace("Trying to save boolean variable named " + field.getName());

				// Save data in the format
				data = SaveUtils.formatPrimitive(field.getName(), fieldType.getName(), field.get(target) + "");
				LOGGER.trace("Saved boolean Field: " + data);

			} else {

				LOGGER.debug("Must have forgotten a primitive type...");

			}
		} catch (IllegalArgumentException e) {

			// Unable to get primitive Data
			LOGGER.error("Was not able to retrieve primitive Type object!", e);
			throw new SaveException(String.format("Unable to save: %s", field.getName()));

		} catch (IllegalAccessException e) {

			// Unable to get primitive Data
			LOGGER.error("Was not able to retrieve primitive Type object!", e);
			throw new SaveException(String.format("Unable to save: %s", field.getName()));

		}

		return new String[] { SaveUtils.tabify(data, callsDeep) };
	}
}
