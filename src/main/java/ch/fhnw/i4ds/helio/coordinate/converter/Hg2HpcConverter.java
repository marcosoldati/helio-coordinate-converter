package ch.fhnw.i4ds.helio.coordinate.converter;

import java.util.Map;

import ch.fhnw.i4ds.helio.coordinate.api.CoordConverter;
import ch.fhnw.i4ds.helio.coordinate.converter.option.ConverterOption;
import ch.fhnw.i4ds.helio.coordinate.coord.HeliocentricCartesianCoordinate;
import ch.fhnw.i4ds.helio.coordinate.coord.HeliographicCoordinate;
import ch.fhnw.i4ds.helio.coordinate.coord.HelioprojectiveCartesianCoordinate;

/**
 * Converter from Stonyhurst Heliographic coordinates (HG) to
 * Helioprojetive-Cartesian (HPC) Coordinates. This class is thread-safe.
 * 
 * @author marco soldati at fhnw ch
 * 
 */
public class Hg2HpcConverter extends
				AbstractConverter<HeliographicCoordinate, HelioprojectiveCartesianCoordinate> implements
				CoordConverter<HeliographicCoordinate, HelioprojectiveCartesianCoordinate> {
	
	private final Hg2HccConverter hg2HccConverter = new Hg2HccConverter();
	private final Hcc2HpcConverter hcc2HpcConverter = new Hcc2HpcConverter();

	@Override
	protected void populateDefaultOptions(Map<ConverterOption<?>, Object> defaultOptions) {
		addDefaultOptions(defaultOptions, Hg2HccConverter.DEFAULT_OPTIONS);
		addDefaultOptions(defaultOptions, Hcc2HpcConverter.DEFAULT_OPTIONS);
	}
	
	/**
	 * Convert from Heliographic coordinates (HG) to Helioprojective-Cartesian
     * (HPC).
	 * 
	 * @param hg
	 *            source coordinate.
	 * @param opt
	 *            options.
	 * @return converted coordinates.
	 */
	@Override
	public HelioprojectiveCartesianCoordinate convert(HeliographicCoordinate hg, Map<ConverterOption<?>, Object> opt) {
		HeliocentricCartesianCoordinate hcc = hg2HccConverter.convert(hg, opt);
		HelioprojectiveCartesianCoordinate hpc = hcc2HpcConverter.convert(hcc, opt);
		return hpc;
	}
}
