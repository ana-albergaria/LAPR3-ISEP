package lapr.project.controller;

import lapr.project.domain.BST.PositionsBST;
import lapr.project.domain.model.Ship;
import lapr.project.domain.model.ShipPosition;
import lapr.project.dto.PositionDTO;
import lapr.project.dto.ShipsFileDTO;
import lapr.project.domain.model.Company;

import java.util.Date;

public class ImportShipsController {

    /**
     * Company instance of the session.
     */
    private final Company company;

    private Ship ship;

    private ShipPosition shipPosition;

    private PositionsBST positionsBST;

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
        this.positionsBST=null;
    }

    public boolean registerPosition(Date baseDateTime, double lat, double lon, double sog, double cog, int heading, String transcieverClass){
        PositionsBST positionsBST = this.ship.getPositionsBST();
        this.shipPosition =  new ShipPosition(this.ship.getMMSI(),baseDateTime,lat,lon,sog,cog,heading,transcieverClass);
        return positionsBST.hasPosition(shipPosition);
    }

    public boolean registerPosition(PositionDTO positionDTO){
        PositionsBST positionsBST = this.ship.getPositionsBST();
        this.shipPosition =  new ShipPosition(this.ship.getMMSI(), positionDTO.getBaseDateTime(), positionDTO.getLat(), positionDTO.getLon(),
                positionDTO.getSog(), positionDTO.getCog(), positionDTO.getHeading(), positionDTO.getTranscieverClass());
        return positionsBST.hasPosition(shipPosition);
    }

    public void savePosition(){
        this.positionsBST = this.ship.getPositionsBST();
        positionsBST.savePosition(shipPosition);
    }

    public void createShip(ShipsFileDTO shipsFileDTO){
        this.ship = new Ship(null,shipsFileDTO.getMmsi(),shipsFileDTO.getVesselName(),shipsFileDTO.getImo(),shipsFileDTO.getCallSign(),
                shipsFileDTO.getVesselType(),shipsFileDTO.getLength(),shipsFileDTO.getLength(),shipsFileDTO.getDraft(),shipsFileDTO.getCargo());
    }

    public boolean saveShip(){
        this.ship.setPositionsBST(positionsBST);
        return this.company.getBstShip().saveShip(ship);
    }

    public boolean importShipFromFile(ShipsFileDTO shipsFileDTO) {
        if (this.company.getBstShip().getShipByMmsiCode(shipsFileDTO.getMmsi())==null){ //nao existe ship logo tem de se criar
            createShip(shipsFileDTO);
        } else {
            //ir buscar o ship pelo mmsi
            this.ship=this.company.getBstShip().getShipByMmsiCode(shipsFileDTO.getMmsi());
        }
        boolean existsPosition = registerPosition(shipsFileDTO.getPositionDTO());
        if (!existsPosition)
            savePosition(); //se a posicao nao existe entao é adicionada
        return saveShip();
    }

}
