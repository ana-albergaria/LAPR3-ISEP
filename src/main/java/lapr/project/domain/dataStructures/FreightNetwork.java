package lapr.project.domain.dataStructures;

import lapr.project.domain.model.Location;
import lapr.project.genericDataStructures.graphStructure.Graph;
import lapr.project.genericDataStructures.graphStructure.matrix.MatrixGraph;

public class FreightNetwork {
    private final Graph<Location, Double> freightNetwork;

    public FreightNetwork() {
        freightNetwork = new MatrixGraph<>(true);
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
}
