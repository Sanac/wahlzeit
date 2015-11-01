package org.wahlzeit.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * All test cases of the class {@link Coordinate}.
 */
public class CoordinateTest {

	// (-90,-180)
	private Coordinate minCoordinate;

	// (0, 0)
	private Coordinate midCoordinate;

	// (89, 179)
	private Coordinate maxCoordinate;

	private final static double EPSILON = 100.0;

	@Before
	public void initCoordinate() {
		minCoordinate = new Coordinate(Coordinate.MIN_LATITUDE,
				Coordinate.MIN_LONGITUDE);
		midCoordinate = new Coordinate(Coordinate.ZERO_VALUE,
				Coordinate.ZERO_VALUE);
		maxCoordinate = new Coordinate(Coordinate.MAX_LATITUDE - 1.0,
				Coordinate.MAX_LONGITUDE - 1.0);
	}

	@Test
	public void testConstructor() {
		assertNotNull(minCoordinate);
		assertNotNull(midCoordinate);
		assertNotNull(maxCoordinate);
	}

	@Test(expected = IllegalArgumentException.class)
	public void tooSmallLatitudeShouldThrowException() {
		new Coordinate(Coordinate.MIN_LATITUDE - 1, Coordinate.ZERO_VALUE);
	}

	@Test(expected = IllegalArgumentException.class)
	public void tooSmallLongitudeShouldThrowException() {
		new Coordinate(Coordinate.ZERO_VALUE, Coordinate.MIN_LONGITUDE - 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void tooLargeLatitudeShouldThrowException() {
		new Coordinate(Coordinate.MAX_LATITUDE + 1, Coordinate.ZERO_VALUE);
	}

	@Test(expected = IllegalArgumentException.class)
	public void largeLongitudeShouldThrowException() {
		new Coordinate(Coordinate.ZERO_VALUE, Coordinate.MAX_LONGITUDE + 1);
	}

	@Test
	public void testGetLongitudinalDistance() {
		// distance to same coordinate should be 0
		assertTrue(minCoordinate.getLongitudinalDistance(minCoordinate) == Coordinate.ZERO_VALUE);
		assertTrue(midCoordinate.getLongitudinalDistance(midCoordinate) == Coordinate.ZERO_VALUE);
		assertTrue(maxCoordinate.getLongitudinalDistance(maxCoordinate) == Coordinate.ZERO_VALUE);

		// distance larger then 180 should map to distance less then 180
		assertTrue(minCoordinate.getLongitudinalDistance(maxCoordinate) == Coordinate.ZERO_VALUE + 1);

		// commutative property
		assertTrue(maxCoordinate.getLongitudinalDistance(minCoordinate) == minCoordinate
				.getLongitudinalDistance(maxCoordinate));

		// mid distance
		assertTrue(minCoordinate.getLongitudinalDistance(midCoordinate) == Coordinate.HALF_CIRCLE_VALUE);
		assertTrue(midCoordinate.getLongitudinalDistance(maxCoordinate) == Coordinate.HALF_CIRCLE_VALUE - 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nullLongitudeShouldThrowException() {
		midCoordinate.getLongitudinalDistance(null);
	}

	@Test
	public void testGetLatitudinalDistance() {
		// distance to same coordinate should be 0
		assertTrue(minCoordinate.getLatitudinalDistance(minCoordinate) == Coordinate.ZERO_VALUE);
		assertTrue(midCoordinate.getLatitudinalDistance(midCoordinate) == Coordinate.ZERO_VALUE);
		assertTrue(maxCoordinate.getLatitudinalDistance(maxCoordinate) == Coordinate.ZERO_VALUE);

		// max distance and commutative property
		assertTrue(minCoordinate.getLatitudinalDistance(maxCoordinate) == Coordinate.HALF_CIRCLE_VALUE - 1);
		assertTrue(maxCoordinate.getLatitudinalDistance(minCoordinate) == minCoordinate
				.getLatitudinalDistance(maxCoordinate));

		// mid distance
		assertTrue(minCoordinate.getLatitudinalDistance(midCoordinate) == Coordinate.QUARTER_CIRCLE_VALUE);
		assertTrue(midCoordinate.getLatitudinalDistance(maxCoordinate) == Coordinate.QUARTER_CIRCLE_VALUE - 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nullLatitudeShouldThrowException() {
		midCoordinate.getLatitudinalDistance(null);
	}

	@Test
	public void testGetDistance() {
		// distance to same coordinate should be 0
		assertEquals(minCoordinate.getDistance(minCoordinate),
				Coordinate.ZERO_VALUE, EPSILON);
		assertEquals(midCoordinate.getDistance(midCoordinate),
				Coordinate.ZERO_VALUE, EPSILON);
		assertEquals(maxCoordinate.getDistance(maxCoordinate),
				Coordinate.ZERO_VALUE, EPSILON);

		// max distance and commutative property
		assertEquals(minCoordinate.getDistance(maxCoordinate), 20000.0, EPSILON);
		assertEquals(maxCoordinate.getDistance(minCoordinate),
				minCoordinate.getDistance(maxCoordinate), EPSILON);

		// mid distance
		assertEquals(minCoordinate.getDistance(midCoordinate), 10000.0, EPSILON);
		assertEquals(midCoordinate.getDistance(maxCoordinate), 10100.0, EPSILON);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nullDistanceShouldThrowException() {
		midCoordinate.getDistance(null);
	}
}