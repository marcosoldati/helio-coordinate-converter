package ch.fhnw.i4ds.helio.coordinate.coord;

import ch.fhnw.i4ds.helio.coordinate.api.Angle;
import ch.fhnw.i4ds.helio.coordinate.api.Coordinate;
import ch.fhnw.i4ds.helio.coordinate.util.Constants;

/**
 * Helioprojective Cartesian Coordinates in Arcsecs from the sun center seen from an observers point.
 * 
 * 
 * @author marco soldati at fhnw ch.
 * 
 */
public class HelioprojectiveCartesianCoordinate implements Coordinate {

	private static final String ACRONYM = "HPC";
	private static final String DESCRIPTION = "Heliorojective-Cartesian coordinate";

	@Override
	public String getCoordinateSystemAcronym() {
		return ACRONYM;
	}

	@Override
	public String getCoordinateSystemDescription() {
		return DESCRIPTION;
	}

	private final Angle x;
	private final Angle y;
	private final double sunDistance;

	/**
	 * Distance between observer and Sun is set to 1AU.
	 * 
	 * @param x
	 *            angle from center of sun as seen from observer.
	 * @param y
	 *            angle from center of sun as seen from observer.
	 */
	public HelioprojectiveCartesianCoordinate(Angle x, Angle y) {
		this(x, y, Constants.AU.getValue());
	}

	/**
	 * 
	 * @param x
	 *            angle from center of sun as seen from observer.
	 * @param y
	 *            angle from center of sun as seen from observer.
	 * @param sunDistance
	 *            distance between observer and Sun in Meters.
	 */
	public HelioprojectiveCartesianCoordinate(Angle x, Angle y, double sunDistance) {
		this.x = x;
		this.y = y;
		this.sunDistance = sunDistance;
	}

	/**
	 * X from center of Sun along equator as seen from observer.
	 * 
	 * @return x
	 */
	public Angle getX() {
		return x;
	}

	/**
	 * Y from center of Sun to pole as seen from observer.
	 * 
	 * @return y
	 */
	public Angle getY() {
		return y;
	}

	public double getSunDistance() {
		return sunDistance;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(DESCRIPTION).append(" (").append(ACRONYM).append(") ");
		sb.append("[").append(x.arcsecValue()).append("''/").append(y.arcsecValue());
		sb.append("'', sunDistance=").append(sunDistance == Constants.AU.getValue() ? "1AU" : (sunDistance + "m"))
						.append("]");
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(sunDistance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((x == null) ? 0 : x.hashCode());
		result = prime * result + ((y == null) ? 0 : y.hashCode());
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
		HelioprojectiveCartesianCoordinate other = (HelioprojectiveCartesianCoordinate) obj;
		if (Double.doubleToLongBits(sunDistance) != Double.doubleToLongBits(other.sunDistance))
			return false;
		if (x == null) {
			if (other.x != null)
				return false;
		} else if (!x.equals(other.x))
			return false;
		if (y == null) {
			if (other.y != null)
				return false;
		} else if (!y.equals(other.y))
			return false;
		return true;
	}
}
