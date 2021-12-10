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
import java.util.LinkedList;
import java.util.List;

public class ShipTripStoreDB {
    private final String header = String.format("%-15s%-15s%-15s%-15s%-14s%-15s%n",
            "Container ID", "ISO Code", "Payload", "Positionx","PositionY", "PositionZ");

    private final String idPort = "Next Port ID: ";

    /**
     * Create shipTrip
     * @param shipTripID ship trip id
     * @return -1 if the input information is wrong, otherwise it returns 1
     */
    public int deleteShipTrip(int shipTripID) {
        int result = 1;
        String createFunction = "create or replace function delete_shipTrip\n" +
                "(f_shiptrip_id shiptrip.shiptrip_id%type) return integer\n" +
                "is\n" +
                "begin\n" +
                "delete\n" +
                "from shipTrip\n" +
                "where\n" +
                "shiptrip_id = f_shiptrip_id;\n" +
                "return 1;\n" +
                "exception\n" +
                "when no_data_found then\n" +
                "return -1;\n" +
                "end;";
        String runFunction = "{? = call delete_shipTrip(?)}";
        DatabaseConnection databaseConnection = App.getInstance().getConnection();
        Connection connection = databaseConnection.getConnection();
        try (Statement createFunctionStat = connection.createStatement();
             CallableStatement callableStatement = connection.prepareCall(runFunction)) {
            createFunctionStat.execute(createFunction);
            callableStatement.registerOutParameter(1, Types.INTEGER);
            callableStatement.setString(2, String.valueOf(shipTripID));

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

    /**
     * Create shipTrip
     * @param shipTripID ship trip id
     * @param mmsi ship mmsi
     * @param depLocation departure location
     * @param arriLocation arrival location
     * @param loadCargID loading cargo manifest id
     * @param unloadCargID unloading cargo manifest id
     * @param estDepDate estimated departure date
     * @param estArriDate estimates arrival date
     * @param realDepDate real departure date
     * @param realArriDate real arrival date
     * @return -1 if the input information is wrong, otherwise it returns 1
     */
    public int createShipTrip(int shipTripID, int mmsi, int depLocation, int arriLocation, int loadCargID, int unloadCargID, java.sql.Date estDepDate, java.sql.Date estArriDate,
                              java.sql.Date realDepDate, java.sql.Date realArriDate) {
        int result = 1;
        String createFunction = "create or replace function create_shipTrip\n" +
                "(f_shiptrip_id shiptrip.shiptrip_id%type, f_mmsi shiptrip.mmsi%type, f_departure_location shiptrip.departure_location%type,\n" +
                "f_arrival_location shiptrip.arrival_location%type, f_loading_cargo_id shiptrip.loading_cargo_id%type, f_unloading_cargo_id shiptrip.unloading_cargo_id%type,\n" +
                "f_est_departure_date shiptrip.est_departure_date%type, f_est_arrival_date shiptrip.est_arrival_date%type,\n" +
                "f_real_departure_date shiptrip.real_departure_date%type, f_real_arrival_date shiptrip.real_arrival_date%type) return integer\n" +
                "is\n" +
                "f_check integer;\n" +
                "f_check2 integer;\n" +
                "begin\n" +
                "f_check:=check_if_ship_exists(f_mmsi);\n" +
                "if f_check=0 then\n" +
                "return -1;\n" +
                "end if;\n" +
                "f_check2:=check_if_cargoManifest_exists(f_loading_cargo_id);\n" +
                "if f_check2=0 then\n" +
                "return -1;\n" +
                "end if;\n" +
                "insert into shiptrip (shiptrip_id, mmsi, departure_location, arrival_location, loading_cargo_id, unloading_cargo_id, est_departure_date, est_arrival_date, real_departure_date, real_arrival_date) values (f_shiptrip_id, f_mmsi, f_departure_location, f_arrival_location, f_loading_cargo_id, f_unloading_cargo_id, f_est_departure_date, f_est_arrival_date, f_real_departure_date, f_real_arrival_date);\n" +
                "return 1;\n" +
                "exception\n" +
                "when no_data_found then\n" +
                "return -1;\n" +
                "end;";
        String runFunction = "{? = call create_shipTrip(?,?,?,?,?,?,?,?,?,?)}";
        DatabaseConnection databaseConnection = App.getInstance().getConnection();
        Connection connection = databaseConnection.getConnection();
        try (Statement createFunctionStat = connection.createStatement();
             CallableStatement callableStatement = connection.prepareCall(runFunction)) {
            createFunctionStat.execute(createFunction);
            callableStatement.registerOutParameter(1, Types.INTEGER);
            callableStatement.setString(2, String.valueOf(shipTripID));
            callableStatement.setString(3, String.valueOf(mmsi));
            callableStatement.setString(4, String.valueOf(depLocation));
            callableStatement.setString(5, String.valueOf(arriLocation));
            callableStatement.setString(6, String.valueOf(loadCargID));
            callableStatement.setString(7, String.valueOf(unloadCargID));
            callableStatement.setString(8, String.valueOf(estDepDate));
            callableStatement.setString(9, String.valueOf(estArriDate));
            callableStatement.setString(10, String.valueOf(realDepDate));
            callableStatement.setString(11, String.valueOf(realArriDate));

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

    /**
     * Get MMSI by cargo manifest ID.
     * @param cargoManifestID cargo manifest ID.
     * @return Ship MMSI.
     */
    public int getMmsiByCargoManifestID(int cargoManifestID) {
        int result = 0;
        String createFunction = "create or replace function get_mmsi_by_cargo_manifest_id(f_cargoManifest_id cargoManifest.cargoManifest_id%type) return integer --est date como parametro\n" +
                "is\n" +
                "f_shiptrip_id shipTrip.shiptrip_id%type;\n" +
                "f_mmsi ship.mmsi%type;\n" +
                "begin\n" +
                "select shiptrip_id into f_shiptrip_id\n" +
                "from shipTrip\n" +
                "where loading_cargo_id=f_cargoManifest_id or unloading_cargo_id=f_cargoManifest_id;\n" +
                "select mmsi into f_mmsi\n" +
                "from shipTrip\n" +
                "where shiptrip_id = f_shiptrip_id;\n" +
                "return f_mmsi;\n" +
                "exception\n" +
                "when no_data_found then\n" +
                "return -1;\n" +
                "end;";
        String runFunction = "{? = call get_mmsi_by_cargo_manifest_id(?)}";
        DatabaseConnection databaseConnection = App.getInstance().getConnection();
        Connection connection = databaseConnection.getConnection();
        try (Statement createFunctionStat = connection.createStatement();
             CallableStatement callableStatement = connection.prepareCall(runFunction)) {
            createFunctionStat.execute(createFunction);
            callableStatement.registerOutParameter(1, Types.INTEGER);
            callableStatement.setString(2, String.valueOf(cargoManifestID));

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

    /**
     * Get containers added and/or removed by the time of a cargo manifest for a ship trip.
     * @param cargoManifestID cargo manifest ID.
     * @return the number of containers added and/or removed.
     */
    public int getAddedRemovedContainersShipTripMoment(int cargoManifestID) {
        int result = 0;
        String createFunction = "create or replace function get_added_removed_containers_ship_trip_moment(f_cargoManifest_id cargoManifest.cargoManifest_id%type) return integer --est date como parametro\n" +
                "is\n" +
                "f_shiptrip_id shipTrip.shiptrip_id%type;\n" +
                "f_loading_cargo_id shipTrip.loading_cargo_id%type;\n" +
                "f_unloading_cargo_id shipTrip.unloading_cargo_id%type;\n" +
                "f_num_added_removed_containers_ship_trip_moment integer:=0;\n" +
                "begin\n" +
                "select shiptrip_id into f_shiptrip_id\n" +
                "from shipTrip\n" +
                "where loading_cargo_id=f_cargoManifest_id or unloading_cargo_id=f_cargoManifest_id;\n" +
                "select loading_cargo_id, unloading_cargo_id into f_loading_cargo_id, f_unloading_cargo_id\n" +
                "from shipTrip\n" +
                "where shiptrip_id = f_shiptrip_id;\n" +
                "f_num_added_removed_containers_ship_trip_moment := f_num_added_removed_containers_ship_trip_moment + get_num_containers_per_cargoManifest(f_loading_cargo_id);\n" +
                "if f_unloading_cargo_id=f_cargoManifest_id then\n" +
                "f_num_added_removed_containers_ship_trip_moment := f_num_added_removed_containers_ship_trip_moment + get_num_containers_per_cargoManifest(f_unloading_cargo_id);\n" +
                "end if;\n" +
                "return f_num_added_removed_containers_ship_trip_moment;\n" +
                "exception\n" +
                "when no_data_found then\n" +
                "return 0;\n" +
                "end;";
        String runFunction = "{? = call get_added_removed_containers_ship_trip_moment(?)}";
        DatabaseConnection databaseConnection = App.getInstance().getConnection();
        Connection connection = databaseConnection.getConnection();
        try (Statement createFunctionStat = connection.createStatement();
             CallableStatement callableStatement = connection.prepareCall(runFunction)) {
            createFunctionStat.execute(createFunction);
            callableStatement.registerOutParameter(1, Types.INTEGER);
            callableStatement.setString(2, String.valueOf(cargoManifestID));

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
    
    /**
     * Get the estimated departure date of a ship trip given a cargo manifest ID.
     * @param cargoManifestID cargo manifest ID.
     * @return estimated departure date.
     */
    public java.sql.Date getEstDepartureDateFromShipTrip(int cargoManifestID) {
        java.sql.Date result = new java.sql.Date(new java.util.Date(2020, Calendar.JANUARY,1).getTime());
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
    public int getInitialNumContainersPerShipTrip(int cargoManifestID, java.sql.Date estDepartureDate, int mmsi) {
        int result = 0;
        String createFunction = "create or replace function get_initial_num_containers_per_ship_trip(f_cargoManifest_id cargoManifest.cargoManifest_id%type,\n" +
                "f_est_departure_date shipTrip.est_departure_date%type, f_mmsi ship.mmsi%type) return integer\n" +
                "is\n" +
                "f_comp_loading_cargo_id cargoManifest.cargoManifest_id%type;\n" +
                "f_comp_unloading_cargo_id cargoManifest.cargoManifest_id%type;\n" +
                "f_initial_num_containers_per_ship_trip integer:=0;\n" +
                "cursor neededShipTrips\n" +
                "is\n" +
                "(select loading_cargo_id, unloading_cargo_id\n" +
                "from shipTrip\n" +
                "where mmsi=f_mmsi AND est_departure_date < f_est_departure_date);\n" +
                "begin\n" +
                "open neededShipTrips;\n" +
                "loop\n" +
                "fetch neededShipTrips into f_comp_loading_cargo_id, f_comp_unloading_cargo_id;\n" +
                "exit when neededShipTrips%notfound;\n" +
                "f_initial_num_containers_per_ship_trip := f_initial_num_containers_per_ship_trip + get_num_containers_per_cargoManifest(f_comp_loading_cargo_id) - get_num_containers_per_cargoManifest(f_comp_unloading_cargo_id);\n" +
                "end loop;\n" +
                "return f_initial_num_containers_per_ship_trip;\n" +
                "exception\n" +
                "when no_data_found then\n" +
                "return 0;\n" +
                "end;";
        String runFunction = "{? = call get_initial_num_containers_per_ship_trip(?,?,?)}";
        DatabaseConnection databaseConnection = App.getInstance().getConnection();
        Connection connection = databaseConnection.getConnection();
        try (Statement createFunctionStat = connection.createStatement();
             CallableStatement callableStatement = connection.prepareCall(runFunction)) {
            createFunctionStat.execute(createFunction);
            callableStatement.registerOutParameter(1, Types.INTEGER);
            callableStatement.setString(2, String.valueOf(cargoManifestID));
            callableStatement.setString(3, String.valueOf(estDepartureDate));
            callableStatement.setString(4, String.valueOf(mmsi));

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

    /**
     * method to get the id of the nest Port in the Route
     * @param databaseConnection connection to database
     * @param mmsi ship mmsi
     * @return port id
     */
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

    /**
     * Method to get the list of containers being offloaded in the next Port.
     * @param databaseConnection connection to database
     * @param mmsi ship mmsi
     * @return list with only Containers id's
     * @throws IOException database exception
     */
    public List<Integer> getListOffloadedContainers(DatabaseConnection databaseConnection, int mmsi) throws IOException {

        int portId = getNextPortID(databaseConnection, mmsi);
        System.out.println("Next Port: " + portId);

        File file = new File("offloadedContainers.txt");
        if (!file.exists())
            file.createNewFile();

        List<Integer> idList = new LinkedList<>();
        Connection connection = databaseConnection.getConnection();

        try (FileWriter fw = new FileWriter(file, false); BufferedWriter bw = new BufferedWriter(fw); CallableStatement cs = connection.prepareCall("{? = call  offcontainers_id(?)}")) {


            bw.write(idPort);
            bw.write(portId);
            bw.write(header);

            cs.setInt(2, mmsi);
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.executeUpdate();


            ResultSet cs1 = (ResultSet) cs.getObject(1);

            while (cs1.next()) {
                int containerId = cs1.getInt(1);
                System.out.println("Container id: " + containerId);
                idList.add(containerId);
                String isoCode = cs1.getString(2);
                System.out.println("Container ISO: " + isoCode);
                int payload = cs1.getInt(3);
                System.out.println("Continer Load: " + payload);
                int positionx = cs1.getInt(4);
                int positiony = cs1.getInt(5);
                int positionz = cs1.getInt(6);
                System.out.println("Position in vehicule: " + positionx + ", " + positiony + ", " + positionz);
                System.out.println();


                bw.write(containerId);
                bw.write(isoCode);
                bw.write(payload);
                bw.write(positionx);
                bw.write(positiony);
                bw.write(positionz);
            }
            cs.close();
            return idList;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        throw new UnsupportedOperationException("Some error with the Data Base occured. Please try again.");

    }

    /**
     * Method to get the list of containers being loaded in the next Port.
     * @param databaseConnection connection to database
     * @param mmsi ship mmsi
     * @return list with only Containers id's
     * @throws IOException database exception
     */
    public List<Integer> getListLoadedContainers(DatabaseConnection databaseConnection, int mmsi) throws IOException {

        int portId = getNextPortID(databaseConnection, mmsi);
        System.out.println("Next Port: " + portId);

        File file = new File("loadedContainers.txt");
        if (!file.exists())
            file.createNewFile();

        List<Integer> listID = new LinkedList<>();
        Connection connection = databaseConnection.getConnection();

        try (FileWriter fw = new FileWriter(file, false); BufferedWriter bw = new BufferedWriter(fw); CallableStatement cs = connection.prepareCall("{? = call  load_containers_id(?)}")) {

            bw.write(idPort);
            bw.write(portId);
            bw.write(header);

            cs.setInt(2, mmsi);
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.executeUpdate();


            ResultSet cs1 = (ResultSet) cs.getObject(1);

            while (cs1.next()) {
                int containerId = cs1.getInt(1);
                System.out.println("Container id: " + containerId);
                listID.add(containerId);
                String isoCode = cs1.getString(2);
                System.out.println("Container ISO: " + isoCode);
                int payload = cs1.getInt(3);
                System.out.println("Continer Load: " + payload);
                int positionx = cs1.getInt(4);
                int positiony = cs1.getInt(5);
                int positionz = cs1.getInt(6);
                System.out.println("Position in vehicule: " + positionx + ", " + positiony + ", " + positionz);
                System.out.println();

                bw.write(containerId);
                bw.write(isoCode);
                bw.write(payload);
                bw.write(positionx);
                bw.write(positiony);
                bw.write(positionz);
            }
            //cs.close();
            return listID;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        throw new UnsupportedOperationException("Some error with the Data Base occured. Please try again.");

    }
}
