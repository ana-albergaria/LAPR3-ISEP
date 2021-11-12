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

    /**
     * The Ship's MMSI.
     */
    private final int MMSI;

    /**
     * The Ship's vessel name.
     */
    private final String vesselName;

    /**
     * The Ship's IMO.
     */
    private final String IMO;

    /**
     * The Ship's call sign.
     */
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

    /**
     * Constructs an instance of Ship receiving as a parameter the Ship's positions BST, MMSI, vessel name, IMO, call sign, vessel type, length, width, draft and cargo.
     * @param positionsBST the Ship's positions BST.
     * @param MMSI the Ship's MMSI
     * @param vesselName the Ship's vessel name
     * @param IMO the Ship's IMO
     * @param callSign the Ship's call sign
     * @param vesselTypeID the Ship's vessel type
     * @param length the Ship's length
     * @param width the Ship's width
     * @param draft the Ship's draft
     * @param cargo the Ship's cargo
     */
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

    /**
     * Checks if the Ship's positions BST is correct, and if not throws an error message.
     * @param positionsBST the Ship's positions BST.
     */
    public void checkPositionsBST(PositionsBST positionsBST){
        if (positionsBST.isEmpty()){
            throw new IllegalArgumentException("Positions BST cannot be empty.");
        }
    }

    /**
     * Checks if the Ship's MMSI is correct, and if not throws an error message.
     * @param MMSI the Ship's MMSI.
     */
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
        if (!cargo.equals("NA") && Integer.parseInt(cargo)<0){
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

    /**
     * Returns the Ship's MMSI.
     *
     * @return the Ship's MMSI.
     */
    public int getMMSI() {
        return MMSI;
    }

    /**
     * Returns the Ship's vessel name.
     *
     * @return the Ship's vessel name.
     */
    public String getVesselName() {
        return vesselName;
    }

    /**
     * Returns the Ship's IMO.
     *
     * @return the Ship's IMO.
     */
    public String getIMO() {
        return IMO;
    }

    /**
     * Returns the Ship's call sign.
     *
     * @return the Ship's call sign.
     */
    public String getCallSign() {
        return callSign;
    }

    /**
     * Returns the Ship's vessel type.
     *
     * @return the Ship's vessel type.
     */
    public int getVesselTypeID() {
        return vesselTypeID;
    }

    /**
     * Returns the Ship's length.
     *
     * @return the Ship's length.
     */
    public int getLength() {
        return length;
    }

    /**
     * Returns the Ship's width.
     *
     * @return the Ship's width.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the Ship's draft.
     *
     * @return the Ship's draft.
     */
    public double getDraft(){
        return draft;
    }

    /**
     * Returns the Ship's cargo.
     *
     * @return the Ship's cargo.
     */
    public String getCargo() {
        return cargo;
    }

    /**
     * Method to update the Ship's positionsBST.
     * @param positionsBST the new positionsBST.
     */
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

    /**
     * Method toString.
     * @return a String with the Ship attributes and its values.
     */
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

    /**
     * Method equals.
     * @param otherObject the object to be compared with.
     * @return true if a Ship is equal to the object in "otherObject";
     * false if a Ship is equal to the object in "otherObject".
     */
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

    /**
     * Method compareTo.
     * @param o the Ship to be compared with.
     * @return the difference between the two Ships' MMSI.
     */
    @Override
    public int compareTo(Ship o) {
        return this.MMSI - o.MMSI;
    }
}

