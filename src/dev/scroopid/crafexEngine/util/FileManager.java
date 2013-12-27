package dev.scroopid.crafexEngine.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import dev.scroopid.crafexEngine.Logger;
import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class FileManager {
	private static final Logger LOGGER = new Logger(FileManager.class);

	/** applications save file location */
	private String fileLocation;

	/** {@link AssetManager} for the {@link Activity} */
	private AssetManager assets;

	static FileOutputStream f_out;

	static FileInputStream f_in;

	static ObjectOutputStream obj_out;

	static ObjectInputStream obj_in;

	static BufferedWriter buf_writer;

	static BufferedReader buf_reader;

	/**
	 * Manager of file shit
	 * 
	 * @param assets
	 * @param files
	 */
	public FileManager(AssetManager assets, String files) {
		this.assets = assets;
		this.fileLocation = files;
	}

	/**
	 * returns a {@link Bitmap} from desired location in the {@link AssetManager}
	 * 
	 * @param file
	 * @return bitmap from desired location in the AssetManager
	 * @throws IOException
	 */
	public Bitmap getAssestImage(String file) throws IOException {
		return BitmapFactory.decodeStream(this.assets.open(file));
	}

	/**
	 * saves a {@link String}[] to a {@link File}
	 * 
	 * @param name
	 * @param list
	 */
	public void saveStringArrayToFile(String name, String[] list) {
		String error = "";
		File file = new File(fileLocation + name);
		try {
			error = "couldn't create buffered writer";
			buf_writer = new BufferedWriter(new FileWriter(file));

			for (int i = 0; i < list.length; i++) {
				if (list.length > i) {
					if (i != 0) {
						error = "couldn't go to new line";
						buf_writer.newLine();
					}
					error = "couldn't write to buffered writer";
					buf_writer.write(list[i].toString());
				}
			}
			error = "couldn't close buffered writer";
			buf_writer.close();
		} catch (IOException e) {
			LOGGER.error(error, e);
		}

	}

	/**
	 * loads a {@link String}[] from a {@link File}
	 * 
	 * @param name
	 * @return
	 */
	public String[] loadStringArrayFromFile(String name) {
		String error = "";
		File file = new File(fileLocation + name);
		try {
			error = "couldn't create buffered reader";
			buf_reader = new BufferedReader(new FileReader(file));
			ArrayList<String> buffer = new ArrayList<String>();

			error = "couldn't read lines from: " + name;
			for (String lineBuffer; (lineBuffer = buf_reader.readLine()) != null;) {
				buffer.add(lineBuffer);
			}

			String[] data = buffer.toArray(new String[buffer.size()]);

			return data;
		} catch (FileNotFoundException e) {
			LOGGER.error(error, e);
		} catch (IOException e) {
			LOGGER.error(error, e);
		}
		return null;
	}
}
