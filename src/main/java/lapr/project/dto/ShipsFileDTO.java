package lapr.project.dto;

public class ShipsFileDTO {

    PositionDTO positionDTO;

    private int mmsi, vesselType, length, width, cargo;

    private double draft;

    private String vesselName, imo, callSign;

    public ShipsFileDTO(PositionDTO positionDTO, int mmsi, String vesselName, String imo, String callSign,
                        int vesselType, int length, int width, double draft, int cargo) {
        this.positionDTO = positionDTO;
        this.mmsi=mmsi;
        this.vesselName=vesselName;
        this.imo=imo;
        this.callSign=callSign;
        this.vesselType=vesselType;
        this.length=length;
        this.width=width;
        this.draft=draft;
        this.cargo=cargo;
    }

    public PositionDTO getPositionDTO() {
        return positionDTO;
    }

    public int getMmsi() {
        return mmsi;
    }

    public String getVesselName() {
        return vesselName;
    }

    public String getImo() {
        return imo;
    }

    public String getCallSign() {
        return callSign;
    }

    public int getVesselType() {
        return vesselType;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public int getCargo() {
        return cargo;
    }

    public double getDraft() {
        return draft;
    }

    //showAddedShip (?)

    //toString (?)

}
