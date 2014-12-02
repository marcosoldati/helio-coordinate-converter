package ch.fhnw.i4ds.helio.coordinate.converter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ch.fhnw.i4ds.helio.coordinate.converter.option.ConverterOption;
import ch.fhnw.i4ds.helio.coordinate.converter.option.ConverterOptions;
import ch.fhnw.i4ds.helio.coordinate.coord.HeliocentricCartesianCoordinate;
import ch.fhnw.i4ds.helio.coordinate.coord.HeliographicCoordinate;

public class Hg2HccConverterTest {
	private static final double SAMPLE_L0_IN_RAD = 0;
	private static final double SAMPLE_B0_IN_RAD = Math.toRadians(-7.064078);
	
	private Hg2HccConverter converter;
	
	@Before
	public void setup() {
		converter = new Hg2HccConverter();
	}
	
    @Test
    public void convert_custom_sunradius() {
    	Map<ConverterOption<?>, Object> opt = converter.getCustomOptions();
    	opt.put(ConverterOptions.SUN_RADIUS, 704945784.41465974);
    	HeliographicCoordinate hg = new HeliographicCoordinate(0.01873188196651189, 3.6599471896203317);
		HeliocentricCartesianCoordinate hcc = converter.convert(hg, opt);
		Assert.assertEquals(230000.0, hcc.getX(), 0);
		Assert.assertEquals(45000000.0, hcc.getY(), 0);
		Assert.assertEquals(703508000.0, hcc.getZ(), 0);
    }
    
    /**
     * Adapted from Sunpy test_wcs.py.
     */
    @Test
    public void convert_custom_l0_b0() {
    	Map<ConverterOption<?>, Object> opt = converter.getCustomOptions();
    	opt.put(ConverterOptions.B0, SAMPLE_B0_IN_RAD);
    	opt.put(ConverterOptions.L0, SAMPLE_L0_IN_RAD);
    	
    	HeliographicCoordinate hg = new HeliographicCoordinate(34.0, 96.0);
    	HeliocentricCartesianCoordinate hcc = converter.convert(hg, opt);
    	Assert.assertEquals(-40653538.0, hcc.getX(), 1.0);
    	Assert.assertEquals(6.7903529e8, hcc.getY(), 1.0);
    	Assert.assertEquals(-1.4487837273551834E8, hcc.getZ(), 0);
    }

    /**
     * Adapted from Sunpy test_wcs.py.
     */
    @Test
    public void convert_custom_halfsunradius() {
    	Map<ConverterOption<?>, Object> opt = converter.getCustomOptions();
    	opt.put(ConverterOptions.B0, SAMPLE_B0_IN_RAD);
    	opt.put(ConverterOptions.L0, SAMPLE_L0_IN_RAD);
    	opt.put(ConverterOptions.SUN_RADIUS, (Double)opt.get(ConverterOptions.SUN_RADIUS) / 2);

    	HeliographicCoordinate hg = new HeliographicCoordinate(34.0, 96.0);
    	HeliocentricCartesianCoordinate hcc = converter.convert(hg, opt);
    	Assert.assertEquals(-40653538.0 / 2, hcc.getX(), 1.0);
    	Assert.assertEquals(6.7903529e8 / 2, hcc.getY(), 1.0);
    }
    
    @Test
    public void convert_custom_occulation() {
    	Map<ConverterOption<?>, Object> opt = converter.getCustomOptions();
    	opt.put(ConverterOptions.B0, SAMPLE_B0_IN_RAD);
    	opt.put(ConverterOptions.L0, SAMPLE_L0_IN_RAD);
    	opt.put(ConverterOptions.OCCULTATION, true);
    	
    	HeliographicCoordinate hg = new HeliographicCoordinate(34.0, 96.0);
    	HeliocentricCartesianCoordinate hcc = converter.convert(hg, opt);
    	Assert.assertTrue(Double.isNaN(hcc.getX()));
    	Assert.assertTrue(Double.isNaN(hcc.getY()));
    }
    
    @Test
    public void convert_list() {
    	Map<ConverterOption<?>, Object> opt = converter.getCustomOptions();
    	opt.put(ConverterOptions.B0, SAMPLE_B0_IN_RAD);
    	opt.put(ConverterOptions.L0, SAMPLE_L0_IN_RAD);
    	
    	HeliographicCoordinate hg1 = new HeliographicCoordinate(34.0, 96.0);
    	HeliographicCoordinate hg2 = new HeliographicCoordinate(55.0, 56.0);
    	List<HeliocentricCartesianCoordinate> hccList = converter.convert(Arrays.asList(hg1, hg2), opt);
    	
    	// coord 1
    	Assert.assertEquals(-40653538.0, hccList.get(0).getX(), 1.0);
    	Assert.assertEquals(6.7903529e8, hccList.get(0).getY(), 1.0);
    	
    	// coord 2
    	Assert.assertEquals(3.18587183e8, hccList.get(1).getX(), 1.0);
    	Assert.assertEquals(5.99659284e8, hccList.get(1).getY(), 1.0);
    }
}
