package lapr.project.controller;

import lapr.project.domain.BST.ShipsBTS;
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
    private Company company;

    /**
     * The binary search Tree with the ships imported from the file
     */
    private ShipsBTS shipBST;

    /**
     * empty constructor for the TopNController Class
     */
    public TopNController(){
        this.company= App.getInstance().getCompany();
        this.shipBST = company.getShipBST();
    }

    /**
     * Constructor for TopNController Class, receiving "company" as attribute
     * @param company
     */
    public TopNController(Company company) {
        this.company = company;
        this.shipBST = company.getShipBST();
    }

    /**
     * Method to get all the ships within the Base Date Time gap
     * @param initialDate initial Base Date Time
     * @param finalDate final Base Date Time
     * @return list with all the ships who belong in the time gap
     */
    public List<Ship> getSipsByDate(Date initialDate, Date finalDate){
        return this.shipBST.getShipsByDate(initialDate, finalDate);
    }

    /**
     * method to sort the ships with the most km travelled
     * @param shipList list with the ships belonging in the time gap
     * @param number number of ships to be sorted
     */
    public void sortNShips(List<Ship> shipList, int number) {
        this.shipBST.sortNShips(shipList, number);
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
