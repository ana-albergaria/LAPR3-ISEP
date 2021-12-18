package lapr.project.domain.store;

import lapr.project.domain.model.Capital;
import lapr.project.domain.model.Country;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CountryStoreTest {

    CountryStore cs1;
    Country c1;
    Country c2;
    Country c3;
    Country c4;

    @BeforeEach
    void setUp(){
        cs1 = new CountryStore();
        List<String> borders = new ArrayList<>();
        borders.add("Country 1");
        borders.add("country 2");
        borders.add("country 3");
        Capital br = new Capital("Brasilia", 12.3, 12.3, "Brazil");
        Capital pt = new Capital("Lisbon", 32.2, 31.2, "Portugal");
        Capital eng = new Capital("London", 10.2, 14.2, "England");
        Capital fr = new Capital("Paris", 4.5, 12.2, "France");
        c1 = new Country("America", "Brazil", br, borders);
        c2 = new Country("Europe", "Portugal", pt, borders);
        c3 = new Country("Europe", "England", eng, borders);
        c4 = new Country("Europe", "France", fr, borders);
    }

    @Test
    void importCountriesList() {
        List<Country> countryList = new ArrayList<>();
        countryList.add(c1);
        countryList.add(c2);
        countryList.add(c3);
        countryList.add(c4);

        cs1.importCountriesList(countryList);
        assertEquals(cs1.getCountriesList().size(), 4, "Expected to have four countries");

        cs1.importCountriesList(countryList);
        assertEquals(cs1.getCountriesList().size(), 4, "Should remain the same size since there are no new countries to be added");
    }


    @Test
    void validateCountryTrue() {
        List<Country> countryList = new ArrayList<>();
        countryList.add(c1);
        countryList.add(c2);
        countryList.add(c3);
        cs1.importCountriesList(countryList);

        assertTrue(cs1.validateCountry(c4), "Country should pass validation");
    }

    @Test
    void validateCountryFalse() {
        List<Country> countryList = new ArrayList<>();
        countryList.add(c1);
        countryList.add(c2);
        countryList.add(c3);
        cs1.importCountriesList(countryList);

        assertFalse(cs1.validateCountry(c3), "Country shouldn't pass validation");

    }

    @Test
    void getCountriesList() {
        List<Country> countryList = new ArrayList<>();
        countryList.add(c1);
        countryList.add(c2);
        countryList.add(c3);
        countryList.add(c4);

        cs1.importCountriesList(countryList);

        List<Country> getCountries = cs1.getCountriesList();
        for (int i = 0; i<countryList.size(); i++){
            assertEquals(countryList.get(i), getCountries.get(i));
        }
    }
}