package lapr.project.domain.dataStructures;

import lapr.project.domain.model.Capital;
import lapr.project.domain.model.Location;
import lapr.project.domain.model.Port;
import lapr.project.genericDataStructures.graphStructure.Graph;
import lapr.project.genericDataStructures.graphStructure.matrix.MatrixGraph;

import java.util.*;

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

    public List< Map.Entry<Capital,Integer> > getOrderedCapitalsList() {
        Map<Capital, Integer> unorderedCapitals = new LinkedHashMap<>();

        for (Location location : freightNetwork.vertices()) {
            if(location instanceof Capital) {
                int numBorders = freightNetwork.adjVertices(location).size();
                unorderedCapitals.put((Capital) location, numBorders);
            }
        }

        List<Map.Entry<Capital,Integer>> orderedCapitals = new ArrayList<>(unorderedCapitals.entrySet());
        orderedCapitals.sort( Map.Entry.<Capital, Integer> comparingByValue().reversed() );

        return orderedCapitals;
    }

    public Map<Capital, Integer> fillCapitalsToColor(Map<Capital, Integer> capitalsToColor) {

        List<Map.Entry<Capital,Integer>> orderedCapitals = getOrderedCapitalsList();

        for (Map.Entry<Capital, Integer> entry : orderedCapitals) {
            Capital capital = entry.getKey();
            capitalsToColor.put(capital, null);
        }
        return capitalsToColor;
    }

    public Map<Capital, Integer> colorMap() {

        Map<Capital, Integer> result = new LinkedHashMap<>();
        fillCapitalsToColor(result);

        int numCapitals = result.size();
        boolean[] availableColors = new boolean[numCapitals];

        Arrays.fill(availableColors, true);

        List<Capital> listCapitals = new ArrayList<>( result.keySet() );
        Capital firstCapital = listCapitals.get(0);
        result.put(firstCapital, 0);

        colorMap(availableColors, result, 1, listCapitals);

        return result;
    }


    private void colorMap(boolean[] availableColors, Map<Capital, Integer> result, int capKey, List<Capital> listCapitals) {

        if(listCapitals.size() <= capKey)
            return;

        Capital capital = listCapitals.get(capKey);

        for (Location vAdj : freightNetwork.adjVertices(capital)) {
            if(vAdj instanceof Capital) {
                if (result.get((Capital) vAdj) != null) {
                    availableColors[result.get((Capital) vAdj)] = false;
                }
            }
        }

        int color = findFirstAvailableColor(availableColors, result.size());

        result.put(capital, color);
        Arrays.fill(availableColors, true);

        colorMap(availableColors, result, capKey+1, listCapitals);
    }


    private int findFirstAvailableColor(boolean[] availableColors, int numCapitals) {
        for (int color = 0; color < numCapitals; color++) {
            if (availableColors[color])
                return color;
        }
        throw new UnsupportedOperationException("An error has occured. " +
                "It isn't possible to assign more colors than the number of vertices.");
    }
}
