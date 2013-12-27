package dev.scroopid.crafexEngine.save.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import dev.scroopid.crafexEngine.Logger;

/**
 * Based on the concept of a command, but much restricted to just certain arguements.
 * @author jameswomack
 *
 */
public class SaveFieldMethod {
	public static final Logger LOGGER = new Logger(SaveFieldMethod.class);
	
	private Method saveMethod;
	
	private String name;
	
	/**
	 * Creates a new SaveMethod with the specified method
	 * @param method The method to base around
	 * @throws SaveException If the method is null or invalid
	 */
	public SaveFieldMethod(Method method){
		if (method == null){
			
			SaveException sEx = new SaveException("Method is null");
			LOGGER.error("The Method is null!", sEx);
			throw sEx;
			
		} else if (!Modifier.isStatic(method.getModifiers())){
			
			// That is ironic! :)) ... Ahem, method must be static.
			SaveException sEx = new SaveException(String.format("Saving Method %s is not static!", method.getName()));
			LOGGER.error(String.format("Method %s is not static!", method.getName()), sEx);
			throw sEx;
			
		} else if (!method.isAccessible()){
			
			// Can't stop laughing about that name XD ... Ahem, method must be accessible
			SaveException sEx = new SaveException(String.format("Saving Method %s is not accessible", method.getName()));
			LOGGER.error(String.format("Method %s is not accessible", method.getName()), sEx);
			throw sEx;
			
		} else if (!validMethod(method)){
			
			// LMAO... ahem, if the method does not have proper parameters or returns, throw SaveException.
			SaveException sEx = new SaveException(String.format("Saving Method %s does not have valid paramters or return", method.getName()));
			LOGGER.error(String.format("Method %s is not valid", sEx));
			throw sEx;
			
		}
		
		// Save full qualified name
		name = saveMethod.getDeclaringClass().getName() + saveMethod.getName();
		
		// Lets set the method now
		saveMethod = method;
	}
	
	/**
	 * Checks if the method is valid.
	 * 
	 * It has to return a {@link String[]} and have these parameters in this order
	 * {@link Object} target, {@link Field} field, {@link int} callsDeep
	 * @param method The method to check
	 * @return Is the method valid?
	 */
	private static boolean validMethod(Method method){
		// Check if return type is valid
		if (!method.getReturnType().equals(String[].class)){
			return false;
		} else if (method.getParameterTypes().length != 3){
			// If the amount of arguements isnt 3, then it isnt valid
			return false;
		}
		
		Class<?>[] arguements = method.getParameterTypes();
		
		// if the parameter types aren't in this order:
		// Object target, Field field, int callsDeep
		return !(arguements[0].equals(Object.class) || arguements[1].equals(Field.class) || arguements[2].equals(int.class));
	}
	
	public List<String> save(Object target, Field field, int callsDeep){
		String[] result = new String[0];
		
		// Try to call the method, if it doesn't work then throw an exception
		try {
			result = (String[]) saveMethod.invoke(null, target, field, callsDeep);
		} catch (IllegalArgumentException e) {
			
			 // This should not happen... haha... sEx
			SaveException sEx = new SaveException("Illegal Arguements");
			LOGGER.error(String.format("Method %s has illegal arguements?", name),sEx);
			throw sEx;
			
		} catch (IllegalAccessException e) {

			 // This should not happen also... lmao... sEx
			SaveException sEx = new SaveException("Illegal Access");
			LOGGER.error(String.format("Method %s cannot be accessed", name),sEx);
			throw sEx;
			
		} catch (InvocationTargetException e) {

			 // The method cannot be invoked... sEx
			SaveException sEx = new SaveException("Cannot invoke the method: " + name);
			LOGGER.error(String.format("Method %s cannot be invoked", name), sEx);
			throw sEx;
			
		}
		
		// Create an arrayList and return that instead of a String[]
		List<String> array = new ArrayList<String>();
		
		for (String line : result){
			array.add(line);
		}
		
		return array;
	}
	
	/**
	 * Gets the name of the save method
	 * @return The name of the SaveMethod
	 */
	public String getName(){
		return name;
	}
	
}
