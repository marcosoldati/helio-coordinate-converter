package ch.fhnw.i4ds.helio.coordinate.coord;

import ch.fhnw.i4ds.helio.coordinate.api.Coordinate;

public class HeliographicCoordinate implements Coordinate {

	private static final String ACRONYM = "HG";

	private static final String DESCRIPTION = "Stonyhurst Heliographic coordinates";

	@Override
	public String getCoordinateSystemAcronym() {
		return ACRONYM;
	}

	@Override
	public String getCoordinateSystemDescription() {
		return DESCRIPTION;
	}

	private final double hgLongitudeDegree;

	private final double hgLatitudeDegree;

	public HeliographicCoordinate(double hgLongitudeDegree, double hgLatitudeDegree) {
		this.hgLongitudeDegree = hgLongitudeDegree;
		this.hgLatitudeDegree = hgLatitudeDegree;
	}
	
	public double getHgLongitudeDegree() {
		return hgLongitudeDegree;
	}
	
	public double getHgLatitudeDegree() {
		return hgLatitudeDegree;
	}
}
