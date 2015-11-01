package org.wahlzeit.model;

/**
 * Coordinate class
 * 
 * Saves latitude and longitude coordinates 
 */
public class Coordinate{

	/**
	 * Constants for min/max and mid points latitude/longitude values
	 */
	
	public static final double EARTH_RADIUS_KM = 6371.0;
	public static final double ZERO_VALUE = 0.0;
	public static final double CIRCLE_VALUE = 360.0;
	public static final double HALF_CIRCLE_VALUE = CIRCLE_VALUE / 2;
	public static final double QUARTER_CIRCLE_VALUE = CIRCLE_VALUE / 4;

	public static final double MIN_LATITUDE = -90.0;
	public static final double MAX_LATITUDE = 90.0;
	public static final double MIN_LONGITUDE = -180.0;
	public static final double MAX_LONGITUDE = 180.0;

	/**
	 * Latitude in degrees Valid range of values: -90 (south pole) to 90 (north
	 * pole)
	 */
	private double latitude;

	/**
	 * Longitude in degrees Valid range of values: -180 to 180
	 * 
	 * 0: prime meridian
	 */
	private double longitude;

	/**
	 * Constructor of Coordiante Only accepts valid values for latitude and
	 * longitude
	 * 
	 * @param latitude
	 *            (valid range: [-90,90])
	 * @param longitude
	 *            (valid range: [-180,180])
	 */
	public Coordinate(double latitude, double longitude) {
		assertValidLatitude(latitude);
		assertValidLongitude(longitude);
		this.latitude = latitude;
		this.longitude = longitude;
	}

	/**
	 * Calculate distance between two Coordinates
	 * 
	 * @methodtype get
	 * @methodproperties composed instance
	 * 
	 * @param c
	 * @return Distance in km
	 */
	public double getDistance(Coordinate c) {
		assertIsArgumentNotNull(c);
		double radiansLatitude1 = Math.toRadians(this.latitude);
		double radiansLatitude2 = Math.toRadians(c.latitude);
		double deltaLongitude = Math.toRadians(getLongitudinalDistance(c));

		double deltaSigma = Math.acos(Math.sin(radiansLatitude1)
				* Math.sin(radiansLatitude2) + Math.cos(radiansLatitude1)
				* Math.cos(radiansLatitude2) * Math.cos(deltaLongitude));
		
		return EARTH_RADIUS_KM * deltaSigma;
	}

	/**
	 * Calculates longitudinal distance to other Coordinate Range of distances:
	 * [0,180]
	 * 
	 * @methodtype get
	 * @methodtype composed instance
	 * 
	 * @param c
	 * @return distance in degrees
	 */
	public double getLongitudinalDistance(Coordinate c) {
		assertIsArgumentNotNull(c);

		// if distance is larger then 180 return the shorter distance
		double distance = Math.abs(this.longitude - c.longitude);
		if (distance > HALF_CIRCLE_VALUE) {
			distance = CIRCLE_VALUE - distance;
		}

		return distance;
	}

	/**
	 * Calculates latitudinal distance to other Coordinate Range of distances:
	 * [0,180]
	 * 
	 * @methodtype get
	 * @methodproperties composed instance
	 * 
	 * @param c
	 * @return distance in degrees
	 */
	public double getLatitudinalDistance(Coordinate c) {
		assertIsArgumentNotNull(c);
		return Math.abs(this.latitude - c.latitude);
	}

	/**
	 * Assert if Argument is null
	 *
	 * @methodtype assertion
	 * @methodproperties primitive class
	 * 
	 * @param c
	 */
	private static void assertIsArgumentNotNull(Object c) {
		if (c == null) {
			throw new IllegalArgumentException("Given Argument is null!");
		}
	}

	/**
	 * Assert if Latitude is valid
	 * 
	 * @methodtype assertion
	 * @methodproperties primitive class
	 * 
	 * @param lat
	 */
	private static void assertValidLatitude(double lat) {
		if (lat > MAX_LATITUDE || lat < MIN_LATITUDE) {
			throw new IllegalArgumentException(
					"Latitude value is not valid. Range:[-90,90]");
		}
	}

	/**
	 * Assert if Longitude is valid
	 * 
	 * @methodtype assertion
	 * @methodproperties primitive class
	 * 
	 * @param lon
	 */
	private static void assertValidLongitude(double lon) {
		if (lon > MAX_LONGITUDE || lon < MIN_LONGITUDE) {
			throw new IllegalArgumentException(
					"Longitude value is not valid. Range:[-180,180]");
		}
	}

	/**
	 * hashCode-method generated by eclipse
	 * 
	 * @methodtype conversion
	 * @methodproperties primitive instance
	 * 
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
	 * 
	 * @methodtype comparison
	 * @methodproperties primitive instance
	 * 
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