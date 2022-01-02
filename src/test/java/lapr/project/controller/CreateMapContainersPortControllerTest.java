package lapr.project.controller;

import lapr.project.data.DatabaseConnection;
import lapr.project.data.dataControllers.CheckIfContainerExceedsShipCapacityController;
import lapr.project.data.dataControllers.CreateMapContainersPortController;
import lapr.project.domain.model.Company;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class CreateMapContainersPortControllerTest {

    private Company comp;
    private CreateMapContainersPortController ctrl;
    private DatabaseConnection databaseConnection;
    private CreateMapContainersPortController createMapContainersPortController;

    @BeforeEach
    public void SetUp(){
        comp = new Company("Company");
        this.ctrl=new CreateMapContainersPortController(comp);
        databaseConnection = mock(DatabaseConnection.class);
        createMapContainersPortController = mock(CreateMapContainersPortController.class);
    }

    @Disabled
    @Test
    void testTheMapIsCorrect1(){

    }

    @Disabled
    @Test
    void testTheMapIsCorrect12(){

    }

    @Disabled
    @Test
    void testNoMapWhenInvalidPortID(){

    }

    @Disabled
    @Test
    void testNoMapWhenMonthMinor1(){

    }

    @Disabled
    @Test
    void testNoMapWhenMonthBigger12(){

    }

}
