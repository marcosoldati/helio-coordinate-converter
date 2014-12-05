package ch.fhnw.i4ds.helio.coordinate.api;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AngleTest {

	@Test
	public void rad_to_deg() {
		rad_to_deg(0.0, 0.0);
		rad_to_deg(Math.PI, 180);
		rad_to_deg(2* Math.PI, 360);
		rad_to_deg(Double.NaN, Double.NaN);
	}
	
	@Test
	public void deg_to_rad() {
		deg_to_rad(0.0, 0.0);
		deg_to_rad(180, Math.PI);
		deg_to_rad(Double.NaN, Double.NaN);
	}

	@Test
	public void set_arcmin() {
		Angle a = Angle.fromRad(0);
		a.setArcmin(400);
		assertEquals(400, a.arcminValue(), 1e-11);
	}
	
	@Test
	public void set_arcsec() {
		Angle a = Angle.fromRad(0);
		a.setArcsec(400);
		assertEquals(400, a.arcsecValue(), 0);
	}
	
	@Test
	public void set_deg() {
		Angle a = Angle.fromRad(0);
		a.setDeg(160);
		assertEquals(160, a.degValue(), 0);
	}
	
	@Test
	public void set_rad() {
		Angle a = Angle.fromRad(0);
		a.setRad(2.0);
		assertEquals(2.0, a.radValue(), 0);
	}
	
	private void rad_to_deg(double rad, double expectedDeg) {
		Angle angle = Angle.fromRad(rad);
		assertEquals(expectedDeg, angle.degValue(), 0);
		assertEquals(expectedDeg * 60, angle.arcminValue(), 0);
		assertEquals(expectedDeg * 3600, angle.arcsecValue(), 0);
	} 
	
	private void deg_to_rad(double deg, double expectedRad) {
		Angle angle = Angle.fromDeg(deg);
		assertEquals(expectedRad, angle.radValue(), 0);
	}
	
	
}
