package lapr.project.data;

import lapr.project.controller.App;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

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
    void getShipTripId() {
        try {
            int expResult = 16347;
            when(shipTripStoreDB.getShipTripId(databaseConnection, 2549246,374)).thenReturn(16347);
            int result = shipTripStoreDB.getShipTripId(databaseConnection, 2549246,374);
            Assert.assertEquals(expResult, result);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test
    void getLocation() {
        String expResult = "PORT, Constantza";
        when(shipTripStoreDB.getLocation(databaseConnection, 16347)).thenReturn("PORT, Constantza");
        String result = shipTripStoreDB.getLocation(databaseConnection, 16347);
        Assert.assertEquals(expResult, result);
    }
}