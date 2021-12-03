package lapr.project.controller;

import lapr.project.data.DatabaseConnection;
import lapr.project.data.ShipTripStoreDB;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GetContainerSituationControllerTest {
    private DatabaseConnection databaseConnection;
    private GetContainerSituationController getContainerSituationController;

    @BeforeEach
    void setUp() {
        databaseConnection = mock(DatabaseConnection.class);
        getContainerSituationController = mock(GetContainerSituationController.class);
    }

    @Test
    void getLocation() {
        try {
            String expResult = "PORT, Constantza";
            when(getContainerSituationController.getLocation(2549246,374)).thenReturn("PORT, Constantza");
            String result = getContainerSituationController.getLocation(2549246,374);
            Assert.assertEquals(expResult, result);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}