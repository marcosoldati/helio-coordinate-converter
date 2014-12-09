package ch.fhnw.i4ds.helio.coordinate.coord;

import ch.fhnw.i4ds.helio.coordinate.api.Angle;
import ch.fhnw.i4ds.helio.coordinate.api.Coordinate;

public class HeliographicCoordinate implements Coordinate {

	private static final String ACRONYM = "HG";

	private static final String DESCRIPTION = "Stonyhurst Heliographic coordinate";

	@Override
	public String getCoordinateSystemAcronym() {
		return ACRONYM;
	}

	@Override
	public String getCoordinateSystemDescription() {
		return DESCRIPTION;
	}

	private final Angle hgLongitude;

	private final Angle hgLatitude;

	public HeliographicCoordinate(Angle hgLongitude, Angle hgLatitude) {
		this.hgLongitude = hgLongitude;
		this.hgLatitude = hgLatitude;
	}
	
	public Angle getHgLongitude() {
		return hgLongitude;
	}
	
	public Angle getHgLatitude() {
		return hgLatitude;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(DESCRIPTION).append(" (").append(ACRONYM).append(") ");
		sb.append("[").append(hgLongitude.degValue()).append("°/").append(hgLatitude.degValue()).append("°]");
		return sb.toString();
	}
}
