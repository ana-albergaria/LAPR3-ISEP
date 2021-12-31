package lapr.project.data;

import lapr.project.controller.App;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ShipTripStoreDBTest {
    private DatabaseConnection databaseConnection;
    private ShipTripStoreDB shipTripStoreDB;

    @BeforeEach
    void setUp() {
        databaseConnection = mock(DatabaseConnection.class);
        shipTripStoreDB = mock(ShipTripStoreDB.class);
    }

    @Test
    void getPortIdTest() {
        int actID;
        try {
            when(shipTripStoreDB.getNextPortID(databaseConnection, 256888000)).thenReturn(10358);
            actID = shipTripStoreDB.getNextPortID(databaseConnection, 256888000);
            Assertions.assertEquals(10358, actID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getOffloadedListTest() {
        List<Integer> expList = new LinkedList<>();
        expList.add(2116393);
        expList.add(3989030);
        expList.add(810979);
        expList.add(2136499);
        expList.add(9143577);
        expList.add(4924955);
        expList.add(8059722);
        expList.add(9834739);
        expList.add(9070585);
        expList.add(5465471);
        expList.add(7964863);
        expList.add(4300882);
        List<Integer> actList = new LinkedList<>();
        try {
            when(shipTripStoreDB.getListOffloadedContainers(databaseConnection, 256888000)).thenReturn(expList);
            actList = shipTripStoreDB.getListOffloadedContainers(databaseConnection, 256888000);
            Assertions.assertEquals(expList, actList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getLoadedListTest() {
        List<Integer> expList = new LinkedList<>();
        expList.add(8529220);
        expList.add(6107524);
        expList.add( 2755128);

        List<Integer> actList = new LinkedList<>();
        try {
            when(shipTripStoreDB.getListLoadedContainers(databaseConnection, 256888000)).thenReturn(expList);
            actList = shipTripStoreDB.getListLoadedContainers(databaseConnection, 256888000);
            Assertions.assertEquals(expList, actList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}