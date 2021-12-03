package lapr.project.controller;

import lapr.project.domain.model.Company;

import java.util.Date;

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
     * @param addedContainerNum containers added in loading cargo manifest.
     * @param removedContainersNum containers removed in loading cargo manifest.
     * @return ship occupancy rate in percentage.
     */
    public double calculateOccupancyRate(int maxCapacity, int initialNumContainers, int addedContainerNum, int removedContainersNum){
        double current = initialNumContainers+addedContainerNum-removedContainersNum;
        if (current>maxCapacity){
            return -1; //when invalid
        } else {
            return (current/maxCapacity)*100;
        }
    }

    /**
     * Get ship occupancy rate by cargo manifest id.
     * @param cargoManifestID cargo manifest id.
     * @return ship occupancy rate in percentage.
     */
    public double getShipOccupancyRateByCargoManifestID(int cargoManifestID){
        int maxCapacity=0, initialNumContainers=0, addedContainersNum=0, removedContainersNum=0;
        /*maxCapacity=;
        initialNumContainers=;
        addedContainersNum=;
        removedContainersNum=;*/
        return calculateOccupancyRate(maxCapacity, initialNumContainers, addedContainersNum,removedContainersNum);
    }

    /**
     * Get cargo manifest id by mmsi and date.
     * @param mmsi ship mmsi.
     * @param date date to analyse.
     * @return cargo manifest id.
     */
    public int getCargoManifestIDByMmsiAndDate(int mmsi, Date date){
        /*int cargoManifestID =;
        return cargoManifestID;*/
        throw new IllegalArgumentException("to be developed");
    }

    /**
     * Get ship occupancy rate by mmsi and date.
     * @param mmsi ship mmsi.
     * @param date date to analyse.
     * @return ship occupancy rate in percentage.
     */
    public double getShipOccupancyRateByMmsiAndDate(int mmsi, Date date){
        int cargoManifestID = getCargoManifestIDByMmsiAndDate(mmsi,date);
        return getShipOccupancyRateByCargoManifestID(cargoManifestID);
    }

}