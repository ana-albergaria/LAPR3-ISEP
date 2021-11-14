package lapr.project.utils;

import lapr.project.domain.model.Ship;

import java.util.Comparator;

public class ShipTotalMovementsComparator implements Comparator<Ship> {

    @Override
    public int compare(Ship e1, Ship e2) {
        int d1 = Math.round(e1.getPositionsBST().size());
        int d2 = Math.round(e2.getPositionsBST().size());
        return Integer.compare(d1, d2);
    }



}
