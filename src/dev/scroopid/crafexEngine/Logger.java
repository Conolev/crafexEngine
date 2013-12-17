package dev.scroopid.crafexEngine;

/**
 * This class is used to dump informational messages.
 */
public class Logger {
	public static final int DEBUG = 4;

	public static final int ERROR = 1;

	public static final int INFO = 3;

	public static final int TRACE = 5;

	public static final int WARNING = 2;

	/** This member holds the current level to be output. */
	private static int level = TRACE;

	private String prefix;

	public Logger(Class cls) {
		this.prefix = this.getName(cls) + " ";
	}

	public void debug(String message) {
		this.log(DEBUG, message, null);
	}

	public void error(String message) {
		this.error(message, null);
	}

	public void error(String message, Throwable t) {
		this.log(ERROR, message, t);
	}

	/**
	 * This method return just the name of the class (i.e. without package).
	 */
	private String getName(Class cls) {
		String name = cls.getName();

		return name.substring(name.lastIndexOf('.') + 1);
	}

	public void info(String message) {
		this.log(INFO, message, null);
	}

	private void log(int messageLevel, String message, Throwable t) {
		if (messageLevel <= level) {
			System.out.print(this.prefix);
			System.out.print('[');

			switch (messageLevel) {
			case TRACE:
				System.out.print("TRACE");
				break;
			case DEBUG:
				System.out.print("DEBUG");
				break;
			case INFO:
				System.out.print("INFO");
				break;
			case WARNING:
				System.out.print("WARN");
				break;
			case ERROR:
				System.out.print("ERROR");
				break;
			default:
				System.out.print("...");
				break;
			}

			System.out.print("] ");
			System.out.println(message);

			if (t != null) {
				t.printStackTrace();
			}
		}
	}

	public void trace(String message) {
		this.log(TRACE, message, null);
	}

	public void warning(String message) {
		this.log(WARNING, message, null);
	}
}
