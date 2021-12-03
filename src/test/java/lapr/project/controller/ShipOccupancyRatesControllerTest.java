package lapr.project.controller;

import lapr.project.data.DatabaseConnection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.mockito.Mockito.mock;

/**
 * @author Marta Ribeiro (1201592)
 */
public class ShipOccupancyRatesControllerTest {

    //private Company comp;
    private DatabaseConnection databaseConnection;
    private ShipOccupancyRatesController shipOccupancyRatesController;
    //private ShipOccupancyRatesController ctrl;

    @BeforeEach
    public void SetUp(){
        //comp = new Company("Company");
        //this.ctrl=new ShipOccupancyRatesController(comp);
        databaseConnection = mock(DatabaseConnection.class);
        shipOccupancyRatesController = mock(ShipOccupancyRatesController.class);
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
        double actual=shipOccupancyRatesController.calculateOccupancyRate(maxCapacity1,initialNumContainers1,alreadyAddedRemovedContainersTripNum1);
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
        double actual=shipOccupancyRatesController.calculateOccupancyRate(maxCapacity1,initialNumContainers1,alreadyAddedRemovedContainersTripNum1);
        Assertions.assertEquals(expected,actual);
    }

    /**
     * Test to check if the occupancy rate of a ship is correctly calculated given a cargo manifest.
     */
    @Test
    public void testOccupancyRatesCargoManifest(){
        //ver se o cargo manifest existe? se nao existir avisar
        int cargoManifestID= 69477; //valor cargoManifestID | 69477 -> este é um unloading cargo manifest
        int mmsi = shipOccupancyRatesController.getMmsiByCargoManifest(cargoManifestID);
        //COLOCAR get mmsi by cargo manifest SCRIPT NO SQL DEVELOPER
        double result = shipOccupancyRatesController.getShipOccupancyRateByCargoManifestID(cargoManifestID);
        System.out.println("OCCUPANCY RATE");
        System.out.println("> For the Ship with MMSI [" + mmsi + "], the occupancy rate is " + result + "%, at the moment of the Cargo Manifest with ID [" + cargoManifestID + "].");
    }

    /**
     * Test to check if the occupancy rate of a ship is correctly calculated given a mmsi and date.
     */
    @Test
    public void testOccupancyRatesMoment(){
        //ver primeiro se o mmsi existe? se nao tiver nenhum cargo manifest entao vai ser 0
        int mmsi = 212180000; //valor mmsi | mmsi: 212180000
        Date date = new Date(2021, Calendar.APRIL,29); //valor data | arrival date: 28/04/2021
        double result = shipOccupancyRatesController.getShipOccupancyRateByMmsiAndDate(mmsi, date); //vai ter de perceber que o cargo manifest é o 69477 e dar o mesmo resultado do teste anterior
        System.out.println("OCCUPANCY RATE");
        System.out.println("> For the Ship with MMSI [" + mmsi + "], the occupancy rate is " + result + "%, at " + date.toString() + ".");
    }

}
