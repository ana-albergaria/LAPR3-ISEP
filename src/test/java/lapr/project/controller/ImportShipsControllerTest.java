package lapr.project.controller;

import lapr.project.domain.model.Company;
import lapr.project.dto.ShipsFileDTO;
import lapr.project.utils.ShipsFileUtils;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ImportShipsControllerTest {

    private Company comp;
    private List<ShipsFileDTO> shipsOfFile, shipsOfFileExp;
    private File /*file1, file2,*/ fileTest, expFileTest;
    private ImportShipsController ctrl;

    @BeforeEach
    public void SetUp() {
        comp = new Company("Company");
        this.shipsOfFile = Collections.emptyList();
        this.shipsOfFileExp = Collections.emptyList();
        /*file1 = new File("data-ships&ports/bships.csv");
        file2 = new File("data-ships&ports/sships.csv");*/
        fileTest = new File("data-ships&ports/testFile.csv");
        expFileTest = new File("data-ships&ports/expImpTestFile.csv");
        this.ctrl = new ImportShipsController(comp);
    }

    @Test
    public void testCorrectlyImportedFromExistingFile() {
        ShipsFileUtils shipsFileUtils = new ShipsFileUtils();
        shipsOfFile = shipsFileUtils.getShipsDataToDto(fileTest.toString());
        List<ShipsFileDTO> addedShips = new ArrayList<>();
        for (int i = 0; i < shipsOfFile.size(); i++) {
            if (ctrl.importShipFromFile(shipsOfFile.get(i)))
                addedShips.add(shipsOfFile.get(i));
        }
        shipsOfFileExp = shipsFileUtils.getShipsDataToDto(expFileTest.toString());
        List<ShipsFileDTO> expAddedShips = new ArrayList<>();
        for (int i = 0; i < shipsOfFileExp.size(); i++) {
            expAddedShips.add(shipsOfFileExp.get(i));
        }
        Assert.assertEquals(expAddedShips, addedShips);
    }
}