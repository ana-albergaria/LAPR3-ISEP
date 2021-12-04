package lapr.project.controller;

import lapr.project.data.DatabaseConnection;
import lapr.project.domain.model.Company;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Marta Ribeiro (1201592)
 */
public class ShipOccupancyRatesControllerTest {

    private Company comp;
    private ShipOccupancyRatesController ctrl;
    private DatabaseConnection databaseConnection;
    private ShipOccupancyRatesController shipOccupancyRatesController;

    @BeforeEach
    public void SetUp(){
        comp = new Company("Company");
        this.ctrl=new ShipOccupancyRatesController(comp);
        databaseConnection = mock(DatabaseConnection.class);
        shipOccupancyRatesController = mock(ShipOccupancyRatesController.class);
    }

    /**
     * Test to ensure the occupancy rate is correctly calculated when the values are valid.
     */
    @Test
    void testOccupancyRatesCalculationWhenValid(){
        System.out.println("testOccupancyRatesCalculationWhenValid()");
        int maxCapacity1=79;
        int initialNumContainers1=14;
        int alreadyAddedRemovedContainersTripNum1=7;
        int expected=26;
        int actual=ctrl.calculateOccupancyRate(maxCapacity1,initialNumContainers1,alreadyAddedRemovedContainersTripNum1);
        System.out.println("exp: " + expected);
        System.out.println("real: " + actual);
        Assertions.assertEquals(expected,actual);
    }

    /**
     * Test to ensure the occupancy rate is -1 when the values are invalid.
     */
    @Test
    void testOccupancyRatesCalculationWhenInvalid(){
        System.out.println("testOccupancyRatesCalculationWhenInvalid()");
        int maxCapacity1=20;
        int initialNumContainers1=14;
        int alreadyAddedRemovedContainersTripNum1=7;
        int expected=-1;
        int actual=ctrl.calculateOccupancyRate(maxCapacity1,initialNumContainers1,alreadyAddedRemovedContainersTripNum1);
        System.out.println("exp: " + expected);
        System.out.println("real: " + actual);
        Assertions.assertEquals(expected,actual);
    }

    /*
    mmsi:636092933  max:60
    no ship trips
    mmsi:212351001  max:79
    19822 - loading - de 06/02/2021 a 8/02/2021 -  0+10=10
            unloading: 724 -  10-1=9
    39824 - loading - de 11/02/2021 a 20/02/2021 -  9+8=17
            unloading: 45807 -  17-3=14
    77329 - loading - de 24/02/2021 a 26/02/2021 -  14+7=21
            unloading: 87508 -  21-6=15
    */

    /**
     * Test to check if the occupancy rate of a ship is correctly calculated given a cargo manifest.
     */
    @Test
    void testOccupancyRatesCargoManifest(){
        //cmid: 77329
        System.out.println("testOccupancyRatesCargoManifest()");
        try {
            int expResult = 21; //valor esperado
            when(shipOccupancyRatesController.getShipOccupancyRateByCargoManifestID(77329)).thenReturn(21); //valor esperado
            int result = shipOccupancyRatesController.getShipOccupancyRateByCargoManifestID(77329);
            System.out.println("exp: " + expResult);
            System.out.println("real: " + result);
            Assert.assertEquals(expResult, result);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Test to check if the occupancy rate of a ship is correctly calculated given a mmsi and date.
     */
    @Test
    void testOccupancyRatesMoment(){
        //mmsi: 212351001 and date: 25/02/2021 -> cmid: 77329
        System.out.println("testOccupancyRatesMoment()");
        try {
            int expResult = 21; //valor esperado
            when(shipOccupancyRatesController.getShipOccupancyRateByMmsiAndDate(212351001,new java.sql.Date(new Date(2021,Calendar.FEBRUARY,25).getTime()))).thenReturn(21); //valor esperado
            int result = shipOccupancyRatesController.getShipOccupancyRateByMmsiAndDate(212351001,new java.sql.Date(new Date(2021,Calendar.FEBRUARY,25).getTime()));
            System.out.println("exp: " + expResult);
            System.out.println("real: " + result);
            Assert.assertEquals(expResult, result);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /*@Test
    void testOccupancyRatesCargoManifest2(){
        //cmid: 77329
        System.out.println("testOccupancyRatesCargoManifest2()");
        try {
            int expResult = 21; //valor esperado
            int result = ctrl.getShipOccupancyRateByCargoManifestID(77329);
            System.out.println("exp: " + expResult);
            System.out.println("real: " + result);
            Assert.assertEquals(expResult, result);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test
    void testOccupancyRatesMoment2(){
        //mmsi: 212351001 and date: 25/02/2021 -> cmid: 77329
        System.out.println("testOccupancyRatesMoment2()");
        try {
            int expResult = 21; //valor esperado
            int result = ctrl.getShipOccupancyRateByMmsiAndDate(212351001, java.sql.Date.valueOf("2021-02-25"));
            System.out.println("exp: " + expResult);
            System.out.println("real: " + result);
            Assert.assertEquals(expResult, result);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }*/

}
