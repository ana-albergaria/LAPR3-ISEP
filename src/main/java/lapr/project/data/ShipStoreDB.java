package lapr.project.data;

import lapr.project.domain.model.Ship;
import lapr.project.domain.model.ShipPosition;
import lapr.project.domain.model.ShipSortMmsi;
import lapr.project.domain.store.ShipStore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShipStoreDB implements Persistable{
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
