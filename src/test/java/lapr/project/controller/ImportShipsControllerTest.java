package lapr.project.controller;

import lapr.project.domain.model.Company;
import lapr.project.dto.ShipsFileDTO;
import lapr.project.utils.ShipsFileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ImportShipsControllerTest {

    private Company comp;
    private List<ShipsFileDTO> shipsOfFile;
    private File file1, file2, file3;

    @BeforeEach
    public void SetUp(){
        this.shipsOfFile= Collections.emptyList();
        file1 = new File("/lapr3-2021-g054/data-ships&ports/bships.csv");
        file2 = new File("/lapr3-2021-g054/data-ships&ports/sships.csv");
        file3 = new File("/lapr3-2021-g054/data-ships&ports/doesntexist.csv");
    }

    /*@Test
    public void testImportExistingFile(){
        if (file1!=null){
            ShipsFileUtils shipsFileUtils = new ShipsFileUtils();
            shipsOfFile = shipsFileUtils.getShipsDataToDto(file1.toString());
            List<ShipsFileDTO> addedShips = new ArrayList<>();
        }
    }*/

    //ImportShipsController ctrl = new ImportShipsController(comp);

}