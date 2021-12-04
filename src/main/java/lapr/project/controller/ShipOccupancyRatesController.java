package lapr.project.controller;

import lapr.project.data.CargoManifestStoreDB;
import lapr.project.data.ShipStoreDB;
import lapr.project.data.ShipTripStoreDB;
import lapr.project.domain.model.Company;

import java.sql.Date;
import java.sql.SQLException;

/**
 * Controller class to coordinate the calculation of ship's occupancy rate
 *
 * @author Marta Ribeiro (1201592)
 */
public class ShipOccupancyRatesController {

    /**
     * Company instance of the session.
     */
    private Company company;

    /**
     * Constructor for the controller.
     */
    public ShipOccupancyRatesController(){
        this(App.getInstance().getCompany());
    }

    /**
     * Constructor receiving the company as an argument.
     *
     * @param companyy instance of company to be used.
     */
    public ShipOccupancyRatesController(Company companyy){
        this.company=companyy;
    }

    /**
     * Calculate occupancy rate with maxCapacity, initialNumContainers, addedContainerNum and removedContainersNum.
     * @param maxCapacity ship cargo.
     * @param initialNumContainers ship trip initial num containers.
     * @param alreadyAddedRemovedContainersTripNum containers added and removed in loading and unloading cargo manifest.
     * @return ship occupancy rate in percentage.
     */
    public int calculateOccupancyRate(int maxCapacity, int initialNumContainers, int alreadyAddedRemovedContainersTripNum){
        int current = initialNumContainers+alreadyAddedRemovedContainersTripNum;
        if (current>maxCapacity){
            return -1; //when invalid
        } else {
            return (current*100/maxCapacity);
        }
    }

    /**
     * Get ship occupancy rate by cargo manifest id.
     * @param cargoManifestID cargo manifest id.
     * @return ship occupancy rate in percentage.
     */
    public int getShipOccupancyRateByCargoManifestID(int cargoManifestID) throws SQLException {
        int maxCapacity=0, initialNumContainers=0, alreadyAddedRemovedContainersTripNum=0;
        ShipStoreDB shipStoreDB = this.company.getShipStoreDB();
        maxCapacity=shipStoreDB.getShipCargo(cargoManifestID);
        System.out.println("maxCapacity: " + maxCapacity);
        ShipTripStoreDB shipTripStoreDB = this.company.getShipTripStoreDB();
        java.sql.Date estDepDate = shipTripStoreDB.getEstDepartureDateFromShipTrip(cargoManifestID);
        System.out.println("estDepDate: " + estDepDate + " | " + estDepDate.toString());
        initialNumContainers=shipTripStoreDB.getInitialNumContainersPerShipTrip(cargoManifestID,estDepDate);
        System.out.println("initialNumContainers: " + initialNumContainers);
        alreadyAddedRemovedContainersTripNum=shipTripStoreDB.getAddedRemovedContainersShipTripMoment(cargoManifestID);
        System.out.println("alreadyAddedRemovedContainersTripNum: " + alreadyAddedRemovedContainersTripNum);
        System.out.println("result: " + calculateOccupancyRate(maxCapacity, initialNumContainers, alreadyAddedRemovedContainersTripNum));
        return calculateOccupancyRate(maxCapacity, initialNumContainers, alreadyAddedRemovedContainersTripNum);
    }

    /**
     * Get cargo manifest id by mmsi and date.
     * @param mmsi ship mmsi.
     * @param date date to analyse.
     * @return cargo manifest id.
     */
    public int getCargoManifestIDByMmsiAndDate(int mmsi, Date date){
        CargoManifestStoreDB cargoManifestStoreDB = this.company.getCargoManifestStoreDB();
        int cargoManifestID = cargoManifestStoreDB.getCargoManifestByMmsiAndDate(mmsi,date);
        return cargoManifestID;
        //throw new IllegalArgumentException("to be developed");
    }

    /**
     * Get ship occupancy rate by mmsi and date.
     * @param mmsi ship mmsi.
     * @param date date to analyse.
     * @return ship occupancy rate in percentage.
     */
    public int getShipOccupancyRateByMmsiAndDate(int mmsi, java.sql.Date date) throws SQLException{
        int cargoManifestID = getCargoManifestIDByMmsiAndDate(mmsi,date);
        System.out.println("cargoManifestID: " + cargoManifestID);
        return getShipOccupancyRateByCargoManifestID(cargoManifestID);
    }

    /**
     * Get MMSI by cargo manifest ID.
     * @param cargoManifestID cargo manifest ID.
     * @return ship MMSI.
     */
    public int getMmsiByCargoManifest(int cargoManifestID) {
        ShipTripStoreDB shipTripStoreDB = this.company.getShipTripStoreDB();
        return shipTripStoreDB.getMmsiByCargoManifestID(cargoManifestID);
    }
}