package lapr.project.domain.model;

import auth.AuthFacade;
import lapr.project.domain.BST.ShipsBST;
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
     * The Ships' binary search tree.
     */
    private final ShipsBST shipsBST;

    public Company(String designation){
        if (StringUtils.isBlank(designation))
            throw new IllegalArgumentException("Designation cannot be blank.");
        this.authFacade=new AuthFacade();
        this.designation=designation;
        this.shipsBST = new ShipsBST();
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