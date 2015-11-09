package org.wahlzeit.model;

/**
 * Spheric coordinate class
 * 
 * Saves latitude, longitude and radius
 */
public class SphericCoordinate implements Coordinate {

	/**
	 * Constants for min/max and mid points latitude/longitude values
	 */
	public static final double MIN_LATITUDE = -90.0;
	public static final double MAX_LATITUDE = 90.0;
	public static final double MIN_LONGITUDE = -180.0;
	public static final double MAX_LONGITUDE = 180.0;

	/**
	 * Latitude in degrees
	 * 
	 * Valid range of values: -90 (south pole) to 90 (north pole)
	 */
	private double latitude;

	/**
	 * Longitude in degrees
	 * 
	 * Valid range of values: -180 to 180
	 * 
	 * 0: prime meridian
	 */
	private double longitude;

	/**
	 * Radius in km
	 * 
	 * Valid range of values: >= 0
	 */
	private double radius;

	/**
	 * Constructor of SphericCoordiante
	 * 
	 * Only accepts valid values for latitude, longitude and radius
	 * 
	 * @param latitude
	 *            (valid range: [-90,90])
	 * @param longitude
	 *            (valid range: [-180,180])
	 * @param radius
	 *            (valid range: >= 0)
	 */
	public SphericCoordinate(double latitude, double longitude, double radius) {
		assertValidLatitude(latitude);
		assertValidLongitude(longitude);
		assertValidRadius(radius);
		this.latitude = latitude;
		this.longitude = longitude;
		this.radius = radius;
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
	public double getLongitudinalDistance(SphericCoordinate c) {
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
	public double getLatitudinalDistance(SphericCoordinate c) {
		assertIsArgumentNotNull(c);
		return Math.abs(this.latitude - c.latitude);
	}

	/**
	 * See Coordinate interface for documentation
	 */
	@Override
	public double getDistance(Coordinate c) {
		assertIsArgumentNotNull(c);
		SphericCoordinate otherSpheric = SphericCoordinate
				.asSphericCoordinate(c);

		double lat1 = Math.toRadians(this.latitude);
		double lat2 = Math.toRadians(otherSpheric.latitude);
		double deltaLong = Math
				.toRadians(getLongitudinalDistance(otherSpheric));

		double deltaSigma = Math.acos(Math.sin(lat1) * Math.sin(lat2)
				+ Math.cos(lat1) * Math.cos(lat2) * Math.cos(deltaLong));
		return EARTH_RADIUS_KM * deltaSigma;
	}

	/**
	 * See Coordinate interface for documentation
	 */
	@Override
	public boolean isEqual(Coordinate c) {
		return equals(SphericCoordinate.asSphericCoordinate(c));
	}

	/**
	 * See Coordinate interface for documentation
	 */
	@Override
	public double[] asSphericRepresentation() {
		return new double[] { latitude, longitude, radius };
	}

	/**
	 * See Coordinate interface for documentation
	 */
	@Override
	public double[] asCartesianRepresentation() {
		//normalized to valid values: [0,180] 
		double normalizedLat = latitude >= ZERO_VALUE ? latitude : HALF_CIRCLE_VALUE + latitude;
		
		double x = radius * Math.sin(Math.toRadians(normalizedLat))
				* Math.cos(Math.toRadians(longitude));
		double y = radius * Math.sin(Math.toRadians(normalizedLat))
				* Math.sin(Math.toRadians(longitude));
		double z = radius * Math.cos(Math.toRadians(normalizedLat));
		return new double[] { x, y, z };
	}

	/**
	 * Convert Coordinate object into SphericCoordinate
	 * 
	 * @methodtype conversion
	 * @methodproperties composed class
	 * 
	 * @param c
	 * @return
	 */
	public static SphericCoordinate asSphericCoordinate(Coordinate c) {
		double[] sphericRep = c.asSphericRepresentation();
		return new SphericCoordinate(sphericRep[0], sphericRep[1],
				sphericRep[2]);
	}

	/**
	 * Assert if Argument is null
	 * 
	 * should be in absract Coordinate class
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
	 * Assert if Radius is valid
	 * 
	 * @methodtype assertion
	 * @methodproperties primitive class
	 * 
	 * @param lon
	 */
	private static void assertValidRadius(double rad) {
		if (rad < 0) {
			throw new IllegalArgumentException(
					"Radius value is not valid. Should be >= 0");
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
		if (!(obj instanceof SphericCoordinate)) {
			return false;
		}
		SphericCoordinate other = (SphericCoordinate) obj;
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
