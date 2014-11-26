package ch.fhnw.i4ds.helio.coordinate.api;

import java.util.List;

/**
 * Converter between two coordinate systems.
 * 
 * @author marco soldati at fhnw ch
 * 
 * @param <S>
 *            source coordinate
 * @param <T>
 *            target coordinate
 */
public interface CoordConverter<S extends Coordinate, T extends Coordinate> {

	/**
	 * Convert a source coordinate to a target system. The default options will
	 * be used.
	 * 
	 * @param source
	 *            Source coordinate, must not be null.
	 * @return Converted coordinate, will not be null.
	 */
	public T convert(S source);

	/**
	 * Convert a source coordinate to a target system. Custom options can be
	 * passed.
	 * 
	 * @param source
	 *            Source coordinate, must not be null.
	 * @param opt
	 *            Custom options. Check the converter implementation for details
	 *            on allowed options. Must not be null.
	 * @return Converted coordinate, will not be null.
	 */
	public T convert(S source, CoordConversionOptions<?> opt);

	/**
	 * Convert an ordered list of source coordinates to a target system.
	 * 
	 * @param sources
	 *            List of source coordinates
	 * @return List of converted coordinates.
	 */
	public List<T> convert(List<S> sources);

	/**
	 * Convert an ordered list of source coordinates to a target system.
	 * 
	 * @param sources
	 *            List of source coordinates
	 * @param opt
	 *            Custom options. Check the converter implementation for details
	 *            on allowed options. Must not be null.
	 * 
	 * @return List of converted coordinates.
	 */
	public List<T> convert(List<S> sources, CoordConversionOptions<?> opt);
}