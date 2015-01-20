package ch.fhnw.i4ds.helio.coordinate.converter;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ch.fhnw.i4ds.helio.coordinate.coord.HGRTNCoordinate;
import ch.fhnw.i4ds.helio.coordinate.coord.HeliocentricCartesianCoordinate;

public class Hgrtn2HccConverterTest {
	
	private Hgrtn2HccConverter converter;
	
	@Before
	public void setup() {
		converter = new Hgrtn2HccConverter();
	}
    
    @Test
    public void convert() {
    	HGRTNCoordinate hgrtn = new HGRTNCoordinate(1, 2, 3);
    	HeliocentricCartesianCoordinate hcc = converter.convert(hgrtn);
    	assertEquals(hgrtn.getX(), hcc.getZ(), 1e-11);
    	assertEquals(hgrtn.getY(), hcc.getX(), 1e-11);
    	assertEquals(hgrtn.getZ(), hcc.getY(), 1e-11);
    }
    
    @Test
    public void convert_back() {
    	HGRTNCoordinate hgrtn = new HGRTNCoordinate(1, 2, 3);
    	HeliocentricCartesianCoordinate hcc = converter.convert(hgrtn);
    	HGRTNCoordinate hgrtn2 = new Hcc2HgrtnConverter().convert(hcc);
    	assertEquals(hgrtn, hgrtn2);
    }

}
