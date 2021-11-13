package lapr.project.controller;

import lapr.project.domain.model.Company;
import lapr.project.domain.model.Ship;
import lapr.project.dto.ShipsFileDTO;
import lapr.project.utils.ShipsFileUtils;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ImportShipsControllerTest {

    private Company comp;
    private List<ShipsFileDTO> shipsOfFile, shipsOfFileExp;
    private File file1, /*file2,*/ fileTest, expFileTest/*, fileTestPairsOfShips, errorFile*/;
    private ImportShipsController ctrl;

    @BeforeEach
    public void SetUp() {
        comp = new Company("Company");
        this.shipsOfFile = Collections.emptyList();
        this.shipsOfFileExp = Collections.emptyList();
        file1 = new File("data-ships&ports/emptyFile.csv");
        //file2 = new File("data-ships&ports/sships.csv");
        fileTest = new File("data-ships&ports/testFile.csv");
        expFileTest = new File("data-ships&ports/expImpTestFile.csv");
        //errorFile = new File("data-ships&ports/error.csv");
        //fileTestPairsOfShips = new File("data-ships&ports/testImpShip366998510.csv");
        this.ctrl = new ImportShipsController(comp);
    }

    /**
     * Test to ensure that the ships are imported correctly.
     */
    @Test
    public void testCorrectlyImportedFromExistingFile() {
        ShipsFileUtils shipsFileUtils = new ShipsFileUtils();
        shipsOfFile = shipsFileUtils.getShipsDataToDto(fileTest.toString());
        List<Ship> addedShips = new ArrayList<>();
        for (int i = 0; i < shipsOfFile.size(); i++) {
            if (ctrl.importShipFromFile(shipsOfFile.get(i)))
                addedShips.add(this.comp.getShipStore().createShip(shipsOfFile.get(i)));
        }
        shipsOfFileExp = shipsFileUtils.getShipsDataToDto(expFileTest.toString());
        List<Ship> expAddedShips = new ArrayList<>();
        for (int i = 0; i < shipsOfFileExp.size(); i++) {
            expAddedShips.add(this.comp.getShipStore().createShip(shipsOfFileExp.get(i)));
        }
        Assert.assertEquals(expAddedShips, addedShips);
    }

    @Test
    public void testImpShip(){
        ShipsFileUtils shipsFileUtils = new ShipsFileUtils();
        int j=0;
        try {
            shipsOfFile = shipsFileUtils.getShipsDataToDto(file1.toString());
            for (int i = 0; i < shipsOfFile.size(); i++) {
                if (!ctrl.importShipFromFile(shipsOfFile.get(i))) {
                    System.out.println("DIDN'T IMPORT LINE " + i + "\n");
                } else {
                    j++;
                }
            }
        } catch (IllegalArgumentException e){
            System.out.println("NOT ADDED : " + e);
        }
        System.out.println("TOTAL IMPORTED: " + j + "\n");
    }

    /*
    @Test
    public void saveShipException() {


        ShipsFileUtils shipsFileUtils = new ShipsFileUtils();
        shipsOfFile = shipsFileUtils.getShipsDataToDto(errorFile.toString());
        List<Ship> addedShips = new ArrayList<>();


        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> ctrl.importShipFromFile(shipsOfFile.get(0)));
        assertEquals("SOG must be positive.", thrown.getMessage());


    }

     */
}