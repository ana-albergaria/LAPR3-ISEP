package lapr.project.controller;

import lapr.project.domain.BST.PositionsBST;
import lapr.project.domain.model.Company;
import lapr.project.domain.model.Ship;
import lapr.project.domain.model.ShipPosition;
import lapr.project.domain.model.VesselType;
import lapr.project.dto.MovementsSummaryDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class MovementSummaryControllerTest {
    private Company comp;
    private VesselType vesselType;
    private int mmsi1, mmsi3, mmsi4;
    private String vesselName;
    private String imo;
    private String callSign;
    private Ship s1, s2, s3, s4;

    int [] mmsiCodes = {333333333, 111111111, 222222222, 123456789};
    double [] lats = {-30.033056, -42.033006, -55.022056, 23.008721};
    double [] lons = {-51.230000, -47.223056, -46.233056, 24.092123};
    double [] sogs = {25.4, 25.8, 31.7, 10.2};
    double [] cogs = {341.2, 330.3, 328.5, 320.9};
    int [] headings = {300, 302, 315, 300};
    String transcieverClass = "AIS";
    Date[] d1 = {new SimpleDateFormat("dd/MM/yyyy").parse("04/01/2021"),
            new SimpleDateFormat("dd/MM/yyyy").parse("07/01/2021"),
            new SimpleDateFormat("dd/MM/yyyy").parse("10/01/2021"),
            new SimpleDateFormat("dd/MM/yyyy").parse("13/01/2021")} ;

    MovementSummaryControllerTest() throws ParseException {
    }

    @BeforeEach
    public void setUp(){
        comp = new Company("Shipping company");
        vesselType = new VesselType(70, 294,32,13.6,79);
        PositionsBST positions = new PositionsBST();
        for(int i=0; i<3;i++){
            positions.insert(new ShipPosition(mmsiCodes[i], d1[i], lats[i], lons[i], sogs[i], cogs[i], headings[i], transcieverClass));
        }
        mmsi1 = 123456789;
        mmsi3 = 123456788;
        mmsi4 = 123456790;
        vesselName = "VARAMO";
        imo = "IMO9395044";
        callSign = "C4SQ2";
        s1 = new Ship(vesselType, positions, mmsi1, vesselName, imo, callSign);
        s2 = new Ship(vesselType, positions, mmsi1, vesselName, imo, callSign);
        s3 = new Ship(vesselType, positions, mmsi3, vesselName, imo, callSign);
        s4 = new Ship(vesselType, positions, mmsi4, vesselName, imo, callSign);

        comp.getBstShip().insert(s1);
        comp.getBstShip().insert(s2);
        comp.getBstShip().insert(s3);
        comp.getBstShip().insert(s4);

    }

    @Test
    public void integrationTestWithController(){
        MovementSummaryController controller = new MovementSummaryController(comp);
        MovementsSummaryDto mSummary = controller.getShipMovementsSummary(123456789);
        System.out.println(mSummary);
    }

}