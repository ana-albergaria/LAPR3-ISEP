package lapr.project.domain.BST;

import lapr.project.domain.model.Ship;
import lapr.project.domain.model.ShipPosition;
import lapr.project.domain.model.ShipSortCallSign;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShipBstCallSignTest {

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
    String [] callSigns = {"C4SQ2", "5BBA4", "C4SR2", "5BZP3"};
    double [] lats = {-30.033056, -42.033006, -55.022056, 23.008721};
    double [] lons = {-51.230000, -47.223056, -46.233056, 24.092123};
    double [] sogs = {25.4, 25.8, 31.7, 10.2};
    double [] cogs = {341.2, 330.3, 328.5, 320.9};
    int [] headings = {300, 302, 315, 300};
    String transcieverClass = "AIS";

    private ShipBstCallSign shipsBST;



    ShipBstCallSignTest() throws ParseException {
    }

    @BeforeEach
    public void setUp() {
        posBST = new PositionsBST();
        posBST1 = new PositionsBST();
        posBST2 = new PositionsBST();
        posBST3 = new PositionsBST();
        shipsBST = new ShipBstCallSign();

        posBST.insert(new ShipPosition(mmsiCodes[0], d1[0], lats[0], lons[0], sogs[0], cogs[0], headings[0], transcieverClass));
        posBST.insert(new ShipPosition(mmsiCodes[1], d1[1], lats[1], lons[1], sogs[1], cogs[1], headings[1], transcieverClass));
        posBST1.insert(new ShipPosition(mmsiCodes[1], d1[1], lats[1], lons[1], sogs[1], cogs[1], headings[1], transcieverClass));
        posBST1.insert(new ShipPosition(mmsiCodes[2], d1[2], lats[2], lons[2], sogs[2], cogs[2], headings[2], transcieverClass));
        posBST2.insert(new ShipPosition(mmsiCodes[2], d1[2], lats[2], lons[2], sogs[2], cogs[2], headings[2], transcieverClass));
        posBST2.insert(new ShipPosition(mmsiCodes[3], d1[3], lats[3], lons[3], sogs[3], cogs[3], headings[3], transcieverClass));
        posBST3.insert(new ShipPosition(mmsiCodes[3], d1[3], lats[3], lons[3], sogs[3], cogs[3], headings[3], transcieverClass));
        posBST3.insert(new ShipPosition(mmsiCodes[1], d1[1], lats[1], lons[1], sogs[1], cogs[1], headings[1], transcieverClass));

        //System.out.println(new Ship(vesselType, positionsBST, mmsiCodes[i], vesselNames[i], imoCodes[i], callSigns[i]));
        shipsBST.insert(new ShipSortCallSign(posBST, mmsiCodes[0], vesselNames[0], imoCodes[0], callSigns[0], 70, 294,32,13.6,"79"));
        shipsBST.insert(new ShipSortCallSign(posBST1, mmsiCodes[1], vesselNames[1], imoCodes[1], callSigns[1], 70, 294,32,13.6,"79"));
        shipsBST.insert(new ShipSortCallSign(posBST2, mmsiCodes[2], vesselNames[2], imoCodes[2], callSigns[2], 70, 294,32,13.6,"79"));
        shipsBST.insert(new ShipSortCallSign(posBST3, mmsiCodes[3], vesselNames[3], imoCodes[3], callSigns[3], 70, 294,32,13.6,"79"));

    }


    @Test
    public void getShipbyCallSign() {
        String callsignToTest = "C4SQ2";
        Ship expShip = new ShipSortCallSign(posBST, mmsiCodes[0], vesselNames[0], imoCodes[0], callSigns[0], 70, 294,32,13.6,"79");

        Ship ship = shipsBST.getShipByCallSign(callsignToTest);

        assertEquals(expShip, ship);
    }

    @Test
    public void checkInsertWithCallSign() {
       ShipBstCallSign newBst = new ShipBstCallSign();
        int i;
       for(i=0;i<4;i++){
           newBst.insert(new ShipSortCallSign(posBST3, mmsiCodes[i], vesselNames[i], imoCodes[i], callSigns[i], 70, 294,32,13.6,"79"));
           assertEquals(newBst.size(), i+1, "size should be = "+(i+1));
       }
        for(i=0;i<4;i++){
            newBst.insert(new ShipSortCallSign(posBST3, mmsiCodes[i], vesselNames[i], imoCodes[i], callSigns[i], 70, 294,32,13.6,"79"));
            assertEquals(newBst.size(), 4, "size should be = "+(i+1));
        }
    }

    @Test
    public void testRemove() {
        int qtd = mmsiCodes.length-1;
        Ship toRemove = shipsBST.getShipByCallSign("C4SQ2");
        shipsBST.remove(toRemove);
        assertEquals(shipsBST.size(), qtd, "size should be = "+qtd);
    }

    @Test
    public void testRemoveNullElement(){
        ShipBstCallSign shipBstCallSign = new ShipBstCallSign();
        int currentSize = shipBstCallSign.size();
        Ship notThere = new ShipSortCallSign(posBST, 987654321, vesselNames[0], imoCodes[0], callSigns[0], 70, 294,32,13.6,"79");

        shipBstCallSign.remove(notThere);
        assertEquals(currentSize, shipBstCallSign.size());
    }
}