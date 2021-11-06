package lapr.project.controller;


import lapr.project.domain.BST.ShipsBST;
import lapr.project.domain.model.Company;
import lapr.project.domain.model.Ship;
import lapr.project.domain.model.VesselType;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class TopNController {
    /**
     * The company associated to the Controller.
     */
    private final Company company;

    /**
     * The binary search Tree with the ships imported from the file
     */
    private final ShipsBST shipBST;

    /**
     * empty constructor for the TopNController Class
     */
    public TopNController(){
        this.company= App.getInstance().getCompany();
        this.shipBST = company.getBstShip();
    }

    /**
     * Constructor for TopNController Class, receiving "company" as attribute
     * @param company instance of company
     */
    public TopNController(Company company) {
        this.company = company;
        this.shipBST = company.getBstShip();
    }

    /**
     * Method to get all the ships within the Base Date Time gap
     * @param initialDate initial Base Date Time
     * @param finalDate final Base Date Time
     * @return list with all the ships who belong in the time gap
     */
    public List<Ship> getShipsByDate(Date initialDate, Date finalDate){
        return this.shipBST.getShipsByDate(initialDate, finalDate);
    }

    /**
     * method to sort the ships with the most km travelled
     * @param shipList list with the ships belonging in the time gap
     * @param number number of ships to be sorted
     */
    public List<Ship> sortNShips(List<Ship> shipList, int number) {

        return this.shipBST.sortNShips(shipList, number);
    }

    /**
     * method to get the map with the ships associated by VesselType and sorted
     * @param sortedShips list with the sorted ships by most km travelled
     * @return map with the ships associated by VesselType and sorted
     */
    public Map<VesselType, List<Ship>> getShipWithMean(List<Ship> sortedShips) {
        return this.shipBST.getShipWithMean(sortedShips);
    }
}
