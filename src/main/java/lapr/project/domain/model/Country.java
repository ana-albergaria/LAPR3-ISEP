package lapr.project.domain.model;

import java.util.Objects;

public class Country {
    /**
     * The continent of the country
     */
    private final String continent;
    /**
     * The Alpha2-Code of the country
     */
    private final String alpha2Code;
    /**
     * The Alpha3-Code of the country
     */
    private final String alpha3Code;
    /**
     * The name of the country
     */
    private final String name;
    /**
     * The population of the country
     */
    private final double population;
    /**
     * The capital of the country
     */
    private final Capital capital;
    /**
     * The latitude of the country
     */
    private final double latitude;
    /**
     * The longitude of the country
     */
    private final double longitude;

    /**
     * Constructs an instance of Country receiving continent, alpha2code, alpha3code,
     * name, population, capital, latitude, longitude
     *
     * @param continent the continent
     * @param alpha2Code the Alpha2-Code
     * @param alpha3Code the Alpha3-Code
     * @param name the name
     * @param population the population
     * @param capital the capital
     * @param latitude the latitude
     * @param longitude the longitude
     */
    public Country(String continent,
                   String alpha2Code,
                   String alpha3Code,
                   String name,
                   double population,
                   Capital capital,
                   double latitude,
                   double longitude) {
        checkContinent(continent);
        checkAlpha2Code(alpha2Code);
        checkAlpha3Code(alpha3Code);
        checkName(name);
        checkPopulation(population);
        checkCapital(capital);
        checkLatitude(latitude);
        checkLongitude(longitude);
        this.continent = continent;
        this.alpha2Code = alpha2Code;
        this.alpha3Code = alpha3Code;
        this.name = name;
        this.population = population;
        this.capital = capital;
        this.latitude = latitude;
        this.longitude = longitude;
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



}
