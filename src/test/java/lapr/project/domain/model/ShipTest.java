package lapr.project.domain.model;

import lapr.project.domain.BST.PositionsBST;
import org.junit.Before;

public class ShipTest {

    private VesselType vesselType;
    private PositionsBST positionsBST;
    private int MMSI;
    private String vesselName;
    private int IMO;
    private String callSign;

    @Before
    public void setUp(){
        vesselType = new VesselType(70, 294,32,13.6,79);
        positionsBST = new PositionsBST();
        //...
    }

}
