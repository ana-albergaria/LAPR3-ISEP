package lapr.project.model;

import lapr.project.store.VesselTypeStore;

public class Company {

    /**
     * The company designation.
     */
    private final String designation;

    /**
     * The vessel type store.
     */
    private final VesselTypeStore vesselTypeStore;


    public Company(String designation){
        this.designation=designation;
        this.vesselTypeStore = new VesselTypeStore();
    }

    /**
     * Calling the Vessel Type Store available in the system.
     * @return the vessel type store.
     */
    public VesselTypeStore getVesselTypeStore(){
        return vesselTypeStore;
    }

}