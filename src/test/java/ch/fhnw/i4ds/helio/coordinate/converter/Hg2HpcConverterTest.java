package ch.fhnw.i4ds.helio.coordinate.converter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ch.fhnw.i4ds.helio.coordinate.converter.option.ConverterOption;
import ch.fhnw.i4ds.helio.coordinate.converter.option.ConverterOptions;
import ch.fhnw.i4ds.helio.coordinate.coord.HeliographicCoordinate;
import ch.fhnw.i4ds.helio.coordinate.coord.HelioprojectiveCartesianCoordinate;

public class Hg2HpcConverterTest {

	private Hg2HpcConverter converter;

	@Before
	public void setup() {
		converter = new Hg2HpcConverter();
	}

	@Test
	public void convert_custom_sunradius() {
		Map<ConverterOption<?>, Object> opt = converter.getCustomOptions();

		HeliographicCoordinate hg = new HeliographicCoordinate(34.0, 45.0);
		HelioprojectiveCartesianCoordinate hpc = converter.convert(hg, opt);
		Assert.assertEquals(380.21852263743313, hpc.getX(), 1e-11);
		Assert.assertEquals(679.9387612695175, hpc.getY(), 1e-11);
	}

	@Test
	public void convert_list() {
		Map<ConverterOption<?>, Object> opt = converter.getCustomOptions();
		opt.put(ConverterOptions.OCCULTATION, true);

		HeliographicCoordinate hg1 = new HeliographicCoordinate(34.0, 96.0);
		HeliographicCoordinate hg2 = new HeliographicCoordinate(55.0, 56.0);
		List<HelioprojectiveCartesianCoordinate> hccList = converter.convert(Arrays.asList(hg1, hg2), opt);
		
		// coord 1
		Assert.assertTrue(Double.isNaN(hccList.get(0).getX()));
		Assert.assertTrue(Double.isNaN(hccList.get(0).getY()));

		// coord 2
		Assert.assertEquals(439.9217730935452, hccList.get(1).getX(), 1e-11);
		Assert.assertEquals(796.1978740233601, hccList.get(1).getY(), 1e-11);
	}
}