package ch.fhnw.i4ds.helio.coordinate.sunpos;

import static org.junit.Assert.assertEquals;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Before;
import org.junit.Test;

public class NewcombSunPositionAlgoTest {

	NewcombSunPositionAlgo sunPositionAlgo;
	
	@Before
	public void setup() {
		sunPositionAlgo = new NewcombSunPositionAlgo();
	}
	
	@Test
	public void testSunPositionAlgo() {
		DateTime date = new DateTime(2013, 03, 27, 0, 0, DateTimeZone.UTC);
		SunPosition sunPosition = sunPositionAlgo.computeSunPos(date);
		
		assertEquals(6.4851737387786201, sunPosition.getLongitudeInDegree(), 1e-10);
		assertEquals(5.9522821787012701, sunPosition.getRa(), 1e-10);
		assertEquals(2.5738385484611932, sunPosition.getDec(), 1e-10);
		assertEquals(6.4830899654298086, sunPosition.getApparentLongitude(), 1e-10);
		assertEquals(23.435885888864924, sunPosition.getObliquity(), 1e-10);
	}
}
