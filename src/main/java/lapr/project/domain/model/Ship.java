package lapr.project.domain.model;

import lapr.project.domain.BST.PositionsBST;

public class Ship implements Comparable<Ship> {

    private VesselType vesselType;

    private PositionsBST positionsBST;

    private int MMSI;

    private String vesselName;

    private String IMO;

    private String callSign;

    /**
     * The Ship's Positions' tree.
     */
    private final PositionsBST bstShipPosition;

    public Ship(VesselType vesselType, PositionsBST positionsBST, int MMSI,
                String vesselName, String IMO, String callSign){
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
        this.bstShipPosition = new PositionsBST();
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

    public void checkIMO(String IMO){
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

    public String getIMO() {
        return IMO;
    }

    public String getCallSign() {
        return callSign;
    }

    /**
     * Returns the Ship's Positions' tree.
     *
     * @return the Ship's Positions' tree.
     */
    public PositionsBST getBstShipPosition() {
        return bstShipPosition;
    }

    //toString (?)


    @Override
    public String toString() {
        return "Ship{" +
                "vesselType=" + vesselType +
                ", positionsBST=" + positionsBST +
                ", MMSI=" + MMSI +
                ", vesselName='" + vesselName + '\'' +
                ", IMO='" + IMO + '\'' +
                ", callSign='" + callSign + '\'' +
                ", bstShipPosition=" + bstShipPosition +
                '}';
    }

    //FALTA TESTES PARA O EQUALS!
    @Override
    public boolean equals(Object otherObject){
        if(this == otherObject)
            return true;

        if(otherObject == null || this.getClass() != otherObject.getClass())
            return false;

        Ship otherShip = (Ship) otherObject;

        if(this.MMSI == otherShip.MMSI)
            return true;
        else
            return false;
    }

    @Override
    public int compareTo(Ship o) {
        return this.MMSI - o.MMSI;
    }
}
