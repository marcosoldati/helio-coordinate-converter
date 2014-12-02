package ch.fhnw.i4ds.helio.coordinate.converter;

import java.util.Map;

import ch.fhnw.i4ds.helio.coordinate.api.CoordConverter;
import ch.fhnw.i4ds.helio.coordinate.converter.option.ConverterOption;
import ch.fhnw.i4ds.helio.coordinate.coord.HeliocentricCartesianCoordinate;
import ch.fhnw.i4ds.helio.coordinate.coord.HeliographicCoordinate;
import ch.fhnw.i4ds.helio.coordinate.coord.HelioprojectiveCartesianCoordinate;

/**
 * Converter from Helioprojective-Cartesian (HPC) to Stonyhurst Heliographic
 * (HG) Coordinates. This class is thread-safe.
 * 
 * @author marco soldati at fhnw ch
 * 
 */
public class Hpc2HgConverter extends AbstractConverter<HelioprojectiveCartesianCoordinate, HeliographicCoordinate>
				implements CoordConverter<HelioprojectiveCartesianCoordinate, HeliographicCoordinate> {

	private final Hcc2HgConverter hcc2HgConverter = new Hcc2HgConverter();
	private final Hpc2HccConverter hpc2HccConverter = new Hpc2HccConverter();

	@Override
	protected void populateDefaultOptions(Map<ConverterOption<?>, Object> defaultOptions) {
		addDefaultOptions(defaultOptions, Hcc2HgConverter.DEFAULT_OPTIONS);
		addDefaultOptions(defaultOptions, Hpc2HccConverter.DEFAULT_OPTIONS);
	}

	@Override
	public HeliographicCoordinate convert(HelioprojectiveCartesianCoordinate hpc, Map<ConverterOption<?>, Object> opt) {
		HeliocentricCartesianCoordinate hcc = hpc2HccConverter.convert(hpc, opt);
		HeliographicCoordinate hg = hcc2HgConverter.convert(hcc, opt);
		return hg;
	}
}
