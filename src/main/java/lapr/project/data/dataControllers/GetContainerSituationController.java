package lapr.project.data.dataControllers;

import lapr.project.controller.App;
import lapr.project.data.DatabaseConnection;
import lapr.project.data.ShipTripStoreDB;
import lapr.project.domain.model.Company;

import java.sql.SQLException;

/**
 * Controller class to get the current situation of a container
 *
 * @author Ana Albergaria <1201518@isep.ipp.pt>
 */
public class GetContainerSituationController {
    /**
     * Company instance of the session.
     */
    private Company company;
    /**
     * Constructor for the controller.
     */
    public GetContainerSituationController() {
        this(App.getInstance().getCompany());
    }
    /**
     * Constructor receiving the company as an argument.
     *
     * @param company instance of company to be used.
     */
    public GetContainerSituationController(Company company) {
        this.company=company;
    }

    /**
     * Method which obtains the current location of a certain container.
     * @param containerID the container id
     * @param shipmentID the shipment id
     *
     * @return current location of the container
     */
    public String getLocation(int containerID, int shipmentID) throws SQLException {
        ShipTripStoreDB shipTripStoreDB = this.company.getShipTripStoreDB();
        DatabaseConnection connection = App.getInstance().getConnection();
        int shipTripID = shipTripStoreDB.getShipTripId(connection, containerID, shipmentID);
        String containerLocation = shipTripStoreDB.getLocation(connection, shipTripID);
        return containerLocation;
    }
}
