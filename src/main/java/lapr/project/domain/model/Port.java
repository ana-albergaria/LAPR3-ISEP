package lapr.project.domain.model;

import java.util.Objects;

public class Port implements Comparable<Port> {
    /**
     * identification of the Port
     */
    private final int identification;

    /**
     * name of the Port
     */
    private final String name;

    /**
     * continent where Port is located
     */
    private final String continent;

    /**
     * country where the Port is located
     */
    private final String country;

    /**
     * latitude of Port's location
     */
    private final double lat;

    /**
     * longitude of Port's location
     */
    private final double lon;

    public Port(int identification, String name, String continent, String country, double lat, double lon) {
        checkPortName(name);
        checkContinentName(continent);
        checkCountryName(country);
        checkLat(lat);
        checkLon(lon);
        this.identification=identification;
        this.name=name;
        this.continent=continent;
        this.country=country;
        this.lat = lat;
        this.lon=lon;
    }

    /**
     * Checks if the Port's Name is correct, and if not throws an error message.
     * @param portName the Port's Name.
     */
    public void checkPortName(String portName){
        if(Objects.isNull(portName)){
            throw new IllegalArgumentException("Port name cannot be null.");
        }
    }

    /**
     * Checks if the Port's Continent Name is correct, and if not throws an error message.
     * @param continentName the Port's Continent Name.
     */
    public void checkContinentName(String continentName){
        if(Objects.isNull(continentName)){
            throw new IllegalArgumentException("Continent name cannot be null.");
        }
    }

    /**
     * Checks if the Port's Country Name is correct, and if not throws an error message.
     * @param countryName the Port's  Country Name.
     */
    public void checkCountryName(String countryName){
        if(Objects.isNull(countryName)){
            throw new IllegalArgumentException("Country name cannot be null.");
        }
    }

    /**
     * Checks if the Port's latitude is correct, and if not throws an error message.
     * @param lat the Port's latitude.
     */
    private void checkLat(double lat){
        if (lat<-90 || (lat>90 && lat!=91)){
            throw new IllegalArgumentException("Latitude must be between -90 and 90. It might also be 91 in case of being unavailable.");
        }
    }

    /**
     * Checks if the Port's longitude is correct, and if not throws an error message.
     * @param lon the Port's longitude.
     */
    private void checkLon(double lon){
        if (lon<-180 || (lon>180 && lon!=181)){
            throw new IllegalArgumentException("Longitude must be between -180 and 180. It might also be 181 in case of being unavailable.");
        }
    }

    /**
     * returns the Port's Identification
     * @return Port's Identification
     */
    public int getIdentification(){
        return this.identification;
    }

    /**
     * returns the Port's Name
     * @return Port's Name
     */
    public String getName(){
        return this.name;
    }

    /**
     * returns the Port's Continent
     * @return Port's Continent
     */
    public String getContinent(){
        return this.continent;
    }

    /**
     * returns the Port's Country
     * @return Port's Country
     */
    public String getCountry(){
        return this.country;
    }

    /**
     * returns the Port's Latitude
     * @return Port's Latitude
     */
    public double getLat(){
        return this.lat;
    }

    /**
     * returns the Port's Longitude
     * @return Port's Longitude
     */
    public double getLon(){
        return this.lon;
    }


    /**
     * Method toString.
     * @return a String with the Port attributes and its values.
     */
    @Override
    public String toString() {
        return "Port{" +
                "Identification=" + identification +
                ", Name=" + name +
                ", Continent=" + continent +
                ", Country='" + country + '\'' +
                ", Latitude='" + lat + '\'' +
                ", Longitude='" + lon +
                '}';
    }

    /**
     * Method equals.
     * @param otherObject the object to be compared with.
     * @return true if a Port is equal to the object in "otherObject";
     * false if a Port isn't equal to the object in "otherObject".
     */
    @Override
    public boolean equals(Object otherObject){
        if(this == otherObject)
            return true;

        if(otherObject == null || this.getClass() != otherObject.getClass())
            return false;

        Port otherShip = (Port) otherObject;

        if(this.identification == otherShip.identification)
            return true;
        else
            return false;
    }

    /**
     * Method compareTo.
     * @param o the Port to be compared with.
     * @return the difference between the two Port's Identification.
     */
    @Override
    public int compareTo(Port o) {
        return this.identification - o.identification;
    }
}
