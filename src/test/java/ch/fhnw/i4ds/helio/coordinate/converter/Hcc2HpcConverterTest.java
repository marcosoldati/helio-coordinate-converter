package ch.fhnw.i4ds.helio.coordinate.converter;

import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ch.fhnw.i4ds.helio.coordinate.converter.option.ConverterOption;
import ch.fhnw.i4ds.helio.coordinate.coord.HeliocentricCartesianCoordinate;
import ch.fhnw.i4ds.helio.coordinate.coord.HelioprojectiveCartesianCoordinate;
import ch.fhnw.i4ds.helio.coordinate.util.Constants;

public class Hcc2HpcConverterTest {
	private Hcc2HpcConverter converter;
	
	@Before
	public void setup() {
		converter = new Hcc2HpcConverter();
	}
    
    /**
     * Adapted from Sunpy test_wcs.py.
     */
    @Test
    public void convert_custom_l0_b0() {
    	Map<ConverterOption<?>, Object> opt = converter.getCustomOptions();

    	HeliocentricCartesianCoordinate hcc = new HeliocentricCartesianCoordinate(28748691, 22998953);
    	
    	HelioprojectiveCartesianCoordinate hpc = converter.convert(hcc, opt);
    	
    	Assert.assertEquals(40.0, hpc.getX().arcsecValue(), 1.0);
    	Assert.assertEquals(32.0, hpc.getY().arcsecValue(), 1.0);
    	Assert.assertEquals(Constants.AU.getValue(), hpc.getSunDistance(), 1.0);
    }
}
