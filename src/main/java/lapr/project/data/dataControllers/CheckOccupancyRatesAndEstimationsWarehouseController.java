package lapr.project.data.dataControllers;

import lapr.project.controller.App;
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
public class CheckOccupancyRatesAndEstimationsWarehouseController {

    /**
     * Company instance of the session.
     */
    private Company company;

    /**
     * Constructor for the controller.
     */
    public CheckOccupancyRatesAndEstimationsWarehouseController(){
        this(App.getInstance().getCompany());
    }

    /**
     * Constructor receiving the company as an argument.
     *
     * @param companyy instance of company to be used.
     */
    public CheckOccupancyRatesAndEstimationsWarehouseController(Company companyy){
        this.company=companyy;
    }

    /**
     * Calculate occupancy rate with maxCapacity and currentCapacity.
     * @param maxCapacity warehouse max capacity.
     * @param currentCapacity current number of containers in the warehouse.
     * @return warehouse occupancy rate in percentage.
     */
    public int calculateOccupancyRate(int maxCapacity, int currentCapacity){
        if (currentCapacity>maxCapacity){
            return -1; //when invalid
        } else {
            return (currentCapacity*100/maxCapacity);
        }
    }

    /**
     * Get warehouse occupancy rate by warehouse id.
     * @param warehouseID warehouse id.
     * @return warehouse occupancy rate in percentage.
     */
    public int getShipOccupancyRateByWarehouseID(int warehouseID) throws SQLException {
        if (checkIfWarehouseExists(warehouseID)==0){
            return -1; //inv
        }
        int maxCapacity = 0; //get with sql
        int currentCapacity = 0; //get with sql
        throw new IllegalArgumentException("to develop");
    }

    /**
     * Check if a warehouse exists in the data base.
     * @param warehouse_id Warehouse's id.
     * @return 1 if the warehouse exists and 0 if it doesn't.
     */
    public int checkIfWarehouseExists(int warehouse_id) {
        throw new IllegalArgumentException("to develop");
    }

}