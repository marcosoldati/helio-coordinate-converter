package ch.fhnw.i4ds.helio.coordinate.coord;

/**
 * Heliocentric (or Heliographic) Radial-Tangential-Normal Coordinate System (HGRTN). 
 * A Heliocentric Cartesian Coordinate system with a different mapping than the 
 * {@link HeliocentricCartesianCoordinate}. Actually it should be called Heliocentric :
 * <ul>
 * <li>x<sub>HCC</sub> = y<sub>HGRTN</sub></li>
 * <li>y<sub>HCC</sub> = z<sub>HGRTN</sub></li>
 * <li>z<sub>HCC</sub> = x<sub>HGRTN</sub></li>
 * </ul>
 * @author marco soldati at fhnw ch
 *
 */
public class HGRTNCoordinate extends HeliocentricCartesianCoordinate {

	private static final String ACRONYM = "HGRTN";
	private static final String DESCRIPTION = "Heliocentric (or Heliographic) Radial-Tangential-Normal Coordinate System";


	public HGRTNCoordinate(double x, double y, double z) {
		super(x, y, z);
	}
	
	
	@Override
	public String getCoordinateSystemAcronym() {
		return ACRONYM;
	}
	
	@Override
	public String getCoordinateSystemDescription() {
		return DESCRIPTION;
	}
}
