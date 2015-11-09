package org.wahlzeit.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class CartesianCoordinateTest {
	// (-2,-8,-5)
	private CartesianCoordinate smallCoordinate;

	// (-1,1,-1)
	private CartesianCoordinate midCoordinate;

	// (2,8,5)
	private CartesianCoordinate largeCoordinate;

	private final static double EPSILON = 100.0;
	
	@Before
	public void initCoordinate() {
		smallCoordinate = new CartesianCoordinate(-2,-8,-5);
		midCoordinate = new CartesianCoordinate(-1, 1, -1);
		largeCoordinate = new CartesianCoordinate(2, 8, 5);
	}
	
	@Test
	public void testConstructor() {
		assertNotNull(smallCoordinate);
		assertNotNull(midCoordinate);
		assertNotNull(largeCoordinate);
	}
	
	@Test
	public void testGetDistance() {
		// distance to same coordinate should be 0
		assertEquals(smallCoordinate.getDistance(smallCoordinate),
				SphericCoordinate.ZERO_VALUE, EPSILON);
		assertEquals(midCoordinate.getDistance(midCoordinate),
				SphericCoordinate.ZERO_VALUE, EPSILON);
		assertEquals(largeCoordinate.getDistance(largeCoordinate),
				SphericCoordinate.ZERO_VALUE, EPSILON);

		// max distance and commutative property
		assertEquals(smallCoordinate.getDistance(largeCoordinate), 16500, EPSILON);
		assertEquals(largeCoordinate.getDistance(smallCoordinate),
				smallCoordinate.getDistance(largeCoordinate), EPSILON);

		// mid distance
		assertEquals(smallCoordinate.getDistance(midCoordinate), 450, EPSILON);
		assertEquals(midCoordinate.getDistance(largeCoordinate), 16050, EPSILON);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nullDistanceShouldThrowException() {
		midCoordinate.getDistance(null);
	}
	
	@Test
	public void testAsCartesianCoordinate() {
		assertEquals(smallCoordinate, CartesianCoordinate.asCartesianCoordinate(smallCoordinate));
		assertEquals(midCoordinate, CartesianCoordinate.asCartesianCoordinate(midCoordinate));
		assertEquals(largeCoordinate, CartesianCoordinate.asCartesianCoordinate(largeCoordinate));
	}
}
