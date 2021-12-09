package lapr.project.data;

import lapr.project.controller.App;

import java.sql.*;
import java.util.Calendar;

/**
 * @author Marta Ribeiro (1201592)
 */
public class CargoManifestStoreDB{

    /**
     * Check if cargo manifest exceeds given ship capacity.
     * @param cargoManifestID Cargo manifest's ID.
     * @param mmsi Desired Ship's mmsi.
     * @return -1 if the input values are invalid, 0 if it exceeds the capacity and 1 if it doesn't.
     */
    public int checkIfCargoManifestExceedsShipCapacity(int cargoManifestID, int mmsi) {
        int result = 0;
        Date date = new Date(Calendar.getInstance().getTime().getTime());
        String createFunction = "create or replace function check_if_cargoManifest_exceeds_ship_capacity\n" +
                "(f_cargoManifest_id cargomanifest.cargomanifest_id%type, f_mmsi shipTrip.mmsi%type, f_date shipTrip.est_departure_date%type) return integer\n" +
                "is\n" +
                "f_result integer;\n" +
                "f_numContainers integer;\n" +
                "f_check integer;\n" +
                "f_check2 integer;\n" +
                "f_comp_cargoManifest_id cargomanifest.cargomanifest_id%type;\n" +
                "f_maxCapacity integer;\n" +
                "f_realDepDate shipTrip.real_departure_date%type;\n" +
                "f_initialNumContainers integer;\n" +
                "f_alreadyAddedRemovedContainersTripNum integer;\n" +
                "f_resultado integer;\n" +
                "begin\n" +
                "f_check:=check_if_ship_exists(f_mmsi);\n" +
                "if f_check=0 then\n" +
                "return -1;\n" +
                "end if;\n" +
                "f_check2:=check_if_cargoManifest_exists(f_cargoManifest_id);\n" +
                "if f_check2=0 then\n" +
                "return -1;\n" +
                "end if;\n" +
                "f_numContainers:=get_num_containers_per_cargoManifest(f_cargoManifest_id);\n" +
                "f_comp_cargoManifest_id:=get_cargo_manifest_by_mmsi_and_date(f_mmsi, f_date);\n" +
                "f_maxCapacity:=get_max_capacity(f_comp_cargoManifest_id);\n" +
                "f_realDepDate:=get_real_departure_date_from_ship_trip(f_comp_cargoManifest_id);\n" +
                "f_initialNumContainers:=get_initial_num_containers_per_ship_trip(f_comp_cargoManifest_id,f_realDepDate,f_mmsi);\n" +
                "f_alreadyAddedRemovedContainersTripNum:=get_added_removed_containers_ship_trip_moment(f_comp_cargoManifest_id);\n" +
                "f_resultado:=f_initialNumContainers+f_alreadyAddedRemovedContainersTripNum;\n" +
                "if (f_numContainers+f_resultado)>f_maxCapacity then\n" +
                "return 0; --ultrapassa\n" +
                "end if;\n" +
                "if (f_numContainers+f_resultado)<=f_maxCapacity then\n" +
                "return 1; --tem espaço suficiente\n" +
                "end if;\n" +
                "exception\n" +
                "when no_data_found then\n" +
                "return -1;\n" +
                "end;";
        String runFunction = "{? = call check_if_cargoManifest_exceeds_ship_capacity(?,?,?)}";
        DatabaseConnection databaseConnection = App.getInstance().getConnection();
        Connection connection = databaseConnection.getConnection();
        try (Statement createFunctionStat = connection.createStatement();
             CallableStatement callableStatement = connection.prepareCall(runFunction)) {
            createFunctionStat.execute(createFunction);
            callableStatement.registerOutParameter(1, Types.INTEGER);
            callableStatement.setString(2, String.valueOf(cargoManifestID));
            callableStatement.setString(3, String.valueOf(mmsi));
            callableStatement.setString(4, String.valueOf(date));

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
     * Check if cargo manifest exists in the data base.
     * @param cargoManifestID Cargo manifest ID.
     * @return 1 if cargo manifest exists and 0 if it doesn't.
     */
    public int checkIfCargoManifestExists(int cargoManifestID) {
        int result = 0;
        String createFunction = "create or replace function check_if_cargoManifest_exists(f_cargoManifest_id cargomanifest.cargomanifest_id%type) return integer\n" +
                "is\n" +
                "f_result integer;\n" +
                "begin\n" +
                "select count(*) into f_result\n" +
                "from cargomanifest\n" +
                "where cargomanifest_id = f_cargomanifest_id;\n" +
                "return (f_result);\n" +
                "exception\n" +
                "when no_data_found then\n" +
                "return 0;\n" +
                "end;";
        String runFunction = "{? = call check_if_cargoManifest_exists(?)}";
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
     * Get the number of containers loaded or unloaded by a cargo manifest.
     * @param cargoManifestID cargo manifest ID.
     * @return number of containers loaded or unloaded by a cargo manifest.
     */
    public int getNumContainersPerCargoManifest(int cargoManifestID){
        int result = 0;
        String createFunction = "create or replace function get_num_containers_per_cargoManifest(f_cargoManifest_id cargoManifest.cargoManifest_id%type) return integer\n" +
                "is\n" +
                "f_num_containers_per_cargoManifest integer;\n" +
                "begin\n" +
                "select count(*) into f_num_containers_per_cargoManifest\n" +
                "from containerInCargoManifest\n" +
                "where cargoManifest_id = f_cargoManifest_id;\n" +
                "return (f_num_containers_per_cargoManifest);\n" +
                "exception\n" +
                "when no_data_found then\n" +
                "return 0;\n" +
                "end;";
        String runFunction = "{? = call get_num_containers_per_cargoManifest(?)}";
        DatabaseConnection databaseConnection = App.getInstance().getConnection();
        Connection connection = databaseConnection.getConnection();
        try(Statement createFunctionStat = connection.createStatement();
            CallableStatement callableStatement = connection.prepareCall(runFunction)) {
            createFunctionStat.execute(createFunction);
            callableStatement.registerOutParameter(1, Types.INTEGER);
            callableStatement.setString(2, String.valueOf(cargoManifestID));

            callableStatement.executeUpdate();

            result = callableStatement.getInt(1);
        }catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Get a cargo manifest given a mmsi and a date.
     * @param mmsi ship's mmsi.
     * @param date date to analyse.
     * @return cargo manifest ID.
     */
    public int getCargoManifestByMmsiAndDate(int mmsi, Date date){
        int result = 0;
        String createFunction = "create or replace function get_cargo_manifest_by_mmsi_and_date(f_mmsi shipTrip.mmsi%type, f_date shipTrip.est_departure_date%type) return cargoManifest.cargoManifest_id%type\n" +
                "is\n" +
                "f_shiptrip_id shipTrip.shiptrip_id%type;\n" +
                "f_unloading_cargo_id cargoManifest.cargoManifest_id%type;\n" +
                "f_est_departure_date shipTrip.est_departure_date%type;\n" +
                "f_est_arrival_date shipTrip.est_arrival_date%type;\n" +
                "f_cargoManifest_id cargoManifest.cargoManifest_id%type;\n" +
                "begin\n" +
                "\n" +
                "select shiptrip_id, unloading_cargo_id, est_departure_date, est_arrival_date, loading_cargo_id\n" +
                "into f_shiptrip_id, f_unloading_cargo_id, f_est_departure_date, f_est_arrival_date, f_cargoManifest_id\n" +
                "from\n" +
                "(select * from \n" +
                "(select * from shipTrip\n" +
                "where mmsi=f_mmsi AND est_departure_date<=f_date)\n" +
                "order by est_departure_date desc)\n" +
                "where rownum=1;\n" +
                "\n" +
                "if f_est_arrival_date <= f_date then\n" +
                "select unloading_cargo_id into f_cargoManifest_id\n" +
                "from shipTrip\n" +
                "where shiptrip_id=f_shiptrip_id;\n" +
                "end if;\n" +
                "\n" +
                "return (f_cargoManifest_id);\n" +
                "exception\n" +
                "when no_data_found then\n" +
                "return -1;\n" +
                "end;";
        String runFunction = "{? = call get_cargo_manifest_by_mmsi_and_date(?,?)}";
        DatabaseConnection databaseConnection = App.getInstance().getConnection();
        Connection connection = databaseConnection.getConnection();
        try(Statement createFunctionStat = connection.createStatement();
            CallableStatement callableStatement = connection.prepareCall(runFunction)) {
            createFunctionStat.execute(createFunction);
            callableStatement.registerOutParameter(1, Types.INTEGER);
            callableStatement.setString(2, String.valueOf(mmsi));
            callableStatement.setString(3, String.valueOf(date));

            callableStatement.executeUpdate();

            result = callableStatement.getInt(1);
        }catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
