package lapr.project.domain.BST;



import lapr.project.BSTesinf.BST;
import lapr.project.domain.model.Ship;
import lapr.project.domain.shared.Constants;
import lapr.project.utils.ShipDeltaDistanceComparato;
import lapr.project.utils.ShipTravelledDistanceComparator;


import java.awt.font.FontRenderContext;
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
     */
    public List<Ship> sortNShips(List<Ship> shipList) {

        ShipTravelledDistanceComparator comparator = new ShipTravelledDistanceComparator();


        Collections.sort(shipList, comparator);
        return shipList;

        //throw new UnsupportedOperationException("Not supported yet.");
    }


    /**
     * method to get the map with the ships associated by VesselType and sorted
     * @param listShip list with the sorted ships by most km travelled
     * @return map with the ships associated by VesselType and sorted
     */
    public Map<Integer, Map<Ship, Set<Double>>> getShipWithMean(List<Ship> listShip, int number) {
        Map<Integer,  Map<Ship, Set<Double>>> map = new HashMap<>();
        Map<Ship, Set<Double>> shipMap = new HashMap<>();
        Set<Double> setter = new HashSet<>();
        Integer vessel = null;

        listShip = sortNShips(listShip);

        for (Ship x: listShip) {
            if (!map.containsKey(x.getVesselTypeID())){
                shipMap = new HashMap<>();
                vessel = x.getVesselTypeID();
                setter = new HashSet<>();
                setter.add(x.getPositionsBST().getTotalDistance());
                setter.add(x.getPositionsBST().getMeanSog());
                if (shipMap.size() <= number) {
                    shipMap.put(x, setter);
                    map.put(vessel, shipMap);
                }
            } else {
                shipMap = map.get(x.getVesselTypeID());
                setter = shipMap.get(x);
                setter.add(x.getPositionsBST().getTotalDistance());
                setter.add(x.getPositionsBST().getMeanSog());
                if (shipMap.size() <= number) {
                    shipMap.put(x, setter);
                    map.put(vessel, shipMap);
                }
            }
        }

        return map;


        //throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * method to get all the ships in the BST
     * @return list with all the ships in the BST
     */
    public List<Ship> getAllShips() {
        List<Ship> allShip = new ArrayList<>();

        getAllShips(root, allShip);

        return allShip;
    }

    public void getAllShips(Node<Ship> node, List<Ship> list) {
        if (node == null) return;

        if (list.contains(node.getElement())){
            getAllShips(node.getRight(), list);
            getAllShips(node.getLeft(), list);
        } else {
            list.add(node.getElement());
            getAllShips(node.getRight(), list);
            getAllShips(node.getLeft(), list);
        }
    }

    /**
     * method to get the MMSI, Total Travelled Distance, Delta Distance and Total Movements
     * of all the ships sorted By Total Travelled Distance
     * @param list list with all the ships
     * @return map with MMSI, Total Travelled Distance, Delta Distance and Total Movements
     * of all the ships sorted By Total Travelled Distance
     */
    public Map<Integer, Set<Double>> sortedByTravelledDistance(List<Ship> list) {
        Map<Integer, Set<Double>> mapByTravelled = new LinkedHashMap<>();
        sortedByTravelledDistance(mapByTravelled, list);
        return mapByTravelled;
    }

    public void sortedByTravelledDistance(Map<Integer, Set<Double>> map, List<Ship> list) {
        ShipTravelledDistanceComparator comparator = new ShipTravelledDistanceComparator();
        Collections.sort(list, comparator);

        for (Ship x : list) {
            if (!map.containsKey(x.getMMSI())) {
                Set<Double> setter = new LinkedHashSet<>();
                setter.add(x.getPositionsBST().getDeltaDistance());
                setter.add(x.getPositionsBST().getTotalDistance());
                setter.add((double) x.getPositionsBST().size());
                map.put(x.getMMSI(), setter);
            }
        }
    }

    /**
     * method to get the MMSI, Total Travelled Distance, Delta Distance and Total Movements
     * of all the ships sorted By Total Movements
     * @param list list with all the ships
     * @return map with MMSI, Total Travelled Distance, Delta Distance and Total Movements
     *  of all the ships sorted By Total Movements
     */
    public Map<Integer, Set<Double>> sortedByTotalMovements(List<Ship> list) {
        Map<Integer, Set<Double>> mapByMovements = new LinkedHashMap<>();
        sortedByTotalMovements(root, mapByMovements, list);
        return mapByMovements;
    }

    public void  sortedByTotalMovements(Node<Ship> node, Map<Integer, Set<Double>> map, List<Ship> list) {
        ShipDeltaDistanceComparato comparator = new ShipDeltaDistanceComparato();
        Collections.sort(list, comparator);

        for (Ship x : list) {
            if (!map.containsKey(x.getMMSI())) {
                Set<Double> setter = new HashSet<>();
                setter.add(x.getPositionsBST().getDeltaDistance());
                setter.add(x.getPositionsBST().getTotalDistance());
                setter.add((double) x.getPositionsBST().size());
                map.put(x.getMMSI(), setter);
            }
        }
    }


    public List<TreeMap<Double, String>> getPairsOfShips() {
        List<TreeMap<Double, String>> listPairsOfShips = new ArrayList<>();
        String header = String.format("%-25s%-25s%-25s%-25s%-25s%-25s%-25s%-25s%-25s%n", "Ship1 MMSI", "Ship2 MMSI", "distOrig", "distDest","Movs Ship 1", "TravelDist Ship1", "Movs Ship 2", "TravelDist Ship2", "TravelDist Diff");


        List<Ship> listShipsWithIntendedTD = (List<Ship>) getShipsInOrderWithIntendedTD();

        for (Ship ship : listShipsWithIntendedTD) {
            TreeMap<Double, String> infoPair = new TreeMap<>(Collections.reverseOrder());

            PositionsBST positionsBST = ship.getPositionsBST();
            Double travelledDistance = positionsBST.getTotalDistance();
            int indexShip = listShipsWithIntendedTD.indexOf(ship);

            fillTreeMapForEachShip(listShipsWithIntendedTD, infoPair, travelledDistance, positionsBST, ship.getMMSI(), indexShip);

            listPairsOfShips.add(infoPair);
        }
        return listPairsOfShips;


        //throw new UnsupportedOperationException();

    }

    public void fillTreeMapForEachShip(List<Ship> listShipsWithIntendedTD,
                                    TreeMap<Double, String> infoPair,
                                    Double travelledDistance,
                                    PositionsBST positionsBST,
                                    int ship1MMSI, int indexShip) {

        /*if(indexShip == listShipsWithIntendedTD.size()-1)
            return;
         */


        for (int i = indexShip+1; i < listShipsWithIntendedTD.size(); i++) {

            Ship ship2 = listShipsWithIntendedTD.get(indexShip+i);

            PositionsBST positionsBST2 = ship2.getPositionsBST();
            Double travelledDistance2 = positionsBST2.getTotalDistance();

            if(!Objects.equals(travelledDistance, travelledDistance2)) {

                Double arrivalDistance = positionsBST.getArrivalDistance(positionsBST2);

                if(arrivalDistance <= Constants.LIMIT_COORDINATES) {

                    Double depDistance = positionsBST.getDepartureDistance(positionsBST2);

                    if(depDistance <= Constants.LIMIT_COORDINATES) {
                        int numMovs = positionsBST.size(), numMovs2 = positionsBST2.size();
                        double diffTravDist = Math.abs(travelledDistance - travelledDistance2);
                        String allInfo = String.format("%-15d%-15d%-15f%-15f%-15d%-15f%-15d%-15f%n", ship1MMSI, ship2.getMMSI(), arrivalDistance, depDistance, numMovs, travelledDistance, numMovs2, travelledDistance2);
                        infoPair.put(diffTravDist, allInfo);
                    }
                }
            }
        }
    }

    public Iterable<Ship> getShipsInOrderWithIntendedTD() {
        List<Ship> listShipsWithIntendedTD = new ArrayList<>();
        getShipsInOrderWithIntendedTD(root, listShipsWithIntendedTD);
        return listShipsWithIntendedTD;
    }

    private void getShipsInOrderWithIntendedTD(Node<Ship> node, List<Ship> listShipsWithIntendedTD) {
        if(node == null) {
            return;
        }

        getShipsInOrderWithIntendedTD(node.getLeft(), listShipsWithIntendedTD);

        Double currentTravelledDistance = node.getElement().getTravelledDistance();

        if(currentTravelledDistance >= Constants.LIMIT_TRAVELLED_DISTANCE)
            listShipsWithIntendedTD.add(node.getElement());

        getShipsInOrderWithIntendedTD(node.getRight(), listShipsWithIntendedTD);
    }

}
