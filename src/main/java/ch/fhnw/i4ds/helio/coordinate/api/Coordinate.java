package ch.fhnw.i4ds.helio.coordinate.api;

/**
 * Data container for a specific coordinate.
 * @author marco soldati at fhnw ch
 */
public interface Coordinate {
	/**
	 * Acronym for the coordinate system.
	 */
	public String getCoordinateSystemAcronym();
	
	/**
	 * Description of the coordinate system.
	 */
	public String getCoordinateSystemDescription();
	
	@Override
	public String toString();
	
	@Override
	public int hashCode();
	
	@Override
	public boolean equals(Object obj);
}
