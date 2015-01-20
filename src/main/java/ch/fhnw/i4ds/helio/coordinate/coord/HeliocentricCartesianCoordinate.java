package ch.fhnw.i4ds.helio.coordinate.coord;

import ch.fhnw.i4ds.helio.coordinate.api.Coordinate;

/**
 * Heliocentric Cartesian Coordinate in meters.
 * Z may be undefined (i.e. Double.NaN).
 * 
 * @author marco soldati at fhnw ch.
 * 
 */
public class HeliocentricCartesianCoordinate implements Coordinate {

	private static final String ACRONYM = "HCC";
	private static final String DESCRIPTION = "Heliocentric-Cartesian coordinate";

	@Override
	public String getCoordinateSystemAcronym() {
		return ACRONYM;
	}

	@Override
	public String getCoordinateSystemDescription() {
		return DESCRIPTION;
	}

	private final double x;
	private final double y;
	private final double z;

	public HeliocentricCartesianCoordinate(double x, double y) {
		this(x, y, Double.NaN);
	}

	public HeliocentricCartesianCoordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}

	/**
	 * Compute the radius from x, y and z
	 * 
	 * @return the radius.
	 */
	public double getRadius() {
		return Math.sqrt(x * x + y * y + z * z);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getCoordinateSystemDescription()).append(" (").append(getCoordinateSystemAcronym()).append(") ");
		sb.append("[").append(x).append("m/").append(y);
		if (!Double.isNaN(z)) {
			sb.append("m/").append(z);
		}
		sb.append("m]");
		return sb.toString();
	}
}
