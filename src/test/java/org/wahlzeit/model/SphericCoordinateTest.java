package org.wahlzeit.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class SphericCoordinateTest {

	// (-90,-180, 1)
	private SphericCoordinate minCoordinate;

	// (0, 0, 180)
	private SphericCoordinate midCoordinate;

	// (89, 179, 360)
	private SphericCoordinate maxCoordinate;

	private final static double EPSILON = 100.0;

	@Before
	public void initCoordinate() {
		minCoordinate = SphericCoordinate.getSphericCoordinate(SphericCoordinate.MIN_LATITUDE,
				SphericCoordinate.MIN_LONGITUDE,
				SphericCoordinate.ZERO_VALUE + 1);
		midCoordinate = SphericCoordinate.getSphericCoordinate(SphericCoordinate.ZERO_VALUE,
				SphericCoordinate.ZERO_VALUE,
				SphericCoordinate.HALF_CIRCLE_VALUE);
		maxCoordinate = SphericCoordinate.getSphericCoordinate(
				SphericCoordinate.MAX_LATITUDE - 1.0,
				SphericCoordinate.MAX_LONGITUDE - 1.0,
				SphericCoordinate.CIRCLE_VALUE);
	}

	@Test
	public void testConstructor() {
		assertNotNull(minCoordinate);
		assertNotNull(midCoordinate);
		assertNotNull(maxCoordinate);
	}

	@Test(expected = IllegalArgumentException.class)
	public void tooSmallLatitudeShouldThrowException() {
		SphericCoordinate.getSphericCoordinate(SphericCoordinate.MIN_LATITUDE - 1,
				SphericCoordinate.ZERO_VALUE, SphericCoordinate.ZERO_VALUE);
	}

	@Test(expected = IllegalArgumentException.class)
	public void tooSmallLongitudeShouldThrowException() {
		SphericCoordinate.getSphericCoordinate(SphericCoordinate.ZERO_VALUE,
				SphericCoordinate.MIN_LONGITUDE - 1,
				SphericCoordinate.ZERO_VALUE);
	}

	@Test(expected = IllegalArgumentException.class)
	public void tooLargeLatitudeShouldThrowException() {
		SphericCoordinate.getSphericCoordinate(SphericCoordinate.MAX_LATITUDE + 1,
				SphericCoordinate.ZERO_VALUE, SphericCoordinate.ZERO_VALUE);
	}

	@Test(expected = IllegalArgumentException.class)
	public void tooLargeLongitudeShouldThrowException() {
		SphericCoordinate.getSphericCoordinate(SphericCoordinate.ZERO_VALUE,
				SphericCoordinate.MAX_LONGITUDE + 1,
				SphericCoordinate.ZERO_VALUE);
	}

	@Test(expected = IllegalArgumentException.class)
	public void negativeRadiusShouldThrowException() {
		SphericCoordinate.getSphericCoordinate(SphericCoordinate.ZERO_VALUE,
				SphericCoordinate.ZERO_VALUE, SphericCoordinate.ZERO_VALUE - 1);
	}

	@Test
	public void testGetLongitudinalDistance() {
		// distance to same coordinate should be 0
		assertTrue(minCoordinate.getLongitudinalDistance(minCoordinate) == SphericCoordinate.ZERO_VALUE);
		assertTrue(midCoordinate.getLongitudinalDistance(midCoordinate) == SphericCoordinate.ZERO_VALUE);
		assertTrue(maxCoordinate.getLongitudinalDistance(maxCoordinate) == SphericCoordinate.ZERO_VALUE);

		// distance larger then 180 should map to distance less then 180
		assertTrue(minCoordinate.getLongitudinalDistance(maxCoordinate) == SphericCoordinate.ZERO_VALUE + 1);

		// commutative property
		assertTrue(maxCoordinate.getLongitudinalDistance(minCoordinate) == minCoordinate
				.getLongitudinalDistance(maxCoordinate));

		// mid distance
		assertTrue(minCoordinate.getLongitudinalDistance(midCoordinate) == SphericCoordinate.HALF_CIRCLE_VALUE);
		assertTrue(midCoordinate.getLongitudinalDistance(maxCoordinate) == SphericCoordinate.HALF_CIRCLE_VALUE - 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nullLongitudeShouldThrowException() {
		midCoordinate.getLongitudinalDistance(null);
	}

	@Test
	public void testGetLatitudinalDistance() {
		// distance to same coordinate should be 0
		assertTrue(minCoordinate.getLatitudinalDistance(minCoordinate) == SphericCoordinate.ZERO_VALUE);
		assertTrue(midCoordinate.getLatitudinalDistance(midCoordinate) == SphericCoordinate.ZERO_VALUE);
		assertTrue(maxCoordinate.getLatitudinalDistance(maxCoordinate) == SphericCoordinate.ZERO_VALUE);

		// max distance and commutative property
		assertTrue(minCoordinate.getLatitudinalDistance(maxCoordinate) == SphericCoordinate.HALF_CIRCLE_VALUE - 1);
		assertTrue(maxCoordinate.getLatitudinalDistance(minCoordinate) == minCoordinate
				.getLatitudinalDistance(maxCoordinate));

		// mid distance
		assertTrue(minCoordinate.getLatitudinalDistance(midCoordinate) == SphericCoordinate.QUARTER_CIRCLE_VALUE);
		assertTrue(midCoordinate.getLatitudinalDistance(maxCoordinate) == SphericCoordinate.QUARTER_CIRCLE_VALUE - 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nullLatitudeShouldThrowException() {
		midCoordinate.getLatitudinalDistance(null);
	}

	@Test
	public void testGetDistance() {
		// distance to same coordinate should be 0
		assertEquals(minCoordinate.getDistance(minCoordinate),
				SphericCoordinate.ZERO_VALUE, EPSILON);
		assertEquals(midCoordinate.getDistance(midCoordinate),
				SphericCoordinate.ZERO_VALUE, EPSILON);
		assertEquals(maxCoordinate.getDistance(maxCoordinate),
				SphericCoordinate.ZERO_VALUE, EPSILON);

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
	
	@Test
	public void testAsSphericCoordinate() {
		assertEquals(minCoordinate, SphericCoordinate.asSphericCoordinate(minCoordinate));
		assertEquals(midCoordinate, SphericCoordinate.asSphericCoordinate(midCoordinate));
		assertEquals(maxCoordinate, SphericCoordinate.asSphericCoordinate(maxCoordinate));
	}
}
