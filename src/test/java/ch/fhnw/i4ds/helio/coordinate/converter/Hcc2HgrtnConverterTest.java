package ch.fhnw.i4ds.helio.coordinate.converter;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ch.fhnw.i4ds.helio.coordinate.coord.HGRTNCoordinate;
import ch.fhnw.i4ds.helio.coordinate.coord.HeliocentricCartesianCoordinate;

public class Hcc2HgrtnConverterTest {
	
	private Hcc2HgrtnConverter converter;
	
	@Before
	public void setup() {
		converter = new Hcc2HgrtnConverter();
	}
    
    @Test
    public void convert() {
    	HeliocentricCartesianCoordinate hcc = new HeliocentricCartesianCoordinate(1, 2, 3);
    	HGRTNCoordinate hgrtn = converter.convert(hcc);
    	assertEquals(hcc.getX(), hgrtn.getY(), 1e-11);
    	assertEquals(hcc.getY(), hgrtn.getZ(), 1e-11);
    	assertEquals(hcc.getZ(), hgrtn.getX(), 1e-11);
    }
    
    @Test
    public void convert_back() {
    	HeliocentricCartesianCoordinate hcc = new HeliocentricCartesianCoordinate(1, 2, 3);
    	HGRTNCoordinate hgrtn = converter.convert(hcc);
    	HeliocentricCartesianCoordinate hcc2 = new Hgrtn2HccConverter().convert(hgrtn);
    	assertEquals(hcc, hcc2);
    }

}
