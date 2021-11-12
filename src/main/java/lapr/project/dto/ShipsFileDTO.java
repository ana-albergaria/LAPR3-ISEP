package lapr.project.dto;

import java.util.Objects;

public class ShipsFileDTO {

    PositionDTO positionDTO;

    private int mmsi, vesselType, length, width;

    private double draft;

    private String vesselName, imo, callSign, cargo;

    public ShipsFileDTO(PositionDTO positionDTO, int mmsi, String vesselName, String imo, String callSign,
                        int vesselType, int length, int width, double draft, String cargo) {
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

    public String getCargo() {
        return cargo;
    }

    public double getDraft() {
        return draft;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) return true;
        if (otherObject == null || this.getClass() != otherObject.getClass()) return false;
        ShipsFileDTO otherShipsFileDTO = (ShipsFileDTO) otherObject;
        if (this.getMmsi()==otherShipsFileDTO.getMmsi() && this.getPositionDTO()==otherShipsFileDTO.getPositionDTO())
            return true;
        else
            return false;
    }

    @Override
    public String toString() {
        return "ShipsFileDTO{" +
                "positionDTO=" + positionDTO +
                ", mmsi=" + mmsi +
                ", vesselType=" + vesselType +
                ", length=" + length +
                ", width=" + width +
                ", cargo=" + cargo +
                ", draft=" + draft +
                ", vesselName='" + vesselName + '\'' +
                ", imo='" + imo + '\'' +
                ", callSign='" + callSign + '\'' +
                '}';
    }
    //showAddedShip (?)

    //toString (?)

}
