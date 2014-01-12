package dev.scroopid.crafexEngine.save.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import dev.scroopid.crafexEngine.Logger;

/**
 * Save utilities for save subsystem. Only accessible within this package.
 * 
 * @author jameswomack
 * 
 */
public class SaveUtils {
	private static final Logger log = new Logger(SaveUtils.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a", Locale.US);

	private static MessageDigest digest;

	// This is needed for the creation of the Hash Digest...
	static {
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			log.error("Could not create hash Digest!", e);
			digest = null;
			System.exit(-1);
		}
	}

	/**
	 * Pre: field is not null, target is not null Gets the object form of the data, catches reflective exception and
	 * throws them as SaveExceptions
	 * 
	 * @param target
	 *        The object to pull data from
	 * @param field
	 *        The field to get data form.
	 * @return The data in object form
	 */
	public static Object getData(Object target, Field field) {
		Object data = null;

		// Lets try to get the data
		try {
			data = field.get(target);
		} catch (IllegalArgumentException e) {
			log.error("Could not get the data, Invalid arguements", e);
			throw new SaveException("Could not get data from field: Invalid Arguements");
		} catch (IllegalAccessException e) {
			log.error("Could not get the data, Access denied", e);
			throw new SaveException("Could not get data from field: Access Denied");
		}

		return data;
	}

	/**
	 * Can the object be cast to another?
	 * 
	 * @param castee
	 *        The object being cast
	 * @param castTo
	 *        The object type desired
	 * @return If it can happen.
	 */
	public static boolean canCastTo(Class<?> castee, Class<?> castTo) {
		if (castee == null || castTo == null) {
			log.error("NULL Arguements!");
			throw new IllegalArgumentException("Null Arguements");
		}
		return castTo.isAssignableFrom(castee);
	}

	/**
	 * Returns the string multiplied by the amount so if str is equal to aba and amount is equal to 2, result is abaaba
	 * 
	 * @param str
	 *        The string to multiply
	 * @param amount
	 *        The times to multiply it
	 * @return The string multipled
	 */
	public static String addMultipleString(String str, int amount) {
		String val = "";
		for (int i = 0; i < amount; ++i) {
			val += str;
		}

		return val;
	}

	/**
	 * Adds tabs to the given string and returns it. I got bored of doing addMultipleString(SaveConstants.TAB,
	 * callsDeep) + data
	 * 
	 * @param str
	 *        The string to add tabs to
	 * @param tabs
	 *        The amount of tabs
	 * @return The tabified string
	 */
	public static String tabify(String str, int tabs) {
		return addMultipleString(SaveConstants.TAB, tabs) + str;
	}

	/**
	 * Creates a comment for the save files
	 * 
	 * @param comment
	 *        The comment to put into the save
	 * @return The comment without newlines and in format.
	 */
	public static String createComment(String comment) {
		// Split newlines if there are any
		String[] splitByNewline = comment.split("\\r?\\n");
		String val = "#";

		// Print out debug message if there was newlines
		if (splitByNewline.length > 1) {
			log.debug("Took out newlines in comment!");
		}

		// For each part of the string, add it to the val
		for (String phrase : splitByNewline) {
			val += " " + phrase;
		}

		// return it
		return val;
	}

	/**
	 * Creates a comment based on current date
	 * 
	 * @return The date comment
	 */
	public static String createDateComment() {
		Date date = new Date();

		return createComment("Created on " + dateFormat.format(date));
	}
	
	/**
	 * Gets the Generic type of a Field
	 * @param field The Field to get the type from
	 * @return The Generic type in class form.
	 */
	public static Class<?> getGenericType(Field field){
		ParameterizedType type;
		if (field.getGenericType() instanceof ParameterizedType){
			type = (ParameterizedType)field.getGenericType();
		} else {
			throw new SaveException("The is not a generic collection");
		}
		return (Class<?>)type.getActualTypeArguments()[0];
	}
	
	/**
	 * Grabs the generic type by trying to access the generic type of an element
	 * @param collection to pull froms
	 * @return type: the generic type of the collection
	 */
	public static Class<?> getGenericType(Collection<?> collection){
		if (collection == null){
			log.error("NULL Object passed int getGenericType");
			throw new SaveException("This is an illegal argument: null object");
		}else if (collection.isEmpty()){
			log.error("Cannot get an object from the collection");
			throw new SaveException("This is an Illegal Arguement: Empty Array");
		}
		
		Class<?> type = null;
		
		/*
		 * checks if not null and sets type to oject
		 */
		for (Object object : collection){
			if (object != null){
				type = object.getClass();
				break;
			}
		}
		
		// Final check
		if (type == null){
			log.error("Cannot get Type from array, no valid elements");
			throw new SaveException("This is an Illegal Arguement: No Valid Elements");
		}
		
		return type;
	}
	
	public static Class<?>[] getGenericType (Map<?,?> map){
		if (map == null) {
			log.error("Null Map passed");
			throw new SaveException("Illegal argument: null map");
		}
		if (map.keySet().isEmpty() || map.values().isEmpty()) {
			log.error("Map is empty (key or value)");
			throw new SaveException("The map keys or values are empty");
		}
		
		Class<?> keyType = null;
		Class<?> valueType = null;
		
		for (Object obj : map.keySet()){
			if (obj != null) {
				keyType = obj.getClass();
				break;
			}
		}
		
		for (Object obj : map.values()) {
			if (obj != null) {
				valueType = obj.getClass();
				break;
			}
		}
		
		if (valueType == null || keyType == null) {
			log.error("Cannot get key or value types");
			throw new SaveException("Illegal argument: no valid elements");
		}
		
		return new Class<?>[] {keyType, valueType};
	}
	
	/**
	 * Formats a primitive type field in this format <br/>
	 * <b>&lt;fieldName : type : data&gt;</b>
	 * 
	 * @param fieldName
	 *        The field name
	 * @param type
	 *        The type of the field
	 * @param data
	 *        The data to put into the field
	 * @return The formatted field data
	 */
	public static String formatPrimitive(String fieldName, String type, String data) {
		return String.format(SaveConstants.PRIMITIVE_TYPE_FORMAT, fieldName, type, data);
	}
	
	/**
	 * Formats a collection type in this format <br/>
	 * <b>[name : type : genericType]</b>
	 * @param name The name of the field/parent collection
	 * @param type The type of collection
	 * @param subType The Generic Type of collection
	 * @return The Formatted header
	 */
	public static String formatCollection(String name, String type, String subType){
		return String.format(SaveConstants.ARRAY_TYPE_FORMAT, name, type, subType);
	}
	
	/**
	 * Formats a map type in this format <br/>
	 * <b>[name : type : keyType : valueType]</b>
	 * @param name The name of field/parent collection/map
	 * @param type The type of map
	 * @param keyType The type of key
	 * @param valueType The type of value
	 * @return The formatted header
	 */
	public static String formatMap(String name, String type, String keyType, String valueType){
		return String.format(SaveConstants.MAP_TYPE_FORMAT, name, type, keyType, valueType);
	}

	/**
	 * Creates the uuid hash for the object
	 * 
	 * @param klass
	 *        the Class reflection variable to pull fields/methods from
	 * @return The Hash
	 */
	public static String uuidObjectHash(Class<?> klass) {
		log.trace("Creating hash for class");
		String hash = klass.getName() + " ";

		// For each field and method, add to hash string.
		for (Field field : klass.getDeclaredFields()) {
			hash += field.toGenericString() + " ";
		}

		for (Method method : klass.getDeclaredMethods()) {
			hash += method.toGenericString() + " ";
		}

		log.trace("Combined info: " + hash);

		byte[] bytes = null;
		try {
			// Try to digest the message and return it
			digest.update(hash.getBytes("UTF-8"));
			bytes = digest.digest();

			BigInteger bigInt = new BigInteger(1, bytes);

			log.trace(String.format("Hash of %s is %s", klass.getName(), bigInt.toString(16)));

			return bigInt.toString(16);
		} catch (UnsupportedEncodingException e) {
			// Shouldn't happen....
			log.error("James must be bad at spelling UTF-8...", e);
		}

		return null;
	}

	/**
	 * creates a SavableFieldMethod from the method name, the infering class and the sfmType
	 * 
	 * @param methodName
	 *        The method name of the saveMethod
	 * @param inferFrom
	 *        The class to pull the static method from
	 * @param sfmType
	 *        The type of SFM
	 * @return The SFM
	 */
	public static SavableFieldMethod getSFM(String methodName, Class<?> inferFrom, Class<?> sfmType) {
		Method method = null;

		try {
			// Try and get the method from the infering class.
			method = inferFrom.getMethod(methodName, Object.class, Field.class, int.class);
		} catch (NoSuchMethodException e) {
			log.error(String.format("No such method named %s with object, field, int parameters", methodName));
			throw new SaveException("No such method named: " + methodName);
		}

		return new SavableFieldMethod(method, sfmType);
	}
}
