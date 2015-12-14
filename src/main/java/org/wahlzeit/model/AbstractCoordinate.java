package org.wahlzeit.model;

public abstract class AbstractCoordinate implements Coordinate {
	/**
	 * Constants needed for calculations
	 */
	public static final double EARTH_RADIUS_KM = 6371.0;
	public static final double ZERO_VALUE = 0.0;
	public static final double CIRCLE_VALUE = 360.0;
	public static final double HALF_CIRCLE_VALUE = CIRCLE_VALUE / 2;
	public static final double QUARTER_CIRCLE_VALUE = CIRCLE_VALUE / 4;

	/**
	 * Constants for min/max and mid points latitude/longitude values
	 */
	public static final double MIN_LATITUDE = -90.0;
	public static final double MAX_LATITUDE = 90.0;
	public static final double MIN_LONGITUDE = -180.0;
	public static final double MAX_LONGITUDE = 180.0;

	/**
	 * See Coordinate interface for documentation
	 * 
	 * @methodtype comparison
	 * @methodproperties template
	 */
	public double getDistance(Coordinate other) {
		assertClassInvariants();

		// Precondition
		assertIsArgumentNotNull(other);

		double thisLatitude = getLatitude();
		double thisLongitude = getLongitude();

		double otherLatitude = other.getLatitude();
		double otherLongitude = other.getLongitude();

		double lat1 = Math.toRadians(thisLatitude);
		double lat2 = Math.toRadians(otherLatitude);
		double deltaLong = Math.toRadians(Math.abs(thisLongitude
				- otherLongitude));

		double deltaSigma = Math.acos(Math.sin(lat1) * Math.sin(lat2)
				+ Math.cos(lat1) * Math.cos(lat2) * Math.cos(deltaLong));
		double result = EARTH_RADIUS_KM * deltaSigma;

		assertClassInvariants();

		// Postcondition
		assertIsNonNegativeValue(result);
		assertIsNotNaN(result);
		assertIsNotInfinite(result);

		return result;
	}

	/**
	 * See Coordinate interface for documentation
	 * 
	 * @methodtype comparison
	 * @methodproperties template
	 */
	@Override
	public boolean isEqual(Coordinate c) {
		assertClassInvariants();

		// Precondition
		assertIsArgumentNotNull(c);

		boolean result = equals(asOwnCoordinate(c));

		assertClassInvariants();
		return result;
	}

	/**
	 * Returns argument as a instance of the own Coordinate class
	 * 
	 * (i.e. CartesianCoordinate transforms to CartesianCoordinate)
	 * 
	 * @methodtype conversion
	 * 
	 * @param c
	 *            Coordinate to transform
	 * @return transformed Coordinate
	 */
	protected abstract Coordinate asOwnCoordinate(Coordinate c);

	/**
	 * Assert if instance is not in an illegal state
	 * 
	 * @methodtype assertion
	 */
	protected abstract void assertClassInvariants();

	/**
	 * Assert if argument is null
	 * 
	 * @methodtype assertion
	 * @methodproperties primitive class
	 * 
	 * @param c
	 */
	protected static void assertIsArgumentNotNull(Object c) {
		if (c == null) {
			throw new IllegalArgumentException("Given Argument is null!");
		}
	}

	/**
	 * Assert if argument is non negative
	 * 
	 * @methodtype assertion
	 * @methodproperties primitive class
	 * 
	 * @param value
	 */
	protected static void assertIsNonNegativeValue(double value) {
		if (value < 0) {
			throw new IllegalStateException("Value is negative!");
		}
	}

	/**
	 * Assert if argument is not NaN
	 * 
	 * @methodtype assertion
	 * @methodproperties primitive class
	 * 
	 * @param value
	 */
	protected static void assertIsNotNaN(double value) {
		if (Double.isNaN(value)) {
			throw new IllegalStateException("Value is NaN!");
		}
	}

	/**
	 * Assert if argument is not infinite
	 * 
	 * @methodtype assertion
	 * @methodproperties primitive class
	 * 
	 * @param value
	 */
	protected static void assertIsNotInfinite(double value) {
		if (Double.isInfinite(value)) {
			throw new IllegalStateException("Value is infinite!");
		}
	}

	/**
	 * Assert if latitude is valid
	 * 
	 * @methodtype assertion
	 * @methodproperties primitive class
	 * 
	 * @param lat
	 */
	protected static void assertIsValidLatitude(double lat) {
		if (lat > MAX_LATITUDE || lat < MIN_LATITUDE) {
			throw new IllegalArgumentException(
					"Latitude value is not valid. Range:[-90,90]");
		} else if (Double.isNaN(lat)) {
			throw new IllegalArgumentException(
					"Latitude value is not valid. (NaN)");
		} else if (Double.isInfinite(lat)) {
			throw new IllegalArgumentException(
					"Latitude value is not valid. (infinite)");
		}
	}

	/**
	 * Assert if longitude is valid
	 * 
	 * @methodtype assertion
	 * @methodproperties primitive class
	 * 
	 * @param lon
	 */
	protected static void assertIsValidLongitude(double lon) {
		if (lon > MAX_LONGITUDE || lon < MIN_LONGITUDE) {
			throw new IllegalArgumentException(
					"Longitude value is not valid. Range:[-180,180]");
		} else if (Double.isNaN(lon)) {
			throw new IllegalArgumentException(
					"Longitude value is not valid. (NaN)");
		} else if (Double.isInfinite(lon)) {
			throw new IllegalArgumentException(
					"Longitude value is not valid. (infinite)");
		}
	}

	/**
	 * Assert if radius is valid
	 * 
	 * @methodtype assertion
	 * @methodproperties primitive class
	 * 
	 * @param rad
	 */
	protected static void assertIsValidRadius(double rad) {
		if (rad < 0) {
			throw new IllegalArgumentException(
					"Radius value is not valid. Should be >= 0");
		} else if (Double.isNaN(rad)) {
			throw new IllegalArgumentException("Radius value is not valid. (NaN)");
		} else if (Double.isInfinite(rad)) {
			throw new IllegalArgumentException(
					"Radius value is not valid. (infinite)");
		}
	}
}
