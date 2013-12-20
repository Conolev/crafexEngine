package dev.scroopid.crafexEngine.util;

public class Util {

	/**
	 * returns the distance between two points
	 * @param x of first point
	 * @param y of first point
	 * @param tx - x of second point
	 * @param ty - y of second point
	 * @return distance between two points
	 */
	public static float getDistance(float x, float y, float tx, float ty) {
		return (float) Math.sqrt(Math.pow(tx - x, 2) + Math.pow(ty - y, 2));
	}

	/**
	 * returns the distance between two points
	 * @param x of first point
	 * @param y of first point
	 * @param tx - x of second point
	 * @param ty - y of second point
	 * @return distance between two points
	 */
	public static int getDistance(int x, int y, int tx, int ty) {
		return (int) Math.sqrt(Math.pow(tx - x, 2) + Math.pow(ty - y, 2));
	}

	/**
	 * checks if a number is between two others
	 * @param number
	 * @param min
	 * @param max
	 * @return is a number is between two others
	 */
	public static boolean isBetween(float number, float min, float max) {
		return number > min && number < max;
	}

	/**
	 * checks if a number is between two others
	 * @param number
	 * @param min
	 * @param max
	 * @return is a number is between two others
	 */
	public static boolean isBetween(int number, int min, int max) {
		return number > min && number < max;
	}
	
	/**
	 * checks if a {@link intPoint} is inside an {@link intRectangle}
	 * @param number
	 * @param min
	 * @param max
	 * @return is a number is between two others
	 */
	public static boolean isBetween(intPoint point, intRectangle area) {
		return Util.isBetween(point.getX(), area.getLeft(), area.getRight()) 
					&& Util.isBetween(point.getY(), area.getTop(), area.getBottom());
	}
	
	/**
	 * checks if a {@link floatPoint} is inside an {@link floatRectangle}
	 * @param number
	 * @param min
	 * @param max
	 * @return is a number is between two others
	 */
	public static boolean isBetween(floatPoint point, floatRectangle area) {
		return Util.isBetween(point.getX(), area.getLeft(), area.getRight()) 
					&& Util.isBetween(point.getY(), area.getTop(), area.getBottom());
	}

	/**
	 * returns the location of a {@link floatPoint} if it moved toward another at a provided speed
	 * @param location
	 * @param targetLocation
	 * @param speed 
	 * @return location of a {@link floatPoint} if it moved toward another at a provided speed
	 */
	public static floatPoint move(floatPoint location, floatPoint targetLocation, float speed) {
		float distance = location.getDistance(targetLocation);
		
		float dx;
		float dy;
		
		if(distance != 0){
			dx = ((targetLocation.getX() - location.getX()) / distance) * speed;
			dy = ((targetLocation.getY() - location.getY()) / distance) * speed;
		}else{
			dx = 0;
			dy = 0;
		}
		
		System.out.println(dx);
		System.out.println(dy);
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
