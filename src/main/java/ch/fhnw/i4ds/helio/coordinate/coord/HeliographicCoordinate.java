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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hgLatitude == null) ? 0 : hgLatitude.hashCode());
		result = prime * result + ((hgLongitude == null) ? 0 : hgLongitude.hashCode());
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
		HeliographicCoordinate other = (HeliographicCoordinate) obj;
		if (hgLatitude == null) {
			if (other.hgLatitude != null)
				return false;
		} else if (!hgLatitude.equals(other.hgLatitude))
			return false;
		if (hgLongitude == null) {
			if (other.hgLongitude != null)
				return false;
		} else if (!hgLongitude.equals(other.hgLongitude))
			return false;
		return true;
	}
}
