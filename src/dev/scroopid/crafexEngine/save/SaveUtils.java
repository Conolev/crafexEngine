package dev.scroopid.crafexEngine.save;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import dev.scroopid.crafexEngine.Logger;

/**
 * Save utilities for save subsystem. 
 * Only accessible within this package.
 * 
 * @author jameswomack
 *
 */
class SaveUtils {
	static final Logger log = new Logger(SaveUtils.class);
	static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a", Locale.US);
	static MessageDigest digest;
	
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
	 * Creates a comment for the save files
	 * @param comment The comment to put into the save
	 * @return The comment without newlines and in format.
	 */
	static String createComment(String comment){
		// Split newlines if there are any
		String[] splitByNewline = comment.split("\\r?\\n");
		String val = "#";
		
		// Print out debug message if there was newlines
		if (splitByNewline.length > 1){
			log.debug("Took out newlines in comment!");
		}
		
		// For each part of the string, add it to the val
		for (String phrase : splitByNewline){
			val += " " + phrase;
		}
		
		// return it
		return val;
	}
	
	/**
	 * Creates a comment based on current date
	 * @return The date comment
	 */
	static String createDateComment(){
		Date date = new Date();
		
		return createComment("Created on " + dateFormat.format(date));
	}
	
	/**
	 * Creates the uuid hash for the object
	 * @param klass the Class reflection variable to pull fields/methods from
	 * @return The Hash
	 */
	static String uuidObjectHash(Class<?> klass) {
		log.trace("Creating hash for class");
		String hash = klass.getName() + " ";
		
		// For each field and method, add to hash string.
		for(Field field : klass.getDeclaredFields()){
			hash += field.toGenericString() + " ";
		}
		
		for (Method method : klass.getDeclaredMethods()){
			hash += method.toGenericString() + " ";
		}
		
		log.trace("Combined info: " +  hash);
		
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
	 * Returns the string multiplied by the amount so if
	 * str is equal to aba and amount is equal to 2,
	 * result is abaaba
	 * @param str The string to multiply
	 * @param amount The times to multiply it
	 * @return The string multipled
	 */
	static String addMultipleString(String str, int amount){
		String val = "";
		for (int i = 0; i < amount; ++i){
			val += str;
		}
		
		return val;
	}
	
	/**
	 * Formats a primitive type field in this format
	 * <br/><b>&lt;fieldName : type : data&gt;</b>
	 * @param fieldName The field name
	 * @param type The type of the field
	 * @param data The data to put into the field
	 * @return The formatted field data
	 */
	static String formatPrimitive(String fieldName, String type, String data){
		return String.format("<%s : %s : \"%s\">", fieldName,type,data);
	}
}
