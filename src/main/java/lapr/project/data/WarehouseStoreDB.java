package lapr.project.data;

import lapr.project.controller.App;

import java.sql.*;

public class WarehouseStoreDB {

    /**
     * Check if warehouse exists in the data base.
     * @param warehouse_id Warehouse's id.
     * @return 1 if warehouse exists and 0 if it doesn't.
     */
    public int checkIfWarehouseExists(int warehouse_id) {
        int result = 0;
        String createFunction = "create or replace function check_if_warehouse_exists(f_warehouse_id warehouse.warehouse_id%type) return integer\n" +
                "is\n" +
                "f_result integer;\n" +
                "begin\n" +
                "select count(*) into f_result\n" +
                "from warehouse\n" +
                "where warehouse_id = f_warehouse_id;\n" +
                "return (f_result);\n" +
                "exception\n" +
                "when no_data_found then\n" +
                "return 0;\n" +
                "end;";
        String runFunction = "{? = call check_if_warehouse_exists(?)}";
        DatabaseConnection databaseConnection = App.getInstance().getConnection();
        Connection connection = databaseConnection.getConnection();
        try (Statement createFunctionStat = connection.createStatement();
             CallableStatement callableStatement = connection.prepareCall(runFunction)) {
            createFunctionStat.execute(createFunction);
            callableStatement.registerOutParameter(1, Types.INTEGER);
            callableStatement.setString(2, String.valueOf(warehouse_id));

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
     * Get warehouse max capacity.
     * @param warehouse_id Warehouse to analyse.
     * @return warehouse max capacity.
     */
    public int getWarehouseMaxCapacity(int warehouse_id) {
        int result = 0;
        String createFunction = "create or replace function get_warehouse_max_capacity(f_warehouse_id warehouse.warehouse_id%type) return integer\n" +
                "is\n" +
                "f_max_capacity integer;\n" +
                "begin\n" +
                "select maxCapacity into f_max_capacity\n" +
                "from warehouse\n" +
                "where warehouse_id = f_warehouse_id;\n" +
                "return (f_max_capacity);\n" +
                "exception\n" +
                "when no_data_found then\n" +
                "return 0;\n" +
                "end;\n";
        String runFunction = "{? = call get_warehouse_max_capacity(?)}";
        DatabaseConnection databaseConnection = App.getInstance().getConnection();
        Connection connection = databaseConnection.getConnection();
        try (Statement createFunctionStat = connection.createStatement();
             CallableStatement callableStatement = connection.prepareCall(runFunction)) {
            createFunctionStat.execute(createFunction);
            callableStatement.registerOutParameter(1, Types.INTEGER);
            callableStatement.setString(2, String.valueOf(warehouse_id));

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

}
