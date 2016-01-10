package org.wahlzeit.model;

import java.util.concurrent.ConcurrentHashMap;

import org.wahlzeit.utils.Assertions;

/**
 * Cartesian coordinate class
 * 
 * Saves latitude and longitude coordinates
 */
public class CartesianCoordinate extends AbstractCoordinate {

	/**
	 * Map for all CartesianCoordinates (shared objects)
	 */
	private static ConcurrentHashMap<String, CartesianCoordinate> allCartesianCoordinates = new ConcurrentHashMap<>();
	
	/**
	 * x coordinate
	 * 
	 * all double values are allowed
	 */
	private final double x;

	/**
	 * y coordinate
	 * 
	 * all double values are allowed
	 */
	private final double y;

	/**
	 * z coordinate
	 * 
	 * all double values are allowed
	 */
	private final double z;

	/**
	 * Value type constructor
	 * 
	 * Assures shared objects
	 * 
	 * @methodtype factory/constructor
	 * 
	 * @return shared CartesianCoordinate object
	 */
	public static CartesianCoordinate getCartesianCoordinate(double x, double y, double z) {
		String cartesianString = String.valueOf(x) + " " + String.valueOf(y) + " " + String.valueOf(z);
		CartesianCoordinate result = allCartesianCoordinates.get(cartesianString);
		if(result == null) {
			synchronized(CartesianCoordinate.class) {
				result = allCartesianCoordinates.get(cartesianString);
				if(result == null) {
					result = new CartesianCoordinate(x, y, z);
					allCartesianCoordinates.put(cartesianString, result);
				}
			}
		}
		return result;
	}
	
	/**
	 * Convert Coordinate object into CartesianCoordinate
	 * 
	 * @methodtype conversion
	 * @methodproperties composed class
	 * 
	 * @param c
	 * @return
	 */
	public static CartesianCoordinate asCartesianCoordinate(Coordinate c) {
		// precondition
		Assertions.assertIsArgumentNotNull(c);

		double latitude = c.getLatitude();
		double longitude = c.getLongitude();
		double radius = c.getRadius();

		// normalized to valid values: [0,180]
		double normalizedLat = latitude >= ZERO_VALUE ? latitude
				: HALF_CIRCLE_VALUE + latitude;

		double x = radius * Math.sin(Math.toRadians(normalizedLat))
				* Math.cos(Math.toRadians(longitude));
		double y = radius * Math.sin(Math.toRadians(normalizedLat))
				* Math.sin(Math.toRadians(longitude));
		double z = radius * Math.cos(Math.toRadians(normalizedLat));

		CartesianCoordinate result = CartesianCoordinate.getCartesianCoordinate(x, y, z);

		// postcondition
		result.assertClassInvariants();

		return result;
	}
	
	/**
	 * Constructor of CartesianCoordinate
	 * 
	 * @param x
	 * @param y
	 * @param z
	 */
	private CartesianCoordinate(double x, double y, double z) {
		Assertions.assertIsNotNaN(x);
		Assertions.assertIsNotNaN(y);
		Assertions.assertIsNotNaN(z);
		Assertions.assertIsNotInfinite(x);
		Assertions.assertIsNotInfinite(y);
		Assertions.assertIsNotInfinite(z);
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Getter for x
	 * 
	 * @methodtype get
	 * @methodproperties primitive
	 * 
	 * @return
	 */
	public double getX() {
		assertClassInvariants();
		return x;
	}

	/**
	 * Getter for y
	 * 
	 * @methodtype get
	 * @methodproperties primitive
	 * 
	 * @return
	 */
	public double getY() {
		assertClassInvariants();
		return y;
	}

	/**
	 * Getter for z
	 * 
	 * @methodtype get
	 * @methodproperties primitive
	 * 
	 * @return
	 */
	public double getZ() {
		assertClassInvariants();
		return z;
	}

	/**
	 * See Coordinate interface for documentation
	 */
	@Override
	public double getLatitude() {
		assertClassInvariants();

		double radius = Math.sqrt(x * x + y * y + z * z);
		double latitude = Math.acos(z / radius);

		// denomalized: latitude to valid values (-90,90]
		double degreesLatitude = Math.toDegrees(latitude);
		double denormalizedLat = degreesLatitude < QUARTER_CIRCLE_VALUE ? degreesLatitude
				: -HALF_CIRCLE_VALUE + degreesLatitude;

		// postcondition
		assertIsValidLatitude(denormalizedLat);

		assertClassInvariants();
		return denormalizedLat;
	}

	/**
	 * See Coordinate interface for documentation
	 */
	@Override
	public double getLongitude() {
		assertClassInvariants();
		double longitude = Math.atan2(y, x);
		longitude = Math.toDegrees(longitude);

		// postcondition
		assertIsValidLongitude(longitude);

		assertClassInvariants();
		return longitude;
	}

	/**
	 * See Coordinate interface for documentation
	 */
	@Override
	public double getRadius() {
		assertClassInvariants();

		double radius = Math.sqrt(x * x + y * y + z * z);

		// postcondition
		assertIsValidRadius(radius);

		assertClassInvariants();
		return radius;
	}

	/**
	 * See AbstractCoordinate class for documentation
	 */
	@Override
	protected Coordinate asOwnCoordinate(Coordinate c) {
		return asCartesianCoordinate(c);
	}

	/**
	 * Assert if attributes are valid
	 * 
	 * @methodtype assertion
	 */
	@Override
	protected void assertClassInvariants() {
		Assertions.assertIsNotNaN(x);
		Assertions.assertIsNotNaN(y);
		Assertions.assertIsNotNaN(z);
		Assertions.assertIsNotInfinite(x);
		Assertions.assertIsNotInfinite(y);
		Assertions.assertIsNotInfinite(z);
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
