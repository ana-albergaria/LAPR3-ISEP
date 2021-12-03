package lapr.project.controller;

import lapr.project.domain.model.Company;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

}
