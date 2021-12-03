package lapr.project.data;

import lapr.project.controller.App;
import oracle.jdbc.OracleTypes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.List;

public class ShipTripStoreDB {

    /*public static void main(String[] args) throws SQLException {
        int shipTripId = getShipTripId(App.getInstance().getConnection(),1,5);
        System.out.println(shipTripId);
        String location = getLocation(App.getInstance().getConnection(), shipTripId);
        System.out.println(location);
    }
     */




    public int getShipTripId(DatabaseConnection databaseConnection, int containerId, int shipmentId) throws SQLException {
        Connection connection = databaseConnection.getConnection();
        CallableStatement cs = connection.prepareCall("{? = call get_shiptrip_id(?, ?)}");

        try {
            cs.setInt(2, containerId);
            cs.setInt(3, shipmentId);
            cs.registerOutParameter(1, Types.INTEGER);
            cs.executeUpdate();
            int shipTripId = cs.getInt(1);
            return shipTripId;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            cs.close();
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
        throw new UnsupportedOperationException("Some error with the Data Base occured. Please try again.");
    }

    public int getNextPortID(DatabaseConnection databaseConnection, int mmsi) {

        try {
            Connection connection = databaseConnection.getConnection();
            CallableStatement cs = connection.prepareCall("{? = call get_port(?)}");
            cs.setInt(2, mmsi);
            cs.registerOutParameter(1, Types.INTEGER);
            cs.executeUpdate();
            int portId= cs.getInt(1);
            cs.close();
            return portId;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        throw new UnsupportedOperationException("Some error with the Data Base occured. Please try again.");
    }

    public void getListOffloadedContainers(DatabaseConnection databaseConnection, int mmsi) throws IOException, SQLException {
        String header = String.format("%-15s%-15s%-15s%-15s%-14s%-15s%n",
                "Container ID", "ISO Code", "Payload", "Positionx","PositionY", "PositionZ");

        int portId = getNextPortID(databaseConnection, mmsi);
        String str = "Next Port ID: ";

        /*File file = new File("offloadedContainers.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        FileWriter fw = new FileWriter(file, false);


        BufferedWriter bw = new BufferedWriter(fw);*/
        CallableStatement cs;
        Connection connection = databaseConnection.getConnection();
        cs = connection.prepareCall("{? = call offloaded(?)}");
        try {
            /*bw.write(str);
            bw.write(portId);
            bw.write(header);*/

            cs.setInt(2, mmsi);
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.executeUpdate();


            ResultSet cs1 = (ResultSet) cs.getObject(1);

            while(cs1.next()) {
                int containerId = cs1.getInt(1);
                String isoCode = cs1.getString(2);
                int payload = cs1.getInt(3);
                int positionx = cs1.getInt(4);
                int positiony = cs1.getInt(5);
                int positionz = cs1.getInt(6);

                /*bw.write(containerId);
                bw.write(isoCode);
                bw.write(payload);
                bw.write(positionx);
                bw.write(positiony);
                bw.write(positionz);*/
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            cs.close();
            //bw.close();
        }

        throw new UnsupportedOperationException("Some error with the Data Base occured. Please try again.");

    }
}
