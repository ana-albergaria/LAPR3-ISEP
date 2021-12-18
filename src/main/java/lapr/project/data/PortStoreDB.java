package lapr.project.data;

import lapr.project.domain.model.Capital;
import lapr.project.domain.model.Country;
import lapr.project.domain.model.Port;

import javax.xml.crypto.Data;
import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PortStoreDB implements Persistable {

    public List<Port> getExistentPorts(DatabaseConnection databaseConnection){
        Connection connection = databaseConnection.getConnection();
        CountryStoreDb countryStoreDb = new CountryStoreDb();
        List<Port> ports = new ArrayList<>();
        String sqlCommand = "select * from port";
        String name, countryName, continentName;
        int locationId, portId;
        double latitude, longitude;
        try (PreparedStatement getAllPorts = connection.prepareStatement(
                sqlCommand)) {
            try (ResultSet portsResult = getAllPorts.executeQuery()) {
                while(portsResult.next()){
                    name = portsResult.getNString(2);
                    portId = portsResult.getInt(1);
                    locationId = portsResult.getInt(3);
                    latitude = getLocationLatitude(databaseConnection, locationId);
                    longitude = getLocationLongitude(databaseConnection, locationId);
                    countryName = getLocationCountry(databaseConnection, locationId);
                    continentName = countryStoreDb.getCountryContinentByName(databaseConnection, countryName);
                    Map<Integer, Double> map =getSeaDists(databaseConnection, portId);
                    ports.add(new Port(portId, name, continentName, countryName, latitude, longitude, map));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PortStoreDB.class.getName())
                    .log(Level.SEVERE, null, ex);
            databaseConnection.registerError(ex);
        }
        return ports;
    }

    private Map<Integer, Double> getSeaDists(DatabaseConnection databaseConnection, int fromPortId){
        Connection connection = databaseConnection.getConnection();
        String sqlCommand = "select * from sea_dist where from_port = ?";
        Map<Integer, Double> seadists = new HashMap<>();
        try (PreparedStatement getPortDists = connection.prepareStatement(
                sqlCommand)) {
            getPortDists.setInt(1,fromPortId);
            try (ResultSet seadistsResult = getPortDists.executeQuery()) {
                while(seadistsResult.next()){
                    int toPortId = seadistsResult.getInt(2);
                    double distance = seadistsResult.getDouble(3);
                    seadists.put(toPortId, distance);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PortStoreDB.class.getName())
                    .log(Level.SEVERE, null, ex);
            databaseConnection.registerError(ex);
        }
        return seadists;
    }

    private double getLocationLatitude(DatabaseConnection databaseConnection, int id) throws SQLException {
        Connection connection = databaseConnection.getConnection();
        double latitude = 0;
        String sqlCommand = "select * from placelocation where location_id = ?";
        try (PreparedStatement getLocation = connection.prepareStatement(
                sqlCommand)) {
            getLocation.setInt(1, id);
            try (ResultSet locationResult = getLocation.executeQuery()) {
                if(locationResult.next()){
                    latitude = locationResult.getDouble(2);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PortStoreDB.class.getName())
                    .log(Level.SEVERE, null, ex);
            databaseConnection.registerError(ex);
        }

        return  latitude;
    }
    private double getLocationLongitude(DatabaseConnection databaseConnection, int id) throws SQLException {
        Connection connection = databaseConnection.getConnection();
        double longitude = 0;
        String sqlCommand = "select * from placelocation where location_id = ?";
        try (PreparedStatement getLocation = connection.prepareStatement(
                sqlCommand)) {
            getLocation.setInt(1, id);
            try (ResultSet locationResult = getLocation.executeQuery()) {
                if(locationResult.next()){
                    longitude = locationResult.getDouble(3);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PortStoreDB.class.getName())
                    .log(Level.SEVERE, null, ex);
            databaseConnection.registerError(ex);
        }

        return  longitude;
    }

    private String getLocationCountry(DatabaseConnection databaseConnection, int id) throws SQLException {
        Connection connection = databaseConnection.getConnection();
        String sqlCommand = "select * from placelocation where location_id = ?";
        try (PreparedStatement getLocation = connection.prepareStatement(
                sqlCommand)) {
            getLocation.setInt(1, id);
            try (ResultSet locationResult = getLocation.executeQuery()) {
                if(locationResult.next()){
                    return locationResult.getNString(4).trim();
                }else{
                    return null;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PortStoreDB.class.getName())
                    .log(Level.SEVERE, null, ex);
            databaseConnection.registerError(ex);
            return null;
        }
    }


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
            Logger.getLogger(PortStoreDB.class.getName())
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
                    Logger.getLogger(PortStoreDB.class.getName())
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
            Logger.getLogger(PortStoreDB.class.getName())
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
            Logger.getLogger(PortStoreDB.class.getName())
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
            Logger.getLogger(PortStoreDB.class.getName())
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
            Logger.getLogger(PortStoreDB.class.getName())
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
            Logger.getLogger(PortStoreDB.class.getName())
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
            Logger.getLogger(PortStoreDB.class.getName())
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
            Logger.getLogger(PortStoreDB.class.getName())
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
