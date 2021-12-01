package lapr.project.data;

import lapr.project.controller.App;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class ShipTripStoreDB {

    /*public static void main(String[] args) throws SQLException {
        int shipTripId = getShipTripId(App.getInstance().getConnection(),1,5);
        System.out.println(shipTripId);
        String location = getLocation(App.getInstance().getConnection(), shipTripId);
        System.out.println(location);
    }
     */



    public int getShipTripId(DatabaseConnection databaseConnection, int containerId, int shipmentId) {
        try {
            Connection connection = databaseConnection.getConnection();
            CallableStatement cs = connection.prepareCall("{? = call get_shiptrip_id(?, ?)}");
            cs.setInt(2, containerId);
            cs.setInt(3, shipmentId);
            cs.registerOutParameter(1, Types.INTEGER);
            cs.executeUpdate();
            int shipTripId = cs.getInt(1);
            cs.close();
            return shipTripId;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return -1;
    }

    public String getLocation(DatabaseConnection databaseConnection, int shipTripId) {
        try {
            Connection connection = databaseConnection.getConnection();
            CallableStatement cs = connection.prepareCall("{? = call get_location(?)}");
            cs.setInt(2, shipTripId);
            cs.registerOutParameter(1, Types.VARCHAR);
            cs.executeUpdate();
            String location = cs.getString(1);
            cs.close();
            return location;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        throw new UnsupportedOperationException("Some error occured. Please try again.");
    }
}
