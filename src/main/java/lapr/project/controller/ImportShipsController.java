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
    private Company company;

    private Ship ship;

    private ShipPosition shipPosition;

    private PositionsBST positionsBST;

    public ImportShipsController(){
        this(App.getInstance().getCompany());
    }

    /**
     * Constructor receiving the company as an argument.
     *
     * @param companyy instance of company to be used.
     */
    public ImportShipsController(Company companyy){
        this.company=companyy;
        this.ship=null;
        this.shipPosition=null;
        this.positionsBST=new PositionsBST();
    }

    /*public boolean registerPosition(Date baseDateTime, double lat, double lon, double sog, double cog, int heading, String transcieverClass){
        PositionsBST positionsBST = this.ship.getPositionsBST();
        this.shipPosition =  new ShipPosition(this.ship.getMMSI(),baseDateTime,lat,lon,sog,cog,heading,transcieverClass);
        return positionsBST.hasPosition(shipPosition);
    }*/

    public boolean registerPosition(PositionDTO positionDTO){
        PositionsBST positionsBST = this.ship.getPositionsBST();
        this.shipPosition =  new ShipPosition(this.ship.getMMSI(), positionDTO.getBaseDateTime(), positionDTO.getLat(), positionDTO.getLon(),
                positionDTO.getSog(), positionDTO.getCog(), positionDTO.getHeading(), positionDTO.getTranscieverClass());
        return positionsBST.hasPosition(shipPosition);
    }

    public void createPosition(PositionDTO positionDTO, int mmsi){
        this.shipPosition =  new ShipPosition(mmsi, positionDTO.getBaseDateTime(), positionDTO.getLat(), positionDTO.getLon(),
                positionDTO.getSog(), positionDTO.getCog(), positionDTO.getHeading(), positionDTO.getTranscieverClass());
    }

    public boolean saveShip(){
        this.ship.setPositionsBST(positionsBST);
        if (this.company.getShipStore().getShipsBstMmsi().getShipByMmsiCode(this.ship.getMMSI())==null){
            this.company.getShipStore().saveShip(ship);
            return true;
        } else if (this.company.getShipStore().getShipsBstMmsi().getShipByMmsiCode(this.ship.getMMSI())!=null && !this.company.getShipStore().getShipsBstMmsi().getShipByMmsiCode(this.ship.getMMSI()).getPositionsBST().hasPosition(this.shipPosition)){
            this.company.getShipStore().getShipsBstMmsi().getShipByMmsiCode(this.ship.getMMSI()).setPositionsBST(this.positionsBST);
            return true;
        }
        return false;
    }

    public boolean importShipFromFile(ShipsFileDTO shipsFileDTO) {
        if (this.company.getShipStore().getShipsBstMmsi().getShipByMmsiCode(shipsFileDTO.getMmsi())==null){ //nao existe ship logo tem de se criar
            try {
                this.ship = this.company.getShipStore().createShip(shipsFileDTO);
            }catch (IllegalArgumentException e){
                System.out.println("NOT ADDED : " + e);
                return false;
            }
        } else {
            //ir buscar o ship pelo mmsi
            try{
                this.ship=this.company.getShipStore().getShipsBstMmsi().getShipByMmsiCode(shipsFileDTO.getMmsi());
            }catch (IllegalArgumentException e){
                System.out.println("NOT ADDED : " + e);
                return false;
            }
        }
        try {
            boolean existsPosition = registerPosition(shipsFileDTO.getPositionDTO());
            if (!existsPosition)
                this.positionsBST = this.ship.getPositionsBST();
            positionsBST.savePosition(shipPosition);
            return saveShip();
        }catch (IllegalArgumentException e){
            System.out.println("NOT ADDED : " + e);
        }
        return false;
        //se a posicao nao existe entao é adicionada
    }

}
