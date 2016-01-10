package org.wahlzeit.model;

import java.util.concurrent.ConcurrentHashMap;

import org.wahlzeit.utils.Assertions;

public class LeafType {

	/**
	 * Map for all LeafType-objects (shared objects)
	 */
	private static ConcurrentHashMap<String, LeafType> allLeafTypes = new ConcurrentHashMap<>();

	/**
	 * Common plant name (primary key for HashMap)
	 */
	private final String commonName;

	/**
	 * Scientific name (can be changed)
	 */
	private String scientificName;

	/**
	 * Mean size of Leaf (can be changed)
	 */
	private double meanSize;

	/**
	 * Native location/origin of plant (can be changed)
	 */
	private String origin;

	/**
	 * Private Constructor
	 * 
	 * @param commonName
	 */
	private LeafType(String commonName) {
		Assertions.assertIsArgumentNotNull(commonName);
		this.commonName = commonName;
	}

	/**
	 * Factory method for Leaf
	 * 
	 * @methodtype factory
	 */
	public Leaf createInstance() {
		return new Leaf(this);
	}

	/**
	 * Factory method for Leaf
	 */
	public Leaf createInstance(LeafColor color, double size, String location) {
		return new Leaf(this, color, size, location);
	}

	/**
	 * Value type constructor
	 * 
	 * Assures shared objects
	 * 
	 * @methodtype factory/constructor
	 * 
	 * @return shared LeafType object
	 */
	public static LeafType getLeafType(String commonName) {
		LeafType result = allLeafTypes.get(commonName);
		if (result == null) {
			result = new LeafType(commonName);
			allLeafTypes.put(commonName, result);
		}
		return result;
	}

	/**
	 * @methodtype get
	 * 
	 * @return
	 */
	public String getScientificName() {
		return scientificName;
	}

	/**
	 * @methodtype set
	 * 
	 * @param scientificName
	 */
	public void setScientificName(String scientificName) {
		this.scientificName = scientificName;
	}

	/**
	 * @methodtype get
	 * 
	 * @return
	 */
	public double getMeanSize() {
		return meanSize;
	}

	/**
	 * @methodtype set
	 * 
	 * @param meanSize
	 */
	public void setMeanSize(double meanSize) {
		this.meanSize = meanSize;
	}

	/**
	 * @methodtype get
	 * 
	 * @return
	 */
	public String getOrigin() {
		return origin;
	}

	/**
	 * @methodtype set
	 * 
	 * @param origin
	 */
	public void setOrigin(String origin) {
		this.origin = origin;
	}

	/**
	 * @methodtype get
	 * 
	 * @return
	 */
	public String getCommonName() {
		return commonName;
	}
}
