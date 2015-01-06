package ch.fhnw.i4ds.helio.coordinate.sundist;

import ch.fhnw.i4ds.helio.coordinate.sunpos.SunPosition;

/**
 * Compute the Distance of the Sun from a given observer.
 * @author marco soldati at fhnw ch
 *
 */
public interface SunDistanceAlgo {
	
	/**
	 * Compute the sun distance relative to a given sun position and a default observer (e.g Earth).
	 * @param sunPosition the sun position and current time.
	 * @return the Distance and apparent diameter of the Sun.
	 */
	public SunDistance computeDistance(SunPosition sunPosition);
	
	/**
	 * Compute the sun distance relative to a given sun position and observer (e.g Earth).
	 * Implementation may support only a specific subset of observers.
	 * @param sunPosition the sun position and current time.
	 * @param observer the observer of the Sun.
	 * @return the Distance and apparent diameter of the Sun.
	 */
	public SunDistance computeDistance(SunPosition sunPosition, Observer observer);
}
