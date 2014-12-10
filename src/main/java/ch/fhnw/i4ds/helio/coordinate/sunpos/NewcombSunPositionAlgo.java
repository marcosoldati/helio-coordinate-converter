package ch.fhnw.i4ds.helio.coordinate.sunpos;

import static java.lang.Math.asin;
import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toRadians;

import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;

import ch.fhnw.i4ds.helio.coordinate.api.Angle;

/**
 * Use a simplified method of Newcomb's Sun to compute the Sun's position. This
 * method is adapted from
 * http://hesperia.gsfc.nasa.gov/ssw/gen/idl/solar/sun_pos.pro.
 * 
 * @author marco solati at fhnw ch
 * 
 */
public class NewcombSunPositionAlgo implements SunPositionAlgo {

	/**
	 * Julian date for January, 1st, 1900.
	 */
	private static final double JAN_1_1900 = 2415020.0;

	@Override
	public SunPosition computeSunPos(DateTime date) {
		double dd = DateTimeUtils.toJulianDay(date.getMillis()) - JAN_1_1900;

		// form time in Julian centuries from 1900.0
		double t = dd / 36525.0;

		// form sun's mean longitude
		double l = (279.6966780 + (36000.7689250 * t) % 360.00) * 3600.0;

		// allow for ellipticity of the orbit (equation of centre) using the
		// Earth's
		// mean anomaly ME
		double me = 358.4758440 + (35999.049750 * t) % 360.0;
		double ellcor = (6910.10 - 17.20 * t) * sin(toRadians(me)) + 72.30 * sin(toRadians(2.0 * me));
		l = l + ellcor;

		// allow for the Venus perturbations using the mean anomaly of Venus MV
		double mv = 212.603219 + (58517.8038750 * t) % 360.0;
		double vencorr = 4.80 * cos(toRadians(299.10170 + mv - me)) + 5.50
						* cos(toRadians(148.31330 + 2.0 * mv - 2.0 * me)) + 2.50
						* cos(toRadians(315.94330 + 2.0 * mv - 3.0 * me)) + 1.60
						* cos(toRadians(345.25330 + 3.0 * mv - 4.0 * me)) + 1.00
						* cos(toRadians(318.15000 + 3.0 * mv - 5.0 * me));
		l = l + vencorr;

		// Allow for the Mars perturbations using the mean anomaly of Mars MM
		double mm = 319.5294250 + (19139.858500 * t) % 360.0;
		double marscorr = 2.0 * cos(toRadians(343.88830 - 2.0 * mm + 2.0 * me)) + 1.80
						* cos(toRadians(200.40170 - 2.0 * mm + me));
		l = l + marscorr;

		// Allow for the Jupiter perturbations using the mean anomaly of Jupiter
		// MJ
		double mj = 225.3283280 + (3034.69202390 * t % 360.00);
		double jupcorr = 7.20 * cos(toRadians(179.53170 - mj + me)) + 2.60 * cos(toRadians(263.21670 - mj)) + 2.70
						* cos(toRadians(87.14500 - 2.0 * mj + 2.0 * me)) + 1.60
						* cos(toRadians(109.49330 - 2.0 * mj + me));
		l = l + jupcorr;

		// Allow for the Moons perturbations using the mean elongation of the
		// Moon
		// from the Sun D
		double d = 350.73768140 + (445267.114220 * t % 360.0);
		double mooncorr = 6.50 * sin(toRadians(d));
		l = l + mooncorr;

		// Note the original code is
		// longterm = + 6.4d0 * sin(( 231.19d0 + 20.20d0 * t )*!dtor)
		double longterm = 6.40 * sin(toRadians(231.190 + 20.20 * t));
		l = l + longterm;
		l = (l + 2592000.0) % 1296000.0;
		double longmed = l / 3600.0;

		// Allow for Aberration
		l = l - 20.5;

		// Allow for Nutation using the longitude of the Moons mean node OMEGA
		double omega = 259.1832750 - (1934.1420080 * t) % 360.0;
		l = l - 17.20 * sin(toRadians(omega));

		// Form the True Obliquity
		double oblt = 23.4522940 - 0.01301250 * t + (9.20 * cos(toRadians(omega))) / 3600.0;

		// Form Right Ascension and Declination
		l = l / 3600.0;

		double ra = atan2(sin(toRadians(l)) * cos(toRadians(oblt)), cos(toRadians(l)));

		if (ra < 0.0) {
			ra = ra + 360.0;
		}

		double dec = asin(sin(toRadians(l)) * sin(toRadians(oblt)));

		// convert the internal variables to those required by the result.
		SunPosition sunPosition = new SunPosition(date);
		sunPosition.setLongitude(Angle.fromDeg(longmed));
		sunPosition.setRa(Angle.fromRad(ra));
		sunPosition.setDec(Angle.fromRad(dec));
		sunPosition.setApparentLongitude(Angle.fromDeg(l));
		sunPosition.setObliquity(Angle.fromDeg(oblt));

		return sunPosition;
	}
}
