package dev.scroopid.crafexEngine.console;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dev.scroopid.crafexEngine.Logger;

public class Command {
	
	private static final Logger logger = new Logger(Command.class);

	/**target {@link Object} of the {@link Command}*/
	private Object target;

	/**{@link Method} to call in the target {@link Object}*/
	private Method method;

	/**name of the {@link Command}*/
	private String name;

	/**default arguments of the method*/
	private Object[] defaultargs;

	/**
	 * a command that saves a {@link Object} and a {@link Method} to be run.
	 * @param name
	 * @param target
	 * @param method
	 * @param args
	 */
	public Command(String name, Class<?> target, Method method, Object... args) {
		this.name = name;
		this.target = target;
		this.method = method;
		this.defaultargs = args;
	}

	/**
	 * runs the {@link Method} of the target {@link Object} of the {@link Command}.
	 * @return if the command successfully ran
	 */
	public boolean run() {

		boolean caught = false;

		try {
			if (this.method.getParameterTypes().length < 1) {
				this.method.invoke(this.target);
			} else {
				this.method.invoke(this.target, this.defaultargs);
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

		if (caught)
			return false;
		else {
			logger.debug("Sucsefully ran command named: " + this.name);
			return true;
		}
	}

	/**
	 * runs the {@link Method} of the target {@link Object} of the {@link Command}.
	 * @param args to run in the method.
	 * @return if the command successfully ran
	 */
	public boolean run(Object... args) {

		boolean caught = false;

		try {
			this.method.invoke(this.target, args);
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

		if (caught)
			return false;
		else {
			logger.debug("Successfully ran command named: " + this.name + ".");
			return true;
		}
	}
}
