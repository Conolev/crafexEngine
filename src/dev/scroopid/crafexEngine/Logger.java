package dev.scroopid.crafexEngine;

/**
 * This {@link Class} is used to dump informational messages.
 */
public class Logger {
	/**debug level*/
	public static final int DEBUG = 4;
	/**error level*/
	public static final int ERROR = 1;
	/**info level*/
	public static final int INFO = 3;
	/**trace level*/
	public static final int TRACE = 5;
	/**warning level*/
	public static final int WARNING = 2;

	/** This member holds the current level to be output. */
	private static int level = TRACE;

	/**prefix of the {@link Logger}*/
	private String prefix;

	public Logger(Class<?> cls) {
		this.prefix = this.getName(cls) + " ";
	}

	/**
	 * sends a debug message
	 * @param message
	 */
	public void debug(String message) {
		this.log(DEBUG, message, null);
	}

	/**
	 * sends an error message.
	 * @param message
	 */
	public void error(String message) {
		this.error(message, null);
	}

	/**
	 * sends an error message.
	 * @param message
	 * @param throwable
	 */
	public void error(String message, Throwable t) {
		this.log(ERROR, message, t);
	}

	/**
	 * This method return just the name of the {@link Class} (i.e. without package).
	 */
	private String getName(Class<?> cls) {
		String name = cls.getName();

		return name.substring(name.lastIndexOf('.') + 1);
	}

	/**
	 * sends an info message.
	 * @param message
	 */
	public void info(String message) {
		this.log(INFO, message, null);
	}

	/**
	 * sends a message at given messageLevel.
	 * @param messageLevel
	 * @param message
	 * @param throwable
	 */
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

	/**
	 * sends a trace message.
	 * @param message
	 */
	public void trace(String message) {
		this.log(TRACE, message, null);
	}

	/**
	 * sends a warning message.
	 * @param message
	 */
	public void warning(String message) {
		this.log(WARNING, message, null);
	}
}
