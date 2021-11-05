package lapr.project.domain.model;

import lapr.project.domain.BST.PositionsBST;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ShipTest {

    private VesselType vesselType;
    private PositionsBST positionsBST;
    private int mmsi1, mmsi3, mmsi4;
    private String vesselName;
    private String imo;
    private String callSign;
    private Ship s1, s2, s3, s4;

    @Before
    public void setUp(){
        vesselType = new VesselType(70, 294,32,13.6,79);
        positionsBST = new PositionsBST();
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

    //compareTo() - situation 1: the mmsi codes are equal
    @Test
    public void compareToEqualDates() {
        int expRes = 0;
        int res = s1.compareTo(s2);
        assertEquals(expRes, res);
    }

    //compareTo() - situation 2: mmsi 1 > mmsi 2
    @Test
    public void compareToMMSI1GreaterMMSI2() {
        int expRes = 1;
        int res = s1.compareTo(s3);
        assertEquals(expRes, res);
    }

    //compareTo() - situation 3: mmsi 1 < mmsi 2
    @Test
    public void compareToMMSI1LessMMSI2() {
        int expRes = -1;
        int res = s1.compareTo(s4);
        assertEquals(expRes, res);
    }



}
