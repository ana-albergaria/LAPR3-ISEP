package lapr.project.controller;

import lapr.project.domain.model.Company;
import lapr.project.domain.model.VesselType;
import lapr.project.domain.store.VesselTypeStore;
//import lapr.project.dto.VesselTypeDTO;

public class RegisterVesselTypeController {

    /**
     * Company instance of the session
     */
    private final Company company;

    private VesselType vesselType;

    public RegisterVesselTypeController(){
        this(App.getInstance().getCompany());
    }

    public RegisterVesselTypeController(Company application){
        this.company=application;
        this.vesselType=null;
    }
    /*
    public boolean registerVesselType(int vesselTypeID, int length, int width, double draft, int cargo){
        VesselTypeStore store = this.company.getVesselTypeStore();
        this.vesselType=store.registerVesselType(vesselTypeID, length, width, draft, cargo);
        return store.validateVesselType(vesselType);
    }

    public boolean registerVesselType(VesselTypeDTO vesselTypeDTO){
        VesselTypeStore store = this.company.getVesselTypeStore();
        this.vesselType = store.registerVesselType(vesselTypeDTO);
        return store.validateVesselType(vesselType);
    }

    public boolean saveVesselType(){
        VesselTypeStore store = this.company.getVesselTypeStore();
        return store.saveVesselType(vesselType);
    }
     */

}
