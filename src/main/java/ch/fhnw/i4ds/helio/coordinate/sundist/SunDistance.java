package ch.fhnw.i4ds.helio.coordinate.sundist;

import ch.fhnw.i4ds.helio.coordinate.api.Angle;
import ch.fhnw.i4ds.helio.coordinate.sunpos.SunPosition;

public class SunDistance {

	private final Observer observer;

	private SunPosition sunPosition;

	private Angle p;

	private Angle b0;

	private Angle semiDiameter;

	private double sunDistance;

	public SunDistance(Observer observer) {
		this.observer = observer;
	}

	/**
	 * "Absolute" position of the sun for a given time.
	 * 
	 * @return the Sun's position
	 */
	public SunPosition getSunPosition() {
		return sunPosition;
	}

	/**
	 * "Absolute" position of the sun for a given time.
	 */
	public void setSunPosition(SunPosition sunPosition) {
		this.sunPosition = sunPosition;
	}

	/**
	 * Solar P (position angle of pole) (degrees)
	 * 
	 * @return p
	 */
	public Angle getP() {
		return p;
	}

	/**
	 * Solar P (position angle of pole) (degrees)
	 * 
	 * @param p
	 *            p
	 */
	public void setP(Angle p) {
		this.p = p;
	}

	/**
	 * latitude of point at disk centre (degrees)
	 * 
	 * @return b0
	 */
	public Angle getB0() {
		return b0;
	}

	/**
	 * latitude of point at disk centre (degrees)
	 * 
	 * @param b0
	 *            b0
	 */
	public void setB0(Angle b0) {
		this.b0 = b0;
	}

	/**
	 * semi-diameter of the solar disk in arcminutes
	 * 
	 * @return
	 */
	public Angle getSemiDiameter() {
		return semiDiameter;
	}

	/**
	 * semi-diameter of the solar disk in arcminutes
	 * 
	 * @param sd
	 */
	public void setSemiDiameter(Angle sd) {
		this.semiDiameter = sd;
	}

	/**
	 * Distance from Sun in Meters.
	 * 
	 * @param sunDistance
	 *            Sun distance in meters
	 */
	public void setSunDistance(double sunDistance) {
		this.sunDistance = sunDistance;
	}

	/**
	 * Sun distance in Meters
	 * 
	 * @return Sun distance in meters.
	 */
	public double getSunDistance() {
		return sunDistance;
	}

	public Observer getObserver() {
		return observer;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SunDistance [observer=").append(observer);
		builder.append(", p=").append(p);
		builder.append(", b0=").append(b0);
		builder.append(", semiDiameter=").append(semiDiameter);
		builder.append(", sunDistance=").append(sunDistance).append("]");
		return builder.toString();
	}
}
