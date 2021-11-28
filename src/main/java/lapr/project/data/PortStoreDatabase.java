package lapr.project.data;

import lapr.project.domain.model.Port;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PortStoreDatabase implements Persistable {
    @Override
    public boolean save(DatabaseConnection databaseConnection, Object object) {
        Connection connection = databaseConnection.getConnection();
        Port port = (Port) object;

        String sqlCommand = "select * from port where port_id = ?";
        boolean returnValue = false;
        try (PreparedStatement getShipPositionStatement = connection.prepareStatement(
                sqlCommand)) {
            getShipPositionStatement.setInt(1, port.getIdentification());

            try (ResultSet addressesResultSet = getShipPositionStatement.executeQuery()) {
                if (addressesResultSet.next()) {
                    return false;
                } else {
                    createNewPort(databaseConnection, port);
                    returnValue = true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PortStoreDatabase.class.getName())
                    .log(Level.SEVERE, null, ex);
            databaseConnection.registerError(ex);
            returnValue = false;
        }
        return returnValue;
    }

    private void createNewPort (DatabaseConnection databaseConnection, Port port) throws SQLException {
        Connection connection = databaseConnection.getConnection();
        boolean status = true;
        if(!existsContinent(databaseConnection, port)){
            if(createOrUpdateContinent(databaseConnection, port)){
                if(!createOrUpdateCountry(databaseConnection, port)){
                    status = false;
                };
            }
        }
        if(!existsCountry(databaseConnection, port)){
            if(!createOrUpdateCountry(databaseConnection, port)){
                status = false;
            };
        }
        if(status){
            if(createLocation(databaseConnection, port)){
                int location_id = getLocationId(databaseConnection, port);
                System.out.println(location_id);
                String sqlCommand = "insert into port(port_id, name, location_id) values(?, ?, ?)";
                try (PreparedStatement saveShipUpdate = connection.prepareStatement(
                        sqlCommand)) {
                    saveShipUpdate.setInt(1, port.getIdentification());
                    saveShipUpdate.setString(2, port.getName());
                    saveShipUpdate.setInt(3, location_id);
                    saveShipUpdate.executeUpdate();
                }

                catch (SQLException ex) {
                    Logger.getLogger(PortStoreDatabase.class.getName())
                            .log(Level.SEVERE, null, ex);
                    databaseConnection.registerError(ex);
                }

            }

        }else{
            throw new SQLException("Failed to create continent and or country"+ port);
        }

    }

    private boolean createLocation(DatabaseConnection databaseConnection, Port port){
        Connection connection = databaseConnection.getConnection();
        String sqlCommand = "insert into placeLocation(locationLatitude, locationLongitude, country) values(?, ?, ?)";
        boolean returnValue = false;
        try (PreparedStatement saveShipUpdate = connection.prepareStatement(
                sqlCommand)) {
            saveShipUpdate.setDouble(1,port.getLat());
            saveShipUpdate.setDouble(2,port.getLon());
            saveShipUpdate.setString(3,port.getCountry());
            saveShipUpdate.executeUpdate();
            returnValue = true;
        }

        catch (SQLException ex) {
            Logger.getLogger(PortStoreDatabase.class.getName())
                    .log(Level.SEVERE, null, ex);
            databaseConnection.registerError(ex);
            returnValue = false;
        }
        return returnValue;
    }
    private int getLocationId(DatabaseConnection databaseConnection, Port port){
        Connection connection = databaseConnection.getConnection();
        int returnVal = -1;
        String sqlCommand = "select * from placeLocation where (locationlatitude = ? AND locationlongitude = ?)";
        try (PreparedStatement getShipPositionStatement = connection.prepareStatement(
                sqlCommand)) {
            getShipPositionStatement.setDouble(1, port.getLat());
            getShipPositionStatement.setDouble(2, port.getLon());
            try (ResultSet addressesResultSet = getShipPositionStatement.executeQuery()) {
                if (addressesResultSet.next())  {
                    returnVal= addressesResultSet.getInt("location_id");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PortStoreDatabase.class.getName())
                    .log(Level.SEVERE, null, ex);
            databaseConnection.registerError(ex);
        }
        return returnVal;
    }

    private int getContinentId(DatabaseConnection databaseConnection, String continentName){
        Connection connection = databaseConnection.getConnection();
        int returnVal = -1;
        String sqlCommand = "select * from continent where continent_name = ?";
        try (PreparedStatement getShipPositionStatement = connection.prepareStatement(
                sqlCommand)) {
            getShipPositionStatement.setString(1, continentName);
            try (ResultSet addressesResultSet = getShipPositionStatement.executeQuery()) {
                if (addressesResultSet.next())  {
                  returnVal= addressesResultSet.getInt("continent_id");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PortStoreDatabase.class.getName())
                    .log(Level.SEVERE, null, ex);
            databaseConnection.registerError(ex);
        }
        return returnVal;
    }
    private boolean createOrUpdateCountry(DatabaseConnection databaseConnection, Port port){
        Connection connection = databaseConnection.getConnection();
        boolean returnVal = false;
        String sqlCommand = "select * from continent where continent_name = ?";
        int continentId = getContinentId(databaseConnection, port.getContinent());
            if (continentId != -1)  {
                    sqlCommand =
                            "insert into country(country_name, continent_id) values (?, ?)";
                }

            try (PreparedStatement saveShipUpdate = connection.prepareStatement(
                    sqlCommand)) {
                saveShipUpdate.setString(1, port.getCountry());
                saveShipUpdate.setInt(2, continentId);
                saveShipUpdate.executeUpdate();
                returnVal = true;
            }
            catch (SQLException ex) {
            Logger.getLogger(PortStoreDatabase.class.getName())
                    .log(Level.SEVERE, null, ex);
            databaseConnection.registerError(ex);
            return returnVal;
        }
        return returnVal;
    }

    private boolean createOrUpdateContinent(DatabaseConnection databaseConnection, Port port){
        Connection connection = databaseConnection.getConnection();
        boolean returnVal = false;
        String sqlCommand = "select * from continent where continent_name = ?";
        try (PreparedStatement getShipPositionStatement = connection.prepareStatement(
                sqlCommand)) {
            getShipPositionStatement.setString(1, port.getContinent());

            try (ResultSet addressesResultSet = getShipPositionStatement.executeQuery()) {
                if (!addressesResultSet.next())  {
                    sqlCommand =
                            "insert into continent(continent_name) values (?)";
                }

                try (PreparedStatement saveShipUpdate = connection.prepareStatement(
                        sqlCommand)) {
                    saveShipUpdate.setString(1, port.getContinent());
                    saveShipUpdate.executeUpdate();
                    returnVal = true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PortStoreDatabase.class.getName())
                    .log(Level.SEVERE, null, ex);
            databaseConnection.registerError(ex);
            return returnVal;
        }
        return returnVal;
    }

    private boolean existsContinent(DatabaseConnection databaseConnection, Port port){
        Connection connection = databaseConnection.getConnection();
        String sqlCommand = "select * from continent where continent_name = ?";
        boolean returnValue = false;
        try (PreparedStatement getShipPositionStatement = connection.prepareStatement(
                sqlCommand)) {
            getShipPositionStatement.setString(1, port.getContinent());

            try (ResultSet continentResultSet = getShipPositionStatement.executeQuery()) {
                if (continentResultSet.next()) {
                    returnValue = true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PortStoreDatabase.class.getName())
                    .log(Level.SEVERE, null, ex);
            databaseConnection.registerError(ex);
            returnValue = false;
        }
        return returnValue;
    }

    private boolean existsCountry(DatabaseConnection databaseConnection, Port port){
        Connection connection = databaseConnection.getConnection();
        String sqlCommand = "select * from country where country_name = ?";
        boolean returnValue = false;
        try (PreparedStatement getShipPositionStatement = connection.prepareStatement(
                sqlCommand)) {
            getShipPositionStatement.setString(1, port.getCountry());

            try (ResultSet continentResultSet = getShipPositionStatement.executeQuery()) {
                if (continentResultSet.next()) {
                    returnValue = true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PortStoreDatabase.class.getName())
                    .log(Level.SEVERE, null, ex);
            databaseConnection.registerError(ex);
            returnValue = false;
        }
        return returnValue;
    }

    @Override
    public boolean delete(DatabaseConnection databaseConnection, Object object) {
        Connection conn = databaseConnection.getConnection();

        boolean returnValue = false;

        return returnValue;
    }
}
