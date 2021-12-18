package lapr.project.domain.dataStructures;

import lapr.project.domain.model.Capital;
import lapr.project.domain.model.Location;
import lapr.project.genericDataStructures.graphStructure.Graph;
import lapr.project.genericDataStructures.graphStructure.matrix.MatrixGraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FreightNetwork {
    private final Graph<Location, Double> freightNetwork;

    public FreightNetwork() {
        freightNetwork = new MatrixGraph<>(false);
    }

    public void addLocation(Location location) {
        freightNetwork.addVertex(location);
    }

    public void addDistance(Location locOrigin, Location locDestination, Double distance) {
        freightNetwork.addEdge(locOrigin, locDestination, distance);
    }

    public Graph<Location, Double> getFreightNetwork() {
        return freightNetwork;
    }

    private List< Map.Entry<Capital,Integer> > getOrderedCapitalsList() {
        /*Map<Capital, Integer> unorderedCapitals = new HashMap<>();

        for (Location location : freightNetwork.vertices()) {
            if(location instanceof Capital) {
                int numBorders = freightNetwork.adjVertices(location).size();
                unorderedCapitals.put((Capital) location, numBorders);
            }
        }

        List<Map.Entry<Capital,Integer>> orderedCapitals = new ArrayList<>(unorderedCapitals.entrySet());
        orderedCapitals.sort( Map.Entry.<Capital, Integer> comparingByValue().reversed() );

        return orderedCapitals;
         */
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private Map<Capital, Integer> getCapitalsToColor() {
        /*
        List<Map.Entry<Capital,Integer>> orderedCapitals = getOrderedCapitalsList();

        Map<Capital, Integer> capitalsToColor = new HashMap<>();

        for (Map.Entry<Capital, Integer> entry : orderedCapitals) {
            Capital capital = entry.getKey();
            capitalsToColor.put(capital, null);
        }
        return capitalsToColor;
         */
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
