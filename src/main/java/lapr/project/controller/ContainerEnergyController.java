package lapr.project.controller;

import lapr.project.domain.model.Company;
import lapr.project.domain.model.Container;
import lapr.project.domain.store.ContainerStore;

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
        double neededPower = (externalTemp-contTemperature)/totalResistance; //result in WATTS unit of POWER
        double timeInSeconds = minutes*60;

        return (neededPower*timeInSeconds);// returns in Joules since we want ENERGY
    }
}
