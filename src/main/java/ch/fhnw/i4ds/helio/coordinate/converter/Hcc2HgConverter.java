package ch.fhnw.i4ds.helio.coordinate.converter;

import static ch.fhnw.i4ds.helio.coordinate.util.Constants.SUN_RADIUS;
import static java.lang.Math.toRadians;
import static java.lang.Math.toDegrees;

import java.util.ArrayList;
import java.util.List;

import ch.fhnw.i4ds.helio.coordinate.api.CoordConversionOptions;
import ch.fhnw.i4ds.helio.coordinate.api.CoordConverter;
import ch.fhnw.i4ds.helio.coordinate.api.Coordinate;
import ch.fhnw.i4ds.helio.coordinate.coord.HeliocentricCartesianCoordinate;
import ch.fhnw.i4ds.helio.coordinate.coord.HeliographicCoordinate;

/**
 * Converter from Heliocentric Cartesian coordinates (HCC) to Stonyhurst
 * Heliographics (HG). This class is thread-safe.
 * 
 * @author marco soldati at fhnw ch
 * 
 */
public class Hcc2HgConverter extends AbstractConverter<HeliocentricCartesianCoordinate, HeliographicCoordinate> implements
				CoordConverter<HeliocentricCartesianCoordinate, HeliographicCoordinate> {

	/**
	 * Options for this converter. use
	 * {@link Hcc2HgConverter#newConversionOptions()} to get a mutable options
	 * object. The class uses a builder pattern to modify it's properties. E.g.
	 * 
	 * <pre>
	 * ConversionOptions conversionOptions = Hg2HccConverter.newConversionOptions().b0InRad(0.001)
	 * 				.sunRadiusInMeter(704945784.41465974);
	 * </pre>
	 */
	public static class ConversionOptions implements
					CoordConversionOptions<CoordConverter<HeliocentricCartesianCoordinate, HeliographicCoordinate>> {
		/**
		 * Tilt of the solar North rotational axis toward the observer
		 * (heliographic latitude of the observer). FITS: usually given as
		 * SOLAR_B0, HGLT_OBS, or CRLT_OBS. Default is 0.
		 */
		private double b0InRad = 0;

		/**
		 * Carrington longitude of central meridian as seen from Earth. Default
		 * is 0.
		 */
		private double l0InRad = 0;

		/**
		 * Solar radius
		 */
		private double sunRadiusInMeter = SUN_RADIUS.getValue();

		private ConversionOptions() {
		}

		public double getB0InRad() {
			return b0InRad;
		}

		public ConversionOptions b0InRad(double b0InRad) {
			this.b0InRad = b0InRad;
			return this;
		}

		public ConversionOptions b0InDegree(double b0InDegree) {
			this.b0InRad = toRadians(b0InDegree);
			return this;
		}

		public double getL0InRad() {
			return l0InRad;
		}

		public ConversionOptions l0InDegree(double l0InDegree) {
			this.l0InRad = toRadians(l0InDegree);
			return this;
		}

		public ConversionOptions l0InRad(double l0InRad) {
			this.l0InRad = l0InRad;
			return this;
		}
		
		public double getSunRadiusInMeter() {
			return sunRadiusInMeter;
		}

		public ConversionOptions sunRadiusInMeter(double sunRadiusInMeter) {
			this.sunRadiusInMeter = sunRadiusInMeter;
			return this;
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
	public HeliographicCoordinate convert(HeliocentricCartesianCoordinate hcc) {
		return convert(hcc, DEFAULT_OPTIONS);
	}

	/**
	 * Convert with given custom options.
	 * Implements Eq. (12) of Thompson (2006), A&A, 449, 791.
	 * 
	 * @param hcc
	 *            source coordinate. If z is UNDEFINED the z-coordinate is
	 *            assumed to be on the Sun.
	 * @param opt
	 *            options.
	 * @return converted coordinates.
	 */
	public HeliographicCoordinate convert(HeliocentricCartesianCoordinate hcc, ConversionOptions opt) {
		double x = hcc.getX();
		double y = hcc.getY();
		double z = hcc.getZ();
		if (Double.isNaN(z)) {
			z = Math.sqrt(opt.getSunRadiusInMeter() * opt.getSunRadiusInMeter() - x * x - y * y);
		}
		
	    double cosb = Math.cos(opt.getB0InRad());
	    double sinb = Math.sin(opt.getB0InRad());

	    double hecRadius = Math.sqrt(x*x + y*y + z*z);
	    double hgLongitude = Math.atan2(x, z * cosb - y * sinb) + opt.getL0InRad();
	    double hgLatitude = Math.asin((y * cosb + z * sinb) / hecRadius);

		return new HeliographicCoordinate(toDegrees(hgLongitude), toDegrees(hgLatitude));
	}

	/**
	 * The default implementation just delegates to
	 * {@link CoordConverter#convert(Coordinate, CoordConversionOptions)},
	 */
	public List<HeliographicCoordinate> convert(List<HeliocentricCartesianCoordinate> hccList, ConversionOptions opt) {
		List<HeliographicCoordinate> ret = new ArrayList<HeliographicCoordinate>();
		for (HeliocentricCartesianCoordinate hg : hccList) {
			ret.add(convert(hg, opt));
		}
		return ret;
	}
}
