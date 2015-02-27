package ch.fhnw.i4ds.helio.coordinate.coord;

import ch.fhnw.i4ds.helio.coordinate.api.Angle;
import ch.fhnw.i4ds.helio.coordinate.api.Coordinate;
import ch.fhnw.i4ds.helio.coordinate.api.Distance;

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

	private final Angle thetaX;
	private final Angle thetaY;
	private final Distance sunDistance;

	/**
	 * Distance between observer and Sun is set to 1AU.
	 * 
	 * @param thetaX
	 *            angle from center of sun as seen from observer.
	 * @param thetaY
	 *            angle from center of sun as seen from observer.
	 */
	public HelioprojectiveCartesianCoordinate(Angle thetaX, Angle thetaY) {
		this(thetaX, thetaY, Distance.fromAU(1));
	}

	/**
	 * 
	 * @param thetaX
	 *            angle from center of sun as seen from observer.
	 * @param thetaY
	 *            angle from center of sun as seen from observer.
	 * @param sunDistance
	 *            distance between observer and Sun in Meters.
	 */
	public HelioprojectiveCartesianCoordinate(Angle thetaX, Angle thetaY, Distance sunDistance) {
		this.thetaX = thetaX;
		this.thetaY = thetaY;
		this.sunDistance = sunDistance;
	}

	/**
	 * X angle from center of Sun along equator as seen from observer.
	 * 
	 * @return thetaX
	 */
	public Angle getThetaX() {
		return thetaX;
	}

	/**
	 * Y angle from center of Sun to pole as seen from observer.
	 * 
	 * @return thetaY
	 */
	public Angle getThetaY() {
		return thetaY;
	}

	public Distance getSunDistance() {
		return sunDistance;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(DESCRIPTION).append(" (").append(ACRONYM).append(") ");
		sb.append("[").append(thetaX.arcsecValue()).append("''/").append(thetaY.arcsecValue());
		sb.append("'', sunDistance=").append(sunDistance.inAU() == 1 ? "1AU" : (sunDistance))
						.append("]");
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sunDistance == null) ? 0 : sunDistance.hashCode());
		result = prime * result + ((thetaX == null) ? 0 : thetaX.hashCode());
		result = prime * result + ((thetaY == null) ? 0 : thetaY.hashCode());
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
		if (sunDistance == null) {
			if (other.sunDistance != null)
				return false;
		} else if (!sunDistance.equals(other.sunDistance))
			return false;
		if (thetaX == null) {
			if (other.thetaX != null)
				return false;
		} else if (!thetaX.equals(other.thetaX))
			return false;
		if (thetaY == null) {
			if (other.thetaY != null)
				return false;
		} else if (!thetaY.equals(other.thetaY))
			return false;
		return true;
	}
	
	
}
