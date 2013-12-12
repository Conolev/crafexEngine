package dev.scroopid.crafexEngine.input;

public class CrafexKeyInputEvent {
	
	public static int SKEY_BACKSPACE = 127;
	
	public static int KEY_PRESSED = 1;
	public static int KEY_RELEASED = 2;
	
	private int eventValue;
	private long eventTime;
	
	public CrafexKeyInputEvent(char value, long time){
		eventTime = time;
		eventValue = value;
	}
	
	public int getValue(){
		return eventValue;
	}
	
	public char getChar(){
		return (char) eventValue;
	}
	
	public long getTime(){
		return eventTime;
	}
	
	public static String doInput(int value, String input, int index){
		String data = input.substring(0, index);
		String last = input.substring(index);
		
		if(value < 127){
			data += String.valueOf((char)value) + last;
		}else{
			if(value == 127 && data.length() > 0){
				data = data.substring(0, data.length() - 1) + last;
			}
		}
		return data;
	}

}
