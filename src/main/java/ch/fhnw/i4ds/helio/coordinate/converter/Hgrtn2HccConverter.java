package ch.fhnw.i4ds.helio.coordinate.converter;

import java.util.Map;

import ch.fhnw.i4ds.helio.coordinate.api.CoordConverter;
import ch.fhnw.i4ds.helio.coordinate.converter.option.ConverterOption;
import ch.fhnw.i4ds.helio.coordinate.coord.HGRTNCoordinate;
import ch.fhnw.i4ds.helio.coordinate.coord.HeliocentricCartesianCoordinate;

/**
 * Converter from Stonyhurst Heliographics (HG) to Heliocentric Cartesian
 * coordinates (HCC). This class is thread-safe.
 * 
 * @author marco soldati at fhnw ch
 * 
 */
public class Hgrtn2HccConverter extends AbstractConverter<HGRTNCoordinate, HeliocentricCartesianCoordinate> implements
				CoordConverter<HGRTNCoordinate, HeliocentricCartesianCoordinate> {

	static final ConverterOption<?>[] DEFAULT_OPTIONS = { };
	
	@Override
	protected void populateDefaultOptions(Map<ConverterOption<?>, Object> defaultOptions) {
		addDefaultOptions(defaultOptions, DEFAULT_OPTIONS);
	}
	
	/**
	 * Convert with given custom options.
	 * 
	 * @param hgrtn
	 *            source coordinate.
	 * @param opt
	 *            options.
	 * @return converted coordinates.
	 */
	@Override
	public HeliocentricCartesianCoordinate convert(HGRTNCoordinate hgrtn, Map<ConverterOption<?>, Object> opt) {
		double x = hgrtn.getX();
		double y = hgrtn.getY();
		double z = hgrtn.getZ();
		
		return new HeliocentricCartesianCoordinate(y, z, x);
	}
}
