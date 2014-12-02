package ch.fhnw.i4ds.helio.coordinate.sunpos;

import org.joda.time.DateTime;

public interface SunPositionAlgo {

	/**
	 * Compute the sun position for a given time.
	 * @param date the time, make sure to set timezone UTC.
	 * @return the sun position.
	 */
	public SunPosition computeSunPos(DateTime date);
}
