package lapr.project.domain.model;

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
        checkVesselTypeID(vesselTypeID);
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

    private void checkVesselTypeID(int vesselTypeID){
    //to develop
    }

    private void checkLength(int length){
    //to develop
    }

    private void checkWidth(int width){
    //to develop
    }

    private void checkDraft(double draft){
    //to develop
    }

    private void checkCargo(int cargo){
    //to develop
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
