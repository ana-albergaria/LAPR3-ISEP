package lapr.project.controller;

import lapr.project.dto.ShipsFileDTO;
import lapr.project.domain.model.Company;

public class ImportShipsController {

    /**
     * Company instance of the session.
     */
    private final Company company;

    private CreateShipController createShipController;

    private RegisterPositionController registerPositionController;

    public ImportShipsController(){
        this(App.getInstance().getCompany());
    }

    /**
     * Constructor receiving the company as an argument.
     *
     * @param company instance of company to be used.
     */
    public ImportShipsController(Company company){
        this.company=company;
        this.createShipController=new CreateShipController();
        this.registerPositionController=new RegisterPositionController();
    }

    public boolean importShipFromFile(ShipsFileDTO shipsFileDTO) {
        //boolean existsVesselType = registerVesselTypeController.registerVesselType(shipsFileDTO.getVesselTypeDTO());
        //if(existsVesselType)
          //  registerVesselTypeController.saveVesselType();
        boolean existsPosition = registerPositionController.registerPosition(shipsFileDTO.getPositionDTO());
        if (existsPosition)
            registerPositionController.savePosition();
        createShipController.createShip(shipsFileDTO);
        return createShipController.saveShip();
        //throw new UnsupportedOperationException("Not supported yet.");
    }

}
