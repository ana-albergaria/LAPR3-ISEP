package lapr.project.controller;

import lapr.project.data.DatabaseConnection;
import lapr.project.data.dataControllers.CheckOccupancyRatesAndEstimationsWarehouseController;
import lapr.project.domain.model.Company;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.mock;

public class CheckOccupancyRatesAndEstimationsWarehouseControllerTest {

    private Company comp;
    private CheckOccupancyRatesAndEstimationsWarehouseController ctrl;
    private DatabaseConnection databaseConnection;
    private CheckOccupancyRatesAndEstimationsWarehouseController checkOccupancyRatesAndEstimationsWarehouseController;

    @BeforeEach
    public void SetUp(){
        comp = new Company("Company");
        this.ctrl=new CheckOccupancyRatesAndEstimationsWarehouseController(comp);
        databaseConnection = mock(DatabaseConnection.class);
        checkOccupancyRatesAndEstimationsWarehouseController = mock(CheckOccupancyRatesAndEstimationsWarehouseController.class);
    }

    /*
    warehouse: 11111
        location: 111   |   max: 10
    warehouse: 22222
        location: 222   |   max: 20
    warehouse: 33333
        location: 333   |   max: 30
    warehouse: 44444
        location: 444   |   max: 40
    warehouse: 55555
        location: 555   |   max: 50

    truckTrip: 11111
        chegam a: 111   |   data: 9/12/2021     |   qtd:2   |   ficam: 2
    truckTrip: 11111
        chegam a: 111   |   data: 9/12/2021     |   qtd:2   |   ficam: 2
     */

    /*
    @Test
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
        int result = ctrl.tryToCreateShipTrip(shipTripID,mmsi,depLocation,arriLocation,loadCargID,estDepDate,estArriDate);
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
        int depLocation = 16485;
        int arriLocation = 224858;
        int loadCargID = 82846;
        Date estDepDate = new Date(Calendar.getInstance().getTime().getTime());
        Date estArriDate = new Date(2022,12,1);
        int result = ctrl.tryToCreateShipTrip(shipTripID,mmsi,depLocation,arriLocation,loadCargID,estDepDate,estArriDate);
        int expResult = 0; //valor esperado: 0 -> não tem espaço, logo ship trip nao é criada
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
        int result = ctrl.tryToCreateShipTrip(shipTripID,mmsi,depLocation,arriLocation,loadCargID,estDepDate,estArriDate);
        int expResult = 0; //valor esperado: 0 -> cargo manifest id invalido, logo ship trip nao é criada
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
        int result = ctrl.tryToCreateShipTrip(shipTripID,mmsi,depLocation,arriLocation,loadCargID,estDepDate,estArriDate);
        int expResult = 0; //valor esperado: 0 -> cargo manifest id invalido, logo ship trip nao é criada
        Assertions.assertEquals(expResult, result);
    }
    */

}
