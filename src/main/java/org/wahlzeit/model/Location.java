package org.wahlzeit.model;

/**
 * Location class
 * 
 * Saves location name and coordinates of location
 */
public class Location {
	private String name;
	private Coordinate coordinate;
	
	public Location(String name, Coordinate coordinate) {
		this.name = name;
		this.coordinate = coordinate;
	}
}
