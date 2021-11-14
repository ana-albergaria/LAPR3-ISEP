package lapr.project.domain.BST;



import lapr.project.BSTesinf.BST;
import lapr.project.domain.model.Ship;
import lapr.project.domain.shared.Constants;
import lapr.project.utils.ShipTotalMovementsComparator;
import lapr.project.utils.ShipTravelledDistanceComparator;


import java.util.*;

public class ShipBST extends BST<Ship> {

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

        if (!shipList.contains(node.getElement())){
        if(node.getElement().getPositionsBST().getShipDate(node.getElement().getMMSI()).after(initialDate)
                && node.getElement().getPositionsBST().getShipDate(node.getElement().getMMSI()).before(finalDate)){

                shipList.add(node.getElement());

            }
        }
        getShipsByDate(node.getLeft(), initialDate, finalDate, shipList);
        getShipsByDate(node.getRight(), initialDate, finalDate, shipList);

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

    }


    /**
     * method to get the map with the ships associated by VesselType and sorted
     * @param listShip list with the sorted ships by most km travelled
     * @return map with the ships associated by VesselType and sorted
     */
    public Map<Integer, Map<Ship, Set<Double>>> getShipWithMean(List<Ship> listShip, int number) {
        Map<Integer,  Map<Ship, Set<Double>>> map = new HashMap<>();
        Map<Ship, Set<Double>> shipMap ;
        Set<Double> setter ;
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
            }
        }

        return map;


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

        if (!list.contains(node.getElement())){
            list.add(node.getElement());
            getAllShips(node.getRight(), list);
            getAllShips(node.getLeft(), list);
        }
    }

    /**
     * method to get the MMSI, Total Travelled Distance, Delta Distance and Total Movements
     * of all the ships sorted By Total Travelled Distance
     * @return map with MMSI, Total Travelled Distance, Delta Distance and Total Movements
     * of all the ships sorted By Total Travelled Distance
     */
    public Map<Integer, Set<Double>> sortedByTravelledDistance() {
        Map<Integer, Set<Double>> mapByTravelled = new LinkedHashMap<>();
        List<Ship> list = getAllShips();
        sortedByTravelledDistance(mapByTravelled, list);
        return mapByTravelled;
    }

    private void sortedByTravelledDistance(Map<Integer, Set<Double>> map, List<Ship> list) {
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
     * @return map with MMSI, Total Travelled Distance, Delta Distance and Total Movements
     *  of all the ships sorted By Total Movements
     */
    public Map<Integer, Set<Double>> sortedByTotalMovements() {
        Map<Integer, Set<Double>> mapByMovements = new LinkedHashMap<>();
        List<Ship> list = getAllShips();
        sortedByTotalMovements(mapByMovements, list);
        return mapByMovements;
    }

    private void  sortedByTotalMovements(Map<Integer, Set<Double>> map, List<Ship> list) {
        ShipTotalMovementsComparator comparator = new ShipTotalMovementsComparator();
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

        List<Ship> listShipsWithIntendedTD = (List<Ship>) getShipsInOrderWithIntendedTD();

        for (Ship ship : listShipsWithIntendedTD) {
            TreeMap<Double, String> infoPair = new TreeMap<>(Collections.reverseOrder());

            PositionsBST positionsBST = ship.getPositionsBST();
            Double travelledDistance = positionsBST.getTotalDistance();
            int indexShip = listShipsWithIntendedTD.indexOf(ship);

            fillTreeMapForEachShip(listShipsWithIntendedTD, infoPair, travelledDistance, positionsBST, ship.getMMSI(), indexShip);

            if(!infoPair.isEmpty())
                listPairsOfShips.add(infoPair);
        }
        return listPairsOfShips;
    }

    public void fillTreeMapForEachShip(List<Ship> listShipsWithIntendedTD,
                                    TreeMap<Double, String> infoPair,
                                    Double travelledDistance,
                                    PositionsBST positionsBST,
                                    int ship1MMSI, int indexShip) {

        for (int j = indexShip+1; j < listShipsWithIntendedTD.size(); j++) {

            Ship ship2 = listShipsWithIntendedTD.get(j);

            PositionsBST positionsBST2 = ship2.getPositionsBST();
            Double travelledDistance2 = positionsBST2.getTotalDistance();

            if(!Objects.equals(travelledDistance, travelledDistance2)) {

                Double arrivalDistance = positionsBST.getArrivalDistance(positionsBST2);

                if(arrivalDistance <= Constants.LIMIT_COORDINATES) {

                    Double depDistance = positionsBST.getDepartureDistance(positionsBST2);

                    if(depDistance <= Constants.LIMIT_COORDINATES) {
                        int numMovs = positionsBST.size()-1, numMovs2 = positionsBST2.size()-1;
                        double diffTravDist = Math.abs(travelledDistance - travelledDistance2);
                        String allInfo = String.format("%-15d%-15d%-15.3f%-15.3f%-15d%-15.3f%-15d%-15.3f%n", ship1MMSI, ship2.getMMSI(), arrivalDistance, depDistance, numMovs, travelledDistance, numMovs2, travelledDistance2);
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

    public boolean hasShip(Ship ship){
        List<Ship> allShip = (List<Ship>) inOrder();
        for (Ship ship1 : allShip) {
            if (ship1.equals(ship)){
                return true;
            }
        }
        return false;
    }

}
