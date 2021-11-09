package lapr.project.domain.BST;

import lapr.project.domain.model.Ship;
import lapr.project.domain.model.ShipPosition;
import lapr.project.domain.model.VesselType;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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

    /* for US7 */
    private ShipsBST shipsBST2;
    List<PositionsBST> positionsList;
    private PositionsBST positionsBST1;
    private PositionsBST positionsBST2;
    private PositionsBST positionsBST3;
    private PositionsBST positionsBST4;

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
            shipsBST.insert(new Ship(vesselType, positionsBST, mmsiCodes[i], vesselNames[i], imoCodes[i], callSigns[i]));
        }

        /* for US7 */

        shipsBST2 = new ShipsBST();
        positionsList = new ArrayList<>();
        positionsBST1 = new PositionsBST();
        positionsBST2 = new PositionsBST();
        positionsBST3 = new PositionsBST();
        positionsBST4 = new PositionsBST();




        for(int i=0; i<4;i++){
            positionsBST1.insert(new ShipPosition(mmsiCodes[0], d1[i], lats[i], lons[i], sogs[i], cogs[i], headings[i], transcieverClass));
            positionsBST2.insert(new ShipPosition(mmsiCodes[1], d1[i], lats[i], lons[i], sogs[i], cogs[i], headings[i], transcieverClass));
            positionsBST3.insert(new ShipPosition(mmsiCodes[2], d1[i], lats[i], lons[i], sogs[i], cogs[i], headings[i], transcieverClass));
        }

        positionsBST4.insert(new ShipPosition(mmsiCodes[3], d1[3], lats[3], lons[3], sogs[3], cogs[3], headings[3], transcieverClass));

        positionsList.add(positionsBST1);
        positionsList.add(positionsBST2);
        positionsList.add(positionsBST3);
        positionsList.add(positionsBST4);




        for(int i=0; i<4;i++){
            shipsBST2.insert(new Ship(vesselType, positionsList.get(i), mmsiCodes[i], vesselNames[i], imoCodes[i], callSigns[i]));
        }

        /* end for US7 */



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


    @Test
    void getShipsInOrderWithIntendedTD() {

        List<Ship> expList = new ArrayList<>();
        Ship s1 = new Ship(vesselType, positionsList.get(1), mmsiCodes[1], vesselNames[1], imoCodes[1], callSigns[1]);
        Ship s2 = new Ship(vesselType, positionsList.get(1), mmsiCodes[2], vesselNames[2], imoCodes[2], callSigns[2]);
        Ship s3 = new Ship(vesselType, positionsList.get(0), mmsiCodes[0], vesselNames[0], imoCodes[0], callSigns[0]);
        expList.add(s1);
        expList.add(s2);
        expList.add(s3);


        List<Ship> list = (List<Ship>) shipsBST2.getShipsInOrderWithIntendedTD();

        Assertions.assertEquals(expList, list);
    }
}