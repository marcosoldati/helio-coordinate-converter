package ch.fhnw.i4ds.helio.coordinate.service;

import java.util.List;

import ch.fhnw.i4ds.helio.coordinate.api.Coordinate;

/**
 * Facade interface to registered converters.
 * @author mercuron
 *
 */
public interface CoordConversionService {

	public boolean canConvert(Class<? extends Coordinate> sourceType, Class<? extends Coordinate> targetType);
	
	public <T extends Coordinate> T convert(Coordinate source, Class<T> targetType);
	
	public <T extends Coordinate> List<T> convert(List<Coordinate> source, Class<T> targetType);
	
}
