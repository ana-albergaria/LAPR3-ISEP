package lapr.project.controller;

import lapr.project.domain.model.Company;
import lapr.project.domain.model.Ship;
import lapr.project.dto.ShipsFileDTO;

public class CreateShipController {

    /**
     * Company instance of the session.
     */
    private final Company company;

    /**
     * Ship to be created by the controller.
     */
    private Ship ship;

    public CreateShipController(){
        this(App.getInstance().getCompany());
    }

    public CreateShipController(Company company){
        this.company=company;
        this.ship=null;
    }

    public boolean createShip(ShipsFileDTO shipsFileDTO){

        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean saveShip(){
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
