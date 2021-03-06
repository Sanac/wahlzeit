package org.wahlzeit.model;

import org.wahlzeit.utils.Assertions;

import com.googlecode.objectify.annotation.Subclass;

/**
 * Class for Photos of the domain Leafs
 */
@Subclass(index = true)
public class LeafPhoto extends Photo {

	/**
	 * Leaf attribute
	 */
	private Leaf leaf;

	/**
	 * Default constructor
	 */
	public LeafPhoto() {
		super();
	}

	public LeafPhoto(PhotoId myId) {
		super(myId);
	}

	public LeafPhoto(Leaf leaf) {
		super();
		this.leaf = leaf;
	}

	/**
	 * Getter for leaf
	 * 
	 * @methodtype get
	 * @return
	 */
	public Leaf getLeaf() {
		return leaf;
	}
	
	/**
	 * Setter for leaf
	 * 
	 * @methodtype set
	 * 
	 * @param leaf
	 */
	public void setLeaf(Leaf leaf) {
		Assertions.assertIsArgumentNotNull(leaf);
		this.leaf = leaf;
	}
}
