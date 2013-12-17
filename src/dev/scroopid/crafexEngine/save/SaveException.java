package dev.scroopid.crafexEngine.save;

/**
 * Exception Class for Saving Failures by the Save Subsystem.
 * 
 * @author jameswomack
 * 
 */
public class SaveException extends RuntimeException {
	private String message;

	/**
	 * 
	 */
	private static final long serialVersionUID = 7833102194465876810L;

	/**
	 * Creates a new SaveException.
	 */
	public SaveException() {
		this.message = "Generic Save Exception";
	}

	/**
	 * Creates a new SaveException with the message
	 * 
	 * @param message
	 */
	public SaveException(String message) {
		this.message = message;
	}

	/**
	 * Getter for getting the message of the exception
	 */
	@Override
	public String getMessage() {
		return this.message;
	}

}
