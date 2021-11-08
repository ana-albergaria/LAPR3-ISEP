package lapr.project.domain.model;

import lapr.project.domain.BST.PositionsBST;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ShipTest {

    private VesselType vesselType;
    private PositionsBST positionsBST, positionsBSTEmpty;
    private int mmsi1, mmsi3, mmsi4;
    private String vesselName;
    private String imo;
    private String callSign;
    private Ship s1, s2, s3, s4;
    private ShipPosition shipPosition;
    private Date dateR1;

    @Before
    public void setUp() throws ParseException {
        vesselType = new VesselType(70, 294,32,13.6,79);
        positionsBST = new PositionsBST();
        dateR1 = new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2020");
        shipPosition = new ShipPosition(211331640, dateR1, 36.39094, -122.71335, 19.7, 145.5, 147, "B");
        positionsBST.insert(shipPosition);
        positionsBSTEmpty = new PositionsBST();
        mmsi1 = 123456789;
        mmsi3 = 123456788;
        mmsi4 = 123456790;
        vesselName = "VARAMO";
        imo = "IMO9395044";
        callSign = "C4SQ2";
        s1 = new Ship(vesselType, positionsBST, mmsi1, vesselName, imo, callSign);
        s2 = new Ship(vesselType, positionsBST, mmsi1, vesselName, imo, callSign);
        s3 = new Ship(vesselType, positionsBST, mmsi3, vesselName, imo, callSign);
        s4 = new Ship(vesselType, positionsBST, mmsi4, vesselName, imo, callSign);
        //...
    }

    /**
     * Test to ensure Null Vessel Types are not allowed.
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureNullVesselTypeNotAllowed(){
        Ship ship = new Ship(null, positionsBST, mmsi1, vesselName, imo, callSign);
    }

    /**
     * Test to ensure Empty Positions BST are not allowed.
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureEmptyPositionsBSTNotAllowed(){
        Ship ship = new Ship(vesselType, positionsBSTEmpty, mmsi1, vesselName, imo, callSign);
    }

    /**
     * Test to ensure MMSI cannot have less than 9 digits.
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureMMSIHasNotLessThan9Digits(){
        Ship ship = new Ship(vesselType, positionsBST, 99999999, vesselName, imo, callSign);
    }

    /**
     * Test to ensure MMSI cannot have more than 9 digits.
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureMMSIHasNotMoreThan9Digits(){
        Ship ship = new Ship(vesselType, positionsBST, 1000000000, vesselName, imo, callSign);
    }

    /**
     * Test to ensure Vessel Name cannot be null.
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureNullVesselNameNotAllowed(){
        Ship ship = new Ship(vesselType, positionsBST, mmsi1, null, imo, callSign);
    }

    /**
     * Test to ensure IMO cannot be blank.
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureBlankIMONotAllowed(){
        Ship ship = new Ship(vesselType, positionsBST, mmsi1, vesselName, "", callSign);
    }

    /**
     * Test to ensure IMO cannot have less than 10 chars.
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureIMOHasNotLessThan10Chars(){
        Ship ship = new Ship(vesselType, positionsBST, mmsi1, vesselName, "IMO123456", callSign);
    }

    /**
     * Test to ensure IMO cannot have more than 10 chars.
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureIMOHasNotMoreThan10Chars(){
        Ship ship = new Ship(vesselType, positionsBST, mmsi1, vesselName, "IMO12345678", callSign);
    }

    /**
     * Test to ensure the IMO code starts with "IMO".
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureIMOStartsWithIMO(){
        Ship ship = new Ship(vesselType, positionsBST, mmsi1, vesselName, "ISO1234567", callSign);
    }

    /**
     * Test to ensure that the IMO code has only numeric digits after the "IMO" part.
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureIMOEndsWithNumbers(){
        Ship ship = new Ship(vesselType, positionsBST, mmsi1, vesselName, "IMO12E4567", callSign);
    }

    /**
     * Test to ensure compareTo() is functioning correctly.
     *      Situation 1: the MMSI codes are equal
     *
     * For demonstration purposes the Arrange/Act/Assert syntax is used:
     * Arranje: two ships (s1 and s2) with the same MMSI (done in @Before)
     * Act: s1 is compared to s2 using compareTo()
     * Assert: the result should be zero.
     */
    @Test
    public void compareToEqualDates() {
        int expRes = 0;
        int res = s1.compareTo(s2);
        assertEquals(expRes, res);
    }

    /**
     * Test to ensure compareTo() is functioning correctly.
     *      Situation 2: MMSI 1 > MMSI 2
     *
     * For demonstration purposes the Arrange/Act/Assert syntax is used:
     * Arranje: one ship (s1) with a mmsi greater than other (s2) (done in @Before)
     * Act: s1 is compared to s2 using compareTo()
     * Assert: the result should be one.
     */
    @Test
    public void compareToMMSI1GreaterMMSI2() {
        int expRes = 1;
        int res = s1.compareTo(s3);
        assertEquals(expRes, res);
    }

    /**
     * Test to ensure compareTo() is functioning correctly.
     *      Situation 3: MMSI 1 < MMSI 2
     *
     * For demonstration purposes the Arrange/Act/Assert syntax is used:
     * Arranje: one ship (s1) with a mmsi less than other (s2) (done in @Before)
     * Act: s1 is compared to s2 using compareTo()
     * Assert: the result should be minus one.
     */
    @Test
    public void compareToMMSI1LessMMSI2() {
        int expRes = -1;
        int res = s1.compareTo(s4);
        assertEquals(expRes, res);
    }



}
