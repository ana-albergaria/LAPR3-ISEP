package lapr.project.dto;

import java.util.Date;
import java.util.List;

public class ShipsFileDTO {

    VesselTypeDTO vesselTypeDTO;

    PositionDTO positionDTO;

    private int mmsi;

    private String vesselName;

    private String imo;

    private String callSign;

    public ShipsFileDTO(VesselTypeDTO vesselTypeDTO, PositionDTO positionDTO, int mmsi, String vesselName, String imo, String callSign) {
        this.vesselTypeDTO = vesselTypeDTO;
        this.positionDTO = positionDTO;
        this.mmsi=mmsi;
        this.vesselName=vesselName;
        this.imo=imo;
        this.callSign=callSign;
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

    public String getImo() {
        return imo;
    }

    public String getCallSign() {
        return callSign;
    }

    //showAddedShip (?)

    //toString (?)

}
