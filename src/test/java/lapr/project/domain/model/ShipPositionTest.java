package lapr.project.domain.model;

<<<<<<< HEAD
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ShipPositionTest {

    Company company;
    private Date dateR1;

    @Before
    public void setUp() throws ParseException {
        company = new Company("cargo shipping company");
        dateR1 = new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2020");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureNullArgsNotAllowed(){
        ShipPosition shipPosition = new ShipPosition(211331640,null,36.39094,
                -122.71335,19.7,145.5,147,null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureNullDateNotAllowed(){
        ShipPosition shipPosition = new ShipPosition(211331640,null,36.39094,
                -122.71335,19.7,145.5,147,"B");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureNullTranscieverClassNotAllowed(){
        ShipPosition shipPosition = new ShipPosition(211331640,dateR1,36.39094,
                -122.71335,19.7,145.5,147,null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureTranscieverClassNotEmpty(){
        ShipPosition shipPosition = new ShipPosition(211331640,dateR1,36.39094,
                -122.71335,19.7,145.5,147,"");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createPositionWithLatitudeUnderMinus90(){
        ShipPosition shipPosition = new ShipPosition(211331640,dateR1,-91,
                -122.71335,19.7,145.5,147,"B");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createPositionWithLatitudeOver91(){
        ShipPosition shipPosition = new ShipPosition(211331640,dateR1,92,
                -122.71335,19.7,145.5,147,"B");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createPositionWithLongitudeUnderMinus180(){
        ShipPosition shipPosition = new ShipPosition(211331640,dateR1,36.39094,
                -181,19.7,145.5,147,"B");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createPositionWithLongitudeOver181(){
        ShipPosition shipPosition = new ShipPosition(211331640,dateR1,36.39094,
                182,19.7,145.5,147,"B");
    }

    @Test
    public void ensureMMSIHas9Digits(){
        ShipPosition shipPosition = new ShipPosition(211331640,dateR1,36.39094,
                -122.71335,19.7,145.5,147,"B");
        //Assert.assertTrue(shipPosition.getMMSI().length()==9);
    }

=======

import lapr.project.BSTesinf.BST;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class ShipPositionTest {

    ShipPosition  s1;
    @Before
    public void setUp(){
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
    }
    /**
     * Tests of getters operations
     * **/
    @Test
    public void getBaseDateTime() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2021);
        cal.set(Calendar.MONTH, Calendar.NOVEMBER);
        cal.set(Calendar.DAY_OF_MONTH, 4);
        Date d1 = cal.getTime();
        assertEquals("date should be equal", d1, s1.getBaseDateTime());
    }

    @Test
    public void getLat() {
    }

    @Test
    public void getLon() {
    }

    @Test
    public void getSog() {
    }

    @Test
    public void getCog() {
    }
>>>>>>> d3c1bea6af8959f9aae2cad52686d7fb7167d031
}
