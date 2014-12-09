package ch.fhnw.i4ds.helio.coordinate.util;

/**
 * Name and sign of unit.
 * @author marco soldati at fhnw ch
 *
 */
public enum Unit {
	/**
	 * Unit less values (e.g. Boolean);
	 */
	NONE(""),
	
	METER ("m"),
	ANGLE("");
	
	private final String symbol;

	private Unit(String symbol) {
		this.symbol = symbol;
	}
	
	public String getSymbol() {
		return symbol;
	}
	
	//public abstract <T> T convertTo(Unit unit, T value);
}
