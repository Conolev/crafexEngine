package dev.scroopid.crafexEngine.input;

public interface CrafexInputer {
	
	public static final int INPUT_TYPE_ASSCI_KEYBOARD = 1;
	
	public CrafexKeyInputEvent[] getInputs();
	public boolean isActive();
	public void addInput(CrafexKeyInputEvent event);
}
