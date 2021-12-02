package lapr.project.controller;

import lapr.project.data.ShipTripStoreDB;
import lapr.project.domain.model.Company;

public class ContainersOffLoadedController {
    Company comp;
    ShipTripStoreDB store;

    public ContainersOffLoadedController() {
        this.comp = App.getInstance().getCompany();
        this.store = comp.getShipTripStoreDB();
    }

    public ContainersOffLoadedController(Company company) {
        this.comp = company;
        this.store = comp.getShipTripStoreDB();
    }

    public void getListOffloadedContainers(int mmsi) throws Exception {
        //store.getNextPortID(database, mmsi);
        throw new Exception("to be developed.");
    }
}
