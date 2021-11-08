package lapr.project.domain.BST;

import lapr.project.domain.model.Ship;
import lapr.project.domain.model.ShipPosition;
import lapr.project.domain.model.VesselType;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShipsBSTTest {

    /* Atributes for the Ship */
    private VesselType vesselType;
    private VesselType vesselType2;
    private PositionsBST positionsBST;

    Date[] d1 = {new SimpleDateFormat("dd/MM/yyyy").parse("04/01/2021"),
            new SimpleDateFormat("dd/MM/yyyy").parse("07/01/2021"),
            new SimpleDateFormat("dd/MM/yyyy").parse("10/01/2021"),
            new SimpleDateFormat("dd/MM/yyyy").parse("13/01/2021")} ;
    int [] mmsiCodes = {333333333, 111111111, 222222222, 123456789};
    String [] vesselNames = {"VARAMO", "SAITA", "VARAMO", "HYUNDAI SINGAPURE"};
    String [] imoCodes = {"IMO9395044", "IMO9395022", "IMO9395066", "IMO9395088"};
    String [] callSigns = {"C4SQ2", "5BBA4", "C4SQ2", "5BZP3"};
    double [] lats = {-30.033056, -42.033006, -55.022056, 23.008721};
    double [] lons = {-51.230000, -47.223056, -46.233056, 24.092123};
    double [] sogs = {25.4, 25.8, 31.7, 10.2};
    double [] cogs = {341.2, 330.3, 328.5, 320.9};
    int [] headings = {300, 302, 315, 300};
    String transcieverClass = "AIS";

    private ShipsBST shipsBST;

    public ShipsBSTTest() throws ParseException {
    }

    @BeforeEach
    public void setUp() {
        vesselType = new VesselType(70, 294,32,13.6,79);
        vesselType2 = new VesselType(80, 250, 50, 25, 65);
        positionsBST = new PositionsBST();
        shipsBST = new ShipsBST();
        for(int i=0; i<4;i++){
            positionsBST.insert(new ShipPosition(mmsiCodes[i], d1[i], lats[i], lons[i], sogs[i], cogs[i], headings[i], transcieverClass));
        }
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
    public void getShipsByDateCorrect() throws ParseException {


        List<Ship> testList = shipsBST.getShipsByDate(new SimpleDateFormat("dd/MM/yyyy").parse("06/01/2021"), new SimpleDateFormat("dd/MM/yyyy").parse("11/01/2021"));
        List<Ship> expectedList = new ArrayList<>();
        expectedList.add(new Ship(vesselType, positionsBST, mmsiCodes[1], vesselNames[1], imoCodes[1], callSigns[1]));
        expectedList.add(new Ship(vesselType, positionsBST, mmsiCodes[2], vesselNames[2], imoCodes[2], callSigns[2]));


        Assert.assertEquals(expectedList, testList);
    }

    /**
     * ensure the shipList is ordered and only the top-N ships are in it
     */
    @Test
    public void sortNshipsCorrect() throws ParseException {
        List<Ship> testList = shipsBST.getShipsByDate(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2021"), new SimpleDateFormat("dd/MM/yyyy").parse("14/01/2021"));
        List<Ship> test1 = shipsBST.sortNShips(testList, 2);
        List<Ship> expectedList = new ArrayList<>();
        expectedList.add(new Ship(vesselType, positionsBST, mmsiCodes[0], vesselNames[0], imoCodes[0], callSigns[0]));
        expectedList.add(new Ship(vesselType, positionsBST, mmsiCodes[1], vesselNames[1], imoCodes[1], callSigns[1]));


        Assert.assertEquals(expectedList, test1);
    }
}