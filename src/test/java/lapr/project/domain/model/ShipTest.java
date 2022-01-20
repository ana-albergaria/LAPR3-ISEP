package lapr.project.domain.model;

import lapr.project.domain.dataStructures.PositionsBST;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        s1 = new ShipSortMmsi(positionsBST, mmsi1, vesselName, imo, callSign, 70, 294,32,13.6,"79");
        s2 = new ShipSortMmsi(positionsBST, mmsi1, vesselName, imo, callSign, 70, 294,32,13.6,"79");
        s3 = new ShipSortMmsi(positionsBST, mmsi3, vesselName, imo, callSign, 70, 294,32,13.6,"79");
        s4 = new ShipSortMmsi(positionsBST, mmsi4, vesselName, imo, callSign, 70, 294,32,13.6,"79");
        //...
    }

    /**
     * Test to ensure Empty Positions BST are not allowed.
     */
    @Test
    public void ensureEmptyPositionsBSTNotAllowed(){
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new ShipSortMmsi(positionsBSTEmpty, mmsi1, vesselName, imo, callSign, 70, 294,32,13.6,"79"));
        assertEquals("Positions BST cannot be empty.", thrown.getMessage());
    }

    /**
     * Test to ensure MMSI cannot have less than 9 digits.
     */
    @Test
    public void ensureMMSIHasNotLessThan9Digits(){
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new ShipSortMmsi(positionsBST, 99999999, vesselName, imo, callSign, 70, 294,32,13.6,"79"));
        assertEquals("MMSI must hold 9 digits.", thrown.getMessage());
    }

    /**
     * Test to ensure MMSI cannot have more than 9 digits.
     */
    @Test
    public void ensureMMSIHasNotMoreThan9Digits(){
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new ShipSortMmsi(positionsBST, 1000000000, vesselName, imo, callSign, 70, 294,32,13.6,"79"));
        assertEquals("MMSI must hold 9 digits.", thrown.getMessage());
    }

    /**
     * Test to ensure Vessel Name cannot be null.
     */
    @Test
    public void ensureNullVesselNameNotAllowed(){
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new ShipSortMmsi(positionsBST, mmsi1, null, imo, callSign, 70, 294,32,13.6,"79"));
        assertEquals("Vessel type cannot be null.", thrown.getMessage());
    }

    /**
     * Test to ensure IMO cannot be blank.
     */
    @Test
    public void ensureBlankIMONotAllowed(){
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new ShipSortMmsi(positionsBST, mmsi1, vesselName, "", callSign, 70, 294,32,13.6,"79"));
        assertEquals("IMO cannot be blank.", thrown.getMessage());
    }

    /**
     * Test to ensure CallSign cannot be blank.
     */
    @Test
    public void ensureBlankCallSignNotAllowed(){
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new ShipSortMmsi(positionsBST, mmsi1, vesselName, imo, null, 70, 294,32,13.6,"79"));
        assertEquals("Call sign cannot be null.", thrown.getMessage());
    }

    /**
     * Test to ensure IMO cannot have less than 10 chars.
     */
    @Test
    public void ensureIMOHasNotLessThan10Chars(){
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new ShipSortMmsi(positionsBST, mmsi1, vesselName, "IMO123456", callSign, 70, 294,32,13.6,"79"));
        assertEquals("IMO must hold 10 characters.", thrown.getMessage());
    }

    /**
     * Test to ensure IMO cannot have more than 10 chars.
     */
    @Test
    public void ensureIMOHasNotMoreThan10Chars(){
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new ShipSortMmsi(positionsBST, mmsi1, vesselName, "IMO12345678", callSign, 70, 294,32,13.6,"79"));
        assertEquals("IMO must hold 10 characters.", thrown.getMessage());
    }

    /**
     * Test to ensure the IMO code starts with "IMO".
     */
    @Test
    public void ensureIMOStartsWithIMO(){
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new ShipSortMmsi(positionsBST, mmsi1, vesselName, "ISO1234567", callSign, 70, 294,32,13.6,"79"));
        assertEquals("IMO code must begin with the letters IMO.", thrown.getMessage());
    }

    /**
     * Test to ensure that the IMO code has only numeric digits after the "IMO" part.
     */
    @Test
    public void ensureIMOEndsWithNumbers(){
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new ShipSortMmsi(positionsBST, mmsi1, vesselName, "IMO12E4567", callSign, 70, 294,32,13.6,"79"));
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
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new ShipSortMmsi(positionsBST, mmsi1, vesselName, imo, callSign, 70, 0,32,13.6,"79"));
        assertEquals("Length needs to be over 0.", thrown.getMessage());
    }

    /**
     * Test to ensure Width cannot be under or equal to 0.
     */
    @Test
    public void ensureWidthIsOver0(){
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new ShipSortMmsi(positionsBST, mmsi1, vesselName, imo, callSign, 70, 294,0,13.6,"79"));
        assertEquals("Width needs to be over 0.", thrown.getMessage());
    }

    /**
     * Test to ensure Draft cannot be under or equal to 0.
     */
    @Test
    public void ensureDraftIsOver0(){
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new ShipSortMmsi(positionsBST, mmsi1, vesselName, imo, callSign, 70, 294,32,0,"79"));
        assertEquals("Draft needs to be over 0.", thrown.getMessage());
    }

    /**
     * Test to ensure Cargo cannot be under or equal to 0.
     */
    @Test
    public void ensureCargoIsOver0(){
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new ShipSortMmsi(positionsBST, mmsi1, vesselName, imo, callSign, 70, 294,32,13.6,"-1"));
        assertEquals("Cargo cannot be negative.", thrown.getMessage());
    }

    /**
     * Test to ensure Cargo can be 0.
     */
    @Test
    public void ensureCargoIs0(){
        new ShipSortMmsi(positionsBST, mmsi1, vesselName, imo, callSign, 70, 294,32,13.6,"0");
    }

    /**
     * Test to ensure Cargo can be NA.
     */
    @Test
    public void ensureCargoIsNA(){
        new ShipSortMmsi(positionsBST, mmsi1, vesselName, imo, callSign, 70, 294,32,13.6,"NA");
    }
    /**
     * Test to ensure that the equals method works correctly.
     */
    @Test
    public void testEqualsMethod(){
        String notShipObj =  "abc";
        assertNotEquals(s1, notShipObj);
        assertNotEquals(null, s1);
    }

    /**
     * Test to ensure that getLength works correctly.
     */
    @Test
    public void testGetLength(){
        assertEquals(s1.getLength(), 294);
    }
    /**
     * Test to ensure that getLength works correctly.
     */
    @Test
    public void testGetVesselTypeId(){
        assertEquals(s1.getVesselTypeID(), 70);
    }

    /**
     * Test to ensure that getLength works correctly.
     */
    @Test
    public void testGetDistanceTravelled(){
        assertEquals(s1.getTravelledDistance(), 0);
    }

    /**
     * Test to ensure that getWidth works correctly.
     */
    @Test
    public void testGetWidth(){
        assertEquals(s1.getWidth(), 32);
    }

    /**
     * Test to ensure that getDraft works correctly.
     */
    @Test
    public void testGetDraft(){
        assertEquals(s1.getDraft(), 13.6);
    }

    /**
     * Test to ensure that getCargo works correctly.
     */
    @Test
    public void testGetCargo(){
        assertEquals(s1.getCargo(), "79");
    }

    /**
     * Test to ensure that getTotalMovs works correctly.
     */
    @Test
    public void testGetTotalMovs(){
        assertEquals(s1.getTotalMovs(), positionsBST.size());
    }


    //US418
    @Test
    void getTotalArea() {
        //Container Ship
        Mass m1 = new Mass(147.0, 294.0, 32.0);
        Mass m2 = new Mass(277.5, 33.0, 32.0);
        Mass m3 = new Mass(71.5, 25.0, 9.0);
        List<Mass> masses = new ArrayList<>();
        masses.add(m1);
        masses.add(m2);
        masses.add(m3);
        Ship s1 = new ShipSortMmsi(positionsBST, mmsi1, "Panamax", imo, callSign, 70, 294, 32, 0.0, "N/A", masses, 155.0);

        double expResult = 10689.0;
        double result = s1.getTotalArea();

        assertEquals(expResult, result);
    }

    @Test
    void getProportionOfMass() {
        Mass m1 = new Mass(147.0, 294.0, 32.0);
        Mass m2 = new Mass(277.5, 33.0, 32.0);
        Mass m3 = new Mass(71.5, 25.0, 9.0);
        List<Mass> masses = new ArrayList<>();
        masses.add(m1);
        masses.add(m2);
        masses.add(m3);
        Ship s1 = new ShipSortMmsi(positionsBST, mmsi1, "Panamax", imo, callSign, 70, 294, 32, 0.0, "N/A", masses, 155.0);

        double expResult = s1.getProportionOfMass(m1);
        double result = 0.88;
        assertEquals(expResult, result, 0.001);

        expResult = s1.getProportionOfMass(m2);
        result = 0.098;
        assertEquals(expResult, result, 0.001);

        expResult = s1.getProportionOfMass(m3);
        result = 0.021;
        assertEquals(expResult, result, 0.001);
    }

    @Test
    void getCertainMass() {
        Mass m1 = new Mass(147.0, 294.0, 32.0);
        Mass m2 = new Mass(277.5, 33.0, 32.0);
        Mass m3 = new Mass(71.5, 25.0, 9.0);
        List<Mass> masses = new ArrayList<>();
        masses.add(m1);
        masses.add(m2);
        masses.add(m3);
        Ship s1 = new ShipSortMmsi(positionsBST, mmsi1, "Panamax", imo, callSign, 70, 294, 32, 0.0, "N/A", masses, 155.0);

        double expResult = s1.getCertainMass(m1);
        double result = 136.424;
        assertEquals(expResult, result, 0.001);

        expResult = s1.getCertainMass(m2);
        result = 15.312;
        assertEquals(expResult, result, 0.001);

        expResult = s1.getCertainMass(m3);
        result = 3.262;
        assertEquals(expResult, result, 0.001);
    }

    @Test
    void getUnloadenCenterOfMassX() {
        //Container Ship
        Mass m1 = new Mass(147.0, 294.0, 32.0);
        Mass m2 = new Mass(277.5, 33.0, 32.0);
        Mass m3 = new Mass(71.5, 25.0, 9.0);
        List<Mass> masses = new ArrayList<>();
        masses.add(m1);
        masses.add(m2);
        masses.add(m3);
        Ship s1 = new ShipSortMmsi(positionsBST, mmsi1, "Panamax", imo, callSign, 70, 294, 32, 0.0, "N/A", masses, 155.000000);

        double expResult = s1.getUnloadenCenterOfMassX();
        double result = 158.303;
        assertEquals(expResult, result, 0.001);
    }

    @Test
    void getUnloadenCenterOfMassY() {
        //Container Ship
        Mass m1 = new Mass(147.0, 294.0, 32.0);
        Mass m2 = new Mass(277.5, 33.0, 32.0);
        Mass m3 = new Mass(71.5, 25.0, 9.0);
        List<Mass> masses = new ArrayList<>();
        masses.add(m1);
        masses.add(m2);
        masses.add(m3);
        Ship s1 = new ShipSortMmsi(positionsBST, mmsi1, "Panamax", imo, callSign, 70, 294, 32, 0.0, "N/A", masses, 155.000000);

        double expResult = s1.getUnloadenCenterOfMassY();
        double result = 16.0;
        assertEquals(expResult, result, 0.001);

    }
}

