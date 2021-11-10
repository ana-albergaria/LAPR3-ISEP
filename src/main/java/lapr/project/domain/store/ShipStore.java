package lapr.project.domain.store;

import lapr.project.domain.BST.PositionsBST;
import lapr.project.domain.BST.ShipBstCallSign;
import lapr.project.domain.BST.ShipBstImo;
import lapr.project.domain.BST.ShipBST;
import lapr.project.domain.model.Ship;
import lapr.project.domain.model.ShipPosition;
import lapr.project.dto.ShipsFileDTO;
import lapr.project.dto.mapper.PositionMapper;
import org.apache.commons.lang3.StringUtils;

public class ShipStore {

    private ShipBST shipsBstMmsi = new ShipBST();
    private ShipBstImo shipBstImo = new ShipBstImo();
    private ShipBstCallSign shipBstCallSign = new ShipBstCallSign();

    public Ship createShip(ShipsFileDTO shipsFileDTO){
        PositionsBST positionsBST = new PositionsBST();
        PositionMapper positionMapper = new PositionMapper();
        ShipPosition shipPosition = positionMapper.toDomain(shipsFileDTO.getPositionDTO(), shipsFileDTO.getMmsi());
        positionsBST.insert(shipPosition);
        return new Ship(positionsBST, shipsFileDTO.getMmsi(), shipsFileDTO.getVesselName(), shipsFileDTO.getImo(), shipsFileDTO.getCallSign(),
                shipsFileDTO.getVesselType(), shipsFileDTO.getLength(), shipsFileDTO.getWidth(), shipsFileDTO.getDraft(), shipsFileDTO.getCargo());
    }

    public boolean validateShip(Ship ship){
        if(ship == null){
            return false;
        }
        return true;
    }

    public boolean saveShip(Ship ship){
        if(!validateShip(ship)){
            return false;
        }
        shipsBstMmsi.insert(ship);
        shipBstImo.insert(ship);
        shipBstCallSign.insert(ship);
        return true;
    }

    public ShipBST getShipsBstMmsi() {
        return shipsBstMmsi;
    }

    public Ship getShipByAnyCode(String code){
        Ship result;
        if(code.substring(0,3).equalsIgnoreCase("imo")){
            result = shipBstImo.getShipByImo(code);
        }else if(code.length() == 9 && StringUtils.isNumeric(code)){
            result = shipsBstMmsi.getShipByMmsiCode(Integer.parseInt(code));
        }else{
            result = shipBstCallSign.getShipByCallSign(code);
        }
        if(result == null){
            throw new UnsupportedOperationException("Couldn't find a ship with given code");
        }
        return result;
    }

}
