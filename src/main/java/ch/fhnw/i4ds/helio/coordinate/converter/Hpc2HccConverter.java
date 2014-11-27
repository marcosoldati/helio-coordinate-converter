package ch.fhnw.i4ds.helio.coordinate.converter;

import static java.lang.Math.toRadians;

import java.util.ArrayList;
import java.util.List;

import ch.fhnw.i4ds.helio.coordinate.api.CoordConversionOptions;
import ch.fhnw.i4ds.helio.coordinate.api.CoordConverter;
import ch.fhnw.i4ds.helio.coordinate.api.Coordinate;
import ch.fhnw.i4ds.helio.coordinate.coord.HeliocentricCartesianCoordinate;
import ch.fhnw.i4ds.helio.coordinate.coord.HelioprojectiveCartesianCoordinate;
import ch.fhnw.i4ds.helio.coordinate.util.Constants;

/**
 * Converter from Helioprojective-Cartesian (HPC) to Heliocentric-Cartesian
 * coordinates (HCC). This class is thread-safe.
 * 
 * @author marco soldati at fhnw ch
 * 
 */
public class Hpc2HccConverter extends
				AbstractConverter<HelioprojectiveCartesianCoordinate, HeliocentricCartesianCoordinate> implements
				CoordConverter<HelioprojectiveCartesianCoordinate, HeliocentricCartesianCoordinate> {

	/**
	 * Options for this converter. use
	 * {@link Hpc2HccConverter#newConversionOptions()} to get a mutable options
	 * object. The class uses a builder pattern to modify it's properties.
	 */
	public static class ConversionOptions implements
		CoordConversionOptions<CoordConverter<HelioprojectiveCartesianCoordinate, HeliocentricCartesianCoordinate>> {

		private ConversionOptions() {
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
	public HeliocentricCartesianCoordinate convert(HelioprojectiveCartesianCoordinate hpc) {
		return convert(hpc, DEFAULT_OPTIONS);
	}

	/**
	 * Convert with given custom options.
	 * 
	 * TODO: this does not take custom sun radius into account.
	 * @param hpc
	 *            source coordinate.
	 * @param opt
	 *            options.
	 * @return converted coordinates.
	 */
	public HeliocentricCartesianCoordinate convert(HelioprojectiveCartesianCoordinate hpc, ConversionOptions opt) {
		double cosx = Math.cos(arcsecToRadians(hpc.getX()));
		double sinx = Math.sin(arcsecToRadians(hpc.getX()));
		double cosy = Math.cos(arcsecToRadians(hpc.getY()));
		double siny = Math.sin(arcsecToRadians(hpc.getY()));

		double sunRadius = Constants.SUN_RADIUS.getValue();
		double sunDistance = hpc.getSunDistance();

		double q = sunDistance * cosy * cosx;
		double distance = q * q - sunDistance * sunDistance + sunRadius * sunRadius;
		distance = q - Math.sqrt(distance);

		double rx = distance * cosy * sinx;
		double ry = distance * siny;
		double rz = sunDistance - distance * cosy * cosx;
		return new HeliocentricCartesianCoordinate(rx, ry, rz);
	}

	private double arcsecToRadians(double arcsec) {
		return toRadians(arcsec / (60.0 * 60.0));
	}

	/**
	 * The default implementation just delegates to
	 * {@link CoordConverter#convert(Coordinate, CoordConversionOptions)},
	 */
	public List<HeliocentricCartesianCoordinate> convert(List<HelioprojectiveCartesianCoordinate> hpcList,
					ConversionOptions opt) {
		List<HeliocentricCartesianCoordinate> ret = new ArrayList<HeliocentricCartesianCoordinate>();
		for (HelioprojectiveCartesianCoordinate hpc : hpcList) {
			ret.add(convert(hpc, opt));
		}
		return ret;
	}
}
