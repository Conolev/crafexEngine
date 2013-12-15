package dev.scroopid.crafexEngine.save;

import java.util.ArrayList;
import java.util.List;

import dev.scroopid.crafexEngine.Logger;


public class SaveManager {
	private static final Logger log = new Logger(SaveManager.class);
	
	public List<String> saveData(List<ISavable> objects){
		// Create the file that we will save to.
		List<String> file = new ArrayList<String>();
		log.trace(String.format("Saving %d objects", objects.size()));
		
		// Create file header
		log.trace("Creating header for file");
		file.add(SaveUtils.createDateComment());
		
		return file;
	}
	
	
}
