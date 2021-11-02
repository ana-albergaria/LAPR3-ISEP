package lapr.project.controller;

import lapr.project.dto.VesselTypeDTO;
import lapr.project.model.Company;
import lapr.project.model.VesselType;
import lapr.project.store.VesselTypeStore;

public class RegisterVesselTypeController {

    /**
     * Company instance of the session
     */
    private final Company company;

    private VesselType vesselType;

    public RegisterVesselTypeController(Company application){
        this.company=application;
        this.vesselType=null;
    }

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
        /*VesselTypeStore store = this.company.getVesselTypeStore();
        return store.saveVesselType(vesselType);*/
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
