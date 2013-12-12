package dev.scroopid.crafexEngine.input;

public interface Inputable {
	public void startInput();
	public void endInput();
	public String getInput();
	public void setInput(String newInput);
	public int getInputLength();
	public int getInputingIndex();
}
