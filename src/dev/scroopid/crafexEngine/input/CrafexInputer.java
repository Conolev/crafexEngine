package dev.scroopid.crafexEngine.input;

public interface CrafexInputer {

	public static final int INPUT_TYPE_ASSCI_KEYBOARD = 1;

	public void addInput(CrafexKeyInputEvent event);

	public CrafexKeyInputEvent[] getInputs();

	public boolean isActive();
}
