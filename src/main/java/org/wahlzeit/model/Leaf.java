package org.wahlzeit.model;

import java.util.concurrent.atomic.AtomicInteger;

import org.wahlzeit.services.DataObject;
import org.wahlzeit.utils.Assertions;

public class Leaf extends DataObject{

	/**
	 * Leaf type 
	 */
	protected LeafType leafType;
	
	/**
	 * Leaf color (see Enum LeafColor)
	 */
	private LeafColor color;
	
	/**
	 * Leaf size (only non negative values)
	 */
	private double size;
	
	/**
	 * Location where leaf was found 
	 */
	private String location;
	
	/**
	 * ID counter for Leaf IDs
	 */
	private static AtomicInteger IDCounter = new AtomicInteger(0);

	/**
	 * Object id for manager 
	 */
	private final int id;
	
	/**
	 * Simple Constructor
	 */
	public Leaf(LeafType leafType) {
		Assertions.assertIsArgumentNotNull(leafType);
		this.leafType = leafType;
		
		// IDs start at 1
		this.id = IDCounter.incrementAndGet();
	}
	
	/**
	 * Constructor
	 */
	public Leaf(LeafType leafType, LeafColor color, double size, String location) {
		this(leafType);
		
		setColor(color);
		setSize(size);
		setLocation(location);
	}

	/**
	 * @methodtype get
	 * 
	 * @return
	 */
	public LeafType getLeafType() {
		return leafType;
	}

	/**
	 * @methodtype set
	 * 
	 * @param leafType
	 */
	public void setLeafType(LeafType leafType) {
		this.leafType = leafType;
	}

	/**
	 * @methodtype get
	 * 
	 * @return
	 */
	public LeafColor getColor() {
		return color;
	}

	/**
	 * @methodtype set
	 * 
	 * @param color
	 */
	public void setColor(LeafColor color) {
		this.color = color;
	}

	/**
	 * @methodtype get
	 * 
	 * @return
	 */
	public double getSize() {
		return size;
	}

	/**
	 * @methodtype set
	 * 
	 * @param color
	 */
	public void setSize(double size) {
		Assertions.assertIsNonNegativeValue(size);
		Assertions.assertIsNotInfinite(size);
		Assertions.assertIsNotNaN(size);
		this.size = size;
	}

	/**
	 * @methodtype get
	 * 
	 * @return
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @methodtype set
	 * 
	 * @param color
	 */
	public void setLocation(String location) {
		Assertions.assertIsArgumentNotNull(location);
		this.location = location;
	}
	/**
	 * @methodtype get
	 * 
	 * @return
	 */
	public int getID() {
		return id;
	}
}
