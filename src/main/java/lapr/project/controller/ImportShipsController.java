package lapr.project.controller;

import lapr.project.dto.ShipsFileDTO;
import lapr.project.model.Company;

public class ImportShipsController {

    /**
     * Company instance of the session.
     */
    private final Company company;

    private CreateShipController createShipController;

    private RegisterVesselTypeController registerVesselTypeController;

    //+ construtor vazio para a instancia da company

    /**
     * Constructor receiving the company as an argument.
     *
     * @param company instance of company to be used.
     */
    public ImportShipsController(Company company){
        this.company=company;
        this.createShipController=new CreateShipController();
        this.registerVesselTypeController=new RegisterVesselTypeController();
    }

    public boolean importShipFromFile(ShipsFileDTO shipsFileDTO) { //colocar as exceptions?
        boolean existsVesselType = registerVesselTypeController.registerVesselType(shipsFileDTO.getVesselTypeDTO());
        if(existsVesselType)
            registerVesselTypeController.saveVesselType();
        createShipController.createShip(shipsFileDTO);
        return createShipController.saveShip();
    }

}
