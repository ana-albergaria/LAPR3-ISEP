package lapr.project.controller;

import lapr.project.domain.model.Company;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Marta Ribeiro (1201592)
 */
public class ShipOccupancyRatesControllerTest {

    private Company comp;
    private ShipOccupancyRatesController ctrl;

    @BeforeEach
    public void SetUp(){
        comp = new Company("Company");
        this.ctrl=new ShipOccupancyRatesController(comp);
    }

    /**
     * Test to ensure the occupancy rate is correctly calculated when the values are valid.
     */
    @Test
    public void testOccupancyRatesCalculationWhenValid(){
        int maxCapacity1=75;
        int initialNumContainers1=15;
        int alreadyAddedRemovedContainersTripNum1=12;
        int expected=36;
        double actual=ctrl.calculateOccupancyRate(maxCapacity1,initialNumContainers1,alreadyAddedRemovedContainersTripNum1);
        Assertions.assertEquals(expected,actual);
    }

    /**
     * Test to ensure the occupancy rate is -1 when the values are invalid.
     */
    @Test
    public void testOccupancyRatesCalculationWhenInvalid(){
        int maxCapacity1=30;
        int initialNumContainers1=15;
        int alreadyAddedRemovedContainersTripNum1=20;
        int expected=-1;
        double actual=ctrl.calculateOccupancyRate(maxCapacity1,initialNumContainers1,alreadyAddedRemovedContainersTripNum1);
        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void testOccupancyRatesCargoManifest(){
        int cargoManifestID= 0; //valor cargoManifestID
        int mmsi = ctrl.getMmsiByCargoManifest(cargoManifestID);
        //get mmsi by cargo manifest SCRIPT NA SHIP TRIP STORE
        double result = ctrl.getShipOccupancyRateByCargoManifestID(cargoManifestID);
        System.out.println("OCCUPANCY RATE");
        System.out.println("> For the Ship with MMSI [" + mmsi + "], the occupancy rate is " + result + "%, at the moment of the Cargo Manifest with ID [" + cargoManifestID + "].");
    }

    @Test
    public void testOccupancyRatesMoment(){
        int mmsi = 0; //valor mmsi
        Date date = new Date(1970, Calendar.JANUARY,1,0,0,0); //valor data
        double result = ctrl.getShipOccupancyRateByMmsiAndDate(mmsi, date);
        System.out.println("OCCUPANCY RATE");
        System.out.println("> For the Ship with MMSI [" + mmsi + "], the occupancy rate is " + result + "%, at " + date.toString() + ".");
    }

}
