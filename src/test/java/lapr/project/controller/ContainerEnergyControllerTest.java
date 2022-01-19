package lapr.project.controller;

import lapr.project.domain.model.Company;
import lapr.project.domain.model.Container;
import lapr.project.domain.model.ContainerLayer;
import lapr.project.domain.store.ContainerStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ContainerEnergyControllerTest {
    Company cmp1;
    ContainerStore containerStore;
    Container c1;
    Container c2;
    Container c3;
    Container c4;
    int[] ids = {12345,1234,123456,123457};
    List<ContainerLayer> layersMinus5 = new ArrayList<>();
    List<ContainerLayer> layerSeven = new ArrayList<>();

    @BeforeEach
    public void setUp() throws ParseException {
        cmp1 = new Company("That company");
        containerStore = cmp1.getContainerStore();
        double[] payloads = {12.3, 14.5, 12.1, 10.0};
        double[] tare = {10.3, 11.5, 2.1, 1.0};
        double[] gross = {22.6, 26.0, 14.2, 11.0};
        String[] iso = {"ISO1", "ISO2", "ISO3", "ISO4"};

        ContainerLayer iron = new ContainerLayer(0.003, 55);
        ContainerLayer rockWool = new ContainerLayer(0.09, 0.045);
        ContainerLayer wood = new ContainerLayer(0.007, 0.13);
        ContainerLayer phenolicFoam = new ContainerLayer(0.09, 0.023);
        ContainerLayer cork = new ContainerLayer(0.007, 0.038);

        layersMinus5.add(iron);
        layersMinus5.add(phenolicFoam);
        layersMinus5.add(cork);

        layerSeven.add(iron);
        layerSeven.add(rockWool);
        layerSeven.add(wood);

        c1 = new Container(ids[0], payloads[0], tare[0], gross[0], iso[0], layersMinus5, -5);
        c2 = new Container(ids[1], payloads[1], tare[1], gross[1], iso[1], layerSeven, 7);
        c3 = new Container(ids[2], payloads[2], tare[2], gross[2], iso[2], layersMinus5, -5);
        c4 = new Container(ids[3], payloads[3], tare[3], gross[3], iso[3], layerSeven, 7);

        containerStore.saveContainer(c1);
        containerStore.saveContainer(c2);
        containerStore.saveContainer(c3);
        containerStore.saveContainer(c4);
    }


    @Test
    void caculateEnergyFor2Point5hours(){
        ContainerEnergyController controller = new ContainerEnergyController(cmp1);
        double containerArea = ((6*2.5)*4)+(2.5*2.5)*2; // considering all sides exposed and a 6x2.5 container.
        System.out.println(containerArea);
        //energy for 7 degrees container with 20 external
        double exp = 4129946;
        System.out.printf("Energy for 7 degrees in 2h30m and 20 external degrees(J): %.4f\n",controller.getEnergyConsumptionOfContainer(150, ids[1], containerArea, 20));
        assertEquals(Math.round(exp), Math.round(controller.getEnergyConsumptionOfContainer(150, ids[1], containerArea, 20)));

        //energy for -5 degrees container with 20 external
        double exp1 = 3981272;
        System.out.printf("Energy for -5 degrees in 2h30m and 20 external degrees(J): %.4f\n",controller.getEnergyConsumptionOfContainer(150, ids[0], containerArea, 20));
        assertEquals(Math.round(exp1), Math.round(controller.getEnergyConsumptionOfContainer(150, ids[0], containerArea, 20)));
    }

}