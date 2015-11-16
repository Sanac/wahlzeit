package org.wahlzeit.model;

/**
 * Coordinate interface
 */
public interface Coordinate {
	/**
	 * Calculate distance between two Coordinates
	 * 
	 * @methodtype comparison
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
	 * Can be used as a SphericCoordinate representation
	 * 
	 * returnArray[0] := latitude
	 * 
	 * returnArray[1] := longitude
	 * 
	 * returnArray[2] := radius
	 * 
	 * @return
	 */
	public double[] asSphericRepresentation();

	/**
	 * Transform Object into a double array
	 * 
	 * Can be used as a CartesianCoordinate representation
	 * 
	 * returnArray[0] := x
	 * 
	 * returnArray[1] := y
	 * 
	 * returnArray[2] := z
	 * 
	 * @return
	 */
	public double[] asCartesianRepresentation();
}