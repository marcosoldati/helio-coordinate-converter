package ch.fhnw.i4ds.helio.coordinate.converter;

import static ch.fhnw.i4ds.helio.coordinate.converter.option.ConverterOptions.SUN_DISTANCE;

import java.util.Map;

import ch.fhnw.i4ds.helio.coordinate.api.Angle;
import ch.fhnw.i4ds.helio.coordinate.api.CoordConverter;
import ch.fhnw.i4ds.helio.coordinate.api.Distance;
import ch.fhnw.i4ds.helio.coordinate.converter.option.ConverterOption;
import ch.fhnw.i4ds.helio.coordinate.coord.HeliocentricCartesianCoordinate;
import ch.fhnw.i4ds.helio.coordinate.coord.HelioprojectiveCartesianCoordinate;
import ch.fhnw.i4ds.helio.coordinate.util.Constants;

/**
 * Converter from Heliocentric Cartesian coordinates (HCC) to
 * Helioprojetive-Cartesian (HPC) Coordinates. This class is thread-safe.
 * 
 * @author marco soldati at fhnw ch
 * 
 */
public class Hcc2HpcConverter extends
				AbstractConverter<HeliocentricCartesianCoordinate, HelioprojectiveCartesianCoordinate> implements
				CoordConverter<HeliocentricCartesianCoordinate, HelioprojectiveCartesianCoordinate> {

	static final ConverterOption<?>[] DEFAULT_OPTIONS = { SUN_DISTANCE };
	
	@Override
	protected void populateDefaultOptions(Map<ConverterOption<?>, Object> defaultOptions) {
		addDefaultOptions(defaultOptions, SUN_DISTANCE);
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
	@Override
	public HelioprojectiveCartesianCoordinate convert(HeliocentricCartesianCoordinate hcc,
					Map<ConverterOption<?>, Object> opt) {
		double x = hcc.getX();
		double y = hcc.getY();
		double z = hcc.getZ();
		double sunRadius = Constants.SUN_RADIUS.getValue();
		Distance sunDistance = ((Distance) opt.get(SUN_DISTANCE));

		if (Double.isNaN(z)) {
			// Calculate the z coordinate by assuming that it is on the surface
			// of the Sun
			z = Math.sqrt(sunRadius * sunRadius - x * x - y * y);
		}

		double zeta = sunDistance.inMeters() - z;
		double distance = Math.sqrt(x * x + y * y + zeta * zeta);
		Angle hpcx = Angle.fromRad((Math.atan2(x, zeta)));
		Angle hpcy = Angle.fromRad(Math.asin(y / distance));

		return new HelioprojectiveCartesianCoordinate(hpcx, hpcy, sunDistance);
	}
}
