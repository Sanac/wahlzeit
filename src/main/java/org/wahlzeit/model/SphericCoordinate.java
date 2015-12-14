package org.wahlzeit.model;

import java.util.HashMap;

/**
 * Spheric coordinate class
 * 
 * Saves latitude, longitude and radius
 */
public class SphericCoordinate extends AbstractCoordinate {

	/**
	 * Map for all SphericCoordinates (shared objects)
	 */
	private static HashMap<String, SphericCoordinate> allSphericCoordinates = new HashMap<>();
	
	/**
	 * Latitude in degrees
	 * 
	 * Valid range of values: -90 (south pole) to 90 (north pole)
	 */
	private final double latitude;

	/**
	 * Longitude in degrees
	 * 
	 * Valid range of values: -180 to 180
	 * 
	 * 0: prime meridian
	 */
	private final double longitude;

	/**
	 * Radius in km
	 * 
	 * Valid range of values: >= 0
	 */
	private final double radius;

	/**
	 * Value type constructor
	 * 
	 * Assures shared objects
	 * 
	 * @methodtype factory/constructor
	 * 
	 * @return shared SphericCoordinate object
	 */
	public static SphericCoordinate getSphericCoordinate(double latitude, double longitude, double radius) {
		String sphericString = String.valueOf(latitude) + " " + String.valueOf(longitude) + " " + String.valueOf(radius);
		SphericCoordinate result = allSphericCoordinates.get(sphericString);
		if(result == null) {
			synchronized(SphericCoordinate.class) {
				result = allSphericCoordinates.get(sphericString);
				if(result == null) {
					result = new SphericCoordinate(latitude, longitude, radius);
					allSphericCoordinates.put(sphericString, result);
				}
			}
		}
		return result;
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
		// precondition
		assertIsArgumentNotNull(c);

		SphericCoordinate result = SphericCoordinate.getSphericCoordinate(c.getLatitude(),
				c.getLongitude(), c.getRadius());

		// postcondition
		result.assertClassInvariants();

		return result;
	}
	
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
	private SphericCoordinate(double latitude, double longitude, double radius) {
		assertIsValidLatitude(latitude);
		assertIsValidLongitude(longitude);
		assertIsValidRadius(radius);
		this.latitude = latitude;
		this.longitude = longitude;
		this.radius = radius;
	}

	/**
	 * See Coordinate interface for documentation
	 */
	@Override
	public double getLatitude() {
		assertClassInvariants();
		return latitude;
	}

	/**
	 * See Coordinate interface for documentation
	 */
	@Override
	public double getLongitude() {
		assertClassInvariants();
		return longitude;
	}

	/**
	 * See Coordinate interface for documentation
	 */
	@Override
	public double getRadius() {
		assertClassInvariants();
		return radius;
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
		assertClassInvariants();

		// precondition
		assertIsArgumentNotNull(c);

		// if distance is larger then 180 return the shorter distance
		double distance = Math.abs(this.longitude - c.longitude);
		if (distance > HALF_CIRCLE_VALUE) {
			distance = CIRCLE_VALUE - distance;
		}

		// postcondition
		assertIsNonNegativeValue(distance);
		assertIsNotNaN(distance);
		assertIsNotInfinite(distance);

		assertClassInvariants();
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
		assertClassInvariants();

		// precondition
		assertIsArgumentNotNull(c);

		double distance = Math.abs(this.latitude - c.latitude);

		// postcondition
		assertIsNonNegativeValue(distance);
		assertIsNotNaN(distance);
		assertIsNotInfinite(distance);

		assertClassInvariants();
		return distance;
	}

	/**
	 * See AbstractCoordinate class for documentation
	 */
	@Override
	protected Coordinate asOwnCoordinate(Coordinate c) {
		return asSphericCoordinate(c);
	}

	@Override
	protected void assertClassInvariants() {
		assertIsValidLatitude(latitude);
		assertIsValidLongitude(longitude);
		assertIsValidRadius(radius);
	}

	/**
	 * hashCode-method
	 * 
	 * @methodtype conversion
	 * @methodproperties primitive instance
	 * 
	 */
	@Override
	public int hashCode() {
		return super.hashCode();
	}

	/**
	 * equals-method
	 * 
	 * @methodtype comparison
	 * @methodproperties primitive instance
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		return this == obj;
	}
}
