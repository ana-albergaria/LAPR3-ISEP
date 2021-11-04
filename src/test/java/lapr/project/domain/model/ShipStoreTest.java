package lapr.project.domain.model;

import lapr.project.controller.App;
import lapr.project.domain.BST.PositionsBST;
import lapr.project.domain.store.ShipStore;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShipStoreTest {
    ShipStore testStore = new ShipStore();
    VesselType testVessel = new VesselType(12345678, 238, 90, 10, 10000);
    VesselType testVessel2 = new VesselType(75324681, 500, 110, 15, 23000);
    PositionsBST positionBstTest = new PositionsBST();
    Company company = App.getInstance().getCompany();
    //PositionStore testPositionStore = new PositionStore();


    /**
     * ensure the ships are correctly extrated to a list if they belong in the Base Date Time interval
     */
    @Test
    public void getShipsByDateCorrect() {
        Ship testShip = new Ship(testVessel, positionBstTest, 987654321, "Marineford", 3216549, "JIRA");
        Ship testShip2 = new Ship(testVessel2, positionBstTest, 147852369, "SeaGull", 9863254, "JIRA");
        Ship testShip3 = new Ship(testVessel, positionBstTest, 458763216, "Cpt. Albert", 6589214, "JIRA");

        List<Ship> testList = testStore.getShipsByDate(new Date(), new Date());
        List<Ship> expectedList = new ArrayList<>();
        expectedList.add(testShip);
        expectedList.add(testShip3);

        assertEquals(expectedList, testList);
    }
}
