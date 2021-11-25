package lapr.project.controller;

import lapr.project.domain.model.Company;
import lapr.project.domain.model.Port;
import lapr.project.domain.model.Ship;
import lapr.project.domain.store.PortStore;
import lapr.project.domain.store.ShipStore;

import java.util.Date;
import java.util.List;

public class NearestPortController {
    private Company company;
    private ShipStore shipStore;
    private PortStore portStore;

    public NearestPortController() {
        company = App.getInstance().getCompany();
        shipStore = company.getShipStore();
        portStore = company.getPortStore();
    }

    public NearestPortController(Company company) {
        this.company = company;
        shipStore = company.getShipStore();
        portStore = company.getPortStore();
    }

    public Ship getShipByCallSign(String callSign) {
        return shipStore.getShipByAnyCode(callSign);
    }

    public List<Double> getShipCoordinates(Ship ship, Date dateTime) {
        return ship.getPositionsBST().getPosInDateTime(dateTime);
    }

    public Port findClosestPort(String callSign, Date dateTime) {
        Ship ship = getShipByCallSign(callSign);
        List<Double> coordinates = getShipCoordinates(ship, dateTime);

        return portStore.findClosestPort(coordinates);
    }
}
