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

}
