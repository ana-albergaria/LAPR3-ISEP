package lapr.project.utils;

import lapr.project.domain.model.Ship;

import java.util.Comparator;

public class ShipTravelledDistanceComparator implements Comparator<Ship> {

    @Override
    public int compare(Ship e1, Ship e2) {
        int d1 = (int) Math.round(e1.getPositionsBST().getTotalDistance());
        int d2 = (int) Math.round(e2.getPositionsBST().getTotalDistance());
        return Integer.compare(d2, d1);
    }




}
