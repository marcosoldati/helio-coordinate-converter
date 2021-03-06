package ch.fhnw.i4ds.helio.coordinate.converter.option;

import ch.fhnw.i4ds.helio.coordinate.api.Angle;
import ch.fhnw.i4ds.helio.coordinate.api.Distance;
import ch.fhnw.i4ds.helio.coordinate.util.Constants;
import ch.fhnw.i4ds.helio.coordinate.util.Unit;

public class ConverterOptions {

	public static ConverterOption<Angle> B0 = newOption("b0",
					"Tilt of the solar North rotational axis toward the observer (heliographic"
									+ "latitude of the observer). This is a synonym of SOLAR_B0, HGLT_OBS, and"
									+ "CRLT_OBS. Default is 0.", new Angle(0.0), Unit.ANGLE);

	public static ConverterOption<Angle> L0 = newOption("l0",
					"Carrington longitude of central meridian as seen from Earth. Default is 0", new Angle(0.0), Unit.ANGLE);

	public static ConverterOption<Boolean> OCCULTATION = newOption("occultation",
					"If true set all points behind the Sun (e.g. not visible) to Nan. Defaults to false", false,
					Unit.NONE);

	public static ConverterOption<Double> SUN_RADIUS = newOption("sunRadius", "Radius of sun in meters. Defaults to "
					+ Constants.SUN_RADIUS.getValue(), Constants.SUN_RADIUS.getValue(), Unit.METER);

	public static ConverterOption<Distance> SUN_DISTANCE = newOption("sunDistance",
					"Distance between the observer and the sun", Distance.fromAU(1), Unit.NONE);
	
	// helper that could do some sanity checks.
	private static <T> ConverterOption<T> newOption(String name, String description, T value, Unit unit) {
		ConverterOption<T> option = new ConverterOption<T>(name, description, value, unit);
		return option;
	}
}
