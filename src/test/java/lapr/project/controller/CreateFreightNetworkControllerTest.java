package lapr.project.controller;

import lapr.project.data.dataControllers.CreateFreightNetworkController;
import lapr.project.domain.model.Capital;
import lapr.project.domain.model.Company;
import lapr.project.domain.model.Country;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class CreateFreightNetworkControllerTest {

    @Test
    public void createFreightNetworkTest(){
        CreateFreightNetworkController createFreightNetworkController = spy(CreateFreightNetworkController.class);

        doNothing().when(createFreightNetworkController).importDataFromDatabase();

        List<Country> ptSpList = new ArrayList<>();
        Capital lisb = new Capital("Lisbon", 38.71666667, -9.133333, "Portugal", "Europe");
        List<String> borderPt = new ArrayList<>();
        borderPt.add("Spain");
        Country pt = new Country("Europe", "Portugal", lisb, borderPt);
        ptSpList.add(pt);

        Capital madrid = new Capital("Madrid", 40.4, -3.683333, "Spain", "Europe");
        List<String> borderSpain = new ArrayList<>();
        Country spain = new Country("Europe", "Spain", madrid, borderSpain);
        ptSpList.add(spain);

        App.getInstance().getCompany().getCountryStore().importCountriesList(ptSpList);

        createFreightNetworkController.createFreightNetworkFromDb();
        System.out.println(App.getInstance().getCompany().getFreightNetwork().getFreightNetwork());
    }
}