package ch.fhnw.i4ds.helio.coordinate.api;

import ch.fhnw.i4ds.helio.coordinate.util.Constants;

public class Distance {

	/**
	 * Distance in Meters.
	 */
	private double meters = 0;
	
	public static Distance fromMeters(double meters) {
		return new Distance(meters);
	}
	
	public static Distance fromAU(double au) {
		return new Distance(Constants.AU.getValue() * au);
	}
	
	public Distance() {
		this.meters = 0;
	}
	
	public Distance(double meters) {
		this.meters = meters;
	}
	
	/**
	 * Get distance in meters
	 * @return in meters
	 */
	public double inMeters() {
		return meters;
	}
	
	/**
	 * Get distance in fraction of AU.
	 * @return in AU.
	 */
	public double inAU() {
		return meters / Constants.AU.getValue();
	}
	
	/**
	 * Set distance in meters.
	 * @param meters distance in meters.
	 */
	public void setMeters(double meters) {
		this.meters = meters;
	}
	
	/**
	 * Set distance as fraction of 1AU
	 * @param au fraction of 1AU.
	 */
	public void setAU(double au) {
		this.meters = Constants.AU.getValue() * au;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(meters).append("m");
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(meters);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Distance other = (Distance) obj;
		if (Double.doubleToLongBits(meters) != Double.doubleToLongBits(other.meters))
			return false;
		return true;
	}
}
