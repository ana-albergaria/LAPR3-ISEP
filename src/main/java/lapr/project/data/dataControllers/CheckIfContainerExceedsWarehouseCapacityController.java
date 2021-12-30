package lapr.project.data.dataControllers;

import lapr.project.controller.App;
import lapr.project.data.ShipTripStoreDB;
import lapr.project.domain.model.Company;

import java.sql.Date;
import java.sql.SQLException;

public class CheckIfContainerExceedsWarehouseCapacityController {

    /**
     * Company instance of the session.
     */
    private Company company;

    /**
     * Constructor for the controller.
     */
    public CheckIfContainerExceedsWarehouseCapacityController(){
        this(App.getInstance().getCompany());
    }

    /**
     * Constructor receiving the company as an argument.
     *
     * @param companyy instance of company to be used.
     */
    public CheckIfContainerExceedsWarehouseCapacityController(Company companyy){
        this.company=companyy;
    }

    /**
     * Try to create ship trip to see if the trigger is fired.
     * @param shipTripID ship trip id.
     * @param mmsi ship mmsi.
     * @param depLocation departure location.
     * @param arriLocation arrival location.
     * @param loadCargID loading cargo manifest id.
     * @param unloadCargID unloading cargo manifest id.
     * @param estDepDate estimated departure date.
     * @param estArriDate estimates arrival date.
     * @return -1 if the input information is wrong, otherwise it returns 1.
     */
    public int tryToCreateShipTrip(int shipTripID, int mmsi, int depLocation, int arriLocation, int loadCargID, int unloadCargID, Date estDepDate, Date estArriDate) throws SQLException {
        ShipTripStoreDB shipTripStoreDB = this.company.getShipTripStoreDB();
        shipTripStoreDB.triggerContainersWarehouse();
        int resultCreate = shipTripStoreDB.createShipTripWithUnloading(shipTripID, mmsi, depLocation, arriLocation, loadCargID, unloadCargID, estDepDate, estArriDate);
        return shipTripStoreDB.checkIfShipTripExists(shipTripID);
    }

    /**
     * Delete ship trip.
     * @param shipTripID ship trip id.
     * @return -1 if the input information is wrong, otherwise it returns 1.
     */
    public int deleteShipTrip(int shipTripID){
        ShipTripStoreDB shipTripStoreDB = this.company.getShipTripStoreDB();
        return shipTripStoreDB.deleteShipTrip(shipTripID);
    }

}
