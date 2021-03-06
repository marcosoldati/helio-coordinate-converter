package ch.fhnw.i4ds.helio.coordinate.converter;

import static ch.fhnw.i4ds.helio.coordinate.converter.option.ConverterOptions.B0;
import static ch.fhnw.i4ds.helio.coordinate.converter.option.ConverterOptions.L0;
import static ch.fhnw.i4ds.helio.coordinate.converter.option.ConverterOptions.OCCULTATION;
import static ch.fhnw.i4ds.helio.coordinate.converter.option.ConverterOptions.SUN_RADIUS;

import java.util.Map;

import ch.fhnw.i4ds.helio.coordinate.api.Angle;
import ch.fhnw.i4ds.helio.coordinate.api.CoordConverter;
import ch.fhnw.i4ds.helio.coordinate.converter.option.ConverterOption;
import ch.fhnw.i4ds.helio.coordinate.coord.HeliocentricCartesianCoordinate;
import ch.fhnw.i4ds.helio.coordinate.coord.HeliographicCoordinate;

/**
 * Converter from Stonyhurst Heliographics (HG) to Heliocentric Cartesian
 * coordinates (HCC). This class is thread-safe.
 * 
 * @author marco soldati at fhnw ch
 * 
 */
public class Hg2HccConverter extends AbstractConverter<HeliographicCoordinate, HeliocentricCartesianCoordinate> implements
				CoordConverter<HeliographicCoordinate, HeliocentricCartesianCoordinate> {

	static final ConverterOption<?>[] DEFAULT_OPTIONS = { B0, L0, SUN_RADIUS, OCCULTATION };
	
	@Override
	protected void populateDefaultOptions(Map<ConverterOption<?>, Object> defaultOptions) {
		addDefaultOptions(defaultOptions, DEFAULT_OPTIONS);
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
	@Override
	public HeliocentricCartesianCoordinate convert(HeliographicCoordinate hg, Map<ConverterOption<?>, Object> opt) {
		double b0 = ((Angle) opt.get(B0)).radValue();
		double l0 = ((Angle) opt.get(L0)).radValue();
		double sunRadius = (Double) opt.get(SUN_RADIUS);
		boolean occultation = (Boolean) opt.get(OCCULTATION);
		
		double lon = hg.getHgLongitude().radValue();
		double lat = hg.getHgLatitude().radValue();

		double cosb = Math.cos(b0);
		double sinb = Math.sin(b0);

		lon -= l0;

		double cosx = Math.cos(lon);
		double sinx = Math.sin(lon);
		double cosy = Math.cos(lat);
		double siny = Math.sin(lat);

		// Perform the conversion.
		double x = sunRadius * cosy * sinx;
		double y = sunRadius * (siny * cosb - cosy * cosx * sinb);
		double z = sunRadius * (siny * sinb + cosy * cosx * cosb);

		if (occultation && z < 0) {
			x = Double.NaN;
			y = Double.NaN;
		}

		return new HeliocentricCartesianCoordinate(x, y, z);
	}
}
