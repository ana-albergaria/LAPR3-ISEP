package lapr.project.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CountryTest {

    private String continent1, continent2;

    private final String [] alpha2Codes = {"CY", "MT", "GR", "PT"};

    private final String [] alpha3Codes = {"CYP", "MLT", "GRC", "PRT"};

    private String [] names = {"Cyprus", "Malta", "Greece", "Portugal"};

    private final double [] population = {0.85, 0.44, 10.76, 10.31};

    private Capital capital1, capital2, capital3, capital4;

    private final double [] lats = {-30.033056, -42.033006, -55.022056, 23.008721};
    private final double [] lons = {-51.230000, -47.223056, -46.233056, 24.092123};

    private Map<String, Double> borders = new HashMap<>();

    private Country country1, country2, country3, country4;

    @BeforeEach
    public void setUp() {
        continent1 = "America";
        continent2 = "Europe";

        capital1 = new Capital("Nicosia",lats[0], lons[0], names[0]);
        capital2 = new Capital("Valletta",lats[1],lons[1], names[1]);
        capital3 = new Capital("Athens", lats[2], lons[2], names[2]);
        capital4 = new Capital("Lisbon", lats[3], lons[3], names[3]);

        borders.put("Belize", 324.0);
        borders.put("Canada", 3000.2);

        country1 = new Country(continent2,alpha2Codes[0],alpha3Codes[0],names[0],population[0],capital1,lats[0],lons[0], borders);
        country2 = new Country(continent2,alpha2Codes[1],alpha3Codes[1],names[1],population[1],capital1,lats[1],lons[1], borders);
        country3 = new Country(continent2,alpha2Codes[2],alpha3Codes[2],names[2],population[2],capital1,lats[2],lons[2], borders);
        country4 = new Country(continent2,alpha2Codes[3],alpha3Codes[3],names[3],population[3],capital1,lats[3],lons[3], borders);
    }

    /**
     * Test to ensure Continent Name cannot be null.
     */
    @Test
    public void ensureNullContinentNameNotAllowed(){
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Country(null,alpha2Codes[0],alpha3Codes[0],names[0],population[0],capital1,lats[0],lons[0], borders));
        assertEquals("Continent name cannot be null.", thrown.getMessage());
    }

    /**
     * Test to ensure alpha2-code can't be null
     */
    @Test
    public void ensureNullAlpha2CodeNotAllowed() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Country(continent2,null,alpha3Codes[0],names[0],population[0],capital1,lats[0],lons[0], borders));
        assertEquals("Alpha2-Code cannot be null.", thrown.getMessage());
    }

    /**
     * Test to ensure alpha3-code can't be null
     */
    @Test
    public void ensureNullAlpha3CodeNotAllowed() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Country(continent2,alpha2Codes[0],null,names[0],population[0],capital1,lats[0],lons[0], borders));
        assertEquals("Alpha3-Code cannot be null.", thrown.getMessage());
    }


    /**
     * Test to ensure name can't be null
     */
    @Test
    public void ensureNullNameNotAllowed() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Country(continent2,alpha2Codes[0],alpha3Codes[0],null,population[0],capital1,lats[0],lons[0], borders));
        assertEquals("Name cannot be null.", thrown.getMessage());
    }

    /**
     * Test to ensure population cannot be below 0
     */
    @Test
    public void ensurePopulationBelowZeroNotAllowed() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Country(continent2,alpha2Codes[0],alpha3Codes[0],names[0],-2,capital1,lats[0],lons[0], borders));
        assertEquals("Population cannot be below 0", thrown.getMessage());
    }

    /**
     * Test to ensure capital cannot be null
     */
    @Test
    public void ensureNullCapitalNotAllowed() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Country(continent2,alpha2Codes[0],alpha3Codes[0],names[0],population[0],null,lats[0],lons[0], borders));
        assertEquals("Capital cannot be null.", thrown.getMessage());
    }


    /**
     * Test to ensure Latitude cannot be under -90.
     */
    @Test
    public void createCountryWithLatitudeUnderMinus90() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Country(continent2,alpha2Codes[0],alpha3Codes[0],names[0],population[0],capital1,-100,lons[0], borders));
        assertEquals("Latitude must be between -90 and 90. It might also be 91 in case of being unavailable.", thrown.getMessage());
    }

    /**
     * Test to ensure latitude cannot de over 91.
     */
    @Test
    public void createCountryWithLatitudeOver91() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Country(continent2,alpha2Codes[0],alpha3Codes[0],names[0],population[0],capital1,93,lons[0], borders));
        assertEquals("Latitude must be between -90 and 90. It might also be 91 in case of being unavailable.", thrown.getMessage());
    }

    /**
     * Test to ensure latitude can have value 90.
     */
    @Test
    public void checkCreateCountryWithLatitude90() {
        new Country(continent2,alpha2Codes[0],alpha3Codes[0],names[0],population[0],capital1,90,lons[0], borders);
    }

    /**
     * Test to ensure Latitude can have value -90.
     */
    @Test
    public void checkCreateCountryWithLatitudeMinus90() {
        new Country(continent2,alpha2Codes[0],alpha3Codes[0],names[0],population[0],capital1,-90,lons[0], borders);
    }

    /**
     * Test to ensure Longitude cannot be under -180.
     */
    @Test
    public void createCountryWithLongitudeUnderMinus180() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Country(continent2,alpha2Codes[0],alpha3Codes[0],names[0],population[0],capital1,lats[0],-200, borders));
        assertEquals("Longitude must be between -180 and 180. It might also be 181 in case of being unavailable.", thrown.getMessage());
    }

    /**
     * Test to ensure longitude cannot be over 181.
     */
    @Test
    public void createCountryWithLongitudeOver181() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Country(continent2,alpha2Codes[0],alpha3Codes[0],names[0],population[0],capital1,lats[0],200, borders));
        assertEquals("Longitude must be between -180 and 180. It might also be 181 in case of being unavailable.", thrown.getMessage());
    }

    /**
     * test to ensure longitude can be 180
     */
    @Test
    public void createCountryWithLongitude180() {
        new Country(continent2,alpha2Codes[0],alpha3Codes[0],names[0],population[0],capital1,lats[0],180, borders);
    }

    /**
     * test to ensure longitude can be -180
     */
    @Test
    public void createCountryWithLongitudeMinus180() {
        new Country(continent2,alpha2Codes[0],alpha3Codes[0],names[0],population[0],capital1,lats[0],-180, borders);
    }

    /**
     * test to ensure borders cannot be empty
     */
    @Test
    public void ensureBordersNotEmpty() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Country(continent2,alpha2Codes[0],alpha3Codes[0],names[0],population[0],capital1,lats[0],lons[0], new HashMap<>()));
        assertEquals("A country must have borders.", thrown.getMessage());
    }






}