package lapr.project.data.dataControllers;

import lapr.project.controller.App;
import lapr.project.data.PortStoreDB;
import lapr.project.data.WarehouseStoreDB;
import lapr.project.domain.model.Company;

import java.sql.Date;
import java.sql.SQLException;

public class CreateMapContainersPortController {

    /**
     * Company instance of the session.
     */
    private Company company;

    /**
     * Constructor for the controller.
     */
    public CreateMapContainersPortController(){
        this(App.getInstance().getCompany());
    }

    /**
     * Constructor receiving the company as an argument.
     *
     * @param companyy instance of company to be used.
     */
    public CreateMapContainersPortController(Company companyy){
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
     * Get port occupancy rate by port id and date.
     * @param portID port id.
     * @param date date.
     * @return port occupancy rate in percentage.
     */
    public int getOccupancyRateByPortIDandDate(int portID, Date date) throws SQLException {
        if (checkIfPortExists(portID)==0){
            return -1; //inv
        }
        PortStoreDB portStoreDB = this.company.getPortStoreDB();
        int maxCapacity = portStoreDB.getPortMaxCapacity(portID); //get with sql
        int currentCapacity = portStoreDB.getPortOccupancyInDay(portID, date); //get with sql
        return calculateOccupancyRate(maxCapacity,currentCapacity);
    }

    /**
     * Check if a port exists in the data base.
     * @param portID Port's id.
     * @return 1 if the port exists and 0 if it doesn't.
     */
    public int checkIfPortExists(int portID) {
        PortStoreDB portStoreDB = this.company.getPortStoreDB();
        return portStoreDB.checkIfPortExists(portID);
    }

}
