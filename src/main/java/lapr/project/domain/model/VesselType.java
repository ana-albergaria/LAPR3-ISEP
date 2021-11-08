package lapr.project.domain.model;

import java.util.Objects;

public class VesselType {

    /**
     * The vessel type ID.
     */
    private int vesselTypeID;

    /**
     * The vessel type length.
     */
    private int length;

    /**
     * The vessel type width.
     */
    private int width;

    /**
     * The vessel type draft.
     */
    private double draft;

    /**
     * The vessel type cargo.
     */
    private int cargo;

    public
    VesselType(int vesselTypeID, int length, int width, double draft, int cargo){
        //checkVesselTypeID(vesselTypeID);
        checkLength(length);
        checkWidth(width);
        checkDraft(draft);
        checkCargo(cargo);
        this.vesselTypeID=vesselTypeID;
        this.length=length;
        this.width=width;
        this.draft=draft;
        this.cargo=cargo;
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

    public int getCargo() {
        return cargo;
    }

    /*private void checkVesselTypeID(int vesselTypeID){
    }*/

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

    private void checkCargo(int cargo){
        if (cargo<0){
            throw new IllegalArgumentException("Cargo cannot be negative.");
        }
    }

    //+ toString ?


    @Override
    public String toString() {
        return "VesselType{" +
                "vesselTypeID=" + vesselTypeID +
                ", length=" + length +
                ", width=" + width +
                ", draft=" + draft +
                ", cargo=" + cargo +
                '}';
    }

    @Override
    public boolean equals(Object o){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    //toString (?)

}
