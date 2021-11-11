package lapr.project.controller;

import lapr.project.domain.model.Company;
import lapr.project.domain.model.Ship;
import lapr.project.domain.store.ShipStore;

public class SearchShipController {

    /**
     * The company associated to the Controller.
     */
    private final Company company;

    /**
     * empty constructor for the  Class
     */
    public SearchShipController(){
        this.company= App.getInstance().getCompany();
    }
    /**
     * empty constructor for the  Class
     */
    public SearchShipController(Company comp){
        this.company= comp;
    }

    public Ship getShipInfoByAnyCode(String code){
        ShipStore shipStore = company.getShipStore();
        return shipStore.getShipByAnyCode(code);
    }

}
