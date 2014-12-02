package ch.fhnw.i4ds.helio.coordinate.sunpos;

import org.joda.time.DateTime;

public class SunPosition {
	/**
	 * Date for this position of the sun.
	 */
	private final DateTime dateTime;
	
	/**
	 * Longitude of sun for mean equinox of date (degs)
	 */
	private double longitudeInDegree;
	
	/**
	 * Apparent RA for true equinox of date (degs)
	 */
	private double ra;
	
	/**
	 * Apparent declination for true equinox of date (degs)
	 */
	private double dec;

	/**
	 * Apparent longitude (degs)
	 */
	private double apparentLongitude;
	
	/**
	 * True obliquity (degs)
	 */
	private double obliquity;
	
	public SunPosition(DateTime dateTime) {
		this.dateTime = dateTime;
	}

	public double getLongitudeInDegree() {
		return longitudeInDegree;
	}

	public void setLongitudeInDegree(double longitudeInDegree) {
		this.longitudeInDegree = longitudeInDegree;
	}

	public double getRa() {
		return ra;
	}

	public void setRa(double ra) {
		this.ra = ra;
	}

	public double getDec() {
		return dec;
	}

	public void setDec(double dec) {
		this.dec = dec;
	}

	public double getApparentLongitude() {
		return apparentLongitude;
	}

	public void setApparentLongitude(double apparentLongitude) {
		this.apparentLongitude = apparentLongitude;
	}

	public double getObliquity() {
		return obliquity;
	}
	
	public void setObliquity(double obliquity) {
		this.obliquity = obliquity;
	}

	public DateTime getDateTime() {
		return dateTime;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SunPosition [dateTime=").append(dateTime).append(", longitudeInDegree=")
						.append(longitudeInDegree).append(", ra=").append(ra).append(", dec=").append(dec)
						.append(", apparentLongitude=").append(apparentLongitude).append(", obliquity=")
						.append(obliquity).append("]");
		return builder.toString();
	}
	
	
}
