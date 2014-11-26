package ch.fhnw.i4ds.helio.coordinate;

/**
 * List of known coordinate systems.
 * Copied from http://hesperia.gsfc.nasa.gov/ssw/stereo/gen/idl/spice/convert_stereo_coord.pro
 * @author marco soldati at fhnw ch
 * @deprecated may be removed soon.
 */
public enum CoordinateSystem {
	
	GEI ("Geocentric Equatorial Inertial"),
	GEO ("Geographic"),
	GSE ("Geocentric Solar Ecliptic"),
	GAE ("Geocentric Aries Ecliptic"),
	MAG ("Geomagnetic"),
	GSM ("Geocentric Solar Magnetospheric"),
	SM ("Solar Magnetic"),
	HCI ("Heliocentric Inertial"),
	HAE ("Heliocentric Aries Ecliptic"),
	HEE ("Heliocentric Earth Ecliptic"),
	HEEQ ("Heliocentric Earth Equatorial (or HEQ)"),
	Carrington ("(can be abbreviated)"),
	GRTN ("Geocentric Radial-Tangential-Normal"),
	HGRTN ("Heliocentric Radial-Tangential-Normal"),
	RTN ("Radial-Tangential-Normal"),
	SCI ("STEREO Science Pointing"),
	HERTN ("Heliocentric Ecliptic RTN"),
	STPLN ("Stereo Mission Plane");
	
	
	private String description;

	private CoordinateSystem(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
}
