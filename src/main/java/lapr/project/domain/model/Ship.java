package lapr.project.domain.model;

import lapr.project.domain.BST.PositionsBST;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Objects;

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
        //checkCallSign(callSign);
        this.vesselType=vesselType;
        this.positionsBST=positionsBST;
        this.MMSI=MMSI;
        this.vesselName=vesselName;
        this.IMO=IMO;
        this.callSign=callSign;
        this.bstShipPosition = new PositionsBST();
    }

    public void checkVesselType(VesselType vesselType){
        if(Objects.isNull(vesselType)){
            throw new IllegalArgumentException("Vessel Type cannot be null.");
        }
    }

    public void checkPositionsBST(PositionsBST positionsBST){
        if (positionsBST.isEmpty()){
            throw new IllegalArgumentException("Positions BST cannot be empty.");
        }
    }

    public void checkMMSI(int MMSI){
        if (Integer.toString(MMSI).length()!=9){
            throw new IllegalArgumentException("MMSI must hold 9 digits.");
        }
    }

    public void checkVesselName(String vesselName){
        if(Objects.isNull(vesselName)){
            throw new IllegalArgumentException("Vessel type cannot be null.");
        }
    }

    public void checkIMO(String IMO){
        if (StringUtils.isBlank(IMO))
            throw new IllegalArgumentException("IMO cannot be blank.");
        if (IMO.length()!=10)
            throw new IllegalArgumentException("IMO must hold 10 characters.");
        if (!IMO.substring(0,2).equals("IMO")) //????
            throw new IllegalArgumentException("IMO code must begin with the letters IMO.");
        if (!NumberUtils.isParsable(IMO.substring(3)))
            throw new IllegalArgumentException("IMO must hold numeric digits starting from character 4.");
    }

    /*public void checkCallSign(String callSign){
        //to develop
    }*/

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
