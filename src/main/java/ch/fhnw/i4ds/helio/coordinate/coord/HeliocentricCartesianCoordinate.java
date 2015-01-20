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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(z);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HeliocentricCartesianCoordinate other = (HeliocentricCartesianCoordinate) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		if (Double.doubleToLongBits(z) != Double.doubleToLongBits(other.z))
			return false;
		return true;
	}
}
