package ch.fhnw.i4ds.helio.coordinate.converter;

import static java.lang.Math.toDegrees;

import java.util.ArrayList;
import java.util.List;

import ch.fhnw.i4ds.helio.coordinate.api.CoordConversionOptions;
import ch.fhnw.i4ds.helio.coordinate.api.CoordConverter;
import ch.fhnw.i4ds.helio.coordinate.api.Coordinate;
import ch.fhnw.i4ds.helio.coordinate.coord.HeliocentricCartesianCoordinate;
import ch.fhnw.i4ds.helio.coordinate.coord.HelioprojectiveCartesianCoordinate;
import ch.fhnw.i4ds.helio.coordinate.util.Constants;

/**
 * Converter from Heliocentric Cartesian coordinates (HCC) to
 * Helioprojetive-Cartesian (HCC) Coordinates. This class is thread-safe.
 * 
 * @author marco soldati at fhnw ch
 * 
 */
public class Hg2HpcConverter extends
				AbstractConverter<HeliocentricCartesianCoordinate, HelioprojectiveCartesianCoordinate> implements
				CoordConverter<HeliocentricCartesianCoordinate, HelioprojectiveCartesianCoordinate> {

	/**
	 * Options for this converter. use
	 * {@link Hg2HpcConverter#newConversionOptions()} to get a mutable options
	 * object.
	 */
	public static class ConversionOptions
					implements
					CoordConversionOptions<CoordConverter<HeliocentricCartesianCoordinate, HelioprojectiveCartesianCoordinate>> {
		
		private double observerSunDistance = Constants.AU.getValue();
		
		/**
		 * The distance between observer and Sun in meters. 
		 * @param observerSunDistanceInMeter the distance
		 * @return 'this' as required be the builder pattern.
		 */
		public ConversionOptions setObserverSunDistance(double observerSunDistanceInMeter) {
			this.observerSunDistance = observerSunDistanceInMeter;
			return this;
		}
		
		/**
		 * Distance between observer and Sun.
		 * @return distance in meters.
		 */
		public double getObserverSunDistance() {
			return observerSunDistance;
		}
	}

	private static final ConversionOptions DEFAULT_OPTIONS = new ConversionOptions();

	/**
	 * Create a new instance of default conversion options. These options can be
	 * customized and passed to the convert method. One conversion option
	 * instance can be used for multiple calls.
	 * 
	 * @return a new conversion options element.
	 */
	public static ConversionOptions newConversionOptions() {
		return new ConversionOptions();
	}

	@Override
	public HelioprojectiveCartesianCoordinate convert(HeliocentricCartesianCoordinate hcc) {
		return convert(hcc, DEFAULT_OPTIONS);
	}

	/**
	 * Convert with given custom options. Implements Eq. (16) of Thompson
	 * (2006), A&A, 449, 791.
	 * 
	 * @param hcc
	 *            source coordinate. If z is UNDEFINED the z-coordinate is
	 *            assumed to be on the Sun.
	 * @param opt
	 *            options.
	 * @return converted coordinates.
	 */
	public HelioprojectiveCartesianCoordinate convert(HeliocentricCartesianCoordinate hcc, ConversionOptions opt) {
		double x = hcc.getX();
		double y = hcc.getY();
		double z = hcc.getZ();
		double sunRadius = Constants.SUN_RADIUS.getValue();
		double sunDistance = opt.getObserverSunDistance();
				
		if (Double.isNaN(z)) {
			// Calculate the z coordinate by assuming that it is on the surface of the Sun
			z = Math.sqrt(sunRadius * sunRadius - x * x - y * y);
		}
		

	    double zeta = sunDistance - z;
	    double distance = Math.sqrt(x*x + y*y + zeta * zeta);
	    double hpcx = radiansToArcsec(Math.atan2(x, zeta));
	    double hpcy = radiansToArcsec(Math.asin(y / distance));
	    
		return new HelioprojectiveCartesianCoordinate(hpcx, hpcy, sunDistance);
	}

	private double radiansToArcsec(double radians) {
		return toDegrees(radians) * 3600;
	}

	/**
	 * The default implementation just delegates to
	 * {@link CoordConverter#convert(Coordinate, CoordConversionOptions)},
	 */
	public List<HelioprojectiveCartesianCoordinate> convert(List<HeliocentricCartesianCoordinate> hccList,
					ConversionOptions opt) {
		List<HelioprojectiveCartesianCoordinate> ret = new ArrayList<HelioprojectiveCartesianCoordinate>();
		for (HeliocentricCartesianCoordinate hg : hccList) {
			ret.add(convert(hg, opt));
		}
		return ret;
	}
}
