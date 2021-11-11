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

    public boolean saveShip(){
        if (this.company.getShipStore().getShipsBstMmsi().getShipByMmsiCode(this.ship.getMMSI())==null){
            this.company.getShipStore().saveShip(new Ship(this.ship.getPositionsBST(), this.ship.getMMSI(),
                    this.ship.getVesselName(), this.ship.getIMO(), this.ship.getCallSign(), this.ship.getVesselTypeID(),
                    this.ship.getLength(), this.ship.getWidth(), this.ship.getDraft(), this.ship.getCargo()));
            return true;
        } else if (this.company.getShipStore().getShipsBstMmsi().getShipByMmsiCode(this.ship.getMMSI())!=null){
            //AQUI
            if (!this.company.getShipStore().getShipsBstMmsi().getShipByMmsiCode(this.ship.getMMSI()).getPositionsBST().hasPosition(this.shipPosition)) {
                positionsBST.savePosition(shipPosition);
                this.ship.setPositionsBST(positionsBST);
                this.company.getShipStore().getShipsBstMmsi().getShipByMmsiCode(this.ship.getMMSI()).setPositionsBST(this.positionsBST);
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean importShipFromFile(ShipsFileDTO shipsFileDTO) {
        if (this.company.getShipStore().getShipsBstMmsi().getShipByMmsiCode(shipsFileDTO.getMmsi())==null){ //nao existe ship logo tem de se criar
            try {
                this.ship = this.company.getShipStore().createShip(shipsFileDTO);
                this.shipPosition = new ShipPosition(shipsFileDTO.getMmsi(),shipsFileDTO.getPositionDTO().getBaseDateTime(),
                        shipsFileDTO.getPositionDTO().getLat(),shipsFileDTO.getPositionDTO().getLon(),
                        shipsFileDTO.getPositionDTO().getSog(), shipsFileDTO.getPositionDTO().getCog(),
                        shipsFileDTO.getPositionDTO().getHeading(), shipsFileDTO.getPositionDTO().getTranscieverClass());
            }catch (IllegalArgumentException e){
                System.out.println("NOT ADDED : " + e);
                return false;
            }
            return saveShip();
        } else {
            //ir buscar o ship pelo mmsi
            try{
                this.ship=new Ship(this.company.getShipStore().getShipsBstMmsi().getShipByMmsiCode(shipsFileDTO.getMmsi()).getPositionsBST(),
                        this.company.getShipStore().getShipsBstMmsi().getShipByMmsiCode(shipsFileDTO.getMmsi()).getMMSI(),
                        this.company.getShipStore().getShipsBstMmsi().getShipByMmsiCode(shipsFileDTO.getMmsi()).getVesselName(),
                        this.company.getShipStore().getShipsBstMmsi().getShipByMmsiCode(shipsFileDTO.getMmsi()).getIMO(),
                        this.company.getShipStore().getShipsBstMmsi().getShipByMmsiCode(shipsFileDTO.getMmsi()).getCallSign(),
                        this.company.getShipStore().getShipsBstMmsi().getShipByMmsiCode(shipsFileDTO.getMmsi()).getVesselTypeID(),
                        this.company.getShipStore().getShipsBstMmsi().getShipByMmsiCode(shipsFileDTO.getMmsi()).getLength(),
                        this.company.getShipStore().getShipsBstMmsi().getShipByMmsiCode(shipsFileDTO.getMmsi()).getWidth(),
                        this.company.getShipStore().getShipsBstMmsi().getShipByMmsiCode(shipsFileDTO.getMmsi()).getDraft(),
                        this.company.getShipStore().getShipsBstMmsi().getShipByMmsiCode(shipsFileDTO.getMmsi()).getCargo());
                this.shipPosition = new ShipPosition(shipsFileDTO.getMmsi(),shipsFileDTO.getPositionDTO().getBaseDateTime(),
                        shipsFileDTO.getPositionDTO().getLat(),shipsFileDTO.getPositionDTO().getLon(),
                        shipsFileDTO.getPositionDTO().getSog(), shipsFileDTO.getPositionDTO().getCog(),
                        shipsFileDTO.getPositionDTO().getHeading(), shipsFileDTO.getPositionDTO().getTranscieverClass());
            }catch (IllegalArgumentException e){
                System.out.println("NOT ADDED : " + e);
                return false;
            }
        }
        try {
            this.positionsBST=this.ship.getPositionsBST();
            if (!(positionsBST.hasPosition(shipPosition))) {
                return saveShip();//AQUI
            }
        }catch (IllegalArgumentException e){
            System.out.println("NOT ADDED : " + e);
            return false;
        }
        //se a posicao nao existe entao é adicionada
        return false;
    }

}
