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
	 * See Coordinate interface for documentation
	 * 
	 * @methodtype comparison
	 * @methodproperties template
	 */
	public double getDistance(Coordinate c) {
		assertIsArgumentNotNull(c);
		double[] thisSphericRepresentaion = this.asSphericRepresentation();
		double[] otherSphericRepresentation = c.asSphericRepresentation();

		double lat1 = Math.toRadians(thisSphericRepresentaion[0]);
		double lat2 = Math.toRadians(otherSphericRepresentation[0]);
		double deltaLong = Math.toRadians(Math.abs(thisSphericRepresentaion[1]
				- otherSphericRepresentation[1]));

		double deltaSigma = Math.acos(Math.sin(lat1) * Math.sin(lat2)
				+ Math.cos(lat1) * Math.cos(lat2) * Math.cos(deltaLong));
		return EARTH_RADIUS_KM * deltaSigma;
	}

	/**
	 * See Coordinate interface for documentation
	 * 
	 * @methodtype comparison
	 * @methodproperties template
	 */
	@Override
	public boolean isEqual(Coordinate c) {
		return equals(asOwnCoordinate(c));
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
	 * Assert if Argument is null
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
}
