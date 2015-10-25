package org.wahlzeit.model;

import org.wahlzeit.services.DataObject;

public class Coordinate extends DataObject{
	
	/**
	 * Constants for min/max and mid points latitude/longitude values
	 */
	public static final double ZEROVALUE = 0.0;
	public static final double MIDLATITUDE = 90.0;
	public static final double MAXLATITUDE = 180.0;
	public static final double MIDLONGITUDE = 180.0;
	public static final double MAXLONGITUDE = 359.0;
	public static final double CIRCLEVALUE = 360.0;
	
	/**
	 * Latitude in degrees
	 * Valid range of values: 0 (south pole) to 180 (north pole)
	 */
	private double latitude;
	
	
	/**
	 * Longitude in degrees
	 * Valid range of values: 0 to 359
	 * 0: prime meridian
	 * Direction is eastward (i.e. longitude:=20 is 20 degrees east from the prime meridian)
	 */
	private double longitude;
	/**
	 * Constructor of Coordiante
	 * Only accepts valid values for latitude and longitude
	 * 
	 * @param latitude (valid range: [0,180])
	 * @param longitude (valid range: [0,359])
	 */
	public Coordinate(double latitude, double longitude) {
		if (latitude > MAXLATITUDE || latitude < ZEROVALUE) {
			throw new IllegalArgumentException("Latitude value is not valid. Range:[0,180]");
		}
		if (longitude > MAXLONGITUDE || longitude < ZEROVALUE) {
			throw new IllegalArgumentException("Longitude value is not valid. Range:[0,360]");	
		}
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	/**
	 * Calculate distance between two Coordinates
	 * Distance is distance in degrees
	 * 
	 * @param c
	 * @return Coordinate-object with latitudinal and longitudinal distances
	 */
	public Coordinate getDistance(Coordinate c) {
		if (c == null) {
			throw new IllegalArgumentException("Given parameter is null!");
		}
		return new Coordinate(getLatitudinalDistance(c), getLongitudinalDistance(c));
	}

	/**
	 * Calculates longitudinal distance to other Coordinate 
	 * Range of distances: [0,180]
	 * 
	 * @param c
	 * @return distance in degrees
	 */
	public double getLongitudinalDistance(Coordinate c) {
		if (c == null) {
			throw new IllegalArgumentException("Given parameter is null!");
		}

		// if distance is larger then 180 return the shorter distance
		double distance = Math.abs(this.longitude - c.longitude);
		if(distance > MIDLONGITUDE) {
			distance = CIRCLEVALUE - distance;
		}
		
		return distance;
	}

	/**
	 * Calculates latitudinal distance to other Coordinate 
	 * Range of distances: [0,180]
	 * 
	 * @param c
	 * @return distance in degrees
	 */
	public double getLatitudinalDistance(Coordinate c) {
		if (c == null) {
			throw new IllegalArgumentException("Given parameter is null!");
		}
		return Math.abs(this.latitude - c.latitude);
	}

	/**
	 * hashCode-method generated by eclipse
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(latitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	
	/**
	 * equals-method generated by eclipse
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Coordinate)) {
			return false;
		}
		Coordinate other = (Coordinate) obj;
		if (Double.doubleToLongBits(latitude) != Double
				.doubleToLongBits(other.latitude)) {
			return false;
		}
		if (Double.doubleToLongBits(longitude) != Double
				.doubleToLongBits(other.longitude)) {
			return false;
		}
		return true;
	}

	
}