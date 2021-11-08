package lapr.project.controller;

import lapr.project.domain.BST.PositionsBST;
import lapr.project.domain.BST.ShipsBST;
import lapr.project.domain.model.Company;
import lapr.project.domain.model.Ship;
import lapr.project.dto.MovementsSummaryDto;

import java.util.Date;

public class MovementSummaryController {
    /**
     * The company associated to the Controller.
     */
    private final Company company;

    /**
     * empty constructor for the TopNController Class
     */
    public MovementSummaryController(){
        this.company= App.getInstance().getCompany();
    }

    /**
     * Constructor for TopNController Class, receiving "company" as attribute
     * @param company instance of company
     */
    public MovementSummaryController(Company company) {
        this.company = company;
    }

    public MovementsSummaryDto getShipMovementsSummary(int MMSI){
        ShipsBST shipBst = this.company.getBstShip();
        Ship currentShip = shipBst.getShipByMmsiCode(MMSI);
        PositionsBST shipMovements = currentShip.getPositionsBST();
        String name = currentShip.getVesselName();
        Date startDate = shipMovements.getStartDate();
        Date endDate = shipMovements.getEndDate();
        double maxSog = shipMovements.getMaxSog();
        double meanSog = shipMovements.getMeanSog();
        double meanCog = shipMovements.getMeanCog();
        double departLat = shipMovements.getDepartLatitude();
        double departLon = shipMovements.getDepartLongitude();
        double arrivalLat = shipMovements.getArrivalLatitude();
        double arrivalLon = shipMovements.getArrivalLongitude();
        double travDist = shipMovements.getTotalDistance();
        double deltaDist = shipMovements.getDeltaDistance();

        return new MovementsSummaryDto(name, startDate, endDate, maxSog, meanSog, meanCog, departLat, departLon, arrivalLat, arrivalLon, travDist, deltaDist);
    }
}
