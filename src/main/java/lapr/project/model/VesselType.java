package lapr.project.model;

public class VesselType {

    /**
     * The vessel type ID.
     */
    private final int vesselTypeID;

    /**
     * The vessel type length.
     */
    private final int length;

    /**
     * The vessel type width.
     */
    private final int width;

    /**
     * The vessel type draft.
     */
    private final double draft;

    /**
     * The vessel type cargo.
     */
    private final int cargo;

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

    }

    private void checkLength(int length){

    }

    private void checkWidth(int width){

    }

    private void checkDraft(double draft){

    }

    private void checkCargo(int cargo){

    }

    //+ toString ?

    @Override
    public boolean equals(Object o){
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
