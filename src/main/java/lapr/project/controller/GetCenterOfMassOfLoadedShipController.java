package lapr.project.controller;

import lapr.project.domain.dataStructures.PositionsBST;
import lapr.project.domain.model.*;

import java.awt.geom.Point2D;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class GetCenterOfMassOfLoadedShipController {
    /**
     * Company instance of the session.
     */
    private Company company;
    /**
     * Constructor for the controller.
     */
    public GetCenterOfMassOfLoadedShipController() {
        this(App.getInstance().getCompany());
    }
    /**
     * Constructor receiving the company as an argument.
     *
     * @param company instance of company to be used.
     */
    public GetCenterOfMassOfLoadedShipController(Company company) {
        this.company=company;
    }

    public Point2D.Double getCenterOfMassLoadedShip(Ship ship, int nContainers,
                                                    Mass blockOne, Mass blockTwo) throws ParseException {

        ship.addNewBlockOfContainersToShip(blockOne, nContainers);
        ship.addNewBlockOfContainersToShip(blockTwo, nContainers);

        Point2D.Double loadCenterOfMass = ship.getCenterOfMass();
        return loadCenterOfMass;
    }
}
