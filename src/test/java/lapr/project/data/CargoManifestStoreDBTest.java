package lapr.project.data;

import lapr.project.utils.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CargoManifestStoreDBTest {
    private DatabaseConnection databaseConnection;
    private CargoManifestStoreDB cargoManifestStoreDB;

    @BeforeEach
    void setUp() {
        databaseConnection = mock(DatabaseConnection.class);
        cargoManifestStoreDB = mock(CargoManifestStoreDB.class);
    }

    @Test
    void getUnLoadingLoadingMap() throws IOException {
        Map<String, String> expMap = new LinkedHashMap<>();

        String monday = FileUtils.readFromFile("./docs/Sprint 4/US407/Output/24-01-2022.txt");
        String tuesday = FileUtils.readFromFile("./docs/Sprint 4/US407/Output/25-01-2022.txt");
        String wednesday = FileUtils.readFromFile("./docs/Sprint 4/US407/Output/26-01-2022.txt");
        String thursday = FileUtils.readFromFile("./docs/Sprint 4/US407/Output/27-01-2022.txt");
        String friday = FileUtils.readFromFile("./docs/Sprint 4/US407/Output/28-01-2022.txt");
        String saturday = FileUtils.readFromFile("./docs/Sprint 4/US407/Output/29-01-2022.txt");
        String sunday = FileUtils.readFromFile("./docs/Sprint 4/US407/Output/30-01-2022.txt");
        expMap.put("24-01-2022", monday);
        expMap.put("25-01-2022", tuesday);
        expMap.put("26-01-2022", wednesday);
        expMap.put("27-01-2022", thursday);
        expMap.put("28-01-2022", friday);
        expMap.put("29-01-2022", saturday);
        expMap.put("30-01-2022", sunday);


        when(cargoManifestStoreDB.getUnLoadingLoadingMap(databaseConnection)).thenReturn(expMap);
        Map<String, String> map = cargoManifestStoreDB.getUnLoadingLoadingMap(databaseConnection);
        assertEquals(expMap, map);
    }


}