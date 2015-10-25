package org.wahlzeit.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * All test cases of the class {@link Coordinate}.
 */
public class CoordinateTest {
	
	// (0,0)
	private Coordinate minCoordinate;

	// (90, 180)
	private Coordinate midCoordinate;

	// (180, 359)
	private Coordinate maxCoordinate;
	
	@Before
	public void initCoordinate() {
		minCoordinate = new Coordinate(Coordinate.ZEROVALUE, Coordinate.ZEROVALUE);
		midCoordinate = new Coordinate(Coordinate.MIDLATITUDE, Coordinate.MIDLONGITUDE);
		maxCoordinate = new Coordinate(Coordinate.MAXLATITUDE, Coordinate.MAXLONGITUDE);
	}
	
	@Test
	public void testConstructor() {
		assertNotNull(minCoordinate);
		assertNotNull(midCoordinate);
		assertNotNull(maxCoordinate);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void negativeLatitudeShouldThrowException(){
		new Coordinate(Coordinate.ZEROVALUE-1,Coordinate.ZEROVALUE);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void negativeLongitudeShouldThrowException(){
		new Coordinate(Coordinate.ZEROVALUE,Coordinate.ZEROVALUE-1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void largeLatitudeShouldThrowException(){
		new Coordinate(Coordinate.MAXLATITUDE+1,Coordinate.ZEROVALUE);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void largeLongitudeShouldThrowException(){
		new Coordinate(Coordinate.ZEROVALUE,Coordinate.MAXLONGITUDE+1);
	}
	
	@Test
	public void testGetLongitudinalDistance(){
		// distance to same coordinate should be 0
		assertTrue(minCoordinate.getLongitudinalDistance(minCoordinate) == Coordinate.ZEROVALUE);
		assertTrue(midCoordinate.getLongitudinalDistance(midCoordinate) == Coordinate.ZEROVALUE);
		assertTrue(maxCoordinate.getLongitudinalDistance(maxCoordinate) == Coordinate.ZEROVALUE);
		
		// distance larger then 180 should map to distance less then 180
		assertTrue(minCoordinate.getLongitudinalDistance(maxCoordinate) == Coordinate.ZEROVALUE+1);
		
		// commutative property
		assertTrue(maxCoordinate.getLongitudinalDistance(minCoordinate) == minCoordinate.getLongitudinalDistance(maxCoordinate));
		
		// mid distance
		assertTrue(minCoordinate.getLongitudinalDistance(midCoordinate) == Coordinate.MIDLONGITUDE);
		assertTrue(midCoordinate.getLongitudinalDistance(minCoordinate) == Coordinate.MIDLONGITUDE);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nullLongitudeShouldThrowException(){
		midCoordinate.getLongitudinalDistance(null);
	}
	
	
	@Test
	public void testGetLatitudinalDistance(){
		// distance to same coordinate should be 0
		assertTrue(minCoordinate.getLatitudinalDistance(minCoordinate) == Coordinate.ZEROVALUE);
		assertTrue(midCoordinate.getLatitudinalDistance(midCoordinate) == Coordinate.ZEROVALUE);
		assertTrue(maxCoordinate.getLatitudinalDistance(maxCoordinate) == Coordinate.ZEROVALUE);
		
		// max distance and commutative property
		assertTrue(minCoordinate.getLatitudinalDistance(maxCoordinate) == Coordinate.MAXLATITUDE);
		assertTrue(maxCoordinate.getLatitudinalDistance(minCoordinate) == minCoordinate.getLatitudinalDistance(maxCoordinate));
		
		// mid distance
		assertTrue(minCoordinate.getLatitudinalDistance(midCoordinate) == Coordinate.MIDLATITUDE);
		assertTrue(midCoordinate.getLatitudinalDistance(minCoordinate) == Coordinate.MIDLATITUDE);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nullLatitudeShouldThrowException(){
		midCoordinate.getLatitudinalDistance(null);
	}
	
	@Test
	public void testGetDistance(){
		// distance to same coordinate should be 0
		assertEquals(minCoordinate.getDistance(minCoordinate), minCoordinate);
		assertEquals(midCoordinate.getDistance(midCoordinate), minCoordinate);
		assertEquals(maxCoordinate.getDistance(maxCoordinate), minCoordinate);
		
		// max distance and commutative property
		assertEquals(minCoordinate.getDistance(maxCoordinate), new Coordinate(Coordinate.MAXLATITUDE, Coordinate.ZEROVALUE+1));
		assertEquals(maxCoordinate.getDistance(minCoordinate), minCoordinate.getDistance(maxCoordinate));
		
		// mid distance
		assertEquals(minCoordinate.getDistance(midCoordinate), midCoordinate);
		assertEquals(midCoordinate.getDistance(minCoordinate), midCoordinate);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nullDistanceShouldThrowException(){
		midCoordinate.getDistance(null);
	}
}