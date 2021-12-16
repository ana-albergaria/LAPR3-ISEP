package lapr.project.domain.model;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Country {
    /**
     * The continent of the country
     */
    private final String continent;
    /**
     * The name of the country
     */
    private final String name;
    /**
     * The capital of the country
     */
    private final Capital capital;
    /**
     * The borders of the country
     */
    private final List<String> borders;

    /**
     * Constructs an instance of Country receiving continent, alpha2code, alpha3code,
     * name, population, capital, latitude, longitude
     *
     * @param continent the continent
     * @param name the name
     * @param population the population
     * @param capital the capital
     */
    public Country(String continent,
                   String name,
                   Capital capital,
                   List<String> borders) {
        checkContinent(continent);
        checkName(name);
        checkCapital(capital);
        checkBorders(borders);
        this.continent = continent;
        this.name = name;
        this.capital = capital;
        this.borders = borders;
    }

    /**
     * Checks if the Continent of the Country is correct, and if not throws an error message.
     * @param continent the continent
     */
    public void checkContinent(String continent){
        if(Objects.isNull(continent)){
            throw new IllegalArgumentException("Continent name cannot be null.");
        }
    }

    /**
     * Checks if the Alpha2-Code is correct, and if not throws an error message.
     * @param alpha2Code the alpha2-code
     */
    public void checkAlpha2Code(String alpha2Code){
        if(Objects.isNull(alpha2Code)){
            throw new IllegalArgumentException("Alpha2-Code cannot be null.");
        }
    }

    /**
     * Checks if the Alpha3-Code is correct, and if not throws an error message.
     * @param alpha3Code the alpha3-code
     */
    public void checkAlpha3Code(String alpha3Code){
        if(Objects.isNull(alpha3Code)){
            throw new IllegalArgumentException("Alpha3-Code cannot be null.");
        }
    }

    /**
     * Checks if the name of the Country is correct, and if not throws an error message.
     * @param name the name
     */
    public void checkName(String name){
        if(Objects.isNull(name)){
            throw new IllegalArgumentException("Name cannot be null.");
        }
    }

    /**
     * Checks if the population of the Country is correct, and if not throws an error message.
     * @param population the population
     */
    public void checkPopulation(double population){
        if(population < 0){
            throw new IllegalArgumentException("Population cannot be below 0");
        }
    }

    /**
     * Checks if the the Capital is correct, and if not throws an error message.
     * @param capital capital
     */
    public void checkCapital(Capital capital){
        if(Objects.isNull(capital)){
            throw new IllegalArgumentException("Capital cannot be null.");
        }
    }

    /**
     * Checks if the latitude is correct, and if not throws an error message.
     * @param latitude the latitude
     */
    private void checkLatitude(double latitude){
        if (latitude<-90 || (latitude>90 && latitude!=91)){
            throw new IllegalArgumentException("Latitude must be between -90 and 90. It might also be 91 in case of being unavailable.");
        }
    }

    /**
     * Checks if the longitude is correct, and if not throws an error message.
     * @param longitude the longitude
     */
    private void checkLongitude(double longitude){
        if (longitude<-180 || (longitude>180 && longitude!=181)){
            throw new IllegalArgumentException("Longitude must be between -180 and 180. It might also be 181 in case of being unavailable.");
        }
    }

    /**
     * Checks if the borders of the country is correct, and if not throws an error message.
     * @param borders
     */
    private void checkBorders(List<String> borders){
        if(borders.isEmpty()){
            throw new IllegalArgumentException("A country must have borders.");
        }
    }



}
