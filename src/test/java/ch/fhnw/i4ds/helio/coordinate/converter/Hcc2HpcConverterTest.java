package ch.fhnw.i4ds.helio.coordinate.converter;

import java.util.Map;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ch.fhnw.i4ds.helio.coordinate.converter.option.ConverterOption;
import ch.fhnw.i4ds.helio.coordinate.converter.option.ConverterOptions;
import ch.fhnw.i4ds.helio.coordinate.coord.HeliocentricCartesianCoordinate;
import ch.fhnw.i4ds.helio.coordinate.coord.HelioprojectiveCartesianCoordinate;
import ch.fhnw.i4ds.helio.coordinate.sundist.Pb0rSunDistanceAlgo;
import ch.fhnw.i4ds.helio.coordinate.sundist.SunDistance;
import ch.fhnw.i4ds.helio.coordinate.sundist.SunDistanceAlgo;
import ch.fhnw.i4ds.helio.coordinate.util.Constants;

public class Hcc2HpcConverterTest {
	private Hcc2HpcConverter converter;
	
	@Before
	public void setup() {
		converter = new Hcc2HpcConverter();
	}
    
    @Test
    public void convert_default() {
    	Map<ConverterOption<?>, Object> opt = converter.getCustomOptions();

    	HeliocentricCartesianCoordinate hcc = new HeliocentricCartesianCoordinate(28748691, 22998953);
    	
    	HelioprojectiveCartesianCoordinate hpc = converter.convert(hcc, opt);
    	
    	Assert.assertEquals(40.0, hpc.getThetaX().arcsecValue(), 1.0);
    	Assert.assertEquals(32.0, hpc.getThetaY().arcsecValue(), 1.0);
    	Assert.assertEquals(Constants.AU.getValue(), hpc.getSunDistance().inMeters(), 0);
    }
    
    /**
     * 
     */
    @Test
    public void convert_custom_sundist() {
    	Map<ConverterOption<?>, Object> opt = converter.getCustomOptions();
    	SunDistance sunDist = getSunDist(2013,01,01);
    	opt.put(ConverterOptions.SUN_DISTANCE, sunDist.getSunDistance());
    	
    	HeliocentricCartesianCoordinate hcc = new HeliocentricCartesianCoordinate(28748691, 22998953);
    	
    	HelioprojectiveCartesianCoordinate hpc = converter.convert(hcc, opt);
    	
    	Assert.assertEquals(40.0, hpc.getThetaX().arcsecValue(), 1.0);
    	Assert.assertEquals(32.0, hpc.getThetaY().arcsecValue(), 1.0);
    	Assert.assertEquals(sunDist.getSunDistance().inMeters(), hpc.getSunDistance().inMeters(), 0);
    }

	private SunDistance getSunDist(int year, int month, int day) {
		DateTime date = new DateTime(year, month, day, 0, 0);
		SunDistanceAlgo sunDistAlgo = new Pb0rSunDistanceAlgo();
		SunDistance sunDist = sunDistAlgo.computeDistance(date);
		return sunDist;
	}
}
