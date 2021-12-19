package lapr.project.domain.dataStructures;

import lapr.project.domain.model.Capital;
import lapr.project.domain.model.Location;
import lapr.project.domain.model.Port;
import lapr.project.genericDataStructures.graphStructure.Graph;
import lapr.project.genericDataStructures.graphStructure.matrix.MatrixGraph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class FreightNetworkTest {
    FreightNetwork freightNetwork;

    private final int[] identifications = {123456789, 987654321, 369258147, 741852963};

    private final String [] names = {"Port1", "Port2", "Port3", "Port4"};

    private String continent1, continent2;

    private final double [] lats = {-30.033056, -42.033006, -55.022056, 23.008721};
    private final double [] lons = {-51.230000, -47.223056, -46.233056, 24.092123};

    private Port port1, port2, port3, port4;

    private Map<Integer, Double> seaDists;

    private String [] countryNames = {"Cyprus", "Malta", "Greece", "Portugal", "Turkey", "Armenia", "Spain", "Albania"};

    private String [] capitals = {"Nicosia", "Valeta", "Athens", "Lisbon", "Ankara", "Yerevan", "Madrid", "Tirana"};

    private Capital capital1, capital2, capital3, capital4, capital5, capital6, capital7, capital8;

    @BeforeEach
    void setUp() {
        freightNetwork = new FreightNetwork();

        continent1 = "America";
        continent2 = "Europe";

        seaDists = new HashMap<>();
        seaDists.put(12501, 1200.0);
        seaDists.put(13409, 500.0);


        port1 = new Port(identifications[0], names[0], continent1, countryNames[0], lats[0], lons[0], seaDists);
        port2 = new Port(identifications[0], names[1], continent1, countryNames[1], lats[1], lons[1], seaDists);
        port3 = new Port(identifications[2], names[2], continent2, countryNames[2], lats[2], lons[2], seaDists);
        port4 = new Port(identifications[3], names[3], continent1, countryNames[3], lats[3], lons[3], seaDists);
        capital1 = new Capital(capitals[0],lats[0],lats[1],countryNames[0]);
        capital2 = new Capital(capitals[1],lats[0],lats[1],countryNames[1]);
        capital3 = new Capital(capitals[2],lats[0],lats[1],countryNames[2]);
        capital4 = new Capital(capitals[3],lats[0],lats[1],countryNames[3]);
        capital5 = new Capital(capitals[4],lats[0],lats[1],countryNames[4]);
        capital6 = new Capital(capitals[5],lats[0],lats[1],countryNames[5]);
        capital7 = new Capital(capitals[6],lats[0],lats[1],countryNames[6]);
        capital8 = new Capital(capitals[7],lats[0],lats[1],countryNames[7]);

        freightNetwork.addLocation(port1);
        freightNetwork.addLocation(capital1);
        freightNetwork.addLocation(capital7);
        freightNetwork.addLocation(port2);
        freightNetwork.addLocation(port3);
        freightNetwork.addLocation(capital2);
        freightNetwork.addLocation(capital3);
        freightNetwork.addLocation(capital4);
        freightNetwork.addLocation(capital5);
        freightNetwork.addLocation(port4);
        freightNetwork.addLocation(capital6);

        freightNetwork.addDistance(port1, port2, 230.0);
        freightNetwork.addDistance(capital1, capital3, 230.0);
        freightNetwork.addDistance(capital3, capital2, 4000.0);
        freightNetwork.addDistance(capital2, capital1, 500.0);
        freightNetwork.addDistance(capital4, capital5, 100.0);
        freightNetwork.addDistance(capital5, capital2, 230.0);
        freightNetwork.addDistance(capital2, capital8, 459.0);
        freightNetwork.addDistance(capital6, capital8, 120.0);
        freightNetwork.addDistance(capital7, capital8, 300.0);
        freightNetwork.addDistance(capital1, capital8, 123.0);
        freightNetwork.addDistance(capital8, capital3, 120.0);


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

    @Test
    void getOrderedCapitalsList() {

        Map<Capital, Integer> expMap = new LinkedHashMap<>();
        expMap.put(capital8, 5);
        expMap.put(capital2, 4);
        expMap.put(capital1, 3);
        expMap.put(capital3, 3);
        expMap.put(capital5, 2);
        expMap.put(capital7, 1);
        expMap.put(capital4, 1);
        expMap.put(capital6, 1);

        List<Map.Entry<Capital, Integer>> expList = new ArrayList<>(expMap.entrySet());

        List<Map.Entry<Capital, Integer>> list = freightNetwork.getOrderedCapitalsList();

        /*for (Map.Entry<Capital,Integer> entry : list) {
            System.out.println("Capital: " + entry.getKey().getName() + "   | Borders: " + entry.getValue());
        }
         */


        assertEquals(expList, list);

    }


    @Test
    void getCapitalsToColor() {
        Map<Capital, Integer> expMap = new LinkedHashMap<>();
        expMap.put(capital8, null);
        expMap.put(capital2, null);
        expMap.put(capital1, null);
        expMap.put(capital3, null);
        expMap.put(capital5, null);
        expMap.put(capital7, null);
        expMap.put(capital4, null);
        expMap.put(capital6, null);

        Map<Capital, Integer> map = freightNetwork.getCapitalsToColor();

        /*for (Map.Entry<Capital,Integer> entry : map.entrySet()) {
            System.out.println("Capital: " + entry.getKey().getName() + "   | Color: " + entry.getValue());
        }
         */

        assertEquals(expMap, map);
    }
}