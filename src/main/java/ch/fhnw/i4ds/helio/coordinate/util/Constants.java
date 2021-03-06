package ch.fhnw.i4ds.helio.coordinate.util;

public class Constants {

	/**
	 * Solar radius in meters
	 */
	public static Constant SUN_RADIUS = new Constant("R_sun", "Solar radius", 6.95508e8, Unit.METER, 0.00026e8,
					"Allen's Astrophysical Quantities 4th Ed.");

	/**
	 * Astronomical Unit
	 */
	public static Constant AU = new Constant("au", "Astronomical Unit", 1.49597870700e11, Unit.METER, 0.0,
					"IAU 2012 Resolution B2");
}
