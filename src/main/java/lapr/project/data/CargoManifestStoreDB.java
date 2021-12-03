package lapr.project.data;

import lapr.project.controller.App;

import java.sql.*;
import java.util.Date;

public class CargoManifestStoreDB{

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
                "f_cargoManifest_id cargoManifest.cargoManifest_id%type;\n" +
                "begin\n" +
                "select loading_cargo_id into f_cargoManifest_id\n" +
                "from\n" +
                "    (select loading_cargo_id from shipTrip\n" +
                "     where mmsi=f_mmsi AND est_departure_date<=f_date\n" +
                "     order by est_departure_date desc)\n" +
                "where rownum=1;\n" +
                "select shiptrip_id into f_shiptrip_id\n" +
                "from shipTrip\n" +
                "where loading_cargo_id=f_cargoManifest_id;\n" +
                "select unloading_cargo_id into f_cargoManifest_id\n" +
                "from shipTrip\n" +
                "where shiptrip_id=f_shiptrip_id AND f_date>est_arrival_date;\n" +
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