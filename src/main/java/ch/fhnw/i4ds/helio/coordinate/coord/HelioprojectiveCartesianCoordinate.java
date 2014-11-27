package ch.fhnw.i4ds.helio.coordinate.coord;

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

	private final double x;
	private final double y;
	private final double sunDistance;

	/**
	 * Distance between observer and Sun is set to 1AU.
	 * 
	 * @param x
	 *            arcsecs from center of sun.
	 * @param y
	 *            arcsecs from center of sun.
	 */
	public HelioprojectiveCartesianCoordinate(double x, double y) {
		this(x, y, Constants.AU.getValue());
	}

	/**
	 * 
	 * @param x
	 *            arcsecs from center of sun.
	 * @param y
	 *            arcsecs from center of sun.
	 * @param sunDistance
	 *            distance between observer and Sun in Meters.
	 */
	public HelioprojectiveCartesianCoordinate(double x, double y, double sunDistance) {
		this.x = x;
		this.y = y;
		this.sunDistance = sunDistance;
	}

	/**
	 * X in arcsecs
	 * 
	 * @return x
	 */
	public double getX() {
		return x;
	}

	/**
	 * Y in arcsecs
	 * 
	 * @return y
	 */
	public double getY() {
		return y;
	}

	public double getSunDistance() {
		return sunDistance;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(DESCRIPTION).append(" (").append(ACRONYM).append(") ");
		sb.append("[").append(x).append("m/").append(y);
		sb.append("m, sunDistance=").append(sunDistance == Constants.AU.getValue() ? "1AU" : (sunDistance + "m"))
						.append("]");
		return sb.toString();
	}
}
