package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.domain.model.Ship;
import lapr.project.domain.model.ShipPosition;
import lapr.project.domain.model.ShipSortMmsi;
import lapr.project.domain.store.ShipStore;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShipStoreDB implements Persistable{

    /**
     * Get a ship cargo (maximum capacity) by a cargo manifest ID.
     * @param cargoManifestID cargo manifest ID.
     * @return ship cargo.
     */
    public int getShipCargo(int cargoManifestID){
        int result = 0;
        String createFunction = "create or replace function get_max_capacity(f_cargoManifest_id cargoManifest.cargoManifest_id%type) return integer\n" +
                "is\n" +
                "f_mmsi shipTrip.mmsi%type;\n" +
                "f_max_capacity integer;\n" +
                "begin\n" +
                "select mmsi into f_mmsi\n" +
                "from shipTrip\n" +
                "where loading_cargo_id = f_cargoManifest_id OR unloading_cargo_id = f_cargoManifest_id;\n" +
                "select to_number(currentCapacity) into f_max_capacity\n" +
                "from ship\n" +
                "where mmsi = f_mmsi;\n" +
                "return (f_max_capacity);\n" +
                "exception\n" +
                "when no_data_found then\n" +
                "return 0;\n" +
                "end;";
        String runFunction = "{? = call get_max_capacity(?)}";
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

    public String getNumberOfCMAndAverageContForYear(int year, int mmsi){
        String returnMessage=String.format("None cargo manifests were carryed in %d", year);
        String createProcedure = "CREATE OR REPLACE PROCEDURE count_CargoManifests_Avg_Containers (givenYear in Varchar, mmsiCode in Varchar, numCargoManifests out Integer, mediaCont out Integer)\n" +
                "IS\n" +
                "    shipscode Ship.mmsi%type;\n" +
                "    loadcargoscode cargomanifest.cargomanifest_id%type;\n" +
                "    unloadcargoscode cargomanifest.cargomanifest_id%type;\n" +
                "    numContainersInCm Integer;\n" +
                "    somaTotalContainer Integer;\n" +
                "    contadorCm Integer;\n" +
                "\n" +
                "    Cursor cargosLoad IS\n" +
                "        Select loading_cargo_id\n" +
                "        from shiptrip\n" +
                "        where mmsi=mmsiCode AND extract(year from real_departure_date)=givenYear;\n" +
                "\n" +
                "    Cursor cargosUnload IS\n" +
                "        Select unloading_cargo_id\n" +
                "        from shiptrip\n" +
                "        where mmsi=mmsiCode AND extract(year from real_arrival_date)=givenYear;\n" +
                "BEGIN\n" +
                "    contadorCm :=0;\n" +
                "    somaTotalContainer:=0;\n" +
                "\n" +
                "   open cargosLoad;\n" +
                "    LOOP\n" +
                "        fetch cargosLoad INTO loadcargoscode;\n" +
                "        Exit When cargosLoad%notfound;\n" +
                "        dbms_output.put_line('cargo load id: ' || loadcargoscode);\n" +
                "        contadorCm := contadorCm + 1;\n" +
                "        dbms_output.put_line('cargocount ' || contadorCm);\n" +
                "        select count(*) into numContainersInCm from containerincargomanifest where cargomanifest_id=loadcargoscode;\n" +
                "        somaTotalContainer := somaTotalContainer + numContainersInCm;\n" +
                "        dbms_output.put_line('cont count ' || somaTotalContainer);\n" +
                "    END LOOP;\n" +
                "\n" +
                "    open cargosUnload;\n" +
                "     LOOP\n" +
                "        fetch cargosUnload INTO unloadcargoscode;\n" +
                "        Exit When cargosUnload%notfound;\n" +
                "        dbms_output.put_line('cargo unload id: ' || unloadcargoscode);\n" +
                "        contadorCm := contadorCm + 1;\n" +
                "        dbms_output.put_line('cargocount ' || contadorCm);\n" +
                "        select count(*) into numContainersInCm from containerincargomanifest where cargomanifest_id=unloadcargoscode;\n" +
                "        somaTotalContainer := somaTotalContainer + numContainersInCm;\n" +
                "        dbms_output.put_line('cont count ' || somaTotalContainer);\n" +
                "    END LOOP;\n" +
                "\n" +
                "    numCargoManifests := contadorCm;\n" +
                "    mediaCont := (somaTotalContainer / contadorCm);\n" +
                "    dbms_output.put_line('final number of CM ' || numCargoManifests);\n" +
                "    dbms_output.put_line('mean of containers ' || mediaCont);\n" +
                "END;";

        String runSP = "{ call count_CargoManifests_Avg_Containers(?,?,?,?) }";
        DatabaseConnection databaseConnection = App.getInstance().getConnection();
        Connection connection = databaseConnection.getConnection();
        try(Statement createPrcedureStat = connection.createStatement();
            CallableStatement callableStatement = connection.prepareCall(runSP)) {
            createPrcedureStat.execute(createProcedure);
            callableStatement.setString(1, String.valueOf(year));
            callableStatement.setString(2, String.valueOf(mmsi));
            callableStatement.registerOutParameter(3, Types.INTEGER);
            callableStatement.registerOutParameter(4, Types.INTEGER);

            callableStatement.executeUpdate();

            int numCm = callableStatement.getInt(3);
            int avgContainer = callableStatement.getInt(4);
            returnMessage = String.format("Number of cargo manifests in %d:\n%d\nAverage number of containers per cargo manifest:\n%d\n", year, numCm, avgContainer);
        }catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnMessage;
    }

    @Override
    public boolean save(DatabaseConnection databaseConnection, Object object) {
        boolean returnValue = false;
        if(object instanceof Ship){
            try{
                Ship toSave = (Ship) object;

                saveNewShipOnDatabase(toSave, databaseConnection);

                saveShipPositions(toSave, databaseConnection);
            }catch (SQLException ex) {
                Logger.getLogger(ShipStore.class.getName())
                        .log(Level.SEVERE, null, ex);
                databaseConnection.registerError(ex);
                returnValue = false;
            }
        }else if(object instanceof ShipPosition){
            ShipPosition positionToSave = (ShipPosition) object;
            Ship mmsiShipToSearch = new ShipSortMmsi(positionToSave.getMMSI());
            try {
                if (!isShipInDataBase(databaseConnection, mmsiShipToSearch)) {
                    throw new SQLException("Ship must exist to save a position with its mmsi");
                }
                PositionsDB positionsStore = new PositionsDB();
                positionsStore.save(databaseConnection, positionToSave);
            }catch (SQLException ex) {
                Logger.getLogger(ShipStore.class.getName())
                        .log(Level.SEVERE, ex.getMessage(), ex);
                databaseConnection.registerError(ex);
                returnValue = false;
            }
        }
        return returnValue;
    }

    private void saveNewShipOnDatabase(Ship ship, DatabaseConnection databaseConnection) throws SQLException {
        if(isShipInDataBase(databaseConnection, ship)){
            updateShipOnDatabase(databaseConnection, ship);
        }else{
            insertShipOnDataBase(databaseConnection, ship);
        }
    }

    private void saveShipPositions(Ship ship, DatabaseConnection databaseConnection) throws SQLException {
        if(ship.getPositionsBST().size() > 0) {
            PositionsDB positionsStore = new PositionsDB();
            if(!isShipInDataBase(databaseConnection, ship)) {
                throw new SQLException("Ship must exist to save a position with its mmsi");
            }
            for (ShipPosition shipPosition : ship.getPositionsBST().inOrder()) {
                if (!positionsStore.save(databaseConnection, shipPosition)) {
                    throw databaseConnection.getLastError();
                }
            }
        }
    }

    private void insertShipOnDataBase(DatabaseConnection databaseConnection, Ship ship) throws SQLException {
        Connection connection = databaseConnection.getConnection();
        String sqlCommand =
                "insert into ship(mmsi, vesselTypeId, imo, callSign, shipName, currentCapacity, draft, length, width) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try(PreparedStatement saveShipStatement =
                connection.prepareStatement(
                        sqlCommand)) {
            saveShipStatement.setInt(1, ship.getMMSI());
            saveShipStatement.setInt(2, ship.getVesselTypeID());
            saveShipStatement.setString(3, ship.getIMO());
            saveShipStatement.setString(4, ship.getCallSign());
            saveShipStatement.setString(5, ship.getVesselName());
            saveShipStatement.setString(6, ship.getCargo());
            saveShipStatement.setDouble(7, ship.getDraft());
            saveShipStatement.setInt(8, ship.getLength());
            saveShipStatement.setInt(9, ship.getWidth());
            System.out.println(ship);
            saveShipStatement.executeUpdate();
        }
    }

    private void updateShipOnDatabase(DatabaseConnection databaseConnection, Ship ship) throws SQLException {
        String sqlCommand =
                "update ship set mmsi = ?, vesseltypeid = ? imo = ? callsign = ? " +
                        "shipname = ? currentcapacity = ? draft = ? where mmsi = ?";

        executeShipStatement(databaseConnection, ship,
                sqlCommand);
    }

    private void executeShipStatement(
            DatabaseConnection databaseConnection,
            Ship ship, String sqlCommand) throws SQLException {
        Connection connection = databaseConnection.getConnection();

        try(PreparedStatement saveShipStatement =
                connection.prepareStatement(
                        sqlCommand)) {
            saveShipStatement.setInt(1, ship.getMMSI());
            saveShipStatement.setInt(2, ship.getVesselTypeID());
            saveShipStatement.setString(3, ship.getIMO());
            saveShipStatement.setString(4, ship.getCallSign());
            saveShipStatement.setString(5, ship.getVesselName());
            saveShipStatement.setString(6, ship.getCargo());
            saveShipStatement.setDouble(7, ship.getDraft());
            saveShipStatement.setInt(8, ship.getMMSI());
            saveShipStatement.executeUpdate();
        }
    }

    private boolean isShipInDataBase(DatabaseConnection databaseConnection,
                                     Ship ship) throws SQLException {
        Connection connection = databaseConnection.getConnection();

        boolean isShipInDataBase = false;

        String sqlCommand = "select * from ship where mmsi = ?";

        try(PreparedStatement getShipPreparedStatement =
                connection.prepareStatement(sqlCommand)) {
            getShipPreparedStatement.setInt(1, ship.getMMSI());

            try (ResultSet shipResultSet = getShipPreparedStatement.executeQuery()) {

                if (shipResultSet.next()) {
                    // if client already exists in the database
                    isShipInDataBase = true;
                } else {
                    // if client does not exist in the database
                    isShipInDataBase = false;
                }
            }
        }
        return isShipInDataBase;
    }

    @Override
    public boolean delete(DatabaseConnection databaseConnection, Object object) {
        boolean returnValue = false;
        Connection connection = databaseConnection.getConnection();
        Ship ship = (Ship) object;

        try {
            String sqlCommand;
            sqlCommand = "delete from ship where mmsi = ?";
            try (PreparedStatement deleteClienteAddressesPreparedStatement = connection.prepareStatement(
                    sqlCommand)) {
                deleteClienteAddressesPreparedStatement.setInt(1,
                        ship.getMMSI());
                deleteClienteAddressesPreparedStatement.executeUpdate();
            }

            //dont need to delete positions because table has ON DELETE CASCADE
            returnValue = true;

        } catch (SQLException exception) {
            Logger.getLogger(ShipStore.class.getName())
                    .log(Level.SEVERE, null, exception);
            databaseConnection
                    .registerError(exception);
            returnValue = false;
        }

        return returnValue;
    }
}
