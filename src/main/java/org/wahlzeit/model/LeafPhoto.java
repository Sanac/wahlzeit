package org.wahlzeit.model;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.annotation.Subclass;


/**
 * Class for Photos of the domain Leafs
 */
@Subclass(index=true)
public class LeafPhoto extends Photo{
	
	/**
	 * Leaf color (see Enum Leafcolor)
	 */
	private LeafColor color;
	
	/**
	 * Length/size of leaf
	 */
	private double size;
	
	/**
	 * Name of the plant/tree of the leaf
	 */
	private String plant;
	
	public LeafPhoto() {
		super();
	}
	
	public LeafPhoto(PhotoId myId) {
		super(myId);
	}
	
	public LeafPhoto(LeafColor color, double size, String plant) {
		super();
		this.color = color;
		this.size = size;
		this.plant = plant;
	}
}
