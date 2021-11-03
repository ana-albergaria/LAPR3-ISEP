package lapr.project.domain.model;

import auth.AuthFacade;
import lapr.project.domain.store.VesselTypeStore;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

public class Company {

    /**
     * The company designation.
     */
    private final String designation;
    private final AuthFacade authFacade;

    /**
     * The vessel type store.
     */
    private final VesselTypeStore vesselTypeStore;

    public Company(String designation){
        if (StringUtils.isBlank(designation))
            throw new IllegalArgumentException("Designation cannot be blank.");
        this.authFacade=new AuthFacade();
        this.designation=designation;
        this.vesselTypeStore = new VesselTypeStore();
    }

    public String getDesignation() {
        return designation;
    }

    /**
     * Calling the Vessel Type Store available in the system.
     * @return the vessel type store.
     */
    public VesselTypeStore getVesselTypeStore(){
        return vesselTypeStore;
    }

    public AuthFacade getAuthFacade() {
        return authFacade;
    }

}