package lapr.project.controller;

import lapr.project.domain.model.Company;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ShipOccupancyRatesControllerTest {

    private Company comp;
    private ShipOccupancyRatesController ctrl;

    @BeforeEach
    public void SetUp(){
        comp = new Company("Company");
        this.ctrl=new ShipOccupancyRatesController(comp);
    }

    @Test
    public void testOccupancyRatesCalculationWhenValid(){
        int maxCapacity1=75;
        int initialNumContainers1=15;
        int addedContainerNum1=36;
        int removedContainersNum1=12;
        int expected=52;
        double actual=ctrl.calculateOccupancyRate(maxCapacity1,initialNumContainers1,addedContainerNum1,removedContainersNum1);
        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void testOccupancyRatesCalculationWhenInvalid(){
        int maxCapacity1=30;
        int initialNumContainers1=15;
        int addedContainerNum1=36;
        int removedContainersNum1=12;
        int expected=-1;
        double actual=ctrl.calculateOccupancyRate(maxCapacity1,initialNumContainers1,addedContainerNum1,removedContainersNum1);
        Assertions.assertEquals(expected,actual);
    }

}
