package lapr.project.data.dataControllers;

import lapr.project.controller.App;
import lapr.project.data.CountryStoreDb;
import lapr.project.data.PortStoreDB;
import lapr.project.domain.dataStructures.FreightNetwork;
import lapr.project.domain.dataStructures.Ports2DTree;
import lapr.project.domain.model.Company;
import lapr.project.domain.model.Country;
import lapr.project.domain.model.Port;
import lapr.project.domain.store.CountryStore;
import lapr.project.domain.store.PortStore;
import lapr.project.utils.DistanceUtils;

import java.util.List;
import java.util.Map;

public class CreateFreightNetworkController {

    /**
     * Company instance of the session.
     */
    private Company company;

    private PortStore portStore;

    private CountryStore countryStore;
    /**
     * Constructor for the controller.
     */
    public CreateFreightNetworkController() {
        this(App.getInstance().getCompany());
    }

    /**
     * Constructor receiving the company as an argument.
     *
     * @param company instance of company to be used.
     */
    public CreateFreightNetworkController(Company company) {
        this.company=company;
    }


    public boolean createFreightNetworkFromDb(){
        this.countryStore = this.company.getCountryStore();
        this.portStore = this.company.getPortStore();
        importDataFromDatabase();

        FreightNetwork freightNetwork = this.company.getFreightNetwork();
        List<Country> countries = countryStore.getCountriesList();
        List<Port> ports = portStore.getPortsList();

        for(Country country : countries){
            freightNetwork.addLocation(country.getCapital());
        }
        for (Port port : ports){
            freightNetwork.addLocation(port);
        }

        for(Country country : countries){
            Map<Country, Double> borders = countryStore.getBordersDistance(country);
            for (Country toCountry : borders.keySet()){
                freightNetwork.addDistance(country.getCapital(), toCountry.getCapital(), borders.get(toCountry));
            }

            List<Port> countryPorts = portStore.getPortsByCountry(country.getName());
            if(countryPorts.size() > 0) {
                Port closestPort = countryStore.getClosestPortFromCapital(country, countryPorts);

                double capitalPortDistance = DistanceUtils.distanceBetweenInKm(country.getCapital().getLatitude(), closestPort.getLat(),
                        country.getCapital().getLongitude(), closestPort.getLon());

                freightNetwork.addDistance(country.getCapital(), closestPort, capitalPortDistance);
            }
        }

        for(Port port : ports){
            for(int portId : port.getToPortsDistance().keySet()){
                Port toPort = portStore.getPortById(portId);
                freightNetwork.addDistance(port, toPort, port.getToPortsDistance().get(portId));
            }
        }

        return true;
    }

    private void importDataFromDatabase(){
        CountryStoreDb countryStoreDb = new CountryStoreDb();
        List<Country> existentCountries = countryStoreDb.getExistentCountries(App.getInstance().getConnection());

        countryStore.importCountriesList(existentCountries);

        PortStoreDB portStoreDb = new PortStoreDB();
        List<Port> existentPorts = portStoreDb.getExistentPorts(App.getInstance().getConnection());

        portStore.importPorts(existentPorts);
    }
}
