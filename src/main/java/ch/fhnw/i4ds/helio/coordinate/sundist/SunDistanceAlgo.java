package ch.fhnw.i4ds.helio.coordinate.sundist;


import org.joda.time.DateTime;


/**
 * Compute the Distance of the Sun from a given observer.
 * @author marco soldati at fhnw ch
 *
 */
public interface SunDistanceAlgo {
	
	/**
	 * Compute the sun distance for Earth at a given time.
	 * @param date the date to compute the distance for.
	 * @return the Distance and apparent diameter of the Sun.
	 */
	public SunDistance computeDistance(DateTime date);
	
	/**
	 * Compute the sun distance for a given observer (e.g.Earth) at a given time.
	 * Implementation may support only a specific subset of observers.
	 * @param date the date to compute the distance for.
	 * @param observer the observer of the Sun.
	 * @return the Distance and apparent diameter of the Sun.
	 */
	public SunDistance computeDistance(DateTime date, Observer observer);
}
