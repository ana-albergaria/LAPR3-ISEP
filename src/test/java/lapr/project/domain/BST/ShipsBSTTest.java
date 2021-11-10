package lapr.project.domain.BST;

import lapr.project.domain.model.Ship;
import lapr.project.domain.model.ShipPosition;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ShipsBSTTest {

    /* Atributes for the Ship */
    private PositionsBST posBST;
    private PositionsBST posBST1;
    private PositionsBST posBST2;
    private PositionsBST posBST3;
    private PositionsBST posBST4;

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
        posBST = new PositionsBST();
        posBST1 = new PositionsBST();
        posBST2 = new PositionsBST();
        posBST3 = new PositionsBST();
        shipsBST = new ShipsBST();

        posBST.insert(new ShipPosition(mmsiCodes[0], d1[0], lats[0], lons[0], sogs[0], cogs[0], headings[0], transcieverClass));
        posBST.insert(new ShipPosition(mmsiCodes[1], d1[1], lats[1], lons[1], sogs[1], cogs[1], headings[1], transcieverClass));
        posBST1.insert(new ShipPosition(mmsiCodes[1], d1[1], lats[1], lons[1], sogs[1], cogs[1], headings[1], transcieverClass));
        posBST1.insert(new ShipPosition(mmsiCodes[2], d1[2], lats[2], lons[2], sogs[2], cogs[2], headings[2], transcieverClass));
        posBST2.insert(new ShipPosition(mmsiCodes[2], d1[2], lats[2], lons[2], sogs[2], cogs[2], headings[2], transcieverClass));
        posBST2.insert(new ShipPosition(mmsiCodes[3], d1[3], lats[3], lons[3], sogs[3], cogs[3], headings[3], transcieverClass));
        posBST3.insert(new ShipPosition(mmsiCodes[3], d1[3], lats[3], lons[3], sogs[3], cogs[3], headings[3], transcieverClass));
        posBST3.insert(new ShipPosition(mmsiCodes[1], d1[1], lats[1], lons[1], sogs[1], cogs[1], headings[1], transcieverClass));

        //System.out.println(new Ship(vesselType, positionsBST, mmsiCodes[i], vesselNames[i], imoCodes[i], callSigns[i]));
        shipsBST.insert(new Ship(posBST, mmsiCodes[0], vesselNames[0], imoCodes[0], callSigns[0], 70, 294,32,13.6,79));
        shipsBST.insert(new Ship(posBST1, mmsiCodes[1], vesselNames[1], imoCodes[1], callSigns[1], 70, 294,32,13.6,79));
        shipsBST.insert(new Ship(posBST2, mmsiCodes[2], vesselNames[2], imoCodes[2], callSigns[2], 70, 294,32,13.6,79));
        shipsBST.insert(new Ship(posBST3, mmsiCodes[3], vesselNames[3], imoCodes[3], callSigns[3], 70, 294,32,13.6,79));


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
            shipsBST2.insert(new Ship(positionsList.get(i), mmsiCodes[i], vesselNames[i], imoCodes[i], callSigns[i], 70, 294,32,13.6,79));
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
            shipsBST3.insert(new Ship(positionsList2.get(i), mmsiCodes[i], vesselNames[i], imoCodes[i], callSigns[i], 70, 294,32,13.6,79));
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
        Ship expShip = new Ship(posBST, mmsiCodes[3], vesselNames[3], imoCodes[3], callSigns[3], 70, 294,32,13.6,79);

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
        expectedList.add(new Ship(posBST, mmsiCodes[1], vesselNames[1], imoCodes[1], callSigns[1], 70, 294,32,13.6,79));
        expectedList.add(new Ship(posBST, mmsiCodes[2], vesselNames[2], imoCodes[2], callSigns[2], 70, 294,32,13.6,79));


        Assert.assertEquals(expectedList, testList);
    }

    /**
     * ensure the shipList is ordered and only the top-N ships are in it
     */
    @Test
    public void sortNshipsCorrect() throws ParseException {
        List<Ship> testList = new LinkedList<>();
        testList.add(new Ship( posBST, mmsiCodes[0], vesselNames[0], imoCodes[0], callSigns[0], 70, 294,32,13.6,79));
        testList.add(new Ship(posBST1, mmsiCodes[1], vesselNames[1], imoCodes[1], callSigns[1], 70, 294,32,13.6,79));
        testList.add(new Ship(posBST2, mmsiCodes[2], vesselNames[2], imoCodes[2], callSigns[2], 70, 294,32,13.6,79));
        testList.add(new Ship(posBST3, mmsiCodes[3], vesselNames[3], imoCodes[3], callSigns[3], 70, 294,32,13.6,79));

        List<Ship> test1 = shipsBST.sortNShips(testList);

        List<Ship> expectedList = new LinkedList<>();
        expectedList.add(new Ship(posBST2, mmsiCodes[2], vesselNames[2], imoCodes[2], callSigns[2], 70, 294,32,13.6,79));
        expectedList.add(new Ship(posBST3, mmsiCodes[3], vesselNames[3], imoCodes[3], callSigns[3], 70, 294,32,13.6,79));
        expectedList.add(new Ship(posBST1, mmsiCodes[1], vesselNames[1], imoCodes[1], callSigns[1], 70, 294,32,13.6,79));
        expectedList.add(new Ship(posBST, mmsiCodes[0], vesselNames[0], imoCodes[0], callSigns[0], 70, 294,32,13.6,79));

        Assert.assertEquals(expectedList, test1);
    }

    /**
     * ensure the ships are associated with the correct vessel type
     */
   /* @Test
    public void getMapWithTopNAssociatedWithVesselType(){
        Map<VesselType, Set<Ship>> testMap = new HashMap<VesselType, Set<Ship>>();
        Set<Ship> expectedList = new HashSet<>();
        expectedList.add(new Ship(vesselType, positionsBST, mmsiCodes[0], vesselNames[0], imoCodes[0], callSigns[0]));
        expectedList.add(new Ship(vesselType2, positionsBST, mmsiCodes[1], vesselNames[1], imoCodes[1], callSigns[1]));
        testMap = shipsBST.getShipWithMean(expectedList);

        Set<Ship> vesselTypeList1 = new HashSet<>();
        vesselTypeList1.add(new Ship(vesselType, positionsBST, mmsiCodes[0], vesselNames[0], imoCodes[0], callSigns[0]));
        Set<Ship> vesselTypeList2 = new HashSet<>();
        vesselTypeList2.add(new Ship(vesselType2, positionsBST, mmsiCodes[1], vesselNames[1], imoCodes[1], callSigns[1]));

        Map<VesselType, Set<Ship>> expectedMap = new HashMap<VesselType, Set<Ship>>(){ {
            put(vesselType, vesselTypeList1);
            put(vesselType2, vesselTypeList2);

        }
        };

        Assert.assertEquals(testMap, expectedMap);
    }

    */

    /**
     * ensure all the ships are int the List
     */
    @Test
    public void getAllShipsMMSI() {
        List<Ship> expectedList = new ArrayList<>();
        for (int i=0; i<4; i++) {
            expectedList.add(new Ship(posBST, mmsiCodes[i], vesselNames[i], imoCodes[i], callSigns[i], 70, 294,32,13.6,79));
        }

        List<Ship> actualList = shipsBST.getAllShips();

        Assert.assertEquals(expectedList, actualList);
    }


    @Test
    public void mapOrderedByTravelledDistance() {
        Set<Double> l1 = new HashSet<>();
        Ship s1 = shipsBST.getShipByMmsiCode(mmsiCodes[0]);
        l1.add(s1.getPositionsBST().getTotalDistance());
        l1.add(s1.getPositionsBST().getDeltaDistance());
        l1.add((double) s1.getPositionsBST().size());

        Set<Double> l2 = new HashSet<>();
        Ship s2 = shipsBST.getShipByMmsiCode(mmsiCodes[1]);
        l2.add(s2.getPositionsBST().getTotalDistance());
        l2.add(s2.getPositionsBST().getDeltaDistance());
        l2.add((double) s2.getPositionsBST().size());

        Set<Double> l3 = new HashSet<>();
        Ship s3 = shipsBST.getShipByMmsiCode(mmsiCodes[2]);
        l3.add(s3.getPositionsBST().getTotalDistance());
        l3.add(s3.getPositionsBST().getDeltaDistance());
        l3.add((double) s3.getPositionsBST().size());

        Set<Double> l4 = new HashSet<>();
        Ship s4 = shipsBST.getShipByMmsiCode(mmsiCodes[3]);
        l4.add(s4.getPositionsBST().getTotalDistance());
        l4.add(s4.getPositionsBST().getDeltaDistance());
        l4.add((double) s4.getPositionsBST().size());

        Map<Integer, Set<Double>> expectedMap = new LinkedHashMap<Integer, Set<Double>>(){
            {

                put(mmsiCodes[2], l3);
                put(mmsiCodes[3], l4);
                put(mmsiCodes[1], l2);
                put(mmsiCodes[0], l1);

            }
        };

        List<Ship> expectedList = new ArrayList<>();
        expectedList.add(new Ship(posBST, mmsiCodes[0], vesselNames[0], imoCodes[0], callSigns[0], 70, 294,32,13.6,79));
        expectedList.add(new Ship(posBST1, mmsiCodes[1], vesselNames[1], imoCodes[1], callSigns[1], 70, 294,32,13.6,79));
        expectedList.add(new Ship(posBST2, mmsiCodes[2], vesselNames[2], imoCodes[2], callSigns[2], 70, 294,32,13.6,79));
        expectedList.add(new Ship(posBST3, mmsiCodes[3], vesselNames[3], imoCodes[3], callSigns[3], 70, 294,32,13.6,79));

        Map<Integer, Set<Double>> actualMap = shipsBST.sortedByTravelledDistance(expectedList);

        Assert.assertEquals(expectedMap, actualMap);
    }

    @Test
    public void mapOrderedByMovements() {
        Set<Double> l1 = new HashSet<>();
        Ship s1 = shipsBST.getShipByMmsiCode(mmsiCodes[0]);
        l1.add(s1.getPositionsBST().getTotalDistance());
        l1.add(s1.getPositionsBST().getDeltaDistance());
        l1.add((double) s1.getPositionsBST().size());

        Set<Double> l2 = new HashSet<>();
        Ship s2 = shipsBST.getShipByMmsiCode(mmsiCodes[1]);
        l2.add(s2.getPositionsBST().getTotalDistance());
        l2.add(s2.getPositionsBST().getDeltaDistance());
        l2.add((double) s2.getPositionsBST().size());

        Set<Double> l3 = new HashSet<>();
        Ship s3 = shipsBST.getShipByMmsiCode(mmsiCodes[2]);
        l3.add(s3.getPositionsBST().getTotalDistance());
        l3.add(s3.getPositionsBST().getDeltaDistance());
        l3.add((double) s3.getPositionsBST().size());

        Set<Double> l4 = new HashSet<>();
        Ship s4 = shipsBST.getShipByMmsiCode(mmsiCodes[3]);
        l4.add(s4.getPositionsBST().getTotalDistance());
        l4.add(s4.getPositionsBST().getDeltaDistance());
        l4.add((double) s4.getPositionsBST().size());

        Map<Integer, Set<Double>> expectedMap = new LinkedHashMap<Integer, Set<Double>>(){
            {
                put(mmsiCodes[1], l2);
                put(mmsiCodes[2], l3);
                put(mmsiCodes[3], l4);
                put(mmsiCodes[0], l1);
            }
        };
    }


    /**
     * US7 - Test to ensure getShipsInOrderWithIntendedTD works correctly.
     */
    @Test
    void getShipsInOrderWithIntendedTD() {

        List<Ship> expList = new ArrayList<>();
        Ship s1 = new Ship(positionsList.get(1), mmsiCodes[1], vesselNames[1], imoCodes[1], callSigns[1], 70, 294,32,13.6,79);
        Ship s2 = new Ship(positionsList.get(2), mmsiCodes[2], vesselNames[2], imoCodes[2], callSigns[2], 70, 294,32,13.6,79);
        Ship s3 = new Ship(positionsList.get(0), mmsiCodes[0], vesselNames[0], imoCodes[0], callSigns[0], 70, 294,32,13.6,79);
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