package dev.scroopid.crafexEngine.input;

public interface Inputable {
	/**
	 * ends the input stream
	 */
	public void endInput();

	/**
	 * gets the input
	 * 
	 * @return input
	 */
	public String getInput();

	/**
	 * gets the inputing index(place in string to put new input)
	 * 
	 * @return index of input
	 */
	public int getInputingIndex();

	/**
	 * get length of input
	 * 
	 * @return input length
	 */
	public int getInputLength();

	/**
	 * handles input
	 * 
	 * @param newInput
	 */
	public void giveInput(String newInput);

	/**
	 * starts input stream
	 */
	public void startInput();
	
	public boolean isInputing();
}
