package org.wahlzeit.model;

/**
 * Coordinate interface
 */
public interface Coordinate{
	/**
	 * Constants needed for calculations
	 */
	public static final double EARTH_RADIUS_KM = 6371.0;
	public static final double ZERO_VALUE = 0.0;
	public static final double CIRCLE_VALUE = 360.0;
	public static final double HALF_CIRCLE_VALUE = CIRCLE_VALUE / 2;
	public static final double QUARTER_CIRCLE_VALUE = CIRCLE_VALUE / 4;
	
	/**
	 * Calculate distance between two Coordinates
	 * 
	 * @methodtype get
	 * 
	 * @param c
	 * @return Distance in km
	 */
	public double getDistance(Coordinate c);
	
	/**
	 * Checks if given Coordinate can be transformed into the current instance
	 * 
	 * @methodtype comparison
	 * 
	 * @param c
	 * @return true if c is transformable to this
	 */
	public boolean isEqual(Coordinate c);
	
	/**
	 * Transform Object into a double array
	 * 
	 * Can be used as a spheric coordinate representation 
	 * 
	 * returnArray[0] := latitude
	 * returnArray[1] := longitude
	 * returnArray[2] := radius
	 * 
	 * @return
	 */
	public double[] asSphericRepresentation();
	
	/**
	 * Transform Object into a double array
	 * 
	 * Can be used as a cartesian coordinate representation 
	 * 
	 * returnArray[0] := x
	 * returnArray[1] := y
	 * returnArray[2] := z
	 * 
	 * @return
	 */
	public double[] asCartesianRepresentation();
}