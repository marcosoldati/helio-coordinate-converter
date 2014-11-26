package ch.fhnw.i4ds.helio.coordinate.coord;

import ch.fhnw.i4ds.helio.coordinate.api.Coordinate;
import static ch.fhnw.i4ds.helio.coordinate.util.Constants.UNDEFINED;;

/**
 * Helio Cartesian Coordinates in meters.
 * 
 * @author mercuron
 * 
 */
public class HelioCartesianCoordinate implements Coordinate {

	private static final String ACRONYM = "HCC";
	private static final String DESCRIPTION = "Heliocentric-Cartesian coordinates";
	
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

	public HelioCartesianCoordinate(double x, double y) {
		this(x, y, UNDEFINED);
	}

	public HelioCartesianCoordinate(double x, double y, double z) {
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
}
