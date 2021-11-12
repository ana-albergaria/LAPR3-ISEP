package lapr.project.domain.model;

import lapr.project.domain.BST.PositionsBST;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ShipTest {


    private PositionsBST positionsBST, positionsBSTEmpty;
    private int mmsi1, mmsi3, mmsi4;
    private String vesselName;
    private String imo;
    private String callSign;
    private Ship s1, s2, s3, s4;
    private ShipPosition shipPosition;
    private Date dateR1;

    @BeforeEach
    public void setUp() throws ParseException {
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
        s1 = new Ship(positionsBST, mmsi1, vesselName, imo, callSign, 70, 294,32,13.6,"79");
        s2 = new Ship(positionsBST, mmsi1, vesselName, imo, callSign, 70, 294,32,13.6,"79");
        s3 = new Ship(positionsBST, mmsi3, vesselName, imo, callSign, 70, 294,32,13.6,"79");
        s4 = new Ship(positionsBST, mmsi4, vesselName, imo, callSign, 70, 294,32,13.6,"79");
        //...
    }


    /**
     * Test to ensure Null Vessel Types are not allowed.
     */
    /*@Test
    public void ensureNullVesselTypeNotAllowed(){
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Ship(positionsBST, mmsi1, vesselName, imo, callSign, 0, 294,32,13.6,79));
        assertEquals("Vessel Type cannot be null.", thrown.getMessage());
    }

     */

    /**
     * Test to ensure Empty Positions BST are not allowed.
     */
    @Test
    public void ensureEmptyPositionsBSTNotAllowed(){
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Ship(positionsBSTEmpty, mmsi1, vesselName, imo, callSign, 70, 294,32,13.6,"79"));
        assertEquals("Positions BST cannot be empty.", thrown.getMessage());
    }

    /**
     * Test to ensure MMSI cannot have less than 9 digits.
     */
    @Test
    public void ensureMMSIHasNotLessThan9Digits(){
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Ship(positionsBST, 99999999, vesselName, imo, callSign, 70, 294,32,13.6,"79"));
        assertEquals("MMSI must hold 9 digits.", thrown.getMessage());
    }

    /**
     * Test to ensure MMSI cannot have more than 9 digits.
     */
    @Test
    public void ensureMMSIHasNotMoreThan9Digits(){
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Ship(positionsBST, 1000000000, vesselName, imo, callSign, 70, 294,32,13.6,"79"));
        assertEquals("MMSI must hold 9 digits.", thrown.getMessage());
    }

    /**
     * Test to ensure Vessel Name cannot be null.
     */
    @Test
    public void ensureNullVesselNameNotAllowed(){
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Ship(positionsBST, mmsi1, null, imo, callSign, 70, 294,32,13.6,"79"));
        assertEquals("Vessel type cannot be null.", thrown.getMessage());
    }

    /**
     * Test to ensure IMO cannot be blank.
     */
    @Test
    public void ensureBlankIMONotAllowed(){
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Ship(positionsBST, mmsi1, vesselName, "", callSign, 70, 294,32,13.6,"79"));
        assertEquals("IMO cannot be blank.", thrown.getMessage());
    }

    /**
     * Test to ensure IMO cannot have less than 10 chars.
     */
    @Test
    public void ensureIMOHasNotLessThan10Chars(){
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Ship(positionsBST, mmsi1, vesselName, "IMO123456", callSign, 70, 294,32,13.6,"79"));
        assertEquals("IMO must hold 10 characters.", thrown.getMessage());
    }

    /**
     * Test to ensure IMO cannot have more than 10 chars.
     */
    @Test
    public void ensureIMOHasNotMoreThan10Chars(){
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Ship(positionsBST, mmsi1, vesselName, "IMO12345678", callSign, 70, 294,32,13.6,"79"));
        assertEquals("IMO must hold 10 characters.", thrown.getMessage());
    }

    /**
     * Test to ensure the IMO code starts with "IMO".
     */
    @Test
    public void ensureIMOStartsWithIMO(){
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Ship(positionsBST, mmsi1, vesselName, "ISO1234567", callSign, 70, 294,32,13.6,"79"));
        assertEquals("IMO code must begin with the letters IMO.", thrown.getMessage());
    }

    /**
     * Test to ensure that the IMO code has only numeric digits after the "IMO" part.
     */
    @Test
    public void ensureIMOEndsWithNumbers(){
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Ship(positionsBST, mmsi1, vesselName, "IMO12E4567", callSign, 70, 294,32,13.6,"79"));
        assertEquals("IMO must hold numeric digits starting from character 4.", thrown.getMessage());
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

    /**
     * Test to ensure Length cannot be under or equal to 0.
     */
    @Test
    public void ensureLengthIsOver0(){
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Ship(positionsBST, mmsi1, vesselName, imo, callSign, 70, 0,32,13.6,"79"));
        assertEquals("Length needs to be over 0.", thrown.getMessage());
    }

    /**
     * Test to ensure Width cannot be under or equal to 0.
     */
    @Test
    public void ensureWidthIsOver0(){
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Ship(positionsBST, mmsi1, vesselName, imo, callSign, 70, 294,0,13.6,"79"));
        assertEquals("Width needs to be over 0.", thrown.getMessage());
    }

    /**
     * Test to ensure Draft cannot be under or equal to 0.
     */
    @Test
    public void ensureDraftIsOver0(){
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Ship(positionsBST, mmsi1, vesselName, imo, callSign, 70, 294,32,0,"79"));
        assertEquals("Draft needs to be over 0.", thrown.getMessage());
    }

    /**
     * Test to ensure Cargo cannot be under or equal to 0.
     */
    @Test
    public void ensureCargoIsOver0(){
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Ship(positionsBST, mmsi1, vesselName, imo, callSign, 70, 294,32,13.6,"-1"));
        assertEquals("Cargo cannot be negative.", thrown.getMessage());
    }

    @Test
    public void testEqualsMethod(){
        String notShipObj =  "abc";
        assertNotEquals(s1, notShipObj);
        assertNotEquals(null, s1);
    }

    @Test
    public void testGetLength(){
        assertEquals(s1.getLength(), 294);
    }

    @Test
    public void testGetWidth(){
        assertEquals(s1.getWidth(), 32);
    }

    @Test
    public void testGetDraft(){
        assertEquals(s1.getDraft(), 13.6);
    }

    @Test
    public void testGetCargo(){
        assertEquals(s1.getCargo(), "79");
    }

    @Test
    public void testGetTotalMovs(){
        assertEquals(s1.getTotalMovs(), positionsBST.size());
    }


}

