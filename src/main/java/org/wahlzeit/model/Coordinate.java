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
	 * Returns latitude of Coordinate object
	 * 
	 * @methodtype get
	 * 
	 * @return
	 */
	public double getLatitude();

	/**
	 * Returns longitude of Coordinate object
	 * 
	 * @methodtype get
	 * 
	 * @return
	 */
	public double getLongitude();

	/**
	 * Returns radius of Coordinate object
	 * 
	 * @methodtype get
	 * 
	 * @return
	 */
	public double getRadius();
}