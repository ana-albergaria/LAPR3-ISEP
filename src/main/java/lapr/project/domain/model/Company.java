package lapr.project.domain.model;

import auth.AuthFacade;
import lapr.project.domain.store.PortStore;
import lapr.project.domain.store.ShipStore;
import org.apache.commons.lang3.StringUtils;

/**
 * Class to instantiate a new ShipPosition
 *
 * @author Group 54
 */
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
     * The Ship Store.
     */
    private final ShipStore shipsStore;

    /**
     * The Port Store.
     */
    private final PortStore portStore;

    /**
     * Constructs an instance of Company receiving as a parameter the Company's designation
     * @param designation the Company's designation
     */
    public Company(String designation){
        if (StringUtils.isBlank(designation))
            throw new IllegalArgumentException("Designation cannot be blank.");
        this.authFacade=new AuthFacade();
        this.designation=designation;
        this.shipsStore = new ShipStore();
        this.portStore = new PortStore();
    }

    /**
     * Returns the Ships' binary search tree.
     *
     * @return the Ships' binary search tree.
     */
    public ShipStore getShipStore() {
        return shipsStore;
    }

    /**
     * Returns the Port Store.
     *
     * @return the Port Store.
     */
    public PortStore getPortStore() {
        return portStore;
    }

    /**
     * Returns the Company's Authfacade.
     * @return the Company's Authfacade.
     */
    public AuthFacade getAuthFacade() {
        return authFacade;
    }



}