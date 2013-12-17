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

	public static floatPoint move(floatPoint location, floatPoint targetLocation, float speed) {
		float distance = location.getDistance(targetLocation);
		float dx = ((targetLocation.getX() - location.getX()) / distance) * speed;
		float dy = ((targetLocation.getY() - location.getY()) / distance) * speed;
		location.addX(dx);
		location.addY(dy);
		return location;
	}
}
