package lapr.project.domain.model;

import auth.AuthFacade;
import lapr.project.domain.BST.PositionsBST;
import lapr.project.domain.BST.ShipsBST;
import lapr.project.domain.store.VesselTypeStore;
import org.apache.commons.lang3.StringUtils;


public class Company {

    /**
     * The company designation.
     */
    private final String designation;

    /**
     * The company's authfacade.
     */
    private final AuthFacade authFacade;

    /**
     * The vessel type store.
     */
    private final VesselTypeStore vesselTypeStore;

    /**
     * The Ships' binary search tree.
     */
    private final ShipsBST shipsBST;

    public Company(String designation){
        if (StringUtils.isBlank(designation))
            throw new IllegalArgumentException("Designation cannot be blank.");
        this.authFacade=new AuthFacade();
        this.designation=designation;
        this.vesselTypeStore = new VesselTypeStore();
        this.shipsBST = new ShipsBST();
    }

    /**
     * Calling the Vessel Type Store available in the system.
     * @return the vessel type store.
     */
    public VesselTypeStore getVesselTypeStore(){
        return vesselTypeStore;
    }

    /**
     * Returns the Ships' binary search tree.
     *
     * @return the Ships' binary search tree.
     */
    public ShipsBST getBstShip() {
        return shipsBST;
    }

    /**
     * Returns the Company's Authfacade.
     * @return the Company's Authfacade.
     */
    public AuthFacade getAuthFacade() {
        return authFacade;
    }

}