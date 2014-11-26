package ch.fhnw.i4ds.helio.coordinate.service;

import ch.fhnw.i4ds.helio.coordinate.api.CoordConverter;

/**
 * Register coordinate converters.
 */
public interface CoordConverterRegistry {

	/**
	 * Add CoordinateConverter to this registry.
	 * A converter can be used for multiple sourceType-targetType pairs.
	 */
	void addConverter(Class<?> sourceType, Class<?> targetType, CoordConverter<?, ?> converter);
}
