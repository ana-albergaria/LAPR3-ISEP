package lapr.project.dto;

import java.util.Date;
import java.util.List;

public class ShipsFileDTO {

    VesselTypeDTO vesselTypeDTO;

    PositionDTO positionDTO;

    private int mmsi;

    private String vesselName;

    private int imo;

    private String callSign;

    private int vesselTypeID;

    public ShipsFileDTO(VesselTypeDTO vesselTypeDTO, PositionDTO positionDTO, int mmsi, String vesselName, int imo, String callSign, int vesselTypeID) {
        this.vesselTypeDTO = vesselTypeDTO;
        this.positionDTO = positionDTO;
        this.mmsi=mmsi;
        this.vesselName=vesselName;
        this.imo=imo;
        this.callSign=callSign;
        this.vesselTypeID=vesselTypeID;
    }

    public VesselTypeDTO getVesselTypeDTO() {
        return vesselTypeDTO;
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

    public int getImo() {
        return imo;
    }

    public String getCallSign() {
        return callSign;
    }

    public int getVesselTypeID() {
        return vesselTypeID;
    }

    //faço um showAddedShip ?

    @Override
    public String toString() {
        return String.format("(...)%n"); //colocar a info que o TM quer ver
    }

}
