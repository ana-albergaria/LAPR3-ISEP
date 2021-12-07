package lapr.project.data.dataControllers;

import lapr.project.controller.App;
import lapr.project.data.CargoManifestStoreDB;
import lapr.project.domain.model.Company;

import java.sql.Date;
import java.sql.SQLException;

public class CheckIfContainerExceedsShipCapacityController {

    /**
     * Company instance of the session.
     */
    private Company company;

    /**
     * Constructor for the controller.
     */
    public CheckIfContainerExceedsShipCapacityController(){
        this(App.getInstance().getCompany());
    }

    /**
     * Constructor receiving the company as an argument.
     *
     * @param companyy instance of company to be used.
     */
    public CheckIfContainerExceedsShipCapacityController(Company companyy){
        this.company=companyy;
    }

    public int checkIfCargoManifestExceedsShipCapacity(int cargoManifestID, int mmsi){
        CargoManifestStoreDB cargoManifestStoreDB = this.company.getCargoManifestStoreDB();
        return cargoManifestStoreDB.checkIfCargoManifestExceedsShipCapacity(cargoManifestID,mmsi);
    }

}
