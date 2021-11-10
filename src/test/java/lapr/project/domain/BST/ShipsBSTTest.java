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
import java.util.*;

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


    double [] lats2 = {62.97875, 72.96912, -22.033006, -70.022056};
    double [] lats3 = {-29.00006,  60.008721, 50.00003, 34.345321};
    double [] lons2 = {50.000000, 60.000000, -30.000000, 20.000000};
    double [] lons3 = {-29.00006,  60.008721, 50.00003, 34.345321};

    double [] closeLats1 = {53.32055555, 53.32055550, 53.1861111, 53.2555};
    double [] closeLats2 = {53.1861211, 53.32055545, 53.32055300, 53.2455};
    double [] closeLats3 = {53.1861220, 53.1861300, 53.2001211, 53.2000};
    double [] closeLons1 = {-1.72972222, -1.69998000, -1.72972322, -1.70097222};
    double [] closeLons2 = {-1.69997222, -1.72972522, -1.72972422, -1.69997300};
    double [] closeLons3 = {-1.69997300,  -1.72972223, -1.72975222, -1.69997252};

    private ShipsBST shipsBST3;
    List<PositionsBST> positionsList2;
    private PositionsBST positionsBST5;
    private PositionsBST positionsBST6;
    private PositionsBST positionsBST7;
    private PositionsBST positionsBST8;


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
            positionsBST2.insert(new ShipPosition(mmsiCodes[1], d1[i], lats2[i], lons2[i], sogs[i], cogs[i], headings[i], transcieverClass));
            positionsBST3.insert(new ShipPosition(mmsiCodes[2], d1[i], lats3[i], lons3[i], sogs[i], cogs[i], headings[i], transcieverClass));
        }

        positionsBST4.insert(new ShipPosition(mmsiCodes[3], d1[3], lats[3], lons[3], sogs[3], cogs[3], headings[3], transcieverClass));

        positionsList.add(positionsBST1);
        positionsList.add(positionsBST2);
        positionsList.add(positionsBST3);
        positionsList.add(positionsBST4);

        for(int i=0; i<4;i++){
            shipsBST2.insert(new Ship(vesselType, positionsList.get(i), mmsiCodes[i], vesselNames[i], imoCodes[i], callSigns[i]));
        }

        /* more for US7 */

        shipsBST3 = new ShipsBST();
        positionsList2 = new ArrayList<>();
        positionsBST5 = new PositionsBST();
        positionsBST6 = new PositionsBST();
        positionsBST7 = new PositionsBST();
        positionsBST8 = new PositionsBST();

        for(int i=0; i<4;i++){
            positionsBST5.insert(new ShipPosition(mmsiCodes[0], d1[i], closeLats1[i], closeLons1[i], sogs[i], cogs[i], headings[i], transcieverClass));
            positionsBST6.insert(new ShipPosition(mmsiCodes[1], d1[i], closeLats2[i], closeLons2[i], sogs[i], cogs[i], headings[i], transcieverClass));
            positionsBST7.insert(new ShipPosition(mmsiCodes[2], d1[i], closeLats3[i], closeLons3[i], sogs[i], cogs[i], headings[i], transcieverClass));
        }

        positionsBST8.insert(new ShipPosition(mmsiCodes[3], d1[3], lats[3], lons[3], sogs[3], cogs[3], headings[3], transcieverClass));

        positionsList2.add(positionsBST5);
        positionsList2.add(positionsBST6);
        positionsList2.add(positionsBST7);
        positionsList2.add(positionsBST8);



        for(int i=0; i<4;i++){
            shipsBST3.insert(new Ship(vesselType, positionsList2.get(i), mmsiCodes[i], vesselNames[i], imoCodes[i], callSigns[i]));
        }

        /* end for US7 */



    }

    /**
     * US3 - Test to ensure getShipByMmsiCode() is functioning correctly.
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
     * US3 - Test to ensure getShipByMmsiCode() is functioning correctly.
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


    /**
     * US7 - Test to ensure getShipsInOrderWithIntendedTD works correctly.
     */
    @Test
    void getShipsInOrderWithIntendedTD() {

        List<Ship> expList = new ArrayList<>();
        Ship s1 = new Ship(vesselType, positionsList.get(1), mmsiCodes[1], vesselNames[1], imoCodes[1], callSigns[1]);
        Ship s2 = new Ship(vesselType, positionsList.get(2), mmsiCodes[2], vesselNames[2], imoCodes[2], callSigns[2]);
        Ship s3 = new Ship(vesselType, positionsList.get(0), mmsiCodes[0], vesselNames[0], imoCodes[0], callSigns[0]);
        expList.add(s1);
        expList.add(s2);
        expList.add(s3);


        List<Ship> list = (List<Ship>) shipsBST2.getShipsInOrderWithIntendedTD();

        Assertions.assertEquals(expList, list);
    }

    /**
     * US7 - Test to ensure fillTreeMapEachShip() works correctly.
     */
    @Test
    void fillTreeMapForEachShip() {
        Iterator<Ship> listShipsWithIntendedTD = shipsBST3.inOrder().iterator();

        TreeMap<Double, String> expInfoPair = new TreeMap<>(Collections.reverseOrder());
        expInfoPair.put(18.0, String.format("%-15d%-15d%-15f%-15f%-15d%-15f%-15d%-15f%n", 111111111, 222222222, 5.0, 0.0, 4, 24.0, 4, 6.0));


        TreeMap<Double, String> infoPair = new TreeMap<>(Collections.reverseOrder());

        Ship ship = listShipsWithIntendedTD.next();

        PositionsBST positionsBST = ship.getPositionsBST();
        Double travelledDistance = positionsBST.getTotalDistance();

        shipsBST3.fillTreeMapForEachShip(listShipsWithIntendedTD, infoPair, travelledDistance, positionsBST, ship.getMMSI());

        /*for (Map.Entry<Double, String> entry : infoPair.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ". Value: " + entry.getValue());
        }
         */

        Assertions.assertEquals(expInfoPair, infoPair);
    }
}