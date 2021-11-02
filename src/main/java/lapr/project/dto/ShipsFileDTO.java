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

    public ShipsFileDTO(int mmsi, String vesselName, int imo, String callSign) {
        this.mmsi=mmsi;
        this.vesselName=vesselName;
        this.imo=imo;
        this.callSign=callSign;
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

    //faço um showAddedShip ?

    @Override
    public String toString() {
        return String.format("(...)%n"); //colocar a info que o TM quer ver
    }

}
