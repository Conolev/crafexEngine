package dev.scroopid.crafexEngine.input;

public interface CrafexInputer {

	/** input type: basic assci keyboard */
	public static final int INPUT_TYPE_ASSCI_KEYBOARD = 1;

	/**
	 * add {@link CrafexKeyInputEvent}
	 * 
	 * @param event
	 */
	public void addInput(CrafexKeyInputEvent event);

	/**
	 * gets inputs of {@link CrafexInputer}
	 * 
	 * @return inputs
	 */
	public CrafexKeyInputEvent[] getInputs();

	/**
	 * is {@link CrafexInputer} active?
	 * 
	 * @return isActive?
	 */
	public boolean isActive();
}
