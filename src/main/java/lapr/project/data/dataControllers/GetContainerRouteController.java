package lapr.project.data.dataControllers;

import lapr.project.controller.App;
import lapr.project.data.DatabaseConnection;
import lapr.project.data.RouteStoreDB;
import lapr.project.data.ShipTripStoreDB;
import lapr.project.domain.model.Company;

import java.sql.SQLException;

/**
 * Controller class to get the current route of a container
 *
 * @author Ana Albergaria <1201518@isep.ipp.pt>
 */
public class GetContainerRouteController {
    /**
     * Company instance of the session.
     */
    private Company company;
    /**
     * Constructor for the controller.
     */
    public GetContainerRouteController() {
        this(App.getInstance().getCompany());
    }
    /**
     * Constructor receiving the company as an argument.
     *
     * @param company instance of company to be used.
     */
    public GetContainerRouteController(Company company) {
        this.company=company;
    }

    /**
     * Method which obtains the current path of a certain container.
     * @param containerID the container id
     * @param registrationCode the shipment id
     *
     * @return current route of the container
     */
    public String getContainerPath(int containerID, int registrationCode) throws SQLException {
        /*RouteStoreDB routeStoreDB = this.company.getRouteStoreDB();
        DatabaseConnection connection = App.getInstance().getConnection();
        String routeID = routeStoreDB.getRouteId(connection, containerID, registrationCode);
        String route = routeStoreDB.getContainerPath(connection, Integer.parseInt(routeID));
        return route;
         */
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
