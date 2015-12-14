package org.wahlzeit.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
	
	private Coordinate tokyoSpheric;
	private Coordinate tokyoCartesian;
	
	private Coordinate berlinSpheric;
	private Coordinate berlinCartesian;
	
	private Coordinate newYorkSpheric;
	private Coordinate newYorkCartesian;
	
	private Coordinate sydneySpheric;
	private Coordinate sydneyCartesian;

	@Before
	public void initCoordinate() {
		minCoordinateSpheric = SphericCoordinate.getSphericCoordinate(
				SphericCoordinate.MIN_LATITUDE,
				SphericCoordinate.MIN_LONGITUDE,
				SphericCoordinate.ZERO_VALUE + 1);
		midCoordinateSpheric = SphericCoordinate.getSphericCoordinate(
				SphericCoordinate.ZERO_VALUE, SphericCoordinate.ZERO_VALUE,
				SphericCoordinate.HALF_CIRCLE_VALUE);
		maxCoordinateSpheric = SphericCoordinate.getSphericCoordinate(
				SphericCoordinate.MAX_LATITUDE - 1.0,
				SphericCoordinate.MAX_LONGITUDE - 1.0,
				SphericCoordinate.CIRCLE_VALUE);
		minCoordinateCartesian = CartesianCoordinate
				.asCartesianCoordinate(minCoordinateSpheric);
		midCoordinateCartesian = CartesianCoordinate
				.asCartesianCoordinate(midCoordinateSpheric);
		maxCoordinateCartesian = CartesianCoordinate
				.asCartesianCoordinate(maxCoordinateSpheric);
		tokyoSpheric = SphericCoordinate.getSphericCoordinate(35.69, 139.69, AbstractCoordinate.EARTH_RADIUS_KM);
		tokyoCartesian = CartesianCoordinate.asCartesianCoordinate(tokyoSpheric);
		berlinSpheric = SphericCoordinate.getSphericCoordinate(52.52, 13.40, AbstractCoordinate.EARTH_RADIUS_KM);
		berlinCartesian = CartesianCoordinate.asCartesianCoordinate(berlinSpheric);
		newYorkSpheric = SphericCoordinate.getSphericCoordinate(40.71, -74.01, AbstractCoordinate.EARTH_RADIUS_KM);
		newYorkCartesian = CartesianCoordinate.asCartesianCoordinate(newYorkSpheric);
		sydneySpheric = SphericCoordinate.getSphericCoordinate(-33.87, 151.21, AbstractCoordinate.EARTH_RADIUS_KM);
		sydneyCartesian = CartesianCoordinate.asCartesianCoordinate(sydneySpheric);
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
		
		// distances of real cities
		assertEquals(tokyoSpheric.getDistance(berlinCartesian), 8926.0, EPSILON);
		assertEquals(berlinSpheric.getDistance(tokyoCartesian), 8926.0, EPSILON);
		
		assertEquals(berlinCartesian.getDistance(newYorkSpheric), 6392.0, EPSILON);
		assertEquals(newYorkCartesian.getDistance(berlinSpheric), 6392.0, EPSILON);
		
		assertEquals(newYorkSpheric.getDistance(tokyoCartesian), 10861.0, EPSILON);
		assertEquals(tokyoSpheric.getDistance(newYorkCartesian), 10861.0, EPSILON);
		
		assertEquals(tokyoCartesian.getDistance(sydneySpheric), 7835.0, EPSILON);
		assertEquals(sydneyCartesian.getDistance(tokyoSpheric), 7835.0, EPSILON);
		
		assertEquals(berlinSpheric.getDistance(sydneyCartesian), 16112.0, EPSILON);
		assertEquals(sydneySpheric.getDistance(berlinCartesian), 16112.0, EPSILON);
		
		assertEquals(newYorkCartesian.getDistance(sydneySpheric), 16007.0, EPSILON);
		assertEquals(sydneyCartesian.getDistance(newYorkSpheric), 16007.0, EPSILON);
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