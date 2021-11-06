package lapr.project.domain.BST;

import lapr.project.domain.model.Ship;
import lapr.project.domain.model.ShipPosition;
import lapr.project.domain.model.VesselType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShipsBSTTest {

    /* Atributes for the Ship */
    private VesselType vesselType;
    private PositionsBST positionsBST;

    Date[] d1 = {new SimpleDateFormat("dd/MM/yyyy").parse("04/01/2021"),
            new SimpleDateFormat("dd/MM/yyyy").parse("07/01/2021"),
            new SimpleDateFormat("dd/MM/yyyy").parse("10/01/2021"),
            new SimpleDateFormat("dd/MM/yyyy").parse("13/01/2021")} ;
    int [] mmsiCodes = {333333333, 111111111, 222222222, 123456789};
    String [] vesselNames = {"VARAMO", "SAITA", "VARAMO", "HYUNDAI SINGAPURE"};
    String [] imoCodes = {"IMO9395044", "IMO9395022", "IMO9395066", "IMO9395088"};
    String [] callSigns = {"C4SQ2", "5BBA4", "C4SQ2", "5BZP3"};

    private ShipsBST shipsBST;

    public ShipsBSTTest() throws ParseException {
    }

    @Before
    public void setUp() {
        vesselType = new VesselType(70, 294,32,13.6,79);
        positionsBST = new PositionsBST();
        shipsBST = new ShipsBST();
        for(int i=0; i<4;i++){
            //System.out.println(new Ship(vesselType, positionsBST, mmsiCodes[i], vesselNames[i], imoCodes[i], callSigns[i]));
            shipsBST.insert(new Ship(vesselType, positionsBST, mmsiCodes[i], vesselNames[i], imoCodes[i], callSigns[i]));
        }
    }

    /**
     * Test to ensure getShipByMmsiCode() is functioning correctly.
     *      Situation 1: the user inserts a MMSI code existent in the system
     *          - the method returns the corresponding Ship
     */
    @Test
    public void getShipByExistentMmsiCode() {
        int mmsiToBeTested = 123456789;
        Ship expShip = new Ship(vesselType, positionsBST, mmsiCodes[3], vesselNames[3], imoCodes[3], callSigns[3]);

        Ship ship = shipsBST.getShipByMmsiCode(mmsiToBeTested);

        Assert.assertEquals(expShip, ship);
    }

    /**
     * Test to ensure getShipByMmsiCode() is functioning correctly.
     *      Situation 2: the user inserts a MMSI code NOT existent in the system
     *          - the method returns null
     */
    @Test
    public void getShipByNonExistentMmsiCode() {
        int mmsiToBeTested = 0123456777;

        Ship result = shipsBST.getShipByMmsiCode(mmsiToBeTested);

        Assert.assertNull(result);
    }

    /**
     * ensure the ships are correctly extrated to a list if they belong in the Base Date Time interval
     */
    @Test
    public void getShipsByDateCorrect() {
        VesselType vesselType2 = new VesselType(85, 294,32,13.6,79);
        Ship testShip = new Ship(vesselType, positionsBST, mmsiCodes[4], vesselNames[4], imoCodes[4], callSigns[4]);
        ShipPosition position1 = new ShipPosition(mmsiCodes[4], d1[4], 80, 100, 150, 250, 300, "JIRA");
        Ship testShip2 = new Ship(vesselType2, positionsBST,mmsiCodes[2], vesselNames[2], imoCodes[2], callSigns[2]);
        ShipPosition position2 = new ShipPosition(mmsiCodes[2], d1[2], 85, 90, 120, 255, 280, "JIRA");
        Ship testShip3 = new Ship(vesselType2, positionsBST, mmsiCodes[1], vesselNames[1], imoCodes[1], callSigns[1]);
        ShipPosition position3 = new ShipPosition(mmsiCodes[1], d1[1], 70, 110, 140, 200, 250, "JIRA");

        List<Ship> testList = shipsBST.getShipsByDate(new Date(), new Date());
        List<Ship> expectedList = new ArrayList<>();
        expectedList.add(testShip);
        expectedList.add(testShip3);

        Assert.assertEquals(expectedList, testList);
    }
}