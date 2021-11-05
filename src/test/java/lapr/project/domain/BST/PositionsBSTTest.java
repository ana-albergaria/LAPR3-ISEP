package lapr.project.domain.BST;

import lapr.project.BSTesinf.BST;
import lapr.project.domain.model.ShipPosition;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class PositionsBSTTest {

    PositionsBST instance;
    Date [] d1 = {new SimpleDateFormat("dd/MM/yyyy").parse("04/01/2021"),
            new SimpleDateFormat("dd/MM/yyyy").parse("07/01/2021"),
            new SimpleDateFormat("dd/MM/yyyy").parse("10/01/2021"),
            new SimpleDateFormat("dd/MM/yyyy").parse("13/01/2021")} ;
    int [] mmsiCodes = {333333333, 111111111, 222222222, 123456789};
    double [] lats = {-30.033056, -42.033006, -55.022056, 23.008721};
    double [] lons = {-51.230000, -47.223056, -46.233056, 24.092123};
    double [] sogs = {25.4, 25.8, 31.7, 10.2};
    double [] cogs = {341.2, 330.3, 328.5, 320.9};
    int [] headings = {300, 302, 315, 300};
    String transcieverClass = "AIS";

    public PositionsBSTTest() throws ParseException {
    }

    @Before
    public void setUp() throws ParseException {
        instance = new PositionsBST();
        for(int i=0; i<3;i++){
            instance.insert(new ShipPosition(mmsiCodes[i], d1[i], lats[i], lons[i], sogs[i], cogs[i], headings[i], transcieverClass));
        }
        ShipPosition p1 = new ShipPosition(mmsiCodes[0], d1[0], lats[0], lons[0], sogs[0], cogs[0], headings[0], transcieverClass);
        ShipPosition p2 = new ShipPosition(mmsiCodes[1], d1[1], lats[1], lons[1], sogs[1], cogs[1], headings[1], transcieverClass);
        ShipPosition p3 = new ShipPosition(mmsiCodes[2], d1[2], lats[2], lons[2], sogs[2], cogs[2], headings[2], transcieverClass);

    }

    @Test
    public void testStartDate(){
        Date expected = d1[0];
        System.out.println("start date");
        assertEquals("Date should be 04/01/2021", expected, instance.getStartDate());
        instance.remove(instance.smallestElement());
        expected = d1[1];
        assertEquals("Date should be 07/01/2021", expected, instance.getStartDate());
    }

    @Test
    public void testEndDate(){
        Date expected = d1[2];
        System.out.println("end date");
        assertEquals("Date should be 10/01/2021", expected, instance.getEndDate());
    }

    @Test
    public void testMaxSog(){
        Double expected = sogs[2];
        System.out.println("max sog");
        System.out.println(instance.getMaxSog());
        assertEquals("Max Sog should be "+expected , expected, instance.getMaxSog());
    }

    @Test
    public void testMeanSog(){
        Double expected;
        Double sum=0.0;
        for(double sog: sogs){
            sum += sog;
        }
        expected = sum/(double)sogs.length;
        System.out.println("mean sog");
        System.out.println(instance.getMeanSog());
        System.out.println(expected);

        assertEquals("Mean sog should be "+expected, expected, instance.getMeanSog());
    }

    @Test
    public void testMeanCog(){
        Double expected;
        Double sum=0.0;
        for(double cog: cogs){
            sum += cog;
        }
        expected = sum/(double)cogs.length;
        System.out.println("mean cog");
        System.out.println(instance.getMeanCog());
        System.out.println(expected);

        assertEquals("Mean cog should be "+expected, expected, instance.getMeanCog());
    }

    @Test
    public void testDepartLatitude(){
        Double expected = lats[0];
        System.out.println("depart latitude");
        assertEquals("depart latitude "+expected, expected, instance.getDepartLatitude());
    }

    @Test
    public void testDepartLongitude(){
        Double expected = lons[0];
        System.out.println("depart longitude");
        assertEquals("depart longitude "+expected, expected, instance.getDepartLongitude());
    }

    @Test
    public void testArrivalLatitude(){
        Double expected = lats[2];
        System.out.println("Arrival latitude");
        assertEquals("Arrival latitude "+expected, expected, instance.getArrivalLatitude());
    }

    @Test
    public void testArrivalLongitude(){
        Double expected = lons[2];
        System.out.println("Arrival longitude");
        assertEquals("Arrival longitude "+expected, expected, instance.getArrivalLongitude());
    }

    @Test
    public void testTravelDistance(){

    }

    @Test
    public void testDeltaDistance(){
        double expected = 2807.0; //got from distance calculator in: http://www.movable-type.co.uk/scripts/latlong.html
        System.out.println("delta distance");
        System.out.println(instance.getDeltaDistance());
        assertEquals("delta distance should be "+expected, expected, instance.getDeltaDistance(), 2);
        instance.insert(new ShipPosition(mmsiCodes[3], d1[3], lats[3], lons[3], sogs[3], cogs[3], headings[3], transcieverClass));
        expected = 9968.0;
        assertEquals("delta distance now should be "+expected, expected, instance.getDeltaDistance(), 2);
    }

}