package dev.scroopid.crafexEngine.input;

public class CrafexKeyInputEvent {

	/** number of backspace */
	public static int SKEY_BACKSPACE = 127;

	/** key was pressed */
	public static int KEY_PRESSED = 1;

	/** key was released */
	public static int KEY_RELEASED = 2;

	/**
	 * does input action to string
	 * 
	 * @param value
	 *        of event
	 * @param input
	 *        string to input on
	 * @param index
	 *        index to input on string
	 * @return string with input
	 */
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

	/** event value */
	private int eventValue;

	/** time of event */
	private long eventTime;

	/**
	 * a keyboard input event
	 * 
	 * @param value
	 *        of event
	 * @param time
	 *        of event
	 */
	public CrafexKeyInputEvent(char value, long time) {
		this.eventTime = time;
		this.eventValue = value;
	}

	/**
	 * returns the char value of {@link CrafexKeyInputEvent}
	 * 
	 * @return char value of event
	 */
	public char getChar() {
		return (char) this.eventValue;
	}

	/**
	 * returns the time {@link CrafexKeyInputEvent} happened
	 * 
	 * @return time event happened
	 */
	public long getTime() {
		return this.eventTime;
	}

	/**
	 * returns the int value of {@link CrafexKeyInputEvent}
	 * 
	 * @return int value of event
	 */
	public int getValue() {
		return this.eventValue;
	}
}
