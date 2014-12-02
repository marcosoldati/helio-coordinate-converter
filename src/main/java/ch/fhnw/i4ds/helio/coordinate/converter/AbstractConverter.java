package ch.fhnw.i4ds.helio.coordinate.converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import ch.fhnw.i4ds.helio.coordinate.api.CoordConverter;
import ch.fhnw.i4ds.helio.coordinate.api.Coordinate;
import ch.fhnw.i4ds.helio.coordinate.converter.option.ConverterOption;

/**
 * Abstract base template for a converter. Implementations must be Thread-safe.
 * 
 * @author marco soldati at fhnw ch
 * 
 */
public abstract class AbstractConverter<S extends Coordinate, T extends Coordinate> implements CoordConverter<S, T> {

	private final Map<ConverterOption<?>, Object> defaultOptions = new LinkedHashMap<ConverterOption<?>, Object>();

	public AbstractConverter() {
		populateDefaultOptions(defaultOptions);
	}

	@Override
	public T convert(S source) {
		return convert(source, getDefaultOptions());
	}

	/**
	 * The default implementation just delegates to
	 * {@link CoordConverter#convert(Coordinate)},
	 */
	@Override
	public List<T> convert(List<S> sources) {
		List<T> ret = new ArrayList<T>();
		for (S s : sources) {
			ret.add(convert(s));
		}
		return ret;
	}

	/**
	 * The default implementation just delegates to
	 * {@link CoordConverter#convert(Coordinate, Map))},
	 */
	@Override
	public List<T> convert(List<S> sources, Map<ConverterOption<?>, Object> opt) {
		List<T> ret = new ArrayList<T>();
		for (S s : sources) {
			ret.add(convert(s, opt));
		}
		return ret;
	}

	/**
	 * Populate the default options object with values. Implementations can
	 * delegate to {@link #addDefaultOptions(Map, ConverterOption...)} or
	 * directly add options to the map. Duplicate entries are silently
	 * overwritten.
	 * 
	 * @param defaultOptions
	 *            the default options object.
	 */
	protected abstract void populateDefaultOptions(Map<ConverterOption<?>, Object> defaultOptions);

	/**
	 * Helper method to add an array of default options to the given map of
	 * default options.
	 * 
	 * @param defaultOptions
	 *            the map to populate.
	 * @param options
	 *            the list of options to add.
	 */
	protected static void addDefaultOptions(Map<ConverterOption<?>, Object> defaultOptions,
					ConverterOption<?>... options) {
		addDefaultOptions(defaultOptions, Arrays.asList(options));
	}

	/**
	 * Helper method to add a collection of default options to the given map of
	 * default options.
	 * 
	 * @param defaultOptions
	 *            the map to populate.
	 * @param options
	 *            a set of options to add.
	 */
	protected static void addDefaultOptions(Map<ConverterOption<?>, Object> defaultOptions,
					Collection<ConverterOption<?>> options) {
		for (ConverterOption<?> option : options) {
			defaultOptions.put(option, option.getValue());
		}
	}

	/**
	 * Get an unmodifiable set of options.
	 * 
	 * @return the default options.
	 */
	protected Map<ConverterOption<?>, Object> getDefaultOptions() {
		return Collections.unmodifiableMap(defaultOptions);
	}

	/**
	 * Get a modifiable set of options. Consider caching this object
	 * as this method does a shallow copy of the default setting on every invocation.
	 * 
	 * @return the modifiable options.
	 */
	public Map<ConverterOption<?>, Object> getCustomOptions() {
		return new LinkedHashMap<ConverterOption<?>, Object>(defaultOptions);
	}

}
