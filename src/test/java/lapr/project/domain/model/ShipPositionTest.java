package lapr.project.domain.model;


import org.junit.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ShipPositionTest {

    Company company;
    ShipPosition s1, s2, s3, s4;
    private Date dateR1, dateR3, dateR4;

    @Before
    public void setUp() throws ParseException {
        company = new Company("cargo shipping company");
        dateR1 = new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2020");
        dateR3 = new SimpleDateFormat("dd/MM/yyyy").parse("29/12/2020");
        dateR4 = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2022");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2021);
        cal.set(Calendar.MONTH, Calendar.NOVEMBER);
        cal.set(Calendar.DAY_OF_MONTH, 4);
        Date d1 = cal.getTime();
        int mmsi = 123456789;
        double lat = -30.033056;
        double lon = -51.230000;
        double sog = 25.4;
        double cog = 341.2;
        int heading = 300;
        String transcieverClass = "AIS";
        s1 = new ShipPosition(mmsi, d1, lat, lon, sog, cog, heading, transcieverClass);
        s2 = new ShipPosition(mmsi, d1, lat, lon, sog, cog, heading, transcieverClass);
        s3 = new ShipPosition(mmsi, dateR3, lat, lon, sog, cog, heading, transcieverClass);
        s4 = new ShipPosition(mmsi, dateR4, lat, lon, sog, cog, heading, transcieverClass);
    }

    /**
     * Tests of getters operations
     **/
    @Test
    public void getBaseDateTime() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2021);
        cal.set(Calendar.MONTH, Calendar.NOVEMBER);
        cal.set(Calendar.DAY_OF_MONTH, 4);
        Date d1 = cal.getTime();
        assertEquals("date should be equal", d1.toString(), s1.getBaseDateTime().toString());
    }

    @Test
    public void getLat() {
        double lat = -30.033056;
        assertEquals(lat, s1.getLat(), 2);
    }

    @Test
    public void getLon() {
        double lon = -51.230000;
        assertEquals(lon, s1.getLon(), 2);
    }

    @Test
    public void getSog() {
        double sog = 25.4;
        assertEquals(sog, s1.getSog(), 2);
    }

    @Test
    public void getCog() {
        double cog = 341.2;
        assertEquals(cog, s1.getCog(), 2);
    }

    /**
     * Test to ensure Base Date Time cannot be null.
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureNullDateNotAllowed() {
        ShipPosition shipPosition = new ShipPosition(211331640, null, 36.39094,
                -122.71335, 19.7, 145.5, 147, "B");
    }

    /**
     * Test to ensure Transciever Class cannot be null.
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureNullTranscieverClassNotAllowed() {
        ShipPosition shipPosition = new ShipPosition(211331640, dateR1, 36.39094,
                -122.71335, 19.7, 145.5, 147, null);
    }

    /**
     * Test to ensure Transciever Class cannot be empty.
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureTranscieverClassNotEmpty() {
        ShipPosition shipPosition = new ShipPosition(211331640, dateR1, 36.39094,
                -122.71335, 19.7, 145.5, 147, "");
    }

    /**
     * Test to ensure Latitude cannot be under -90.
     */
    @Test(expected = IllegalArgumentException.class)
    public void createPositionWithLatitudeUnderMinus90() {
        ShipPosition shipPosition = new ShipPosition(211331640, dateR1, -91,
                -122.71335, 19.7, 145.5, 147, "B");
    }

    /**
     * Test to ensure latitude cannot de over 91.
     */
    @Test(expected = IllegalArgumentException.class)
    public void createPositionWithLatitudeOver91() {
        ShipPosition shipPosition = new ShipPosition(211331640, dateR1, 92,
                -122.71335, 19.7, 145.5, 147, "B");
    }

    /**
     * Test to ensure Longitude cannot be under -180.
     */
    @Test(expected = IllegalArgumentException.class)
    public void createPositionWithLongitudeUnderMinus180() {
        ShipPosition shipPosition = new ShipPosition(211331640, dateR1, 36.39094,
                -181, 19.7, 145.5, 147, "B");
    }

    /**
     * Test to ensure longitude cannot be over 181.
     */
    @Test(expected = IllegalArgumentException.class)
    public void createPositionWithLongitudeOver181() {
        ShipPosition shipPosition = new ShipPosition(211331640, dateR1, 36.39094,
                182, 19.7, 145.5, 147, "B");
    }

    /**
     * Test to ensure cog cannot be under 0.
     */
    @Test(expected = IllegalArgumentException.class)
    public void createPositionWithCOGUnder0() {
        ShipPosition shipPosition = new ShipPosition(211331640, dateR1, 36.39094,
                -122.71335, 19.7, -1, 147, "B");
    }

    /**
     * Test to ensure Heading cannot be over 359.
     */
    @Test(expected = IllegalArgumentException.class)
    public void createPositionWithCOGOver359() {
        ShipPosition shipPosition = new ShipPosition(211331640, dateR1, 36.39094,
                -122.71335, 19.7, 360, 147, "B");
    }

    /**
     * Test to ensure Heading cannot be under 0.
     */
    @Test(expected = IllegalArgumentException.class)
    public void createPositionWithHeadingUnder0() {
        ShipPosition shipPosition = new ShipPosition(211331640, dateR1, 36.39094,
                -122.71335, 19.7, 145.5, -1, "B");
    }

    /**
     * Test to ensure Heading cannot be over 511.
     */
    @Test(expected = IllegalArgumentException.class)
    public void createPositionWithHeadingOver511() {
        ShipPosition shipPosition = new ShipPosition(211331640, dateR1, 36.39094,
                -122.71335, 19.7, 145.5, 512, "B");
    }

    /**
     * Test to ensure MMSI has 9 digits.
     */
    @Test
    public void ensureMMSIHas9Digits() {
        ShipPosition shipPosition = new ShipPosition(211331640, dateR1, 36.39094,
                -122.71335, 19.7, 145.5, 147, "B");
        Assert.assertTrue(String.valueOf(shipPosition.getMMSI()).length()==9);
    }


    /**
     * Test to ensure compareTo() is functioning correctly.
     *      Situation 1: the dates are equal
     *
     * For demonstration purposes the Arrange/Act/Assert syntax is used:
     * Arranje: two ships (s1 and s2) with the same date (done in @Before)
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
     *      Situation 2: date 1 > date 2
     *
     * For demonstration purposes the Arrange/Act/Assert syntax is used:
     * Arranje: one ship (s1) with a date greater than other (s2) (done in @Before)
     * Act: s1 is compared to s2 using compareTo()
     * Assert: the result should be one.
     */
    @Test
    public void compareToD1GreaterD2() {
        int expRes = 1;
        int res = s1.compareTo(s3);
        assertEquals(expRes, res);
    }

    /**
     * Test to ensure compareTo() is functioning correctly.
     *      Situation 3: date 1 < date 2
     *
     * For demonstration purposes the Arrange/Act/Assert syntax is used:
     * Arranje: one ship (s1) with a mmsi less than other (s2) (done in @Before)
     * Act: s1 is compared to s2 using compareTo()
     * Assert: the result should be minus one.
     */
    @Test
    public void compareToD1LessD2() {
        int expRes = -1;
        int res = s1.compareTo(s4);
        assertEquals(expRes, res);
    }


}