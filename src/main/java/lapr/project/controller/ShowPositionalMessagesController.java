package lapr.project.controller;

import lapr.project.domain.BST.PositionsBST;
import lapr.project.domain.BST.ShipsBST;
import lapr.project.domain.model.Company;
import lapr.project.domain.model.Ship;
import lapr.project.domain.model.VesselType;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * Method which verifies whether the MMSI code
     * inserted by the user is already in the system,
     * meaning whether the corresponding Ship is stored in the tree or not.
     *
     * Returns true if the ship exists in the tree,
     * otherwise returns false
     *
     * @param mmsiCode the MMSI code inserted by the user
     * @return true if the ship exists in the tree,
     * otherwise returns false
     */
    public boolean isValidShip(int mmsiCode) {
        ShipsBST shipsBST = this.company.getBstShip();
        this.ship = shipsBST.getShipByMmsiCode(mmsiCode);
        return ship != null;
    }

    /**
     * Calling method for returning the positional messages of the ship
     * chosen by the user in the wished period of time.
     *
     * @param initialDate the initial Date
     * @param finalDate the final Date
     * @return a map containing information about the chosen ship and the positional messages
     */
    public Map<String, List<String>> showPositionalMessages(Date initialDate, Date finalDate) {
        PositionsBST positionsBST = this.ship.getPositionsBST();
        List<String> listPositionalMessages = positionsBST.getPositionalMessages(initialDate, finalDate);
        VesselType vesselType = this.ship.getVesselType();
        Map<String, List<String>> messagesWithVesselType = new HashMap<>();
        messagesWithVesselType.put(vesselType.toString(), listPositionalMessages);

       return messagesWithVesselType;
    }


}
