package ch.fhnw.i4ds.helio.coordinate.sunpos;

import org.joda.time.DateTime;

import ch.fhnw.i4ds.helio.coordinate.api.Angle;

public class SunPosition {
	/**
	 * Date for this position of the sun.
	 */
	private final DateTime dateTime;

	/**
	 * Longitude of sun for mean equinox of date
	 */
	private Angle longitude;

	/**
	 * Apparent RA for true equinox of date
	 */
	private Angle ra;

	/**
	 * Apparent declination for true equinox of date
	 */
	private Angle dec;

	/**
	 * Apparent longitude
	 */
	private Angle apparentLongitude;

	/**
	 * True obliquity
	 */
	private Angle obliquity;

	public SunPosition(DateTime dateTime) {
		this.dateTime = dateTime;
	}

	public Angle getLongitude() {
		return longitude;
	}

	public void setLongitude(Angle longitude) {
		this.longitude = longitude;
	}

	public Angle getRa() {
		return ra;
	}

	public void setRa(Angle ra) {
		this.ra = ra;
	}

	public Angle getDec() {
		return dec;
	}

	public void setDec(Angle dec) {
		this.dec = dec;
	}

	public Angle getApparentLongitude() {
		return apparentLongitude;
	}

	public void setApparentLongitude(Angle apparentLongitude) {
		this.apparentLongitude = apparentLongitude;
	}

	public Angle getObliquity() {
		return obliquity;
	}

	public void setObliquity(Angle obliquity) {
		this.obliquity = obliquity;
	}

	public DateTime getDateTime() {
		return dateTime;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SunPosition [dateTime=").append(dateTime).append(", longitude=").append(longitude.degValue())
						.append("°, ra=").append(ra.degValue()).append("°, dec=").append(dec.degValue())
						.append("°, apparentLongitude=").append(apparentLongitude).append("°, obliquity=")
						.append(obliquity.degValue()).append("°]");
		return builder.toString();
	}

}
