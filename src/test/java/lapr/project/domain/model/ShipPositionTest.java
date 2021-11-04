package lapr.project.domain.model;


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
}
