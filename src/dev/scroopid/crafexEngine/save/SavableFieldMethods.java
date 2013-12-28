package dev.scroopid.crafexEngine.save;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import dev.scroopid.crafexEngine.Logger;
import dev.scroopid.crafexEngine.save.util.SaveUtils;

public class SavableFieldMethods {
	private static final String SAVE_SAVE_HANDER = "saveSaveHander";
	private static final String SAVE_I_SAVABLE = "saveISavable";
	
	private static final Logger LOGGER = new Logger(SavableFieldMethods.class);
	
	static {
		SaveManager.addSFM(SaveUtils.getSFM(SAVE_I_SAVABLE, SavableFieldMethods.class, ISavable.class));
		SaveManager.addSFM(SaveUtils.getSFM(SAVE_SAVE_HANDER, SavableFieldMethods.class, ISaveHandler.class));
	}

	/**
	 * Saves an ISavable variable type in a field
	 * @param target The target to save from
	 * @param field The field to save from
	 * @param callsDeep How many calls deep we are.
	 * @return The Data in String form
	 */
	public static String[] saveISavable(Object target, Field field, int callsDeep) {
		List<String> data = new ArrayList<String>();
		data.add(String.format("(%s)", field.getName()));
		data.addAll(SaveManager.getInstance().saveISavable((ISavable) SaveUtils.getData(target, field), callsDeep + 1));

		return (String[]) data.toArray();
	}

	public static String[] saveSaveHandler(Object target, Field field, int callsDeep) {
		List<String> data = new ArrayList<String>(); 
		data.addAll(SaveManager.getInstance().saveSaveHandler((ISaveHandler) SaveUtils.getData(target, field)));

		// Add tabs
		for (int i = 0; i < data.size(); ++i) {
			data.set(i, SaveUtils.tabify(data.get(i), callsDeep));
		}

		return (String[]) data.toArray();
	}
}
