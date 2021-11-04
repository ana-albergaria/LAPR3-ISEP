package lapr.project.domain.BST;

import lapr.project.domain.model.Company;
import lapr.project.domain.model.Ship;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShipsBTS {
    /**
     * attribute from Company Class.
     */
    private Company company;
    /**
     * attribute from PositionsBST Class.
     */
    private PositionsBST positionsBST;

    /**
     * method to get the ships within the Base Date Time gap, using the Company Class
     * @param initialBase initial Base Date Time
     * @param finalBase final Base Date Time
     * @return list of ships within the Base Date Time gap
     */
    public List<Ship> getShipsByDate(Date initialBase, Date finalBase){
        //return company.getShipsByDate(initialBase, finalBase);
        return new ArrayList<>();
    }
}
