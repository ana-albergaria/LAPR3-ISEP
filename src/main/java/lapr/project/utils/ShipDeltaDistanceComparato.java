package lapr.project.utils;

import lapr.project.domain.model.Ship;

import java.util.Comparator;

public class ShipDeltaDistanceComparato implements Comparator<Ship> {

    @Override
    public int compare(Ship e1, Ship e2) {
        int d1 = (int) Math.round(e1.getPositionsBST().getDeltaDistance());
        int d2 = (int) Math.round(e2.getPositionsBST().getDeltaDistance());
        return Integer.compare(d1, d2);
        //throw new UnsupportedOperationException("Not supported yet.");
    }



}
