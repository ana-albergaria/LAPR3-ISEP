package lapr.project.controller;

import lapr.project.domain.BST.PositionsBST;
import lapr.project.domain.model.Company;
import lapr.project.domain.model.Ship;
import lapr.project.domain.model.ShipPosition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.*;

public class ShowPositionalMessagesControllerTest {

    private Company comp;
    private int mmsi1, mmsi3, mmsi4;
    private String vesselName;
    private String imo;
    private String callSign;
    private Ship s1, s2, s3, s4;

    double [] lats = {-30.033056, -42.033006, -55.022056, 23.008721};
    double [] lons = {-51.230000, -47.223056, -46.233056, 24.092123};
    double [] sogs = {25.4, 25.8, 31.7, 10.2};
    double [] cogs = {341.2, 330.3, 328.5, 320.9};
    int [] headings = {300, 302, 315, 300};
    String transcieverClass = "AIS";
    Date[] d1 = {new SimpleDateFormat("dd/MM/yyyy").parse("04/05/2021"),
            new SimpleDateFormat("dd/MM/yyyy").parse("07/04/2021"),
            new SimpleDateFormat("dd/MM/yyyy").parse("10/03/2021"),
            new SimpleDateFormat("dd/MM/yyyy").parse("13/02/2021")} ;

    public ShowPositionalMessagesControllerTest() throws ParseException {}


    @BeforeEach
    public void setUp() throws Exception {
        comp = new Company("Shipping company");
        PositionsBST positions = new PositionsBST();
        mmsi1 = 123456789;
        for(int i=0; i<4;i++){
            positions.insert(new ShipPosition(mmsi1, d1[i], lats[i], lons[i], sogs[i], cogs[i], headings[i], transcieverClass));
        }
        mmsi3 = 123456788;
        mmsi4 = 123456790;
        vesselName = "VARAMO";
        imo = "IMO9395044";
        callSign = "C4SQ2";
        PositionsBST positions2 = new PositionsBST();
        positions2.insert(new ShipPosition(mmsi1, d1[3], lats[3], lons[3], sogs[3], cogs[3], headings[3], transcieverClass));
        s1 = new Ship(positions, mmsi1, vesselName, imo, callSign, 70, 294,32,13.6,79);
        s2 = new Ship(positions2, mmsi1, vesselName, imo, callSign, 70, 294,32,13.6,79);
        s3 = new Ship(positions2, mmsi3, vesselName, imo, callSign, 70, 294,32,13.6,79);
        s4 = new Ship(positions2, mmsi4, vesselName, imo, callSign, 70, 294,32,13.6,79);

        comp.getBstShip().insert(s1);
        comp.getBstShip().insert(s2);
        comp.getBstShip().insert(s3);
        comp.getBstShip().insert(s4);
    }

    @Test
    public void isValidShipWhenExistent() {
        ShowPositionalMessagesController ctrl = new ShowPositionalMessagesController(comp);
        int mmsiCode = 123456788;
        boolean result = ctrl.isValidShip(mmsiCode);

        Assertions.assertTrue(result);
    }

    @Test
    public void isValidShipWhenNonExistent() {
        ShowPositionalMessagesController ctrl = new ShowPositionalMessagesController(comp);
        int mmsiCode = 123000008;
        boolean result = ctrl.isValidShip(mmsiCode);

        Assertions.assertFalse(result);
    }

    @Test
    public void showPositionalMessages() {
        ShowPositionalMessagesController ctrl = new ShowPositionalMessagesController(comp);
        int mmsiCode = 123456789;
        ctrl.isValidShip(mmsiCode);

        Map<Integer, List<String>> expMap = new HashMap<>();
        List<String> messages = new ArrayList<>();
        ShipPosition sp1 = new ShipPosition(mmsiCode, d1[3], lats[3], lons[3], sogs[3], cogs[3], headings[3], transcieverClass);
        ShipPosition sp2 = new ShipPosition(mmsiCode, d1[2], lats[2], lons[2], sogs[2], cogs[2], headings[2], transcieverClass);
        ShipPosition sp3 = new ShipPosition(mmsiCode, d1[1], lats[1], lons[1], sogs[1], cogs[1], headings[1], transcieverClass);
        ShipPosition sp4 = new ShipPosition(mmsiCode, d1[0], lats[0], lons[0], sogs[0], cogs[0], headings[0], transcieverClass);
        messages.add(sp1.toString());
        messages.add(sp2.toString());
        messages.add(sp3.toString());
        messages.add(sp4.toString());

        expMap.put(s1.getVesselTypeID(), messages);

        Map<Integer, List<String>> map = ctrl.showPositionalMessages(d1[3], d1[0]);

        for (Map.Entry<Integer, List<String>> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue().toString());
        }

        assertEquals(expMap, map);

    }
}