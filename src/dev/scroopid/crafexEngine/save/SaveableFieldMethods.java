package dev.scroopid.crafexEngine.save;

import java.lang.reflect.Field;
import java.util.List;

import dev.scroopid.crafexEngine.Logger;
import dev.scroopid.crafexEngine.save.util.SaveUtils;

public class SaveableFieldMethods {
	private static final Logger LOGGER = new Logger(SaveableFieldMethods.class);
	static {
		SaveManager.addSFM(SaveUtils.getSFM("saveISavable", SaveableFieldMethods.class, ISavable.class));
	}

	public static String[] saveISavable(Object target, Field field, int callsDeep) {
		List<String> data =
					SaveManager.getInstance().saveISavable((ISavable) SaveUtils.getData(target, field), callsDeep);

		return (String[]) data.toArray();
	}

	public static String[] saveSaveHandler(Object target, Field field, int callsDeep) {
		List<String> data = SaveManager.getInstance().saveSaveHandler((ISaveHandler) SaveUtils.getData(target, field));

		// Add tabs
		for (int i = 0; i < data.size(); ++i) {
			data.set(i, SaveUtils.tabify(data.get(i), callsDeep));
		}

		return (String[]) data.toArray();
	}
}
