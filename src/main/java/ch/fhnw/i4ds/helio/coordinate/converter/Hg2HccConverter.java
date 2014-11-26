package ch.fhnw.i4ds.helio.coordinate.converter;

import static ch.fhnw.i4ds.helio.coordinate.util.Constants.SUN_RADIUS;
import static ch.fhnw.i4ds.helio.coordinate.util.Constants.UNDEFINED;
import static java.lang.Math.toRadians;

import java.util.ArrayList;
import java.util.List;

import ch.fhnw.i4ds.helio.coordinate.api.CoordConversionOptions;
import ch.fhnw.i4ds.helio.coordinate.api.CoordConverter;
import ch.fhnw.i4ds.helio.coordinate.api.Coordinate;
import ch.fhnw.i4ds.helio.coordinate.coord.HelioCartesianCoordinate;
import ch.fhnw.i4ds.helio.coordinate.coord.HeliographicCoordinate;

/**
 * Converter from Stonyhurst Heliographics (HG) to Heliocentric Cartesian
 * coordinates (HCC). This class is thread-safe.
 * 
 * @author marco soldati at fhnw ch
 * 
 */
public class Hg2HccConverter extends AbstractConverter<HeliographicCoordinate, HelioCartesianCoordinate> implements
				CoordConverter<HeliographicCoordinate, HelioCartesianCoordinate> {

	/**
	 * Options for this converter. use
	 * {@link Hg2HccConverter#newConversionOptions()} to get a mutable options
	 * object. The class uses a builder pattern to modify it's properties. E.g.
	 * 
	 * <pre>
	 * ConversionOptions conversionOptions = Hg2HccConverter.newConversionOptions().b0InRad(0.001)
	 * 				.sunRadiusInMeter(704945784.41465974);
	 * </pre>
	 */
	public static class ConversionOptions implements
					CoordConversionOptions<CoordConverter<HeliographicCoordinate, HelioCartesianCoordinate>> {
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
		private double l0InRad;

		/**
		 * If true set all points behind the Sun (e.g. not visible) to Nan.
		 * Defaults to false.
		 */
		private boolean occultation = false;

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

		public boolean isOccultation() {
			return occultation;
		}

		public ConversionOptions occultation(boolean occultation) {
			this.occultation = occultation;
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
	public HelioCartesianCoordinate convert(HeliographicCoordinate hg) {
		return convert(hg, DEFAULT_OPTIONS);
	}

	/**
	 * Convert with given custom options.
	 * 
	 * @param hg
	 *            source coordinate.
	 * @param opt
	 *            options.
	 * @return converted coordinates.
	 */
	public HelioCartesianCoordinate convert(HeliographicCoordinate hg, ConversionOptions opt) {
		double lon = toRadians(hg.getHgLongitudeDegree());
		double lat = toRadians(hg.getHgLatitudeDegree());

		double cosb = Math.cos(opt.getB0InRad());
		double sinb = Math.sin(opt.getB0InRad());

		lon -= opt.getL0InRad();

		double cosx = Math.cos(lon);
		double sinx = Math.sin(lon);
		double cosy = Math.cos(lat);
		double siny = Math.sin(lat);

		// Perform the conversion.
		double x = opt.getSunRadiusInMeter() * cosy * sinx;
		double y = opt.getSunRadiusInMeter() * (siny * cosb - cosy * cosx * sinb);
		double z = opt.getSunRadiusInMeter() * (siny * sinb + cosy * cosx * cosb);

		if (opt.isOccultation() && z < 0) {
			x = UNDEFINED;
			y = UNDEFINED;
		}

		return new HelioCartesianCoordinate(x, y, z);
	}
	
	/**
	 * The default implementation just delegates to
	 * {@link CoordConverter#convert(Coordinate, CoordConversionOptions)},
	 */
	public List<HelioCartesianCoordinate> convert(List<HeliographicCoordinate> hgList, ConversionOptions opt) {
		List<HelioCartesianCoordinate> ret = new ArrayList<HelioCartesianCoordinate>();
		for (HeliographicCoordinate hg : hgList) {
			ret.add(convert(hg, opt));
		}
		return ret;
	}
}
