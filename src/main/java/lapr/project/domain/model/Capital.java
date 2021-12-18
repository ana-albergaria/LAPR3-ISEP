package lapr.project.domain.model;

import java.util.Objects;

public class Capital extends Location {
    /**
     * The capital name
     */
    private final String name;
    /**
     * The latitude
     */
    private double latitude;
    /**
     * The longitude
     */
    private double longitude;
    /**
     * The country name
     */
    private String countryName;

    /**
     * Constructs an instance of Capital receiving the following parameters:
     *
     * @param name name
     * @param latitude latitude
     * @param longitude longitude
     * @param countryName the country name
     */
    public Capital(String name, double latitude, double longitude, String countryName) {
        super(latitude, longitude, countryName);
        checkName(name);
        this.name = name;
    }

    /**
     * Checks if the name of the Capital is correct, and if not throws an error message.
     * @param name of the capital
     */
    public void checkName(String name){
        if(Objects.isNull(name)){
            throw new IllegalArgumentException("Name cannot be null.");
        }
    }

    /**
     * Gets the Capital name
     * @return capital name
     */
    public String getName() {
        return name;
    }
}
