package lapr.project.domain.store;

import lapr.project.domain.BST.Ports2DTree;
import lapr.project.domain.BST.PositionsBST;
import lapr.project.domain.model.Port;
import lapr.project.domain.model.Ship;
import lapr.project.domain.model.ShipPosition;
import lapr.project.domain.model.ShipSortMmsi;
import lapr.project.dto.PositionDTO;
import lapr.project.dto.ShipsFileDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class PortStoreTest {
    private final PortStore store = new PortStore();
    private PositionsBST posBST = new PositionsBST();
    private Ship ship;
    private Date dateR1;
    private Port port1, port2;
    private Ports2DTree portTree = new Ports2DTree();
    private ShipPosition shipPosition;


    @BeforeEach
    public void setUp() throws ParseException {
        dateR1 = new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2020");
        shipPosition = new ShipPosition(123456789, dateR1, 36.39094, -122.71335, 19.7, 145.5, 147, "B");
        posBST.insert(shipPosition);
        ship = new ShipSortMmsi(posBST, 123456789, "VARAMO", "IMO9395044", "C4SQ2", 70, 294,32,13.6,"79");

        port1 = new Port( 123456789, "Porto Leixões", "Europa", "Portugal", 35.22, -120.71);
        store.savePort(port1);
        port2 = new Port(987654321, "Porto Algarve", "Europa", "Portugal", 20.54, -100.87);
        store.savePort(port2);
        store.balancePorts2DTree();
    }

    @Test
    public void getNearestPort() {
        Port exp = port1;


        List<Double> coordinates = new LinkedList<>();
        coordinates.add(shipPosition.getLat());
        coordinates.add(shipPosition.getLon());

        Port actual = store.findClosestPort(coordinates);

        Assertions.assertEquals(exp, actual);
    }
}
