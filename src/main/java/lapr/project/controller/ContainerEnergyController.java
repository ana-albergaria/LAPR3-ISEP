package lapr.project.controller;

import lapr.project.domain.model.Company;
import lapr.project.domain.model.Container;
import lapr.project.domain.store.ContainerStore;

import lapr.project.utils.PhysicsUtils;

import java.util.List;

public class ContainerEnergyController {
    /**
     * Company instance of the session.
     */
    private Company company;

    /**
     * Constructor for the controller.
     */
    public ContainerEnergyController() {
        this(App.getInstance().getCompany());
    }

    /**
     * Constructor receiving the company as an argument.
     *
     * @param company instance of company to be used.
     */
    public ContainerEnergyController(Company company) {
        this.company=company;
    }

    public double getEnergyConsumptionOfContainer(double minutes, int id, double area, double externalTemp){
        ContainerStore containerStore = this.company.getContainerStore();
        Container container = containerStore.getContainerById(id);

        double totalResistance = container.getTotalThermalResistance(area);
        double contTemperature = container.getTemperature();

        double timeInSeconds =( minutes*60);

        return (PhysicsUtils.calculateEnergyPowerForContainer(externalTemp, contTemperature, totalResistance)*timeInSeconds);
    }

    public int getNumberOfAuxiliaryPower(double auxiliaryPower, double area, double externalTemp){
        ContainerStore containerStore = this.company.getContainerStore();
        List<Container> allContainers = containerStore.getContainersList();
        double consumptionTotal = 0.0;
        for (Container container : allContainers){
            double totalResistance = container.getTotalThermalResistance(area);
            double contTemperature = container.getTemperature();
            System.out.printf("%f\n", PhysicsUtils.calculateEnergyPowerForContainer(externalTemp, contTemperature, totalResistance));
            consumptionTotal += PhysicsUtils.calculateEnergyPowerForContainer(externalTemp, contTemperature, totalResistance); //give in power already
            System.out.printf("%f\n", consumptionTotal);

        }

        consumptionTotal = consumptionTotal / 1000; // watts to killowatts

        int neededGenerators =  (consumptionTotal % auxiliaryPower) != 0 ? (int) ((consumptionTotal / auxiliaryPower)+1) : (int) (consumptionTotal / auxiliaryPower);

        return neededGenerators;
    }
}
