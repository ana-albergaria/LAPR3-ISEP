package lapr.project.dto;

public class VesselTypeDTO {

    private final int vesselTypeID;

    private final int length;

    private final int width;

    private final double draft;

    private final int cargo;

    public VesselTypeDTO(int vesselTypeID, int length, int width, double draft, int cargo){
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

    //+ toString ?

}
