package lapr.project.domain.model;

import java.util.Objects;

public class Capital extends Location {
    private String name;
    private double latitude;
    private double longitude;
    private String countryName;

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
}
