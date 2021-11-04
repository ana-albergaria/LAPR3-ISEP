package lapr.project.domain.model;

import lapr.project.domain.BST.PositionsBST;

public class Ship implements Comparable<Ship> {

    private final VesselType vesselType;

    private final PositionsBST positionsBST;

    private final int MMSI;

    private final String vesselName;

    private final int IMO;

    private final String callSign;

    public Ship(VesselType vesselType, PositionsBST positionsBST, int MMSI,
                String vesselName, int IMO, String callSign){
        checkVesselType(vesselType);
        checkPositionsBST(positionsBST);
        checkMMSI(MMSI);
        checkVesselName(vesselName);
        checkIMO(IMO);
        checkCallSign(callSign);
        this.vesselType=vesselType;
        this.positionsBST=positionsBST;
        this.MMSI=MMSI;
        this.vesselName=vesselName;
        this.IMO=IMO;
        this.callSign=callSign;
    }

    public void checkVesselType(VesselType vesselType){
        //to develop
    }

    public void checkPositionsBST(PositionsBST positionsBST){
        //to develop
    }

    public void checkMMSI(int MMSI){
        //to develop
    }

    public void checkVesselName(String vesselName){
        //to develop
    }

    public void checkIMO(int IMO){
        //to develop
    }

    public void checkCallSign(String callSign){
        //to develop
    }

    public VesselType getVesselType() {
        return vesselType;
    }

    public PositionsBST getPositionsBST() {
        return positionsBST;
    }

    public int getMMSI() {
        return MMSI;
    }

    public String getVesselName() {
        return vesselName;
    }

    public int getIMO() {
        return IMO;
    }

    public String getCallSign() {
        return callSign;
    }

    //toString (?)

    @Override
    public boolean equals(Object o){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int compareTo(Ship o) {
        throw new UnsupportedOperationException("Not supported yet.");
        //return this.MMSI - o.MMSI;
    }
}
