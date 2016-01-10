package org.wahlzeit.utils;

public class Assertions {

	/**
	 * Assert if argument is null
	 * 
	 * @methodtype assertion
	 * @methodproperties primitive class
	 * 
	 * @param c
	 */
	public static void assertIsArgumentNotNull(Object c) {
		if (c == null) {
			throw new IllegalArgumentException("Given Argument is null!");
		}
	}

	/**
	 * Assert if argument is non negative
	 * 
	 * @methodtype assertion
	 * @methodproperties primitive class
	 * 
	 * @param value
	 */
	public static void assertIsNonNegativeValue(double value) {
		if (value < 0) {
			throw new IllegalStateException("Value is negative!");
		}
	}

	/**
	 * Assert if argument is not NaN
	 * 
	 * @methodtype assertion
	 * @methodproperties primitive class
	 * 
	 * @param value
	 */
	public static void assertIsNotNaN(double value) {
		if (Double.isNaN(value)) {
			throw new IllegalStateException("Value is NaN!");
		}
	}

	/**
	 * Assert if argument is not infinite
	 * 
	 * @methodtype assertion
	 * @methodproperties primitive class
	 * 
	 * @param value
	 */
	public static void assertIsNotInfinite(double value) {
		if (Double.isInfinite(value)) {
			throw new IllegalStateException("Value is infinite!");
		}
	}
}
