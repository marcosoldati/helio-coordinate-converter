package ch.fhnw.i4ds.helio.coordinate.converter;

import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ch.fhnw.i4ds.helio.coordinate.converter.option.ConverterOption;
import ch.fhnw.i4ds.helio.coordinate.converter.option.ConverterOptions;
import ch.fhnw.i4ds.helio.coordinate.coord.HeliocentricCartesianCoordinate;
import ch.fhnw.i4ds.helio.coordinate.coord.HeliographicCoordinate;

public class Hcc2HgConverterTest {
	private static final double SAMPLE_L0_IN_RAD = 0;
	private static final double SAMPLE_B0_IN_RAD = Math.toRadians(-7.064078);
	private Hcc2HgConverter converter;
	
	@Before
	public void setup() {
		converter = new Hcc2HgConverter();
	}
    
    /**
     * Adapted from Sunpy test_wcs.py.
     */
    @Test
    public void convert_custom_l0_b0() {
    	Map<ConverterOption<?>, Object> opt = converter.getCustomOptions();
    	opt.put(ConverterOptions.B0, SAMPLE_B0_IN_RAD);
    	opt.put(ConverterOptions.L0, SAMPLE_L0_IN_RAD);
    	
    	HeliocentricCartesianCoordinate hcc = new HeliocentricCartesianCoordinate(13.0, 58.0);
    	HeliographicCoordinate hg = converter.convert(hcc, opt);
    	Assert.assertEquals(1.0791282e-06, hg.getHgLongitude().degValue(), 1e-10);
    	Assert.assertEquals(-7.0640732, hg.getHgLatitude().degValue(), 1e-6);
    }
}
