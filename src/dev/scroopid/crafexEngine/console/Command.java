package dev.scroopid.crafexEngine.console;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dev.scroopid.crafexEngine.Logger;

public class Command {
	
	private static final Logger logger = new Logger(Command.class); 
	private Object target;
	private Method method;
	private String name;
	private Object[] defaultargs;
	
	public Command(String name, Class<?> target, Method method, Object ... args) {
		this.name = name;
		this.target = target;
		this.method = method;
		defaultargs = args;
	}
	
	public boolean run() {
		
		boolean caught = false;
		
		try {
			if(method.getParameterTypes().length < 1){
				method.invoke(target);
			}else{
				method.invoke(target, defaultargs);
			}
		} catch (IllegalArgumentException e) {
			logger.error("running command had an Illegal Argument.", e);
			caught = true;
		} catch (IllegalAccessException e) {
			logger.error("running command had an Illegal Access.", e);
			caught = true;
		} catch (InvocationTargetException e) {
			logger.error("running command had an Illegal Target.", e);
			caught = true;
		}
		
		if(caught)
			return false;
		else {
			logger.debug("Sucsefully ran command named: " + name);
			return true;
		}
	}
	
	public boolean run(Object ... args) {
		
		boolean caught = false;
		
		try {
			method.invoke(target, args);
		} catch (IllegalArgumentException e) {
			logger.error("running command had an Illegal Argument.", e);
			caught = true;
		} catch (IllegalAccessException e) {
			logger.error("running command had an Illegal Access.", e);
			caught = true;
		} catch (InvocationTargetException e) {
			logger.error("running command had an Illegal Target.", e);
			caught = true;
		}
		
		if(caught)
			return false;
		else {
			logger.debug("Successfully ran command named: " + name + ".");
			return true;
		}
	}
	
}
