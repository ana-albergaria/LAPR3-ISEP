package lapr.project.controller;

import lapr.project.domain.BST.PositionsBST;
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

    public double calculateOccupancyRate(int maxCapacity, int initialNumContainers, int addedContainerNum, int removedContainersNum){
        double current = initialNumContainers+addedContainerNum-removedContainersNum;
        if (current>maxCapacity){
            return -1; //when invalid
        } else {
            return (current/maxCapacity)*100;
        }
    }

    public int getShipOccupancyRateByCargoManifestID(int cargoManifestID){
        int maxCapacity=0, initialNumContainers=0, addedContainersNum=0, removedContainersNum=0;
        calculateOccupancyRate(maxCapacity, initialNumContainers, addedContainersNum,removedContainersNum);
        throw new IllegalArgumentException("to be developed");
    }

    public int getCargoManifestIDByMmsiAndDate(int mmsi, Date date){
        throw new IllegalArgumentException("to be developed");
    }

    public int getShipOccupancyRateByMmsiAndDate(int mmsi, Date date){
        int cargoManifestID = getCargoManifestIDByMmsiAndDate(mmsi,date);
        getShipOccupancyRateByCargoManifestID(cargoManifestID);
        throw new IllegalArgumentException("to be developed");
    }

}
