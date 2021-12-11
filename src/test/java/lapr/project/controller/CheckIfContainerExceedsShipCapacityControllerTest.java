package lapr.project.controller;

import lapr.project.data.DatabaseConnection;
import lapr.project.data.dataControllers.CheckIfContainerExceedsShipCapacityController;
import lapr.project.domain.model.Company;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.Calendar;

import static org.mockito.Mockito.mock;

public class CheckIfContainerExceedsShipCapacityControllerTest {

    private Company comp;
    private CheckIfContainerExceedsShipCapacityController ctrl;
    private DatabaseConnection databaseConnection;
    private CheckIfContainerExceedsShipCapacityController shipOccupancyRatesController;

    @BeforeEach
    public void SetUp(){
        comp = new Company("Company");
        this.ctrl=new CheckIfContainerExceedsShipCapacityController(comp);
        databaseConnection = mock(DatabaseConnection.class);
        shipOccupancyRatesController = mock(CheckIfContainerExceedsShipCapacityController.class);
    }

    //mmsi:212351004  max:30
    //atual:0 (no passado fez +2 e -2)
    //0+34=34 -> excede     82846
    //0+5=5 -> tem espaço   82847
    //cmid invalido: 77330
    //mmsi invalido: 636092934

    //int shipTripID, int mmsi, int depLocation, int arriLocation, int loadCargID, java.sql.Date estDepDate, java.sql.Date estArriDate

    /*alter session set nls_date_format = 'DD-MM-YYYY';

    insert into shiptrip (shiptrip_id, mmsi, departure_location, arrival_location, loading_cargo_id, unloading_cargo_id, est_departure_date, est_arrival_date, real_departure_date, real_arrival_date) values (81348, 212351004, 224858, 16485, 82846, NULL, '10-12-2021', '12-12-2022', NULL, NULL);

    insert into shiptrip (shiptrip_id, mmsi, departure_location, arrival_location, loading_cargo_id, unloading_cargo_id, est_departure_date, est_arrival_date, real_departure_date, real_arrival_date) values (81348, 212351004, 224858, 16485, 82847, NULL, '10-12-2021', '12-12-2022', NULL, NULL);

    delete from shipTrip where shiptrip_id = 81348;*/

    /*@Test
    void testcheckIfCargoManifestExceedsShipCapacityValidValuesEnoughSpace(){
        //0+5=5 -> tem espaço   82847
        System.out.println("Test1: testcheckIfCargoManifestExceedsShipCapacityValidValuesEnoughSpace()");
        ctrl.deleteShipTrip(81348);
        int shipTripID = 81348;
        int mmsi = 212351004;
        int depLocation = 224858;
        int arriLocation = 16485;
        int loadCargID = 82847;
        Date estDepDate = new Date(Calendar.getInstance().getTime().getTime());
        Date estArriDate = new Date(2022,12,1);
        int result = ctrl.tryToCeateShipTrip(shipTripID,mmsi,depLocation,arriLocation,loadCargID,estDepDate,estArriDate);
        int expResult = 1; //valor esperado: 1 -> tem espaço
        Assertions.assertEquals(expResult, result);
    }

    @Test
    void testcheckIfCargoManifestExceedsShipCapacityValidValuesNotEnoughSpace(){
        //0+34=34 -> excede     82846
        System.out.println("Test2: testcheckIfCargoManifestExceedsShipCapacityValidValuesNotEnoughSpace()");
        ctrl.deleteShipTrip(81348);
        int shipTripID = 81348;
        int mmsi = 212351004;
        int depLocation = 224858;
        int arriLocation = 16485;
        int loadCargID = 82846;
        Date estDepDate = new Date(Calendar.getInstance().getTime().getTime());
        Date estArriDate = new Date(2022,12,1);
        int result = ctrl.tryToCeateShipTrip(shipTripID,mmsi,depLocation,arriLocation,loadCargID,estDepDate,estArriDate);
        int expResult = -1; //valor esperado: -1 -> não tem espaço
        Assertions.assertEquals(expResult, result);
    }

    @Test
    void testcheckIfCargoManifestExceedsShipCapacityInvalidValueCargoManifestID(){
        System.out.println("Test3: testcheckIfCargoManifestExceedsShipCapacityInvalidValueCargoManifestID()");
        ctrl.deleteShipTrip(81348);
        int shipTripID = 81348;
        int mmsi = 212351004;
        int depLocation = 224858;
        int arriLocation = 16485;
        int loadCargID = 77330;
        Date estDepDate = new Date(Calendar.getInstance().getTime().getTime());
        Date estArriDate = new Date(2022,12,1);
        int result = ctrl.tryToCeateShipTrip(shipTripID,mmsi,depLocation,arriLocation,loadCargID,estDepDate,estArriDate);
        int expResult = -1; //valor esperado: -1 -> cargo manifest id invalido
        Assertions.assertEquals(expResult, result);
    }

    @Test
    void testcheckIfCargoManifestExceedsShipCapacityInvalidValueMMSI(){
        System.out.println("Test4: testcheckIfCargoManifestExceedsShipCapacityInvalidValueMMSI()");
        ctrl.deleteShipTrip(81348);
        int shipTripID = 81348;
        int mmsi = 636092934;
        int depLocation = 224858;
        int arriLocation = 16485;
        int loadCargID = 82847;
        Date estDepDate = new Date(Calendar.getInstance().getTime().getTime());
        Date estArriDate = new Date(2022,12,1);
        int result = ctrl.tryToCeateShipTrip(shipTripID,mmsi,depLocation,arriLocation,loadCargID,estDepDate,estArriDate);
        int expResult = -1; //valor esperado: -1 -> cargo manifest id invalido
        Assertions.assertEquals(expResult, result);
    }*/

}
