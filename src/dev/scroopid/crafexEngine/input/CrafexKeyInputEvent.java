package dev.scroopid.crafexEngine.input;

public class CrafexKeyInputEvent {

	public static int SKEY_BACKSPACE = 127;

	public static int KEY_PRESSED = 1;

	public static int KEY_RELEASED = 2;

	public static String doInput(int value, String input, int index) {
		String data = input.substring(0, index);
		String last = input.substring(index);

		if (value < 127) {
			data += String.valueOf((char) value) + last;
		} else {
			if (value == 127 && data.length() > 0) {
				data = data.substring(0, data.length() - 1) + last;
			}
		}
		return data;
	}

	private int eventValue;

	private long eventTime;

	public CrafexKeyInputEvent(char value, long time) {
		this.eventTime = time;
		this.eventValue = value;
	}

	public char getChar() {
		return (char) this.eventValue;
	}

	public long getTime() {
		return this.eventTime;
	}

	public int getValue() {
		return this.eventValue;
	}

}
