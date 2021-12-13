package lapr.project.domain.dataStructures;

import lapr.project.domain.model.Capital;
import lapr.project.domain.model.Location;
import lapr.project.domain.model.Port;
import lapr.project.genericDataStructures.graphStructure.Graph;
import lapr.project.genericDataStructures.graphStructure.matrix.MatrixGraph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FreightNetworkTest {
    FreightNetwork freightNetwork;
    private Port port1;
    private Capital capital1;

    @BeforeEach
    void setUp() {
        freightNetwork = new FreightNetwork();
        port1 = new Port(10136, "Larnaca", "Europe", "Cyprus", 34.91666667,33.65);
        capital1 = new Capital("Nicosia",34.91666667,33.65,"Cyprus");
    }

    @Test
    void addLocation() {
        freightNetwork.addLocation(port1);
        freightNetwork.addLocation(capital1);
    }

    @Test
    void addDistance() {
        freightNetwork.addDistance(port1, capital1, 230.0);
    }
}