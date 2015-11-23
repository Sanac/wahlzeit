package org.wahlzeit.model;

/**
 * Cartesian coordinate class
 * 
 * Saves latitude and longitude coordinates
 */
public class CartesianCoordinate extends AbstractCoordinate {

	/**
	 * x coordinate
	 * 
	 * all double values are allowed
	 */
	private double x;

	/**
	 * y coordinate
	 * 
	 * all double values are allowed
	 */
	private double y;

	/**
	 * z coordinate
	 * 
	 * all double values are allowed
	 */
	private double z;

	/**
	 * Constructor of CartesianCoordinate
	 * 
	 * @param x
	 * @param y
	 * @param z
	 */
	public CartesianCoordinate(double x, double y, double z) {
		assertIsNotNaN(x);
		assertIsNotNaN(y);
		assertIsNotNaN(z);
		assertIsNotInfinite(x);
		assertIsNotInfinite(y);
		assertIsNotInfinite(z);
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
		assertIsNotNaN(x);
		assertIsNotNaN(y);
		assertIsNotNaN(z);
		assertIsNotInfinite(x);
		assertIsNotInfinite(y);
		assertIsNotInfinite(z);
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
		assertIsArgumentNotNull(c);

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

		CartesianCoordinate result = new CartesianCoordinate(x, y, z);

		// postcondition
		result.assertClassInvariants();

		return result;
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
		result = prime * result + (int) x;
		result = prime * result + (int) y;
		result = prime * result + (int) z;
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
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartesianCoordinate other = (CartesianCoordinate) obj;
		double delta = 0.001;
		if (Math.abs(x - other.x) > delta)
			return false;
		if (Math.abs(y - other.y) > delta)
			return false;
		if (Math.abs(z - other.z) > delta)
			return false;
		return true;
	}
}
