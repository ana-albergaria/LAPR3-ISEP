package lapr.project.domain.dataStructures;

import lapr.project.domain.model.Capital;
import lapr.project.domain.model.Location;
import lapr.project.domain.model.Port;
import lapr.project.genericDataStructures.graphStructure.Algorithms;
import lapr.project.genericDataStructures.graphStructure.Edge;
import lapr.project.genericDataStructures.graphStructure.Graph;
import lapr.project.genericDataStructures.graphStructure.matrix.MatrixGraph;

import java.util.*;

import static java.util.Map.*;

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

    public List<Map.Entry<Location, Integer>> getMostCentralPorts(){
        Map<Location, Integer> ports = new HashMap<>();
        ArrayList<LinkedList<Location>> paths = new ArrayList<>();
        ArrayList<Double> dists = new ArrayList<>();

        for (Location location : freightNetwork.vertices()){
            Algorithms.shortestPaths(freightNetwork, location, Double::compare, Double::sum, 0.0, paths, dists);
            for (LinkedList<Location> path : paths){
                for (Location loc : path){
                    if(loc instanceof Port){
                        ports.merge(loc, 1, Integer::sum);
                    }
                }
            }
            paths.clear();
            dists.clear();
        }
        List<Map.Entry<Location, Integer>> toBeSortedMap = new ArrayList<>(ports.entrySet());
        toBeSortedMap.sort(Comparator.comparing(Map.Entry<Location, Integer>::getValue).reversed());
        return toBeSortedMap;
    }

    public Graph<Location, Double> getFreightNetwork() {
        return freightNetwork;
    }

    public List< Map.Entry<Capital,Integer> > getOrderedCapitalsList() {
        Map<Capital, Integer> unorderedCapitals = new LinkedHashMap<>();

        for (Location location : freightNetwork.vertices()) {
            if(location instanceof Capital) {
                int numBorders = getNumBorders((Capital) location);
                unorderedCapitals.put((Capital) location, numBorders); //O(1)
            }
        }

        List<Map.Entry<Capital,Integer>> orderedCapitals = new ArrayList<>(unorderedCapitals.entrySet());
        orderedCapitals.sort( Entry.<Capital, Integer> comparingByValue().reversed() );

        return orderedCapitals;
    }

    public int getNumBorders(Capital capital) {
        int cont = 0;
        for (Location location : freightNetwork.adjVertices(capital)) {
            if(location instanceof Capital)
                cont++;
        }
        return cont;
    }

    public Map<Capital, Integer> fillCapitalsToColor(Map<Capital, Integer> capitalsToColor) {

        List<Map.Entry<Capital,Integer>> orderedCapitals = getOrderedCapitalsList(); //O(V x E)

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
            if(vAdj instanceof Capital && result.get((Capital) vAdj) != null) {
                availableColors[result.get((Capital) vAdj)] = false;
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

    protected HashSet<String> getNetworkContinents(){
        HashSet<String> currentContinents = new HashSet<>();
        for(Location location : freightNetwork.vertices()){
            currentContinents.add(location.getContinent());
        }
        return currentContinents;
    }

    public Map<String, List<Map.Entry<Location, Double>>> closenessPlacesByContinent(){
        Map<String, List<Map.Entry<Location, Double>>> closenessPlacesByContinent = new HashMap<>();
        HashSet<String> continents = getNetworkContinents();
        for(String continent : continents){
            Graph<Location, Double> contGraph = getSubGraphByContinent(continent);
            closenessPlacesByContinent.put(continent, getClosenessPlaces(contGraph));
        }
        return closenessPlacesByContinent;
    }

    private List<Map.Entry<Location, Double>> getClosenessPlaces(Graph<Location, Double> places){
        Graph<Location, Double> dists = Algorithms.minDistGraph(places, Double::compare, Double::sum);
        Map<Location, Double> countriesMap = new HashMap<>();
        assert dists != null;
        double sum, closenessNumber;
        for (Location location : dists.vertices()){
            sum = 0;
            Collection<Edge<Location, Double>> vertEdges = dists.incomingEdges(location); // can be either incoming or outcoming since its not directed
            for(Edge<Location,Double> edge : vertEdges){
                sum += edge.getWeight();
            }
            closenessNumber = sum / (dists.vertices().size()-1);
            countriesMap.put(location, closenessNumber);
        }
        List<Map.Entry<Location, Double>> toBeSortedMap = new ArrayList<>(countriesMap.entrySet());
        toBeSortedMap.sort(Entry.<Location, Double> comparingByValue());
        return toBeSortedMap;
    }

    public Graph<Location, Double> getSubGraphByContinent(String continent){
        Graph<Location, Double> continentNetwork = new MatrixGraph<>(this.freightNetwork);
        for(Location location : continentNetwork.vertices()){
            if(!location.getContinent().equalsIgnoreCase(continent)){
                continentNetwork.removeVertex(location);
            }
        }
        return  continentNetwork;
    }

    //VAI RETORNAR O CAMINHO MAIS PEQUENO
    private LinkedList<Location> getShortestPath(Graph<Location,Double> places, Location origin, Location destination){ //Recebe todos os lugares (capitais e portos)
        LinkedList<Location> shortestPath = new LinkedList<>();
        Algorithms.shortestPath(places, origin, destination, Double::compare, Double::sum, 0.0, shortestPath);
        return shortestPath;
    }
}
