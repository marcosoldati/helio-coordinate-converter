package ch.fhnw.i4ds.helio.coordinate.converter;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ch.fhnw.i4ds.helio.coordinate.api.Angle;
import ch.fhnw.i4ds.helio.coordinate.coord.HeliocentricCartesianCoordinate;
import ch.fhnw.i4ds.helio.coordinate.coord.HelioprojectiveCartesianCoordinate;
import ch.fhnw.i4ds.helio.coordinate.util.Constants;

public class Hpc2HccConverterTest {
	private Hpc2HccConverter converter;

	@Before
	public void setup() {
		converter = new Hpc2HccConverter();
	}

	@Test
	public void convert_default_options() {
		HelioprojectiveCartesianCoordinate hpc = new HelioprojectiveCartesianCoordinate(Angle.fromArcsec(40.0),
						Angle.fromArcsec(32.0));
		HeliocentricCartesianCoordinate hcc = converter.convert(hpc);
		Assert.assertEquals(28876152.176423457, hcc.getX(), 0);
		Assert.assertEquals(23100922.071266972, hcc.getY(), 0);
		Assert.assertEquals(694524220.8157959, hcc.getZ(), 0);
	}

	@Test
	public void convert_custom_sun_distance() {
		HelioprojectiveCartesianCoordinate hpc = new HelioprojectiveCartesianCoordinate(Angle.fromArcsec(40.0),
						Angle.fromArcsec(32.0), Constants.AU.getValue() / 2);
		HeliocentricCartesianCoordinate hcc = converter.convert(hpc);
		Assert.assertEquals(1.4370589563681226E7, hcc.getX(), 0);
		Assert.assertEquals(1.149647181523754E7, hcc.getY(), 0);
		Assert.assertEquals(6.952644787097931E8, hcc.getZ(), 0);
	}

	@Test
	public void convert_list() {
		HelioprojectiveCartesianCoordinate hpc1 = new HelioprojectiveCartesianCoordinate(Angle.fromArcsec(34.0),
						Angle.fromArcsec(96.0));
		HelioprojectiveCartesianCoordinate hpc2 = new HelioprojectiveCartesianCoordinate(Angle.fromArcsec(55.0),
						Angle.fromArcsec(56.0));
		List<HeliocentricCartesianCoordinate> hccList = converter.convert(Arrays.asList(hpc1, hpc2));

		// coord 1
		Assert.assertEquals(2.45452094613846E7, hccList.get(0).getX(), 1.0);
		Assert.assertEquals(6.930412615013668E7, hccList.get(0).getY(), 1.0);
		Assert.assertEquals(6.916110531532593E8, hccList.get(0).getZ(), 1.0);

		// coord 2
		Assert.assertEquals(3.970506388129124E7, hccList.get(1).getX(), 1.0);
		Assert.assertEquals(4.0426975606033444E7, hccList.get(1).getY(), 1.0);
		Assert.assertEquals(6.931958926653137E8, hccList.get(1).getZ(), 1.0);
	}
}
