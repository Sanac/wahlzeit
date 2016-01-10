package org.wahlzeit.model;

/**
 * Factory for creating LeafPhotos
 */
public class LeafPhotoFactory extends PhotoFactory{
	/**
	 * Creates LeafPhoto
	 * 
	 * @methodtype factory
	 * @methodproperties primitive instance convenience
	 */
	@Override
	public Photo createPhoto() {
		return new LeafPhoto();
	}
	
	/**
	 * Creates LeafPhoto with given id
	 * 
	 * @methodtype factory
	 * @methodproperties primitive instance convenience
	 */
	@Override
	public Photo createPhoto(PhotoId id) {
		return new LeafPhoto();
	}
	
	/**
	 * Creates LeafPhoto given leaf
	 * 
	 * @methodtype factory
	 * @methodproperties primitive instance convenience
	 */
	public Photo createPhoto(Leaf leaf) {
		return new LeafPhoto(leaf);
	}
}
