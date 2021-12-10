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

    /**
     * Try to create ship trip to see if the trigger is fired.
     * @param shipTripID ship trip id.
     * @param mmsi ship mmsi.
     * @param depLocation departure location.
     * @param arriLocation arrival location.
     * @param loadCargID loading cargo manifest id.
     * @param estDepDate estimated departure date.
     * @param estArriDate estimates arrival date.
     * @return -1 if the input information is wrong, otherwise it returns 1.
     */
    public int tryToCeateShipTrip(int shipTripID, int mmsi, int depLocation, int arriLocation, int loadCargID, java.sql.Date estDepDate, java.sql.Date estArriDate){
        ShipTripStoreDB shipTripStoreDB = this.company.getShipTripStoreDB();
        return shipTripStoreDB.createShipTrip(shipTripID, mmsi, depLocation, arriLocation, loadCargID, estDepDate, estArriDate);
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
