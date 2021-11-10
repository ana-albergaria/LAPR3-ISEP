package lapr.project.controller;

import lapr.project.domain.BST.PositionsBST;
import lapr.project.domain.model.Ship;
import lapr.project.domain.model.ShipPosition;
import lapr.project.dto.PositionDTO;
import lapr.project.dto.ShipsFileDTO;
import lapr.project.domain.model.Company;
import lapr.project.dto.VesselTypeDTO;

import java.util.Date;

public class ImportShipsController {

    /**
     * Company instance of the session.
     */
    private final Company company;

    private Ship ship;

    private ShipPosition shipPosition;

    //private VesselType vesselType;

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
        this.ship=null;
        this.shipPosition=null;
    }

    public boolean registerPosition(Date baseDateTime, double lat, double lon, double sog, double cog, int heading, String transcieverClass){
        PositionsBST positionsBST = this.ship.getPositionsBST();
        this.shipPosition =  new ShipPosition(this.ship.getMMSI(),baseDateTime,lat,lon,sog,cog,heading,transcieverClass);
        return positionsBST.lookForPosition(shipPosition);
    }

    public boolean registerPosition(PositionDTO positionDTO){
        PositionsBST positionsBST = this.ship.getPositionsBST();
        //como é que vou ir buscar a bst se o ship nao esta criado??
        //tenho de o criar para ele ter positions bst a partir do mmsi
        //se ele já existe eu nao o crio, só o vou buscar à ship bst pelo mmsi
        this.shipPosition =  new ShipPosition(this.ship.getMMSI(), positionDTO.getBaseDateTime(), positionDTO.getLat(), positionDTO.getLon(),
                positionDTO.getSog(), positionDTO.getCog(), positionDTO.getHeading(), positionDTO.getTranscieverClass());
        return positionsBST.lookForPosition(shipPosition);
    }

    public boolean savePosition(){
        PositionsBST positionsBST = this.ship.getPositionsBST();
        return positionsBST.savePosition(shipPosition);
    }

    public boolean createShip(ShipsFileDTO shipsFileDTO){
        /*this.ship = new Ship(null,null,shipsFileDTO.getMmsi(),shipsFileDTO.getVesselName(),shipsFileDTO.getImo(),shipsFileDTO.getCallSign());
        return true;*/
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean saveShip(){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean importShipFromFile(ShipsFileDTO shipsFileDTO) {
        /*if (this.company.getBstShip().getShipByMmsiCode(shipsFileDTO.getMmsi())==null){ //nao existe ship
            createShip(shipsFileDTO);
            //boolean existsVesselType = registerVesselType(shipsFileDTO.getVesselTypeDTO());
            //if(existsVesselType)
                //saveVesselType();
        } else {
            //ir buscar o ship pelo mmsi
            this.ship=this.company.getBstShip().getShipByMmsiCode(shipsFileDTO.getMmsi());
        }
        boolean existsPosition = registerPosition(shipsFileDTO.getPositionDTO());
        if (existsPosition)
            savePosition();
        return saveShip();*/
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
