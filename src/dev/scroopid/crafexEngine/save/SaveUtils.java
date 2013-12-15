package dev.scroopid.crafexEngine.save;

import java.text.SimpleDateFormat;
import java.util.Date;

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
	static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a");
	
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
	
	static String createDateComment(){
		Date date = new Date();
		
		return createComment("Created on " + dateFormat.format(date));
	}
}
