package lapr.project.controller;

import lapr.project.domain.BST.PositionsBST;
import lapr.project.domain.BST.ShipTree;
import lapr.project.domain.BST.ShipTreeMmsi;
import lapr.project.domain.model.Ship;
import lapr.project.domain.model.ShipPosition;
import lapr.project.domain.store.ShipStore;
import lapr.project.dto.ShipsFileDTO;
import lapr.project.domain.model.Company;

/**
 * Controller class to coordinate the creation of a ship with all attributes
 *
 * @author Marta Ribeiro (1201592)
 */
public class ImportShipsController {

    /**
     * Company instance of the session.
     */
    private Company company;

    /**
     * Ship to be created by the controller.
     */
    private Ship ship;

    /**
     * New ShipPosition of the Ship to be created by the controller.
     */
    private ShipPosition shipPosition;

    /**
     * All the ShipPositions of the Ship to be created by the controller.
     */
    private PositionsBST positionsBST;

    /**
     * Constructor for the controller.
     */
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

    /**
     * Saves current ship in the ShipBST and new position in its PositionsBST.
     *
     * @return true if successfully saved
     * and false if unsuccessfully saved.
     */
    public boolean saveShip(){
        ShipStore store = this.company.getShipStore();
        ShipTreeMmsi mmsiTree = store.getShipsBstMmsi();

        if (mmsiTree.getShipByMmsiCode(this.ship.getMMSI())==null){
            store.saveShip(ship);
            return true;
        } else if (mmsiTree.getShipByMmsiCode(this.ship.getMMSI())!=null){
            //AQUI
            if (!mmsiTree.getShipByMmsiCode(this.ship.getMMSI()).getPositionsBST().hasPosition(this.shipPosition)) {
                store.addPosition(this.ship.getMMSI(), this.shipPosition);
                return true;
            }
        }
        return false;
    }

    /**
     * Method for creating a ship instance with all attributes.
     * @param shipsFileDTO shipfile dto which contains all needed data
     * @return true if successfully created
     * and false if unsuccessfully created.
     */
    public boolean importShipFromFile(ShipsFileDTO shipsFileDTO) {
        ShipStore store = this.company.getShipStore();
        if (store.getShipsBstMmsi().getShipByMmsiCode(shipsFileDTO.getMmsi())==null){
            try {
                this.ship = store.createShip(shipsFileDTO);
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
            try{
                this.ship=store.getShipsBstMmsi().getShipByMmsiCode(shipsFileDTO.getMmsi());
                this.shipPosition = new ShipPosition(shipsFileDTO.getMmsi(),shipsFileDTO.getPositionDTO().getBaseDateTime(),
                        shipsFileDTO.getPositionDTO().getLat(),shipsFileDTO.getPositionDTO().getLon(),
                        shipsFileDTO.getPositionDTO().getSog(), shipsFileDTO.getPositionDTO().getCog(),
                        shipsFileDTO.getPositionDTO().getHeading(), shipsFileDTO.getPositionDTO().getTranscieverClass());
            }catch (IllegalArgumentException e){
                System.out.println("NOT ADDED : " + e);
                return false;
            }
            try {
                this.positionsBST = ship.getPositionsBST();
                if (!(positionsBST.hasPosition(shipPosition))) {
                    return saveShip();
                }
            }catch (IllegalArgumentException e){
                System.out.println("NOT ADDED : " + e);
            }
        }
        return false;
    }

}
