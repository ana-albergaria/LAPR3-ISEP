package lapr.project.data.dataControllers;

import lapr.project.controller.App;
import lapr.project.data.CargoManifestStoreDB;
import lapr.project.data.ShipTripStoreDB;
import lapr.project.domain.model.Company;

import java.sql.Date;
import java.sql.SQLException;

public class CheckIfContainerExceedsShipCapacityController {

    /**
     * Company instance of the session.
     */
    private Company company;

    /**
     * Constructor for the controller.
     */
    public CheckIfContainerExceedsShipCapacityController(){
        this(App.getInstance().getCompany());
    }

    /**
     * Constructor receiving the company as an argument.
     *
     * @param companyy instance of company to be used.
     */
    public CheckIfContainerExceedsShipCapacityController(Company companyy){
        this.company=companyy;
    }

    public int tryToCeateShipTrip(int shipTripID, int mmsi, int depLocation, int arriLocation, int loadCargID, int unloadCargID, java.sql.Date estDepDate, java.sql.Date estArriDate,
                                                       java.sql.Date realDepDate, java.sql.Date realArriDate){
        ShipTripStoreDB shipTripStoreDB = this.company.getShipTripStoreDB();
        return shipTripStoreDB.createShipTrip(shipTripID, mmsi, depLocation, arriLocation, loadCargID, unloadCargID, estDepDate, estArriDate,
        realDepDate, realArriDate);
    }

    public int deleteShipTrip(int shipTripID){
        ShipTripStoreDB shipTripStoreDB = this.company.getShipTripStoreDB();
        return shipTripStoreDB.deleteShipTrip(shipTripID);
    }

}
