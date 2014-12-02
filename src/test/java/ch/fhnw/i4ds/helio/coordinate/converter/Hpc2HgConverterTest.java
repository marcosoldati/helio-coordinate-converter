package ch.fhnw.i4ds.helio.coordinate.converter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ch.fhnw.i4ds.helio.coordinate.coord.HeliographicCoordinate;
import ch.fhnw.i4ds.helio.coordinate.coord.HelioprojectiveCartesianCoordinate;

public class Hpc2HgConverterTest {
	private Hpc2HgConverter converter;
	
	@Before
	public void setup() {
		converter = new Hpc2HgConverter();
	}
	
	@Test
	public void convert_default_options() {
		HelioprojectiveCartesianCoordinate hpc = new HelioprojectiveCartesianCoordinate(382, 748);
		HeliographicCoordinate hg = converter.convert(hpc);
		
		Assert.assertEquals(39.266211004047022, hg.getHgLongitudeDegree(), 1e-9);
		Assert.assertEquals(51.100710468438109, hg.getHgLatitudeDegree(), 1e-9);
	}
}