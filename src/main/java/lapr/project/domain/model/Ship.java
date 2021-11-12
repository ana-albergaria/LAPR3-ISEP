package lapr.project.domain.model;

import lapr.project.domain.BST.PositionsBST;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Objects;

public class Ship implements Comparable<Ship> {

    /**
     * The Ship's Positions' tree.
     */
    private PositionsBST positionsBST;

    private final int MMSI;

    private final String vesselName;

    private final String IMO;

    private final String callSign;

    /**
     * The vessel type ID.
     */
    private final int vesselTypeID;

    /**
     * The ship length.
     */
    private final int length;

    /**
     * The ship width.
     */
    private final int width;

    /**
     * The ship draft.
     */
    private final double draft;

    /**
     * The ship cargo.
     */
    private final String cargo;



    public Ship(PositionsBST positionsBST, int MMSI,
                String vesselName, String IMO, String callSign, int vesselTypeID, int length, int width, double draft, String cargo) {
        checkPositionsBST(positionsBST);
        checkMMSI(MMSI);
        checkVesselName(vesselName);
        checkIMO(IMO);
        checkLength(length);
        checkWidth(width);
        checkDraft(draft);
        checkCargo(cargo);
        //checkCallSign(callSign);
        this.MMSI = MMSI;
        this.vesselName = vesselName;
        this.IMO = IMO;
        this.callSign = callSign;
        this.positionsBST = positionsBST;
        this.vesselTypeID=vesselTypeID;
        this.length=length;
        this.width=width;
        this.draft=draft;
        this.cargo=cargo;
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
        if (!IMO.substring(0,3).equals("IMO")) //????
            throw new IllegalArgumentException("IMO code must begin with the letters IMO.");
        if (!NumberUtils.isParsable(IMO.substring(3)))
            throw new IllegalArgumentException("IMO must hold numeric digits starting from character 4.");
    }

    private void checkLength(int length){
        if (length<=0){
            throw new IllegalArgumentException("Length needs to be over 0.");
        }
    }

    private void checkWidth(int width){
        if (width<=0){
            throw new IllegalArgumentException("Width needs to be over 0.");
        }
    }

    private void checkDraft(double draft){
        if (draft<=0){
            throw new IllegalArgumentException("Draft needs to be over 0.");
        }
    }

    private void checkCargo(String cargo){
        if (Integer.parseInt(cargo)<0 && !cargo.equals("NA")){
            throw new IllegalArgumentException("Cargo cannot be negative.");
        }
    }

    /*public void checkCallSign(String callSign){
        //to develop
    }*/


    /**
     * Returns the Ship's Positions' tree.
     *
     * @return the Ship's Positions' tree.
     */
    public PositionsBST getPositionsBST() {
        return this.positionsBST;
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

    public int getVesselTypeID() {
        return vesselTypeID;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public double getDraft(){
        return draft;
    }

    public String getCargo() {
        return cargo;
    }

    public void setPositionsBST(PositionsBST positionsBST) {
        this.positionsBST = positionsBST;
    }

    /**
     * Returns the total movements of the ship
     *
     * @return total movements of the ship
     */
    public int getTotalMovs() {
        return this.positionsBST.size();
    }


    /**
     * Returns the Travelled Distance of the ship
     *
     * @return the Travelled Distance of the Ship
     */
    public Double getTravelledDistance() {
        return this.positionsBST.getTotalDistance();
    }

    @Override
    public String toString() {
        return "Ship{" +
                "MMSI=" + MMSI +
                ", vesselType=" + vesselTypeID +
                ", positionsBST=" + positionsBST +
                ", vesselName='" + vesselName + '\'' +
                ", IMO='" + IMO + '\'' +
                ", callSign='" + callSign +
                "vesselTypeID=" + vesselTypeID +
                ", length=" + length +
                ", width=" + width +
                ", draft=" + draft +
                ", cargo=" + cargo + //'\'' +
                //", bstShipPosition=" + bstShipPosition +
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

