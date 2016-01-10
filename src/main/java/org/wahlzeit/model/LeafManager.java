package org.wahlzeit.model;

import java.util.concurrent.ConcurrentHashMap;

import org.wahlzeit.utils.Assertions;

public class LeafManager {

	/**
	 * In-memory cache for Leafs
	 */
	private static ConcurrentHashMap<Integer, Leaf> leafs = new ConcurrentHashMap<>(); 
	
	/**
	 * Create new Leaf object
	 * 
	 * @methodtype factory
	 */
	public Leaf createLeaf(String commonName, LeafColor color, double size, String location){
		LeafType lt = LeafType.getLeafType(commonName);
		Assertions.assertIsArgumentNotNull(lt);
		Leaf result = lt.createInstance(color, size, location);
		leafs.put(result.getID(), result);
		
		return result;
	}
	
	/**
	 * Create new default Leaf
	 * 
	 * Uses default values for leafColor and size and location
	 * 
	 * @methodtype factory
	 */
	public Leaf createLeaf(String commonName){
		return createLeaf(commonName, LeafColor.GREEN, 0.0, "");
	}
	
	/**
	 * Getter for Leafs
	 * 
	 * @methodtype get
	 */
	
	public Leaf getLeaf(int id) {
		return leafs.get(new Integer(id));
	}
}