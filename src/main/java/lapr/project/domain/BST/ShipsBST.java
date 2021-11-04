package lapr.project.domain.BST;

import lapr.project.BSTesinf.BST;
import lapr.project.domain.model.Ship;
import lapr.project.domain.model.VesselType;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class ShipsBST extends BST<Ship> {
    public void createBstShip() {
        throw new UnsupportedOperationException("Not supported yet.");
    }




    private Node<Ship> getShipByMmsiCode(Node<Ship> node, int mmsiCode) {
        /*if(node != null) {
            if(node.getElement().getMMSI() == mmsiCode) {
                return node;
            } else {

            }
        }
         */
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Ship getShipByMmsiCode(int mmsiCode)  {
        //root = getShipByMmsiCode(root, mmsiCode);
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Method which validates the existence of a ship
     * in the Ships' binary search tree.
     * Returns true if it is stored in the tree, otherwise
     * returns false.
     *
     * @param ship the ship to be searched
     * @return true if the ship is stored in the tree,
     *          otherwise returns false.
     */
    public boolean validateShip(Ship ship) {
        /*Node<Ship> node = find(ship);
        return node != null;
         */
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Method to get all the ships within the Base Date Time gap
     * @param initialDate initial Base Date Time
     * @param finalDate final Base Date Time
     * @return list with all the ships who belong in the time gap
     */
    public List<Ship> getShipsByDate(Date initialDate, Date finalDate) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * method to sort the ships with the most km travelled
     * @param shipList list with the ships belonging in the time gap
     * @param number number of ships to be sorted
     */
    public void sortNShips(List<Ship> shipList, int number) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * method to get the map with the ships associated by VesselType and sorted
     * @param sortedShips list with the sorted ships by most km travelled
     * @return map with the ships associated by VesselType and sorted
     */
    public Map<VesselType, List<Ship>> getShipWithMean(List<Ship> sortedShips) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
