package dev.scroopid.crafexEngine.input;

public interface Inputable {
	public void endInput();

	public String getInput();

	public int getInputingIndex();

	public int getInputLength();

	public void setInput(String newInput);

	public void startInput();
}
