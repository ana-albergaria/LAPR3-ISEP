package lapr.project.domain.model;

import lapr.project.domain.BST.PositionsBST;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Objects;

/**
 * Class to instantiate a new Ship
 *
 * @author Marta Ribeiro (1201592)
 */
public abstract class Ship implements Comparable<Ship> {

    /**
     * The Ship's Positions' tree.
     */
    private PositionsBST positionsBST;

    /**
     * The Ship's MMSI.
     */
    private int MMSI;

    /**
     * The Ship's vessel name.
     */
    private  String vesselName;

    /**
     * The Ship's IMO.
     */
    private  String IMO;

    /**
     * The Ship's call sign.
     */
    private  String callSign;

    /**
     * The vessel type ID.
     */
    private  int vesselTypeID;

    /**
     * The ship length.
     */
    private  int length;

    /**
     * The ship width.
     */
    private  int width;

    /**
     * The ship draft.
     */
    private  double draft;

    /**
     * The ship cargo.
     */
    private  String cargo;

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
        checkCallSign(callSign);
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

    public Ship() {}

    public void setIMO(String IMO) {
        this.IMO = IMO;
    }

    public void setMMSI(int MMSI) {
        this.MMSI = MMSI;
    }

    public void setCallSign(String callSign) {
        this.callSign = callSign;
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

    /**
     * Checks if the Ship's Vessel Name is correct, and if not throws an error message.
     * @param vesselName the Ship's Vessel Name.
     */
    public void checkVesselName(String vesselName){
        if(Objects.isNull(vesselName)){
            throw new IllegalArgumentException("Vessel type cannot be null.");
        }
    }

    /**
     * Checks if the Ship's IMO is correct, and if not throws an error message.
     * @param IMO the Ship's IMO.
     */
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

    /**
     * Checks if the Ship's Length is correct, and if not throws an error message.
     * @param length the Ship's Length.
     */
    private void checkLength(int length){
        if (length<=0){
            throw new IllegalArgumentException("Length needs to be over 0.");
        }
    }

    /**
     * Checks if the Ship's Width is correct, and if not throws an error message.
     * @param width the Ship's Width.
     */
    private void checkWidth(int width){
        if (width<=0){
            throw new IllegalArgumentException("Width needs to be over 0.");
        }
    }

    /**
     * Checks if the Ship's Draft is correct, and if not throws an error message.
     * @param draft the Ship's Draft.
     */
    private void checkDraft(double draft){
        if (draft<=0){
            throw new IllegalArgumentException("Draft needs to be over 0.");
        }
    }

    /**
     * Checks if the Ship's Cargo is correct, and if not throws an error message.
     * @param cargo the Ship's Cargo.
     */
    private void checkCargo(String cargo){
        if (!cargo.equals("NA") && Integer.parseInt(cargo)<0){
            throw new IllegalArgumentException("Cargo cannot be negative.");
        }
    }

    /**
     * Checks if the Ship's Call Sign is correct, and if not throws an error message.
     * @param callSign the Ship's Call Sign.
     */
    public void checkCallSign(String callSign){
        if(Objects.isNull(callSign)){
            throw new IllegalArgumentException("Call sign cannot be null.");
        }
    }

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

    public void addPosition(ShipPosition position) {
        this.positionsBST.insert(position);
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

    /**
     * Method equals.
     * @param otherObject the object to be compared with.
     * @return true if a Ship is equal to the object in "otherObject";
     * false if a Ship isn't equal to the object in "otherObject".
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
    public abstract int compareTo(Ship o);
}

