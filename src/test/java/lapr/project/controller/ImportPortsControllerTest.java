package lapr.project.controller;

import lapr.project.domain.model.Company;
import lapr.project.domain.model.Port;
import lapr.project.dto.PortFileDTO;
import lapr.project.utils.PortsFileUtils;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ImportPortsControllerTest {
    private Company comp;
    private List<PortFileDTO> portsOfFile, portsOfFileExp;
    private File fileTest, expFileTest;
    private ImportPortsController ctrl;

    @BeforeEach
    public void SetUp() {
        comp = new Company("Company");
        this.portsOfFile = Collections.emptyList();
        this.portsOfFileExp = Collections.emptyList();
        fileTest = new File("data-ships&ports/bportsTest.csv");
        expFileTest = new File("data-ships&ports/bportsTestExp.csv");
        this.ctrl = new ImportPortsController(comp);
    }

    @Test
    void importPortFromFile() {
        PortsFileUtils portsFileUtils = new PortsFileUtils();
        portsOfFile = portsFileUtils.getPortsDataToDto(fileTest.toString());
        List<Port> addedPorts = new ArrayList<>();

        for (int i = 0; i < portsOfFile.size(); i++) {
            if (ctrl.importPortFromFile(portsOfFile.get(i))) {
                addedPorts.add(this.comp.getPortStore().createPort(portsOfFile.get(i)));
            }
        }

        portsOfFileExp = portsFileUtils.getPortsDataToDto(expFileTest.toString());
        List<Port> expAddedPorts = new ArrayList<>();
        for (int i = 0; i < portsOfFileExp.size(); i++) {
            expAddedPorts.add(this.comp.getPortStore().createPort(portsOfFileExp.get(i)));
        }
        Assert.assertEquals(expAddedPorts, addedPorts);
    }




    @Test
    void ensureCorrectNodesAreAdded() {
        PortsFileUtils portsFileUtils = new PortsFileUtils();
        portsOfFile = portsFileUtils.getPortsDataToDto(fileTest.toString());
        List<Port> addedPorts = new ArrayList<>();

        for (int i = 0; i < portsOfFile.size(); i++) {
            if (ctrl.importPortFromFile(portsOfFile.get(i))) {
                addedPorts.add(this.comp.getPortStore().createPort(portsOfFile.get(i)));
            }
        }
        assertEquals(addedPorts.size(), this.comp.getPortStore().getPorts2DTree().getListOfPortNodes().size());
    }
}