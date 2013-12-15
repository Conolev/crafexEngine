package dev.scroopid.crafexEngine.save;
/**
 * Exception Class for Saving Failures by the Save Subsystem.
 * @author jameswomack
 *
 */
public class SaveException extends RuntimeException {
	private String message;
	
	/**
	 * Creates a new SaveException.
	 */
	public SaveException(){
		this.message = "Generic Save Exception";
	}
	
	/**
	 * Creates a new SaveException with the message
	 * @param message
	 */
	public SaveException(String message){
		this.message = message;
	}
	
	/**
	 * Getter for getting the message of the exception
	 */
	public String getMessage(){
		return message;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 7833102194465876810L;

}
