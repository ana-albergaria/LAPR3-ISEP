package lapr.project.dto;

import lapr.project.domain.model.Port;

public class PortFileDTO {
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

    /**
     * constructs an instance of PortFileDTO, receiving as parameter identification, name, continent, country, latitude and longitude
     * @param identification PortFileDTO's Identification
     * @param name PortFileDTO's Name
     * @param continent PortFileDTO's Continent
     * @param country PortFileDTO's Country
     * @param lat PortFileDTO's Latitude
     * @param lon PortFileDTO's Longitude
     */
    public PortFileDTO(int identification, String name, String continent, String country, double lat, double lon) {
        this.identification=identification;
        this.name=name;
        this.continent=continent;
        this.country=country;
        this.lat = lat;
        this.lon=lon;
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

        if(this.identification == otherShip.getIdentification())
            return true;
        else
            return false;
    }

    /**
     * Method toString.
     * @return a String with the Port attributes and its values.
     */
    @Override
    public String toString() {
        return "PortFileDTO{" +
                "Identification=" + identification +
                ", Name=" + name +
                ", Continent=" + continent +
                ", Country='" + country + '\'' +
                ", Latitude='" + lat + '\'' +
                ", Longitude='" + lon +
                '}';
    }
}
