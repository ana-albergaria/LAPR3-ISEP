package lapr.project.domain.BST;

import lapr.project.BSTesinf.BST;
import lapr.project.domain.model.Ship;

public class ShipsBST extends BST<Ship> {
    public void createBstShip() {
        throw new UnsupportedOperationException("Not supported yet.");
    }




    private Ship getShipByMmsiCode(Node<Ship> node, int mmsiCode) {
        /*if(node == null)
            return null;
        if(node.getElement().getMMSI() == mmsiCode) {
            return node.getElement();
        }
        if(node.getElement().getMMSI() > mmsiCode) {
            return getShipByMmsiCode(node.getLeft(), mmsiCode);
        }
        if(node.getElement().getMMSI() < mmsiCode) {
            return getShipByMmsiCode(node.getRight(), mmsiCode);
        }
         */
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Ship getShipByMmsiCode(int mmsiCode)  {
        // return getShipByMmsiCode(root, mmsiCode);
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
}
