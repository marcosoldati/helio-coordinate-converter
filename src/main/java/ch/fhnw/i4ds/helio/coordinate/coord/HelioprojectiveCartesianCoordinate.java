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
}
