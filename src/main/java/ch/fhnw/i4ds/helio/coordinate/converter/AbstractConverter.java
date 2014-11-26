package ch.fhnw.i4ds.helio.coordinate.converter;

import java.util.ArrayList;
import java.util.List;

import ch.fhnw.i4ds.helio.coordinate.api.CoordConversionOptions;
import ch.fhnw.i4ds.helio.coordinate.api.CoordConverter;
import ch.fhnw.i4ds.helio.coordinate.api.Coordinate;

/**
 * Abstract base template for a converter. Implementations must be Thread-safe.
 * 
 * @author marco soldati at fhnw ch
 * 
 */
public abstract class AbstractConverter<S extends Coordinate, T extends Coordinate> implements CoordConverter<S, T> {

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

	@Override
	public T convert(S source, CoordConversionOptions<?> opt) {
		throw new IllegalArgumentException("Invalid conversion options for this converter: " + opt.getClass().getName());
	}

	@Override
	public List<T> convert(List<S> sources, CoordConversionOptions<?> opt) {
		throw new IllegalArgumentException("Invalid conversion options for this converter: " + opt.getClass().getName());
	}

}
