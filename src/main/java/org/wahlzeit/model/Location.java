package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Container;

/**
 * Location class
 * 
 * Saves location name and coordinates of location
 */
public class Location {
	private String name;
	@Container
	private Coordinate coordinate;
	
	public Location(String name, Coordinate coordinate) {
		this.name = name;
		this.coordinate = coordinate;
	}
}
