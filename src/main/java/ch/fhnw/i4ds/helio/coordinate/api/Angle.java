package ch.fhnw.i4ds.helio.coordinate.api;

import static java.lang.Math.toDegrees;
import static java.lang.Math.toRadians;

/**
 * Describe an angle and provide methods to convert between degrees and radians.
 * @author marco soldati at fhnw ch
 *
 */
public class Angle {
	private static final int ARCMIN_FACTOR = 60;
	private static final int ARCSEC_FACTOR = 3600;
	
	/**
	 * Store the angle in Radians.
	 */
	private double radians;
	
	/**
	 * Factory method to create an angle for a value in degrees.
	 * @param deg the degree
	 * @return the Angle
	 */
	public static Angle fromDeg(double deg) {
		return new Angle(toRadians(deg));
	}
	
	/**
	 * Factory method to create an angle for a value in arc minutes
	 * @param arcmin the arc minutes.
	 * @return the Angle
	 */
	public static Angle fromArcmin(double arcmin) {
		return new Angle(toRadians(arcmin / ARCMIN_FACTOR));
	}
	/**
	 * Factory method to create an angle for a value in degrees.
	 * @return the Angle
	 */
	public static Angle fromArcsec(double arcsec) {
		return new Angle(toRadians(arcsec / ARCSEC_FACTOR));
	}
	
	/**
	 * Factory method to create an angle from a radians value. 
	 * @param rad the rad value
	 * @return the Angle
	 */
	public static Angle fromRad(double rad) {
		return new Angle(rad);
	}
	
	/**
	 * Create an angle with value 0.
	 */
	public Angle() {
		this.radians = 0;
	}
	
	/**
	 * Create a value with a given radians.
	 * Consider using the static factory methods instead of this constructor.
	 * @param radians the radians value.
	 */
	public Angle(double radians) {
		this.radians = radians;
	}
	
	/**
	 * The value in radians
	 * @return the value in radians
	 */
	public double radValue() {
		return radians;
	}
	
	/**
	 * The value in degrees
	 * @return degrees.
	 */
	public double degValue() {
		return toDegrees(radians);
	}
	
	/**
	 * The value in arc minutes.
	 * @return arc minutes
	 */
	public double arcminValue() {
		return toDegrees(radians) * 60;
	}
	
	/**
	 * The value in arc seconds.
	 * @return arc seconds.
	 */
	public double arcsecValue() {
		return toDegrees(radians) * 3600;
	}
	
	/**
	 * Factory method to create an angle for a value in degrees.
	 * @param deg the degree
	 * @return the Angle
	 */
	public void setDeg(double deg) {
		this.radians = toRadians(deg);
	}
	
	/**
	 * Factory method to create an angle for a value in arc minutes
	 * @param arcmin the arc minutes.
	 * @return the Angle
	 */
	public void setArcmin(double arcmin) {
		 this.radians = toRadians(arcmin / ARCMIN_FACTOR);
	}
	/**
	 * Factory method to create an angle for a value in degrees.
	 * @return the Angle
	 */
	public void setArcsec(double arcsec) {
		 this.radians = toRadians(arcsec / ARCSEC_FACTOR);
	}
	
	/**
	 * Factory method to create an angle from a radians value. 
	 * @param rad the rad value
	 * @return the Angle
	 */
	public void setRad(double rad) {
		 this.radians = rad;
	}
	
	public String formatDegreeString() {
		double deg = degValue();
		StringBuilder sb = new StringBuilder();
		sb.append(Math.floor(deg)).append("°");
		sb.append(Math.floor(deg - Math.floor(deg) * ARCMIN_FACTOR)).append("'");
		sb.append(deg - Math.floor(deg) * ARCSEC_FACTOR).append("''");
		return sb.toString();
	}

	@Override
	public String toString() {
		return radians + "rad";
	}
}
