package dev.scroopid.crafexEngine.save;

import java.lang.reflect.Field;
import java.util.Collection;

import dev.scroopid.crafexEngine.Logger;
import dev.scroopid.crafexEngine.save.util.SaveUtils;

public class CollectionSaveMethods {
	private static final Logger LOGGER = new Logger(CollectionSaveMethods.class);
	
	static{
		SaveManager.addSFM(SaveUtils.getSFM("saveCollection", CollectionSaveMethods.class, Collection.class));
	}
	
	public static String[] saveCollection(Object target, Field field, int callsDeep){
		String[] data = null;
		
		return data;
	}
	
	public static String[] saveMap(Object target, Field field, int callsDeep){
		String[] data = null;
		
		return data;
	}
}
