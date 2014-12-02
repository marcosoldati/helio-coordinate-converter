package ch.fhnw.i4ds.helio.coordinate.converter;

import static java.lang.Math.toRadians;

import java.util.Map;

import ch.fhnw.i4ds.helio.coordinate.api.CoordConverter;
import ch.fhnw.i4ds.helio.coordinate.converter.option.ConverterOption;
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

	@Override
	protected void populateDefaultOptions(Map<ConverterOption<?>, Object> defaultOptions) {
		// no options available.
	}

	/**
	 * Convert with given custom options. Implements Eq. (15) of Thompson (2006), A&A, 449, 791.
	 * 
	 * TODO: this does not take custom sun radius into account.
	 * @param hpc
	 *            source coordinate5
	 * @param opt
	 *            options.
	 * @return converted coordinates.
	 */
	@Override
	public HeliocentricCartesianCoordinate convert(HelioprojectiveCartesianCoordinate hpc,
					Map<ConverterOption<?>, Object> opt) {
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
}
