package lapr.project.controller;

import lapr.project.domain.BST.ShipsBST;
import lapr.project.domain.model.Company;

import java.util.List;
import java.util.TreeMap;

/** Controller class for showing the positional messages of a ship temporally organized
 * and associated with each of the ships
 *
 *  @author Ana Albergaria <1201518@isep.ipp.pt>
 *
 */
public class ShowPairsOfShipsController {
    /**
     * The company associated to the Controller.
     */
    private Company company;

    /**
     * Builds an empty constructor for having the actual instance of the company when instantiated.
     */
    public ShowPairsOfShipsController() {
        this(App.getInstance().getCompany());
    }


    /**
     * Builds a Show Pairs Of Ship's instance receiving the company.
     *
     * @param company company associated to the Controller.
     */
    public ShowPairsOfShipsController(Company company) {
        this.company = company;
    }

    public List<TreeMap<Double, String>> getPairsOfShips() {
        ShipsBST shipsBST = this.company.getBstShip();
        List<TreeMap<Double, String>> listPairsOfShips = shipsBST.getPairsOfShips();
        return listPairsOfShips;
        //throw new UnsupportedOperationException();
    }


}
