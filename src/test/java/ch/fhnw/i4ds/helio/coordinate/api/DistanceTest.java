package ch.fhnw.i4ds.helio.coordinate.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ch.fhnw.i4ds.helio.coordinate.util.Constants;

public class DistanceTest {

	@Test
	public void test_fromMeters() {
		Distance dist = Distance.fromMeters(1000);
		assertEquals(1000, dist.inMeters(), 0);
	}

	@Test
	public void test_setMeters() {
		Distance dist = new Distance(10);
		assertEquals(10, dist.inMeters(), 0);
		dist.setMeters(1000);
		assertEquals(1000, dist.inMeters(), 0);
	}
	
	@Test
	public void test_setAU() {
		Distance dist = new Distance();
		dist.setAU(1.1);
		assertEquals(1.1, dist.inAU(), 0);
	}
	
	@Test
	public void test_fromAU() {
		Distance dist = Distance.fromAU(1);
		assertEquals(Constants.AU.getValue(), dist.inMeters(), 0);
		assertEquals(1, dist.inAU(), 0);
	}
	
	@Test
	public void test_inAU() {
		Distance dist = Distance.fromAU(0.9);
		assertEquals(0.9 * Constants.AU.getValue(), dist.inMeters(), 0);
		assertEquals(0.9, dist.inAU(), 0);
	}
	
	@Test 
	public void test_equals() {
		Distance dist1 = Distance.fromMeters(1000);
		Distance dist2 = Distance.fromMeters(1000);
		Distance dist3 = Distance.fromMeters(999);
		
		assertTrue(dist1.equals(dist1));
		assertTrue(dist1.equals(dist2));
		assertFalse(dist1.equals(dist3));
		
		assertFalse(dist1.equals(null));
		assertFalse(dist1.equals(new Object()));
		
		assertEquals(dist1.hashCode(), dist2.hashCode());
	}
	
	
	@Test
	public void testToString() {
		Distance dist = new Distance(100);
		assertEquals("100.0m", dist.toString());
	}
	
	
	
}
