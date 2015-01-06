package ch.fhnw.i4ds.helio.coordinate.util;

import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;

public class JulianDateUtils {
	/**
	 * Julian date for January, 1st, 1900.
	 */
	private static final double JAN_1_1900 = 2415020.0;

	private JulianDateUtils() {
	}

	public static double julianDaySinceJ19000101(DateTime date) {
		double dd = DateTimeUtils.toJulianDay(date.getMillis()) - JAN_1_1900;
		return dd;
	}
}
