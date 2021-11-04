package lapr.project.controller;

import lapr.project.domain.BST.BstShip;
import lapr.project.domain.model.Company;
import lapr.project.domain.model.Ship;

/** Controller class for showing the positional messages of a ship temporally organized
 * and associated with each of the ships
 *
 *  @author Ana Albergaria <1201518@isep.ipp.pt>
 *
 */
public class ShowPositionalMessagesController {

    /**
     * The company associated to the Controller.
     */
    private Company company;

    /**
     * The chosen ship whose positional messages are to be displayed.
     */
    private Ship ship;

    /**
     * Builds an empty constructor for having the actual instance of the company when instantiated.
     */
    public ShowPositionalMessagesController() {
        this(App.getInstance().getCompany());
    }


    /**
     * Builds a Show Positional Message's instance receiving the company.
     *
     * @param company company associated to the Controller.
     */
    public ShowPositionalMessagesController(Company company) {
        this.company = company;
    }

    public boolean isValidShip(int mmsiCode) {
        BstShip bstShip = this.company.getBstShip();
        this.ship = bstShip.getShipByMmsiCode(mmsiCode);
        return bstShip.validateShip(ship);
        //throw new UnsupportedOperationException("Not supported yet.");
    }


}
