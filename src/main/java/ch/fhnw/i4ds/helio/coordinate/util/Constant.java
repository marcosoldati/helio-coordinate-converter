package ch.fhnw.i4ds.helio.coordinate.util;

public class Constant {
	private final String abbrev;
	private final String name;
	private final double value;
	private final Unit unit;
	private final double uncertainty;
	private final String reference;
	
	public Constant(String abbrev, String name, double value, Unit unit, double uncertainty, String reference) {
		this.abbrev = abbrev;
		this.name = name;
		this.value = value;
		this.unit = unit;
		this.uncertainty = uncertainty;
		this.reference = reference;
	}
	
	public String getAbbrev() {
		return abbrev;
	}
	
	public String getName() {
		return name;
	}
	
	public double getValue() {
		return value;
	}
	
	public Unit getUnit() {
		return unit;
	}
	
	public double getUncertainty() {
		return uncertainty;
	}
	
	public String getReference() {
		return reference;
	}
}
