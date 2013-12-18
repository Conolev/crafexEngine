package dev.scroopid.crafexEngine.util;

public class Util {

	public static float getDistance(float x, float y, float tx, float ty) {
		return (float) Math.sqrt(Math.pow(tx - x, 2) + Math.pow(ty - y, 2));
	}

	public static int getDistance(int x, int y, int tx, int ty) {
		return (int) Math.sqrt(Math.pow(tx - x, 2) + Math.pow(ty - y, 2));
	}

	public static boolean isBetween(float number, float min, float max) {
		return number > min && number < max;
	}

	public static boolean isBetween(int number, int min, int max) {
		return number > min && number < max;
	}
	
	public static boolean isBetween(intPoint point, intRectangle area) {
		return Util.isBetween(point.getX(), area.getLeft(), area.getRight()) 
					&& Util.isBetween(point.getY(), area.getTop(), area.getBottom());
	}

	public static floatPoint move(floatPoint location, floatPoint targetLocation, float speed) {
		float distance = location.getDistance(targetLocation);
		float dx = ((targetLocation.getX() - location.getX()) / distance) * speed;
		float dy = ((targetLocation.getY() - location.getY()) / distance) * speed;
		
		if(Math.abs(dx) > Math.abs(targetLocation.getX() - location.getX())){
			dx = targetLocation.getX() - location.getX();
		}
		
		if(Math.abs(dy) > Math.abs(targetLocation.getY() - location.getY())){
			dy = targetLocation.getY() - location.getY();
		}
		
		location.addX(dx);
		location.addY(dy);
		return location;
	}
}
