package lapr.project.domain.BST;

import lapr.project.BSTesinf.BST;
import lapr.project.domain.model.Ship;
import lapr.project.domain.model.VesselType;
import lapr.project.utils.ShipTravelledDistanceComparator;

import java.util.*;

public class ShipsBST extends BST<Ship> {
    public void createBstShip() {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    /**
     * Method which gets the Ship chosen by the user through
     * the MMSI code
     *
     * @param node the node
     * @param mmsiCode the MMSI code
     *
     * @return the Ship chosen by the user
     */
    private Ship getShipByMmsiCode(Node<Ship> node, int mmsiCode) {
        if(node == null)
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
        return node.getElement();
    }

    /**
     * Method which calls its private method to obtain
     * the ship chosen by the user
     *
     * @param mmsiCode the MMSI code
     *
     * @return Ship chosen by the user
     */
    public Ship getShipByMmsiCode(int mmsiCode)  {
        return getShipByMmsiCode(root, mmsiCode);
    }


    /**
     * Method to get all the ships within the Base Date Time gap
     * @param initialDate initial Base Date Time
     * @param finalDate final Base Date Time
     */
    private void getShipsByDate(Node<Ship> node, Date initialDate, Date finalDate, List<Ship> shipList) {
        if(node==null) return;
        Date x = node.getElement().getPositionsBST().getStartDate();
        Date y = node.getElement().getPositionsBST().getEndDate();

        if(node.getElement().getPositionsBST().getShipDate(node.getElement().getMMSI()).after(initialDate) && node.getElement().getPositionsBST().getShipDate(node.getElement().getMMSI()).before(finalDate)){
            if (!shipList.contains(node.getElement())){
                shipList.add(node.getElement());

            }
        }
        getShipsByDate(node.getLeft(), initialDate, finalDate, shipList);
        getShipsByDate(node.getRight(), initialDate, finalDate, shipList);

        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Ship> getShipsByDate(Date initialDate, Date finalDate) {

        List<Ship> shipList = new ArrayList<>();
        getShipsByDate(root, initialDate, finalDate, shipList);
        return shipList;
    }

    /**
     * method to sort the ships with the most km travelled
     * @param shipList list with the ships belonging in the time gap
     * @param number number of ships to be sorted
     */
    public List<Ship> sortNShips(List<Ship> shipList, int number) {

        ShipTravelledDistanceComparator comparator = new ShipTravelledDistanceComparator();

        Collections.sort(shipList, comparator);
        return shipList.subList(0, number);

        //throw new UnsupportedOperationException("Not supported yet.");
    }


    /**
     * method to get the map with the ships associated by VesselType and sorted
     * @param sortedShips list with the sorted ships by most km travelled
     * @return map with the ships associated by VesselType and sorted
     */
    public Map<VesselType, Set<Ship>> getShipWithMean(Set<Ship> sortedShips) {
        Map<VesselType, Set<Ship>> map = new HashMap<>();
        Set<Ship> setter = new HashSet<>(sortedShips);
        VesselType vessel = null;

        for (Ship x: sortedShips) {
            if (!map.containsKey(x.getVesselType())){
                vessel = x.getVesselType();
                setter = new HashSet<>();
                setter.add(x);
                map.put(vessel, setter);    //falta só associar com meanSOG
            } else {
                setter = map.get(x.getVesselType());
                setter.add(x);
            }
        }

        return map;


        //throw new UnsupportedOperationException("Not supported yet.");
    }
}
