package lapr.project.data.dataControllers;

import lapr.project.controller.App;
import lapr.project.data.PortStoreDB;
import lapr.project.data.WarehouseStoreDB;
import lapr.project.domain.model.Company;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;

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
     * @param month month.
     * @param year year.
     * @param day day.
     * @return port occupancy rate in percentage.
     */
    public int getOccupancyRateByPortIDandDate(int portID, int month, int year, int day) throws SQLException {
        if (checkIfPortExists(portID)==0){
            return -1; //inv
        }
        PortStoreDB portStoreDB = this.company.getPortStoreDB();
        int maxCapacity = portStoreDB.getPortMaxCapacity(portID); //get with sql
        Date date = new Date(year,month,day);
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

    /**
     * Get a map of the occupation of the existing resources in the port during a given month.
     * @param portID port id.
     * @param month month.
     * @param year year.
     * @return a map of the occupation of the existing resources in the port during a given month.
     * @throws SQLException sql exception.
     */
    public int[][] getOccupancyMap(int portID, int month, int year) throws SQLException {
        if (month==1 || month==3 || month==5 || month==7 || month==8 || month==10 || month==12){
            return getMap31dMonth(portID, month, year);
        } else if (month==4 || month==6 || month==9 || month==11){
            return getMap30dMonth(portID,month,year);
        } else if (((year % 4 == 0) && (year % 100!= 0)) || (year % 400 == 0)){
            return getMap29dMonth(portID, month, year);
        } else {
            return getMap28dMonth(portID, month, year);
        }
    }

    /**
     * Get a map of the occupation of the existing resources in the port during a 28 days month.
     * @param portID port id.
     * @param month month.
     * @param year year.
     * @return a map of the occupation of the existing resources in the port during a 28 days month.
     * @throws SQLException sql exception.
     */
    public int[][] getMap28dMonth(int portID, int month, int year) throws SQLException {
        int[][] map = new int[28][2];
        int day=1;
        int result=0;
        Date date;
        for (int i = 0; i < 28; i++) {
            map[i][0]=i+1;
        }
        for (int i = 0; i < 28; i++) {
            result=getOccupancyRateByPortIDandDate(portID,month,year,day);
            map[i][1]=result;
            day++;
        }
        return map;
    }

    /**
     * Get a map of the occupation of the existing resources in the port during a 29 days month.
     * @param portID port id.
     * @param month month.
     * @param year year.
     * @return a map of the occupation of the existing resources in the port during a 29 days month.
     * @throws SQLException sql exception.
     */
    public int[][] getMap29dMonth(int portID, int month, int year) throws SQLException {
        int[][] map = new int[29][2];
        int day=1;
        int result=0;
        Date date;
        for (int i = 0; i < 29; i++) {
            map[i][0]=i+1;
        }
        for (int i = 0; i < 29; i++) {
            result=getOccupancyRateByPortIDandDate(portID,month,year,day);
            map[i][1]=result;
            day++;
        }
        return map;
    }

    /**
     * Get a map of the occupation of the existing resources in the port during a 30 days month.
     * @param portID port id.
     * @param month month.
     * @param year year.
     * @return a map of the occupation of the existing resources in the port during a 30 days month.
     * @throws SQLException sql exception.
     */
    public int[][] getMap30dMonth(int portID, int month, int year) throws SQLException {
        int[][] map = new int[30][2];
        int day=1;
        int result=0;
        Date date;
        for (int i = 0; i < 30; i++) {
            map[i][0]=i+1;
        }
        for (int i = 0; i < 30; i++) {
            result=getOccupancyRateByPortIDandDate(portID,month,year,day);
            map[i][1]=result;
            day++;
        }
        return map;
    }

    /**
     * Get a map of the occupation of the existing resources in the port during a 31 days month.
     * @param portID port id.
     * @param month month.
     * @param year year.
     * @return a map of the occupation of the existing resources in the port during a 31 days month.
     * @throws SQLException sql exception.
     */
    public int[][] getMap31dMonth(int portID, int month, int year) throws SQLException {
        int[][] map = new int[31][2];
        int day=1;
        int result=0;
        Date date;
        for (int i = 0; i < 31; i++) {
            map[i][0]=i+1;
        }
        for (int i = 0; i < 31; i++) {
            result=getOccupancyRateByPortIDandDate(portID,month,year,day);
            map[i][1]=result;
            day++;
        }
        return map;
    }

}
