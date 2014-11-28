package ch.fhnw.i4ds.helio.coordinate.converter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ch.fhnw.i4ds.helio.coordinate.converter.Hcc2HgConverter.ConversionOptions;
import ch.fhnw.i4ds.helio.coordinate.coord.HeliocentricCartesianCoordinate;
import ch.fhnw.i4ds.helio.coordinate.coord.HeliographicCoordinate;

public class Hcc2HgConverterTest {
	private static final int SAMPLE_LO_IN_DEGREE = 0;
	private static final double SAMPLE_B0_IN_DEGREE = -7.064078;
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
    	ConversionOptions opt = Hcc2HgConverter.newConversionOptions().b0InDegree(SAMPLE_B0_IN_DEGREE)
    					.l0InDegree(SAMPLE_LO_IN_DEGREE);
    	HeliocentricCartesianCoordinate hcc = new HeliocentricCartesianCoordinate(13.0, 58.0);
    	HeliographicCoordinate hg = converter.convert(hcc, opt);
    	Assert.assertEquals(1.0791282e-06, hg.getHgLongitudeDegree(), 1.0);
    	Assert.assertEquals(-7.0640732, hg.getHgLatitudeDegree(), 1.0);
    }
}
