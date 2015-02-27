package ch.fhnw.i4ds.helio.coordinate.sundist;

import static org.junit.Assert.assertEquals;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Before;
import org.junit.Test;

public class Pb0rSunDistanceAlgoTest {

	private Pb0rSunDistanceAlgo sunDistanceAlgo;
	
	@Before 
	public void setup() {
		sunDistanceAlgo = new Pb0rSunDistanceAlgo();
	}
	
	@Test
	public void testSunDistanceAlgo() {
		DateTime date = getSampleSunPosition(2013, 03, 27);
		SunDistance sunDistance = sunDistanceAlgo.computeDistance(date);

		// verified with IDL
		// IDL> print,  pb0r('2013-03-27')
		assertEquals(-25.85716709011732, sunDistance.getP().degValue(), 1e-11);
		assertEquals(-6.787164229758466, sunDistance.getB0().degValue(), 1e-11);
		assertEquals(16.018312926110276, sunDistance.getSemiDiameter().arcminValue(), 1e-11);
		// sun distance is in AU.
		assertEquals(0.9977813876481756, sunDistance.getSunDistance(), 1e-11);
	}

	private DateTime getSampleSunPosition(int year, int month, int day) {
		DateTime date = new DateTime(year, month, day, 0, 0, DateTimeZone.UTC);
		return date;
	}
	
}
