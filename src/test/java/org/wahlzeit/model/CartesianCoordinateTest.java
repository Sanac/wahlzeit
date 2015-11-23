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
		smallCoordinate = new CartesianCoordinate(-2, -8, -5);
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
		Coordinate c1 = CartesianCoordinate
				.asCartesianCoordinate(smallCoordinate);
		Coordinate c2 = CartesianCoordinate
				.asCartesianCoordinate(midCoordinate);
		Coordinate c3 = CartesianCoordinate
				.asCartesianCoordinate(largeCoordinate);
		System.out.println("(" + smallCoordinate.getLatitude() + ", "
				+ smallCoordinate.getLongitude() + ", "
				+ smallCoordinate.getRadius() + ")" + " (" + c1.getLatitude()
				+ ", " + c1.getLongitude() + ", " + c1.getRadius() + ")");
		System.out.println("(" + midCoordinate.getLatitude() + ", "
				+ midCoordinate.getLongitude() + ", "
				+ midCoordinate.getRadius() + ")" + " (" + c2.getLatitude()
				+ ", " + c2.getLongitude() + ", " + c2.getRadius() + ")");
		System.out.println("(" + largeCoordinate.getLatitude() + ", "
				+ largeCoordinate.getLongitude() + ", "
				+ largeCoordinate.getRadius() + ")" + " (" + c3.getLatitude()
				+ ", " + c3.getLongitude() + ", " + c3.getRadius() + ")");
		assertEquals(smallCoordinate,
				CartesianCoordinate.asCartesianCoordinate(smallCoordinate));
		assertEquals(midCoordinate,
				CartesianCoordinate.asCartesianCoordinate(midCoordinate));
		assertEquals(largeCoordinate,
				CartesianCoordinate.asCartesianCoordinate(largeCoordinate));
	}
}
