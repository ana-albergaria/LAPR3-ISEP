package lapr.project.controller;

import lapr.project.data.DatabaseConnection;
import lapr.project.data.dataControllers.CheckIfContainerExceedsShipCapacityController;
import lapr.project.domain.model.Company;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

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

    /*
    @Test
    void testcheckIfCargoManifestExceedsShipCapacityValidValuesEnoughSpace(){
        //cmid: 77329
        System.out.println("Test1: testcheckIfCargoManifestExceedsShipCapacityValidValuesEnoughSpace()");
        int cargoManifestID = 82847;
        int mmsi = 212351004;
        int expResult = 1; //valor esperado: 1 -> tem espaço
        int result = ctrl.checkIfCargoManifestExceedsShipCapacity();
        Assertions.assertEquals(expResult, result);
    }

    @Test
    void testcheckIfCargoManifestExceedsShipCapacityValidValuesNotEnoughSpace(){
        //mmsi: 212351001 and date: 25/02/2021 -> cmid: 77329
        System.out.println("Test2: testcheckIfCargoManifestExceedsShipCapacityValidValuesNotEnoughSpace()");
        int cargoManifestID = 82846;
        int mmsi= 212351004;
        int expResult = 0; //valor esperado: 0 -> não tem espaço
        int result = ctrl.checkIfCargoManifestExceedsShipCapacity();
        Assertions.assertEquals(expResult, result);
    }

    @Test
    void testcheckIfCargoManifestExceedsShipCapacityInvalidValueCargoManifestID(){
        System.out.println("Test3: testcheckIfCargoManifestExceedsShipCapacityInvalidValueCargoManifestID()");
        int cargoManifestID = 77330;
        int mmsi= 212351004;
        int expResult = -1; //valor esperado: -1 -> cargo manifest id invalido
        int result = ctrl.checkIfCargoManifestExceedsShipCapacity();
        Assertions.assertEquals(expResult, result);
    }

    @Test
    void testcheckIfCargoManifestExceedsShipCapacityInvalidValueMMSI(){
        System.out.println("Test4: testcheckIfCargoManifestExceedsShipCapacityInvalidValueMMSI()");
        int cargoManifestID = 82847;
        int mmsi= 636092934;
        int expResult = -1; //valor esperado: -1 -> mmsi invalido
        int result = ctrl.checkIfCargoManifestExceedsShipCapacity();
        Assertions.assertEquals(expResult, result);
    }*/

}
