package lapr.project.domain.store;

import lapr.project.domain.model.VesselType;
import lapr.project.dto.VesselTypeDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class VesselTypeStore {

    /**
     * List of vessel types.
     */
    private ArrayList<VesselType> vesselTypeList = new ArrayList<>();

    public List<VesselType> getVesselTypesList(){
        return vesselTypeList;
    }

    /**
     * Creates a vessel type instance with all the arguments.
     * @param vesselTypeID vessel type ID.
     * @param length vessel type length.
     * @param width vessel type width.
     * @param draft vessel type draft.
     * @param cargo vessel type cargo.
     * @return the created vessel type.
     */
    public VesselType registerVesselType(int vesselTypeID, int length, int width, double draft, int cargo){
        return new VesselType(vesselTypeID, length, width, draft, cargo);
    }

    public VesselType registerVesselType(VesselTypeDTO vesselTypeDTO){
        return new VesselType(vesselTypeDTO.getVesselTypeID(), vesselTypeDTO.getLength(), vesselTypeDTO.getWidth(), vesselTypeDTO.getDraft(), vesselTypeDTO.getCargo());
    }

    public boolean validateVesselType(VesselType vesselType){
        if (vesselType==null)
            return false;
        return !this.vesselTypeList.contains(vesselType);
    }

    public boolean saveVesselType(VesselType vesselType){
        if (!validateVesselType(vesselType))
            return false;
        return this.vesselTypeList.add(vesselType);
    }

    /*public VesselType getVesselTypeByCode(int vesselTypeID){
        for (VesselType vesselType : vesselTypeList){
            if (vesselType.getVesselTypeID()==vesselTypeID){
                return vesselType;
            }
        }
        throw new UnsupportedOperationException("Vessel Type not found!");
    }*/

}
