package ch.fhnw.i4ds.helio.coordinate.converter;

import java.util.Map;

import ch.fhnw.i4ds.helio.coordinate.api.CoordConverter;
import ch.fhnw.i4ds.helio.coordinate.converter.option.ConverterOption;
import ch.fhnw.i4ds.helio.coordinate.coord.HGRTNCoordinate;
import ch.fhnw.i4ds.helio.coordinate.coord.HeliocentricCartesianCoordinate;

/**
 * Converter from Heliocentric Cartesian coordinates (HCC) to HGRTN coordinates.
 * 
 * @author marco soldati at fhnw ch
 * 
 */
public class Hcc2HgrtnConverter extends AbstractConverter<HeliocentricCartesianCoordinate, HGRTNCoordinate>
				implements CoordConverter<HeliocentricCartesianCoordinate, HGRTNCoordinate> {

	static final ConverterOption<?>[] DEFAULT_OPTIONS = { };
	
	@Override
	protected void populateDefaultOptions(Map<ConverterOption<?>, Object> defaultOptions) {
		addDefaultOptions(defaultOptions, DEFAULT_OPTIONS);
	}
	
	/**
	 * Just replace the order of the axes. 
	 * @param hcc
	 *            source coordinate. If z is UNDEFINED the z-coordinate is
	 *            assumed to be on the Sun.
	 * @param opt
	 *            options.
	 * @return converted coordinates.
	 */
	@Override
	public HGRTNCoordinate convert(HeliocentricCartesianCoordinate hcc, Map<ConverterOption<?>, Object> opt) {
		double x = hcc.getX();
		double y = hcc.getY();
		double z = hcc.getZ();

		return new HGRTNCoordinate(z, x, y);
	}
}
