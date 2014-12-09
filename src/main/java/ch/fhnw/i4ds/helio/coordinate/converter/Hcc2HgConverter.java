package ch.fhnw.i4ds.helio.coordinate.converter;

import static ch.fhnw.i4ds.helio.coordinate.converter.option.ConverterOptions.B0;
import static ch.fhnw.i4ds.helio.coordinate.converter.option.ConverterOptions.L0;
import static ch.fhnw.i4ds.helio.coordinate.converter.option.ConverterOptions.SUN_RADIUS;

import java.util.Map;

import ch.fhnw.i4ds.helio.coordinate.api.Angle;
import ch.fhnw.i4ds.helio.coordinate.api.CoordConverter;
import ch.fhnw.i4ds.helio.coordinate.converter.option.ConverterOption;
import ch.fhnw.i4ds.helio.coordinate.coord.HeliocentricCartesianCoordinate;
import ch.fhnw.i4ds.helio.coordinate.coord.HeliographicCoordinate;

/**
 * Converter from Heliocentric Cartesian coordinates (HCC) to Stonyhurst
 * Heliographics (HG). This class is thread-safe.
 * 
 * @author marco soldati at fhnw ch
 * 
 */
public class Hcc2HgConverter extends AbstractConverter<HeliocentricCartesianCoordinate, HeliographicCoordinate>
				implements CoordConverter<HeliocentricCartesianCoordinate, HeliographicCoordinate> {

	static final ConverterOption<?>[] DEFAULT_OPTIONS = { B0, L0, SUN_RADIUS };
	
	@Override
	protected void populateDefaultOptions(Map<ConverterOption<?>, Object> defaultOptions) {
		addDefaultOptions(defaultOptions, DEFAULT_OPTIONS);
	}
	
	/**
	 * Convert with given custom options. Implements Eq. (12) of Thompson
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
	public HeliographicCoordinate convert(HeliocentricCartesianCoordinate hcc, Map<ConverterOption<?>, Object> opt) {
		double sunRadius = (Double)opt.get(SUN_RADIUS);
		double b0 = (Double)opt.get(B0);
		double l0 = (Double)opt.get(L0);
		double x = hcc.getX();
		double y = hcc.getY();
		double z = hcc.getZ();
		if (Double.isNaN(z)) {
			z = Math.sqrt(sunRadius * sunRadius - x * x - y * y);
		}

		double cosb = Math.cos(b0);
		double sinb = Math.sin(b0);

		double hecRadius = Math.sqrt(x * x + y * y + z * z);
		Angle hgLongitude = Angle.fromRad(Math.atan2(x, z * cosb - y * sinb) + l0);
		Angle hgLatitude = Angle.fromRad(Math.asin((y * cosb + z * sinb) / hecRadius));

		return new HeliographicCoordinate(hgLongitude, hgLatitude);
	}
}
