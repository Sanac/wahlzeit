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

	private final static double EPSILON = 1.0;

	@Before
	public void initCoordinate() {
		smallCoordinate = CartesianCoordinate.getCartesianCoordinate(-2, -8, -5);
		midCoordinate = CartesianCoordinate.getCartesianCoordinate(-1, 1, -1);
		largeCoordinate = CartesianCoordinate.getCartesianCoordinate(2, 8, 5);
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
		assertEquals(smallCoordinate.getDistance(largeCoordinate), 20015,
				EPSILON);
		assertEquals(largeCoordinate.getDistance(smallCoordinate),
				smallCoordinate.getDistance(largeCoordinate), EPSILON);

		// mid distance
		assertEquals(smallCoordinate.getDistance(midCoordinate), 6341, EPSILON);
		assertEquals(midCoordinate.getDistance(largeCoordinate), 13673, EPSILON);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nullDistanceShouldThrowException() {
		midCoordinate.getDistance(null);
	}

	@Test
	public void testAsCartesianCoordinate() {
		assertEquals(smallCoordinate.getX(),
				CartesianCoordinate.asCartesianCoordinate(smallCoordinate).getX(), EPSILON);
		assertEquals(smallCoordinate.getY(),
				CartesianCoordinate.asCartesianCoordinate(smallCoordinate).getY(), EPSILON);
		assertEquals(smallCoordinate.getZ(),
				CartesianCoordinate.asCartesianCoordinate(smallCoordinate).getZ(), EPSILON);
		assertEquals(midCoordinate.getX(),
				CartesianCoordinate.asCartesianCoordinate(midCoordinate).getX(), EPSILON);
		assertEquals(midCoordinate.getY(),
				CartesianCoordinate.asCartesianCoordinate(midCoordinate).getY(), EPSILON);
		assertEquals(midCoordinate.getZ(),
				CartesianCoordinate.asCartesianCoordinate(midCoordinate).getZ(), EPSILON);
		assertEquals(largeCoordinate.getX(),
				CartesianCoordinate.asCartesianCoordinate(largeCoordinate).getX(), EPSILON);
		assertEquals(largeCoordinate.getY(),
				CartesianCoordinate.asCartesianCoordinate(largeCoordinate).getY(), EPSILON);
		assertEquals(largeCoordinate.getZ(),
				CartesianCoordinate.asCartesianCoordinate(largeCoordinate).getZ(), EPSILON);
	}
}
