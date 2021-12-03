package lapr.project.data;

import lapr.project.controller.App;
import oracle.jdbc.OracleTypes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ShipTripStoreDB {
    
    /**
     * Get the estimated departure date of a ship trip given a cargo manifest ID.
     * @param cargoManifestID cargo manifest ID.
     * @return estimated departure date.
     */
    public java.util.Date getEstDepartureDateFromShipTrip(int cargoManifestID) {
        Date result = new java.util.Date(1970, Calendar.JANUARY, 1, 0, 0, 0);
        String createFunction = "create or replace function get_est_departure_date_from_ship_trip(f_cargoManifest_id cargoManifest.cargoManifest_id%type) return shipTrip.est_departure_date%type\n" +
                "is\n" +
                "f_shiptrip_id shipTrip.shiptrip_id%type;\n" +
                "f_est_departure_date shipTrip.est_departure_date%type;\n" +
                "begin\n" +
                "select shiptrip_id into f_shiptrip_id\n" +
                "from shipTrip\n" +
                "where loading_cargo_id = f_cargoManifest_id OR unloading_cargo_id = f_cargoManifest_id;\n" +
                "select est_departure_date into f_est_departure_date\n" +
                "from shipTrip\n" +
                "where shiptrip_id = f_shiptrip_id;\n" +
                "return f_est_departure_date;\n" +
                "end;";
        String runFunction = "{? = call get_est_departure_date_from_ship_trip(?)}";
        DatabaseConnection databaseConnection = App.getInstance().getConnection();
        Connection connection = databaseConnection.getConnection();
        try (Statement createFunctionStat = connection.createStatement();
             CallableStatement callableStatement = connection.prepareCall(runFunction)) {
            createFunctionStat.execute(createFunction);
            callableStatement.registerOutParameter(1, Types.DATE);
            callableStatement.setString(2, String.valueOf(cargoManifestID));

            callableStatement.executeUpdate();

            result = callableStatement.getDate(1);
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Get a ship trip's initial num of containers given a cargo manifest ID and a estimates departure date.
     * @param cargoManifestID cargo manifest ID.
     * @param estDepartureDate Ship trip's estimated departure date.
     * @return ship trip's initial num of containers.
     */
    public int getInitialNumContainersPerShipTrip(int cargoManifestID, Date estDepartureDate) {
        int result = 0;
        String createFunction = "create or replace function get_initial_num_containers_per_ship_trip(f_cargoManifest_id cargoManifest.cargoManifest_id%type,\n" +
                "f_est_departure_date shipTrip.est_departure_date%type) return integer --est date como parametro\n" +
                "is\n" +
                "f_comp_shiptrip_id shipTrip.shiptrip_id%type;\n" +
                "f_comp_loading_cargo_id shipTrip.loading_cargo_id%type;\n" +
                "f_comp_unloading_cargo_id shipTrip.unloading_cargo_id%type;\n" +
                "f_initial_num_containers_per_ship_trip integer;\n" +
                "cursor shipTrips\n" +
                "is\n" +
                "select shiptrip_id\n" +
                "from shipTrip\n" +
                "where est_departure_date < f_est_departure_date; --o f_est_departure_date ainda está vazio??\n" +
                "begin\n" +
                "select loading_cargo_id, unloading_cargo_id into f_comp_loading_cargo_id, f_comp_unloading_cargo_id\n" +
                "from shipTrip\n" +
                "where shiptrip_id = f_comp_shiptrip_id;\n" +
                "loop\n" +
                "fetch shipTrips into f_comp_shiptrip_id;\n" +
                "exit when shipTrips%notfound;\n" +
                "select loading_cargo_id, unloading_cargo_id into f_comp_loading_cargo_id, f_comp_unloading_cargo_id\n" +
                "from shipTrip\n" +
                "where shiptrip_id = f_comp_shiptrip_id;\n" +
                "f_initial_num_containers_per_ship_trip := f_initial_num_containers_per_ship_trip + get_num_containers_per_cargoManifest(f_comp_loading_cargo_id) - get_num_containers_per_cargoManifest(f_comp_unloading_cargo_id);\n" +
                "end loop;\n" +
                "return f_initial_num_containers_per_ship_trip;\n" +
                "exception\n" +
                "when no_data_found then\n" +
                "return 0;\n" +
                "end;";
        String runFunction = "{? = call get_initial_num_containers_per_ship_trip(?,?)}";
        DatabaseConnection databaseConnection = App.getInstance().getConnection();
        Connection connection = databaseConnection.getConnection();
        try (Statement createFunctionStat = connection.createStatement();
             CallableStatement callableStatement = connection.prepareCall(runFunction)) {
            createFunctionStat.execute(createFunction);
            callableStatement.registerOutParameter(1, Types.INTEGER);
            callableStatement.setString(2, String.valueOf(cargoManifestID));
            callableStatement.setString(3, String.valueOf(estDepartureDate));

            callableStatement.executeUpdate();

            result = callableStatement.getInt(1);
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public int getShipTripId(DatabaseConnection databaseConnection, int containerId, int shipmentId) throws SQLException {
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
        throw new UnsupportedOperationException("Some error with the Data Base occured. Please try again.");
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
