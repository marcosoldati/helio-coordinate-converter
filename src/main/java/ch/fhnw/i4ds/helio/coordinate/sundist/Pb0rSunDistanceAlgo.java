package ch.fhnw.i4ds.helio.coordinate.sundist;

import ch.fhnw.i4ds.helio.coordinate.api.Angle;
import ch.fhnw.i4ds.helio.coordinate.sunpos.SunPosition;
import ch.fhnw.i4ds.helio.coordinate.util.Constants;
import ch.fhnw.i4ds.helio.coordinate.util.JulianDateUtils;

/**
 * Compute the geometric distance between Earth and Sun as implemented in
 * http://hesperia.gsfc.nasa.gov/ssw/gen/idl/solar/pb0r.pro.
 * 
 * @author marco soldati at fhnw ch
 *
 */
public class Pb0rSunDistanceAlgo implements SunDistanceAlgo {

	@Override
	public SunDistance computeDistance(SunPosition sunPosition) {
		return computeDistance(sunPosition, Observer.EARTH);
	}

	@Override
	public SunDistance computeDistance(SunPosition sunPosition, Observer observer) {
		assertObserverIsEarth(observer);
		
	    // number of Julian days since 2415020.0
	    double de = JulianDateUtils.julianDaySinceJ19000101(sunPosition.getDateTime());

		double longmed = sunPosition.getLongitude().degValue();
	    double appl = sunPosition.getApparentLongitude().degValue();
	    double oblt = sunPosition.getObliquity().degValue();

	    // form the aberrated longitude
	    double lambda = longmed - (20.50 / 3600.0);
	
	    // form longitude of ascending node of sun's equator on ecliptic
	    double node = 73.6666660 + (50.250 / 3600.0) * ((de / 365.250) + 50.0);
	    double arg = lambda - node;
	
	    // calculate P, the position angle of the pole
	    double p = Math.toDegrees(
	        Math.atan(-Math.tan(Math.toRadians(oblt)) * Math.cos(Math.toRadians(appl))) + 
	        Math.atan(-0.127220 * Math.cos(Math.toRadians(arg))));
	
	    // B0 the tilt of the axis...
	    double b = Math.toDegrees(Math.asin(0.12620 * Math.sin(Math.toRadians(arg))));

	    // ... and the semi-diameter
	    // Form the mean anomalies of Venus(MV),Earth(ME),Mars(MM),Jupiter(MJ)
	    // and the mean elongation of the Moon from the Sun(D).
	    double t = de / 36525.0;
	    double mv = 212.60 + (58517.80 * t % 360.0);
	    double me = 358.4760 + (35999.04980 * t % 360.0);
	    double mm = 319.50 + (19139.860 * t % 360.0);
	    double mj = 225.30 + (3034.690 * t % 360.0);
	    double d = 350.70 + (445267.110 * t % 360.0);

	    // Form the geocentric distance(r) and semi-diameter(sd)
	    double r = 1.0001410 - (0.0167480 - 0.00004180 * t) * Math.cos(Math.toRadians(me)) 
	        - 0.000140 * Math.cos(Math.toRadians(2.0 * me)) 
	        + 0.0000160 * Math.cos(Math.toRadians(58.30 + 2.0 * mv - 2.0 * me)) 
	        + 0.0000050 * Math.cos(Math.toRadians(209.10 + mv - me)) 
	        + 0.0000050 * Math.cos(Math.toRadians(253.80 - 2.0 * mm + 2.0 * me)) 
	        + 0.0000160 * Math.cos(Math.toRadians(89.50 - mj + me)) 
	        + 0.0000090 * Math.cos(Math.toRadians(357.10 - 2.0 * mj + 2.0 * me)) 
	        + 0.0000310 * Math.cos(Math.toRadians(d));

	    double sd_const = Constants.SUN_RADIUS.getValue() / Constants.AU.getValue();
	    double sd = Math.asin(sd_const / r) * 10800.0 / Math.PI;
	    
	    SunDistance sunDistance = new SunDistance(observer);
	    sunDistance.setB0(Angle.fromDeg(b));
	    sunDistance.setP(Angle.fromDeg(p));
	    sunDistance.setSemiDiameter(Angle.fromArcmin(sd));
	    sunDistance.setSunDistance(r);
	    sunDistance.setSunPosition(sunPosition);
		
		return sunDistance;
	}

	private void assertObserverIsEarth(Observer observer) {
		if (observer != Observer.EARTH) {
			throw new IllegalArgumentException("Argument 'observer' must be Earth.");
		}
		
	}

}
