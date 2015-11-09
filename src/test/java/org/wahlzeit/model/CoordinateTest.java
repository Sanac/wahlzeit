package org.wahlzeit.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

/**
 * All test cases of the class {@link Coordinate}.
 */
public class CoordinateTest {

	// (-90,-180, 1)
	private Coordinate minCoordinateSpheric;
	private Coordinate minCoordinateCartesian;

	// (0, 0, 180)
	private Coordinate midCoordinateSpheric;
	private Coordinate midCoordinateCartesian;

	// (89, 179, 360)
	private Coordinate maxCoordinateSpheric;
	private Coordinate maxCoordinateCartesian;

	private final static double EPSILON = 100.0;

	@Before
	public void initCoordinate() {
		minCoordinateSpheric = new SphericCoordinate(
				SphericCoordinate.MIN_LATITUDE,
				SphericCoordinate.MIN_LONGITUDE,
				SphericCoordinate.ZERO_VALUE + 1);
		midCoordinateSpheric = new SphericCoordinate(
				SphericCoordinate.ZERO_VALUE, SphericCoordinate.ZERO_VALUE,
				SphericCoordinate.HALF_CIRCLE_VALUE);
		maxCoordinateSpheric = new SphericCoordinate(
				SphericCoordinate.MAX_LATITUDE - 1.0,
				SphericCoordinate.MAX_LONGITUDE - 1.0,
				SphericCoordinate.CIRCLE_VALUE);
		minCoordinateCartesian = CartesianCoordinate
				.asCartesianCoordinate(minCoordinateSpheric);
		midCoordinateCartesian = CartesianCoordinate
				.asCartesianCoordinate(midCoordinateSpheric);
		maxCoordinateCartesian = CartesianCoordinate
				.asCartesianCoordinate(maxCoordinateSpheric);
	}

	@Test
	public void testAsSphericRepresentation() {
		assertTrue(Arrays.equals(
				minCoordinateSpheric.asSphericRepresentation(),
				minCoordinateCartesian.asSphericRepresentation()));
		assertTrue(Arrays.equals(
				midCoordinateSpheric.asSphericRepresentation(),
				midCoordinateCartesian.asSphericRepresentation()));
		assertTrue(Arrays.equals(
				maxCoordinateSpheric.asSphericRepresentation(),
				maxCoordinateCartesian.asSphericRepresentation()));
	}

	@Test
	public void testAsCartesianRepresentation() {
		assertTrue(Arrays.equals(
				minCoordinateSpheric.asCartesianRepresentation(),
				minCoordinateCartesian.asCartesianRepresentation()));
		assertTrue(Arrays.equals(
				midCoordinateSpheric.asCartesianRepresentation(),
				midCoordinateCartesian.asCartesianRepresentation()));
		assertTrue(Arrays.equals(
				maxCoordinateSpheric.asCartesianRepresentation(),
				maxCoordinateCartesian.asCartesianRepresentation()));
	}

	@Test
	public void testGetDistance() {
		// distance to same coordinate should be 0
		assertEquals(minCoordinateSpheric.getDistance(minCoordinateSpheric),
				SphericCoordinate.ZERO_VALUE, EPSILON);
		assertEquals(midCoordinateSpheric.getDistance(midCoordinateSpheric),
				SphericCoordinate.ZERO_VALUE, EPSILON);
		assertEquals(maxCoordinateSpheric.getDistance(maxCoordinateSpheric),
				SphericCoordinate.ZERO_VALUE, EPSILON);

		// max distance and commutative property
		assertEquals(minCoordinateSpheric.getDistance(maxCoordinateSpheric),
				20000.0, EPSILON);
		assertEquals(maxCoordinateSpheric.getDistance(minCoordinateSpheric),
				minCoordinateSpheric.getDistance(maxCoordinateSpheric), EPSILON);

		// mid distance
		assertEquals(minCoordinateSpheric.getDistance(midCoordinateSpheric),
				10000.0, EPSILON);
		assertEquals(midCoordinateSpheric.getDistance(maxCoordinateSpheric),
				10100.0, EPSILON);

		// mixing spheric and cartesian
		assertEquals(minCoordinateSpheric.getDistance(maxCoordinateCartesian),
				20000.0, EPSILON);
		assertEquals(maxCoordinateCartesian.getDistance(minCoordinateSpheric),
				20000.0, EPSILON);
	}

	@Test
	public void testIsEqual() {
		assertTrue(minCoordinateCartesian.isEqual(minCoordinateSpheric));
		assertTrue(minCoordinateSpheric.isEqual(minCoordinateCartesian));

		assertTrue(midCoordinateCartesian.isEqual(midCoordinateSpheric));
		assertTrue(midCoordinateSpheric.isEqual(midCoordinateCartesian));

		assertTrue(maxCoordinateCartesian.isEqual(maxCoordinateSpheric));
		assertTrue(maxCoordinateSpheric.isEqual(maxCoordinateCartesian));
	}
}